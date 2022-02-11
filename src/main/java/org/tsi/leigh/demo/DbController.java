package org.tsi.leigh.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

public class DbController
{

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public DbController(LanguageRepository languageRepository, ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository catRepo)
    {
        this.languageRepository = languageRepository;
        this.actorRepository = actorRepo;
        this.filmRepository = filmRepo;
        this.categoryRepository = catRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    public @ResponseBody
    String deleteActor(int id)
    {
        actorRepository.deleteById(id);
        return "deleted";
    }

    public @ResponseBody
    String deleteFilm(int id)
    {
        filmRepository.deleteById(id);
        return "deleted";
    }

    public @ResponseBody
    Iterable<Language> getAllLanguages()
    {
        return languageRepository.findAll();
    }

    public @ResponseBody
    Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public @ResponseBody
    String getAllActorsText()
    {
        Iterable<Actor> actorIterable =  actorRepository.findAll();
        String output = "";
        for(Actor a : actorIterable)
        {
            String line = "Name: " + a.getFirst_name() + " " + a.getLast_name() + ", " + "Films: {";
            Iterable<Film> films = a.getFilms();
            for(Film f : films)
            {
                line += f.getTitle() + ",";
            }
            output += line + "}<br><br>";
        }
        return output;
    }

    public @ResponseBody
    Iterable<Film> getAllFilms()
    {
        return filmRepository.findAll();
    }


    public @ResponseBody
    Iterable<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    public @ResponseBody
    String addCategory(String name)
    {
        Category cat = new Category(name);
        categoryRepository.save(cat);
        return "saved";
    }


    public @ResponseBody
    String addActor(@RequestParam String first_name, @RequestParam String last_name)
    {
        Actor a = new Actor(first_name, last_name);
        actorRepository.save(a);
        return "saved";
    }

    @PostMapping("/add_language")
    public @ResponseBody
    String addLanguage(@RequestParam String name)
    {
        Language a = new Language(name);
        languageRepository.save(a);
        return "saved";
    }

    public @ResponseBody
    String addFilm(@RequestParam String title,
                   @RequestParam String description,
                   @RequestParam int language_id,
                   @RequestParam Integer original_language_id,
                   @RequestParam int rental_duration,
                   @RequestParam float rental_rate,
                   int length,
                   float replacement_cost,
                   String rating,
                   String special_features)
    {
        Film f = new Film(title, description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features);
        filmRepository.save(f);
        return "saved";
    }

    public @ResponseBody
    String addFilm(@RequestParam String title,
                   @RequestParam String description,
                   @RequestParam int language_id,
                   @RequestParam int rental_duration,
                   @RequestParam float rental_rate,
                   int length,
                   float replacement_cost,
                   String rating,
                   String special_features)
    {
        Film f = new Film(title, description, language_id, null, rental_duration, rental_rate, length, replacement_cost, rating, special_features);
        filmRepository.save(f);
        return "saved";
    }

    public @ResponseBody
    String addFilm(@RequestParam String title,
                   @RequestParam String description,
                   @RequestParam int language_id,
                   @RequestParam int rental_duration,
                   @RequestParam float rental_rate,
                   int length,
                   float replacement_cost,
                   String rating)
    {
        Film f = new Film(title, description, language_id, null, rental_duration, rental_rate, length, replacement_cost, rating, null);
        filmRepository.save(f);
        return "saved";
    }

    String addFilm(@RequestParam String title,
                   @RequestParam String description,
                   @RequestParam int language_id,
                   @RequestParam Integer original_language_id,
                   @RequestParam int rental_duration,
                   @RequestParam float rental_rate,
                   int length,
                   float replacement_cost,
                   String rating)
    {
        Film f = new Film(title, description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, null);
        filmRepository.save(f);
        return "saved";
    }
}
