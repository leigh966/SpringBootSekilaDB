package org.tsi.leigh.demoTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.tsi.leigh.demo.Film;
import org.tsi.leigh.demo.Language;

import java.util.HashSet;
import java.util.Set;

public class editRecordCucumberTests
{
    Set<Film> filmSet;
    Language l;
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
}
