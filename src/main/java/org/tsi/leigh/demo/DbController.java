package org.tsi.leigh.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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


    private Integer lastAddedFilmId;

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


    public String deleteActor(int id)
    {
        actorRepository.deleteById(id);
        return "deleted";
    }

    public String deleteFilm(int id)
    {
        filmRepository.deleteById(id);
        return "deleted";
    }

    public Iterable<Film> getAllFilmsById(Integer id)
    {
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(id);

        return filmRepository.findAllById(idList);
    }

    public Iterable<Actor> getAllActorsById(Integer id)
    {
        ArrayList<Integer> idList = new ArrayList<>();
        idList.add(id);

        return actorRepository.findAllById(idList);
    }

    public Iterable<Language> getAllLanguages()
    {
        return languageRepository.findAll();
    }

    public Iterable<Actor> getAllActors() {
        return actorRepository.findAll();
    }

    public String getAllActorsText()
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

    public Iterable<Film> getAllFilms()
    {
        return filmRepository.findAll();
    }


    public Iterable<Category> getAllCategories()
    {
        return categoryRepository.findAll();
    }

    public String addCategory(String name)
    {
        Category cat = new Category(name);
        categoryRepository.save(cat);
        return "saved";
    }


    public String addActor(String first_name, String last_name)
    {
        Actor a = new Actor(first_name, last_name);
        actorRepository.save(a);
        return "saved";
    }

    public String addLanguage(String name)
    {
        Language a = new Language(name);
        languageRepository.save(a);
        return "saved";
    }

    public String addFilm(String title,
                   String description,
                   int language_id,
                   Integer original_language_id,
                   int rental_duration,
                   float rental_rate,
                   Integer length,
                   float replacement_cost,
                   String rating,
                   String special_features)
    {
        Film f = new Film(title, description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features);
        filmRepository.save(f);
        lastAddedFilmId = f.getFilm_id();
        return "saved";
    }


    public Integer getLastAddedFilmId()
    {
        return lastAddedFilmId;
    }
}
