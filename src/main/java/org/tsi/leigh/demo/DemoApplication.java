package org.tsi.leigh.demo;

import com.amazonaws.services.secretsmanager.*;
import com.amazonaws.services.secretsmanager.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;


@SpringBootApplication
@RestController
@RequestMapping("/home")
public class DemoApplication {

    public DbController controller;


    public DemoApplication(LanguageRepository languageRepository, ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository catRepo)
    {

        controller = new DbController(languageRepository, actorRepo, filmRepo, catRepo);



    }



    // Use this code snippet in your app.
// If you need more information about configurations or implementing the sample code, visit the AWS docs:
// https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/java-dg-samples.html#prerequisites
    @Bean
    @Primary
    public static DataSource getSecret() {

        String secretName = "leigh-db-secret";
        String region = "eu-west-2";

        // Create a Secrets Manager client
        AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
                .withRegion(region)
                .build();

        // In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
        // See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
        // We rethrow the exception by default.

        String secret, decodedBinarySecret;
        GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
                .withSecretId(secretName);
        GetSecretValueResult getSecretValueResult = null;

        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (DecryptionFailureException e) {
            // Secrets Manager can't decrypt the protected secret text using the provided KMS key.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InternalServiceErrorException e) {
            // An error occurred on the server side.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InvalidParameterException e) {
            // You provided an invalid value for a parameter.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InvalidRequestException e) {
            // You provided a parameter value that is not valid for the current state of the resource.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (ResourceNotFoundException e) {
            // We can't find the resource that you asked for.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        }

        // Decrypts secret using the associated KMS key.
        // Depending on whether the secret is a string or binary, one of these fields will be populated.
        secret = getSecretValueResult.getSecretString();
        System.out.println(secret);

        JSONObject jo = new JSONObject(secret);
        String username = jo.get("username").toString();
        String password = jo.get("password").toString();
        String url = jo.get("host").toString();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Url: " + url);

        return DataSourceBuilder
                .create()
                .username(username)
                .password(password)
                .url("jdbc:mysql://"+url)
                .driverClassName("com.mysql.jdbc.Driver")
                .build();




    }

    public static void main(String[] args)
    {
        System.out.println("Running Web Server...");
        getSecret();
        //SpringApplication.run(DemoApplication.class, args);
    }

    @GetMapping("get_last_film_id")
    public @ResponseBody
    Integer getLastFilmId()
    {
        return controller.getLastAddedFilmId();
    }

    @GetMapping("get_film")
    public @ResponseBody
    Iterable<Film> getFilms
            (
                @RequestParam(value = "id", required = false) Integer id,
                @RequestParam(value = "titleQuery", required = false) String titleQuery
            )
    {
        Iterable<Film> table;
        if(id != null)
        {
            table = controller.getAllFilmsById(id);
        }
        else
        {
            table = controller.getAllFilms();
        }

        if(titleQuery == null)
        {
            return table;
        }
        else
        {
            titleQuery = titleQuery.toUpperCase();
            Iterator<Film> filmIt = table.iterator();
            ArrayList<Film> returnFilms = new ArrayList<>();
            while(filmIt.hasNext())
            {
                Film f = filmIt.next();
                if(f.getTitle().contains(titleQuery))
                {
                    returnFilms.add(f);
                }
            }
            return returnFilms;
        }
    }


    @GetMapping("get_actor")
    public @ResponseBody
    Iterable<Actor> getActor(@RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "nameQuery", required = false) String actorQuery)
    {
        Iterable<Actor> table;
        if(id != null)
        {
            table = controller.getAllActorsById(id);
        }
        else
        {
            table = controller.getAllActors();
        }

        if(actorQuery == null)
        {
            return table;
        }
        else
        {
            actorQuery = actorQuery.toUpperCase();
            Iterator<Actor> actorIt = table.iterator();
            ArrayList<Actor> returnActors = new ArrayList<>();
            while(actorIt.hasNext())
            {
                Actor a = actorIt.next();
                if(a.getFirst_name().contains(actorQuery) || a.getLast_name().contains(actorQuery))
                {
                    returnActors.add(a);
                }
            }
            return returnActors;
        }
    }


    @PostMapping("add_film")
    public @ResponseBody
    String addFilm(@RequestParam String title,
                   @RequestParam String description,
                   @RequestParam int language_id,
                   @RequestParam(value = "original_language_id", required = false) Integer original_language_id,
                   @RequestParam int rental_duration,
                   @RequestParam float rental_rate,
                   @RequestParam(value = "length", required = false) Integer length,
                   @RequestParam float replacement_cost,
                   @RequestParam String rating,
                   @RequestParam(value = "special_features", required = false) String special_features)
    {
        return controller.addFilm(title.toUpperCase(), description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features);
    }

    @PostMapping("add_actor")
    public @ResponseBody
    String addActor(@RequestParam String first_name,
                    @RequestParam String last_name)
    {
        return controller.addActor(first_name, last_name);
    }

    @PostMapping
    public @ResponseBody
    String addCategory(@RequestParam String name)
    {
        return controller.addCategory(name);
    }

    @DeleteMapping("delete_film")
    public @ResponseBody
    String deleteFilm(@RequestParam int id)
    {
        return controller.deleteFilm(id);
    }

}
