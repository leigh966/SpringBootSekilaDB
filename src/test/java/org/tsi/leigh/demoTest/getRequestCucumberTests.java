package org.tsi.leigh.demoTest;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.tsi.leigh.demo.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class getRequestCucumberTests
{
    Integer id, linkedId;
    String query;

    @Mock
    private LanguageRepository languageRepo; // Fake language table

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private DbController mockController;

    private DemoApplication app;

    void setup()
    {
        languageRepo = mock(LanguageRepository.class);
        actorRepository = mock(ActorRepository.class);
        filmRepository = mock(FilmRepository.class);
        mockController = mock(DbController.class);
        app = new DemoApplication(languageRepo, actorRepository, filmRepository);
        app.controller = mockController;
        films = new ArrayList<>();
    }
    @Given("there are no more filters")
    public void there_are_no_more_filters() {
        id = null;
        linkedId = null;
        query = null;
    }

    ArrayList<Film> films;

    void populateFilms()
    {
        for(int i = 0; i<3; i++)
        {
            Film f = new Film();
            Assertions.assertDoesNotThrow(()->{f.setTitle("gom");}, "Setting title should not fail");
            films.add(f);
        }
    }
    @Given("query is {string}")
    public void query_is(String q)
    {
        query = q;
    }

    @Given("a film title in the database is equal to the query")
    public void db_has_equals_query()
    {
        Assertions.assertDoesNotThrow(()->{films.iterator().next().setTitle(query);}, "setting title should not fail");
    }

    @Given("a film title in the database contains the query")
    public void db_has_contains_query()
    {
        Assertions.assertDoesNotThrow(()->{films.iterator().next().setTitle(query.substring(0,1)+"b");}, "setting title should not fail");
    }

    @Given("We have some films in the database")
    public void we_have_some_films_in_the_database() {
        setup();
        populateFilms();
        when(mockController.getAllFilms()).thenReturn(films);

    }

    @Given("linked id is {int}")
    public void linked_id_is(Integer int1) {
        linkedId = int1;
    }

    Actor a;
    Set<Actor> al;
    @Given("There is a film associated with the actor_id")
    public void there_is_a_film_associated_with_the_actor_id() {

        Assertions.assertDoesNotThrow(()->
        {a = new Actor("blah", "blah");
            a.setActor_id(linkedId);
            Film f = films.iterator().next();
            Set<Film> fs = new HashSet<>();
            fs.add(f);
            a.setFilms(fs);
            }, "Creating actor should not fail");
        al = new HashSet<>();
        al.add(a);
        films.iterator().next().setActor(al);
        when(mockController.getAllActorsById(linkedId)).thenReturn(al);
        Assertions.assertEquals(al, films.iterator().next().getActor(), "Sanity check");
        Assertions.assertEquals(linkedId, films.iterator().next().getActor().iterator().next().getActor_id(), "Sanity check");
    }

    @Then("We return the film associated with the actor_id")
    public void we_return_the_film_associated_with_the_actor_id() {
        Assertions.assertEquals(al,actualFilms.iterator().next().getActor(), "The returned Film should be associated with the actor_id");
        Assertions.assertEquals(1, ((ArrayList<?>)actualFilms).size(), "There should only be one film returned");
    }


    @When("we receive a get film request")
    public void we_receive_a_get_request() {
        actualFilms = this.app.getFilms(null, query, linkedId);
    }
    Iterable<Film> actualFilms;
    @Then("we get all the films in the database")
    public void we_get_all_the_films_in_the_database() {
        Assertions.assertEquals(films, actualFilms, "All films should be returned when there is no filters");
    }

    @Then("we get the film that matches the query")
    public void get_all_match_query()
    {
        Assertions.assertEquals(films.iterator().next(), actualFilms.iterator().next(), "We should return the item that matches that contains the query");
    }

}
