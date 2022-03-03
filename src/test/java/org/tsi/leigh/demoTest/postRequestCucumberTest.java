package org.tsi.leigh.demoTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.tsi.leigh.demo.*;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static org.mockito.Mockito.mock;

public class postRequestCucumberTest {
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

    private DemoApplication app;

    private String actual;

    void setup()
    {
        languageRepo = mock(LanguageRepository.class);
        actorRepository = mock(ActorRepository.class);
        filmRepository = mock(FilmRepository.class);
        app = new DemoApplication(languageRepo, actorRepository, filmRepository);
    }

    @Given("We have all the fields required for a film")
    public void we_have_all_the_fields_required_for_a_film() {
        setup();
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
    final int MAX_TITLE_LENGTH = 60;
    @Given("The title is too long")
    public void the_title_is_too_long() {
        title = TestTools.buildString(MAX_TITLE_LENGTH+1);
    }
    @When("We send a post request for a film")
    public void we_send_a_post_request_for_a_film() {
        actual = app.addFilm(title,description,language_id,original_language_id,rental_duration,rental_rate,length,replacement_cost,rating,release_year);
    }
    @Then("The server should return {string}")
    public void the_server_should_return(String expected) {
        Assertions.assertEquals(expected, actual, "Unexpected return");
    }

    String first_name, last_name;

    @Given("We have all the fields for an actor")
    public void we_have_all_the_fields_for_an_actor() {
        first_name = "blah";
        last_name = "blah";
        setup();
    }
    @When("We send a post request for an actor")
    public void we_send_a_post_request_for_an_actor() {
        actual = app.addActor(first_name, last_name);
    }


}
