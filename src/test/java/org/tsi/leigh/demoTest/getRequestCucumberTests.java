package org.tsi.leigh.demoTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.tsi.leigh.demo.*;

import java.util.ArrayList;

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
            films.add(new Film());
        }
    }

    @Given("We have some films in the database")
    public void we_have_some_films_in_the_database() {
        setup();
        populateFilms();
        when(mockController.getAllFilms()).thenReturn(films);

    }
    @When("we receive a get film request")
    public void we_receive_a_get_request() {
        actualFilms = this.app.getFilms(null, null, null);
    }
    Iterable<Film> actualFilms;
    @Then("we get all the films in the database")
    public void we_get_all_the_films_in_the_database() {
        Assertions.assertEquals(films, actualFilms, "All films should be returned when there is no filters");
    }

}
