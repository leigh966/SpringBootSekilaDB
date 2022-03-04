package org.tsi.leigh.demoTest;

import io.cucumber.java.bs.A;
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
    private Integer release_year;
    private Integer language_id;

    private Integer original_language_id;

    private Integer rental_duration;
    private Float rental_rate;
    private Integer length;
    private Float replacement_cost;
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
        replacement_cost = 0f;
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

    @Given("all film fields are null")
    public void all_film_fields_are_null() {
        setup();
        id=1;
        title = null;
        description = null;
        release_year = null;
        language_id = null;
        original_language_id = null;
        rental_duration = null;
        rental_rate = null;
        length = null;
        replacement_cost = null;
        rating = null;
    }

    @Given("all actor fields are null")
    public void all_actor_fields_are_null() {
        setup();
        id=1;
        first_name = null;
        last_name = null;
        when(mockController.saveActor(any(Actor.class))).thenReturn("saved");
    }

    @Given("id is {int}")
    public void id_is(Integer int1) {
        id = int1;
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


    String first_name, last_name;
    @Given("We have all the fields required for an actor")
    public void we_have_all_the_fields_required_for_an_actor() {
        setup();
        id = 1;
        first_name = "hhhh";
        last_name = "hhhh";
        when(mockController.saveActor(any(Actor.class))).thenReturn("saved");

    }


    public Iterable<Actor> getActorList()
    {
        ArrayList<Actor> ActorList = new ArrayList<>();
        ActorList.add(new Actor());
        return ActorList;
    }
    @Given("actor with id exists")
    public void actor_with_id_exists() {
        when(mockController.getAllActorsById(anyInt())).thenReturn(getActorList());
    }
    @When("A put actor request is received")
    public void a_put_actor_request_is_received() {
        actual = app.updateActor(id, first_name, last_name);
    }


}
