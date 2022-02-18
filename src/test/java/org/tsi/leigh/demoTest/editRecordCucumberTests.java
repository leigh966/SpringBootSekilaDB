package org.tsi.leigh.demoTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.tsi.leigh.demo.Actor;
import org.tsi.leigh.demo.Film;
import org.tsi.leigh.demo.Language;

import java.util.HashSet;
import java.util.Set;

public class editRecordCucumberTests
{

    Set<Film> filmSet;
    Language l;
    // Fill Language
    @Given("We have an empty language and a set with a Film")
    public void prepForLanguageFill()
    {
        l = new Language();
        filmSet = new HashSet<Film>();
        filmSet.add(new Film());
    }

    String languageName;
    @When("We add the name {string} and the film set")
    public void fillLanguage(String langName)
    {
        languageName = langName;
        l.setName(langName);
        l.setFilm(filmSet);
    }

    @Then("The language should contain the name and set that we gave it")
    public void checkLanguageFill()
    {
        Assertions.assertEquals(languageName, l.getName(), "Name should have changed to what we set");
        Assertions.assertEquals(filmSet, l.getFilm(), "Film set should have changed to what we set");
    }


    Actor a;
    // Fill Actor
    @Given("We have an empty Actor and a set with a Film")
    public void prepForActorFill()
    {
        a = new Actor();
        filmSet = new HashSet<Film>();
        filmSet.add(new Film());
    }


    String firstName;
    String lastName;
    @When("We add the first_name {string} last_name {string} and the film set")
    public void fillActor(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        a.setFirst_name(firstName);
        a.setLast_name(lastName);
        a.setFilms(filmSet);
    }

    @Then("The actor should contain the values we gave it")
    public void checkActorFill()
    {
        Assertions.assertEquals(firstName, a.getFirst_name(), "First name should have changed to what we set");
        Assertions.assertEquals(lastName, a.getLast_name(), "Last name should have changed to what we set");
        Assertions.assertEquals(filmSet, a.getFilms(), "Film set should have changed to what we set");
    }
}
