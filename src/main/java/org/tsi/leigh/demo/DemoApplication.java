package org.tsi.leigh.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

@SpringBootApplication
@RestController
@RequestMapping("/home")
public class DemoApplication {

    public DbController controller;

    public DemoApplication(LanguageRepository languageRepository, ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository catRepo)
    {
        controller = new DbController(languageRepository, actorRepo, filmRepo, catRepo);
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
        return controller.addFilm(title, description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features);
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

}
