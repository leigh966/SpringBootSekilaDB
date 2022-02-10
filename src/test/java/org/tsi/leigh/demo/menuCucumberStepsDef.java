package org.tsi.leigh.demo;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class menuCucumberStepsDef
{
    private String filmTitle;
    private Iterable<String> actors;
    FilmRepository fr;

    @Given("film is {String}")
    void title_is(String title)
    {
        filmTitle = title;
    }

    @When("we check who is in the film")
    void check_actors()
    {
        // Set the actors
    }

    @Then("{String} is in the film")
    void isInFilm(String name)
    {
        boolean found = false;
        for(String actor : actors)
        {
            found = found || actor == name;
        }
        Assertions.assertTrue(found);
    }

}
