package org.tsi.leigh.demoTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.tsi.leigh.demo.Actor;
import org.tsi.leigh.demo.Language;
import org.tsi.leigh.demo.NotSavedException;

public class createRecordCucumberTests
{
    // Create Language
    String languageName;
    @Given("We choose name {string} for language")
    public void chooseLanguageName(String langName)
    {
        languageName = langName;
    }

    Language l;
    @When("We create a language")
    public void createLanguage()
    {
        l = new Language(languageName);
    }

    @Then("The created language should contain the given values")
    public void checkLanguage()
    {
        Assertions.assertEquals(languageName, l.getName(), "Returned language name differs from assigned one");
    }

    //Create Actor
    String firstName;
    String lastName;
    @Given("We choose first_name {string} and last_name {string}")
    public void chooseActorName(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    Actor a;
    @When("We create an actor")
    public void createActor()
    {
        try
        {a = new Actor(firstName, lastName);}catch (NotSavedException nse)
        {
            Assertions.fail("Save should be successful");
        }

    }

    @Then("The created actor should contain the given values")
    public void checkActor()
    {
        Assertions.assertEquals(firstName, a.getFirst_name());
        Assertions.assertEquals(lastName, a.getLast_name());
    }
}
