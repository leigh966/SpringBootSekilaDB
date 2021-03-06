package org.tsi.leigh.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import java.util.ArrayList;

public class DbController
{

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private FilmRepository filmRepository;



    private Integer lastAddedFilmId;

    private final String saved = "saved";
    private final String deleted = "deleted";

    public Iterable<Language> getAllLanguagesById(int id)
    {
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(id);
        return languageRepository.findAllById(ids);
    }

    public DbController(LanguageRepository languageRepository, ActorRepository actorRepo, FilmRepository filmRepo)
    {
        this.languageRepository = languageRepository;
        this.actorRepository = actorRepo;
        this.filmRepository = filmRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


    public String deleteActor(int id)
    {
        actorRepository.deleteById(id);
        return deleted;
    }

    public String deleteFilm(int id)
    {
        filmRepository.deleteById(id);
        return deleted;
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


    public Iterable<Film> getAllFilms()
    {
        return filmRepository.findAll();
    }


    public String addActor(String first_name, String last_name)
    {
        Actor a;
        try {
            a = new Actor(first_name, last_name);
        }catch (NotSavedException nse)
        {
            return nse.getMessage();
        }
        return saveActor(a);
    }

    public String saveActor(Actor a)
    {
        actorRepository.save(a);
        return saved;
    }

    public String addLanguage(String name)
    {
        Language a = new Language(name);
        languageRepository.save(a);
        return saved;
    }

    public String addFilm(String title,
                   String description,
                   int language_id,
                   Integer original_language_id,
                   int rental_duration,
                   float rental_rate,
                   Integer length,
                   float replacement_cost,
                   String rating)
    {
        Film f;
        try {
            f = new Film(title, description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating);
        }catch(NotSavedException nse)
        {
            return nse.getMessage();
        }
        filmRepository.save(f);
        lastAddedFilmId = f.getFilm_id();
        return saved;
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
                          Integer release_year)
    {
        Film f;
        try {
            f = new Film(title, description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating);
        }catch(NotSavedException nse)
        {
            return nse.getMessage();
        }
        f.setRelease_year(release_year);
        filmRepository.save(f);
        lastAddedFilmId = f.getFilm_id();
        return saved;
    }

    public String saveFilm(Film f)
    {
        filmRepository.save(f);
        return saved;
    }


    public Integer getLastAddedFilmId()
    {
        return lastAddedFilmId;
    }
}
