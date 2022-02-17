package org.tsi.leigh.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

import java.util.Iterator;
import java.util.Set;


@SpringBootApplication
@RestController
@RequestMapping("/home")
public class DemoApplication {

    public DbController controller;


    public DemoApplication(LanguageRepository languageRepository, ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository catRepo)
    {

        controller = new DbController(languageRepository, actorRepo, filmRepo, catRepo);

    }

    public static void main(String[] args)
    {
        SpringApplication.run(DemoApplication.class, args);
    }

    @PostMapping("link_actor_film")
    public @ResponseBody
    String linkActorToFilm(@RequestParam int actorId, @RequestParam int filmId)
    {
        Iterator<Actor> actorIt = getActor(actorId, null).iterator();
        if(!actorIt.hasNext()) // No actor of this id found
        {
            return "Actor of id " + actorId + " does not exist";
        }
        Actor a = actorIt.next();
        Set<Film> actorFilms = a.getFilms();

        Iterator<Film> filmIt = getFilms(filmId, null).iterator();
        if(!filmIt.hasNext())
        {
            return "Film of id " + actorId + " does not exist";
        }
        Film f = filmIt.next();
        Set<Actor> filmActors = f.getActor();

        // Only existing films and actors should make it here
        actorFilms.add(f);
        filmActors.add(a);
        controller.saveActor(a);
        controller.saveFilm(f);
        return "linked";
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
        return controller.saveCategory(name);
    }

    @DeleteMapping("delete_film")
    public @ResponseBody
    String deleteFilm(@RequestParam int id)
    {
        return controller.deleteFilm(id);
    }

}
