package org.tsi.leigh.demo;


import com.amazonaws.services.secretsmanager.model.ResourceNotFoundException;
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


    void checkLanguageExist(Integer language_id)
    {
        Iterable<Language> langIt = controller.getAllLanguagesById(language_id);
        if(!langIt.iterator().hasNext())
        {
            throw new ResourceNotFoundException("Language with id " + language_id + " not found");
        }
    }


    @CrossOrigin(origins = "*")
    @PutMapping("update_film")
    public @ResponseBody
    String updateFilm(@RequestParam int id,
                      @RequestParam(value = "title", required = false) String title,
                      @RequestParam(value = "description", required = false) String description,
                      @RequestParam(value = "language_id", required = false) Integer language_id,
                      @RequestParam(value = "original_language_id", required = false) Integer original_language_id,
                      @RequestParam(value = "rental_duration", required = false) Integer rental_duration,
                      @RequestParam(value = "rental_rate", required = false) Float rental_rate,
                      @RequestParam(value = "length", required = false) Integer length,
                      @RequestParam(value = "replacement_cost", required = false) Float replacement_cost,
                      @RequestParam(value = "rating", required = false) String rating,
    @RequestParam(value = "release_year", required = false) Integer release_year)
    {
        Iterable<Film> filmIt = controller.getAllFilmsById(id);
        if(filmIt == null)
        {
            throw new ResourceNotFoundException("Film with id " + id + " not found");
        }
        Film f = filmIt.iterator().next();
        if(title!=null)
        {
            f.setTitle(title);
        }
        if(description!=null)
        {
            f.setDescription(description);
        }
        if(language_id!=null)
        {
            checkLanguageExist(language_id);
            f.setLanguage_id(language_id);
        }
        if(original_language_id!=null)
        {
            checkLanguageExist(original_language_id);
            f.setOriginal_language_id(original_language_id);
        }
        if(rental_duration!=null)
        {
            f.setRental_duration(rental_duration);
        }
        if(rental_rate!=null)
        {
            f.setRental_rate(rental_rate);
        }
        if(length!=null)
        {
            f.setLength(length);
        }
        if(replacement_cost!=null)
        {
            f.setReplacement_cost(replacement_cost);
        }
        if(rating != null)
        {
            f.setRating(rating);
        }
        if(release_year!=null)
        {
            f.setRelease_year(release_year);
        }
        return controller.saveFilm(f);
    }


    @CrossOrigin(origins = "*")
    @PutMapping("update_actor")
    public @ResponseBody
    String updateActor(@RequestParam int id, @RequestParam(value = "first_name", required = false) String first_name,  @RequestParam(value = "last_name", required = false) String last_name)
    {
        Iterable<Actor> actorIt = controller.getAllActorsById(id);
        if(actorIt == null)
        {
            throw new ResourceNotFoundException("Actor with id " + id + " not found");
        }
        Actor a = actorIt.iterator().next();
        if(first_name != null)
        {
            a.setFirst_name(first_name.toUpperCase());
        }
        if(last_name != null)
        {
            a.setLast_name(last_name.toUpperCase());
        }
        return controller.saveActor(a);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("link_actor_film")
    public @ResponseBody
    String linkActorToFilm(@RequestParam int actor_id, @RequestParam int film_id)
    {
        Iterator<Actor> actorIt = getActor(actor_id, null,null).iterator();
        if(!actorIt.hasNext()) // No actor of this id found
        {
            return "Actor of id " + actor_id + " does not exist";
        }
        Actor a = actorIt.next();
        Set<Film> actorFilms = a.getFilms();

        Iterator<Film> filmIt = getFilms(film_id, null, null).iterator();
        if(!filmIt.hasNext())
        {
            return "Film of id " + actor_id + " does not exist";
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


    @CrossOrigin(origins = "*")
    @DeleteMapping("link_actor_film")
    public @ResponseBody
    String unlinkActorFromFilm(@RequestParam int actor_id, @RequestParam int film_id)
    {
        Iterator<Actor> actorIt = getActor(actor_id, null,null).iterator();
        if(!actorIt.hasNext()) // No actor of this id found
        {
            return "Actor of id " + actor_id + " does not exist";
        }
        Actor a = actorIt.next();
        Set<Film> actorFilms = a.getFilms();

        Iterator<Film> filmIt = getFilms(film_id, null, null).iterator();
        if(!filmIt.hasNext())
        {
            return "Film of id " + actor_id + " does not exist";
        }
        Film f = filmIt.next();
        Set<Actor> filmActors = f.getActor();

        filmActors.remove(a);
        actorFilms.remove(f);
        controller.saveActor(a);
        controller.saveFilm(f);
        return "unlinked";
    }


    @CrossOrigin(origins = "*")
    @GetMapping("get_last_film_id")
    public @ResponseBody
    Integer getLastFilmId()
    {
        return controller.getLastAddedFilmId();
    }


    @CrossOrigin(origins = "*")
    @GetMapping("get_film")
    public @ResponseBody
    Iterable<Film> getFilms
            (
                @RequestParam(value = "id", required = false) Integer id,
                @RequestParam(value = "titleQuery", required = false) String titleQuery,
                @RequestParam(value = "actor_id", required = false) Integer actor_id
            )
    {
        Iterable<Film> table;
        // Get the film with the given id
        if(id != null)
        {
            table = controller.getAllFilmsById(id);
        }
        else
        {
            table = controller.getAllFilms();
        }

        // Make the table only contain films that fit the title query
        if(titleQuery != null)
        {
            titleQuery = titleQuery.toUpperCase();
            Iterator<Film> filmIt = table.iterator();
            ArrayList<Film> returnFilms = new ArrayList<>();
            while(filmIt.hasNext())
            {
                Film f = filmIt.next();
                if(f.getTitle().contains(titleQuery) || f.getTitle() == titleQuery)
                {
                    returnFilms.add(f);
                }
            }
            table = returnFilms;
        }

        if(actor_id != null)
        {
            Iterator<Actor> actorIt = getActor(actor_id, null, null).iterator();
            if(!actorIt.hasNext()) // No actor of this id found
            {
                throw new ResourceNotFoundException("Actor of id " + actor_id + " does not exist");
            }
            Actor a = actorIt.next();
            Set<Film> actorFilms = a.getFilms();
            ArrayList<Film> returnFilms = new ArrayList<>();
            for(Film f : table)
            {
                if(actorFilms.contains(f)) {
                    returnFilms.add(f);
                }
            }
            table = returnFilms;
        }
        return table;
    }


    @CrossOrigin(origins = "*")
    @GetMapping("get_actor")
    public @ResponseBody
    Iterable<Actor> getActor(@RequestParam(value = "id", required = false) Integer id,
                            @RequestParam(value = "nameQuery", required = false) String actorQuery,
                             @RequestParam(value = "film_id", required = false) Integer film_id)
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

        if(actorQuery != null)
        {
            actorQuery = actorQuery.toUpperCase();
            Iterator<Actor> actorIt = table.iterator();
            ArrayList<Actor> returnActors = new ArrayList<>();
            while(actorIt.hasNext())
            {
                Actor a = actorIt.next();
                String name = a.getFirst_name()+" "+a.getLast_name();
                if(name.contains(actorQuery) || name == actorQuery)
                {
                    returnActors.add(a);
                }
            }
            table = returnActors;
        }
        if(film_id != null)
        {
            Iterator<Film> filmIt = getFilms(film_id, null, null).iterator();
            if(!filmIt.hasNext()) // No film of this id found
            {
                throw new ResourceNotFoundException("Film of id " + film_id + " does not exist");
            }
            Film f = filmIt.next();
            Set<Actor> actorFilms = f.getActor();
            ArrayList<Actor> returnActors = new ArrayList<>();
            for(Actor a : table)
            {
                if(actorFilms.contains(a))
                {
                    returnActors.add(a);
                }
            }
            table = returnActors;
        }
        return table;
    }


    @CrossOrigin(origins = "*")
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
    @RequestParam(value="release_year", required=false)Integer release_year)
    {
        if(release_year != null)
        {
            return controller.addFilm(title.toUpperCase(), description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, release_year);
        }
        return controller.addFilm(title.toUpperCase(), description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating);
    }


    @CrossOrigin(origins = "*")
    @PostMapping("add_actor")
    public @ResponseBody
    String addActor(@RequestParam String first_name,
                    @RequestParam String last_name)
    {
        return controller.addActor(first_name, last_name);
    }


    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody
    String addCategory(@RequestParam String name)
    {
        return controller.saveCategory(name);
    }


    @CrossOrigin(origins = "*")
    @DeleteMapping("delete_film")
    public @ResponseBody
    String deleteFilm(@RequestParam int id)
    {
        return controller.deleteFilm(id);
    }

}
