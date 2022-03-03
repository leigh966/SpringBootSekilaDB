package org.tsi.leigh.demoTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.jv.Lan;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.tsi.leigh.demo.*;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class putRequestCucumberTests
{

    private String title;
    private String description;
    private int release_year;
    private int language_id;

    private Integer original_language_id;

    private int rental_duration;
    private float rental_rate;
    private Integer length;
    private float replacement_cost;
    private String rating;
    @Mock
    private LanguageRepository languageRepo; // Fake language table

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private DbController mockController;
    private DemoApplication app;

    private String actual;

    void setup()
    {
        languageRepo = mock(LanguageRepository.class);
        actorRepository = mock(ActorRepository.class);
        filmRepository = mock(FilmRepository.class);
        mockController = mock(DbController.class);
        app = new DemoApplication(languageRepo, actorRepository, filmRepository);
        app.controller = mockController;
    }

    int id;
    @Given("We have all fields required for a film")
    public void we_have_all_the_fields_required_for_a_film() {
        setup();
        id = 1;
        title = "hhhh";
        description = "hhhhh";
        release_year = 2000;
        language_id = 1;
        original_language_id = null;
        rental_duration = 0;
        rental_rate = 0f;
        length = 0;
        replacement_cost = 0;
        rating = "R";
    }

    private Iterable<Film> getFilmList() {
        ArrayList<Film> fl = new ArrayList<Film>();
        fl.add(new Film());
        return fl;
    }

    private Iterable<Language> getLanguageList() {
        ArrayList<Language> languageList = new ArrayList<Language>();
        languageList.add(new Language());
        return languageList;
    }

    @Given("film with id exists")
    public void film_with_id_exists()
    {

        when(mockController.getAllFilmsById(anyInt())).thenReturn(getFilmList());
    }

    @Given("language with id exists")
    public void language_with_id_exists()
    {
        when(mockController.getAllLanguagesById(anyInt())).thenReturn(getLanguageList());
    }

    @Given("All other film fields okay")
        public void all_okay()
        {
            when(mockController.saveFilm(any(Film.class))).thenReturn("saved");
        }

    @When("A put film request is received")
    public void put_film()
    {

        actual = app.updateFilm(id, title, description,language_id,original_language_id,rental_duration,rental_rate,length,replacement_cost,rating,release_year);
    }

    @Then("The server returns {string}")
            public void server_returns(String expected)
    {
        Assertions.assertEquals(expected, actual, "Save should be successful");
    }
}
