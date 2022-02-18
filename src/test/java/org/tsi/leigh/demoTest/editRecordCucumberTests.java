package org.tsi.leigh.demoTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.tsi.leigh.demo.Actor;
import org.tsi.leigh.demo.Category;
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

    Film f;
    Category c;
    //Fill Film
    String title;
    String description;
    int release_year;
    int language_id;
    Integer original_langauge_id;
    int rental_duration;
    float rental_rate;
    Integer length;
    float replacement_cost;
    String rating;
    @Given("We have an empty film and values for each field")
    public void prepFilmFill()
    {
        title = "a film";
        description = "it's a film";
        release_year = 2022;
        language_id = 1;
        original_langauge_id = 2;
        rental_duration = 10;
        rental_rate = 1.99f;
        length = 5;
        replacement_cost = 15.99f;
        rating = "R";
        l = new Language();
        f = new Film();
        c = new Category();
        a = new Actor();
    }

    Set<Category> categorySet;
    Set<Actor> actorSet;
    @When("We add every field to the film")
    public void fillFilm()
    {

        f.setTitle(title);
        f.setDescription(description);
        f.setRelease_year(release_year);
        f.setLanguage_id(language_id);
        f.setLanguage(l);
        f.setOriginal_language_id(original_langauge_id);
        f.setOriginal_language(l);
        f.setRental_duration(rental_duration);
        f.setRental_rate(rental_rate);
        f.setLength(length);
        f.setReplacement_cost(replacement_cost);
        f.setRating(rating);
        actorSet = new HashSet<Actor>();
        actorSet.add(a);
        f.setActor(actorSet);
        categorySet = new HashSet<Category>();
        categorySet.add(c);
        f.setCategory(categorySet);

    }

    @Then("Every field should fit what was added")
    public void checkFilmFilled()
    {
        Assertions.assertEquals(title, f.getTitle(), "Title should have changed to what we set it");
        Assertions.assertEquals(description, f.getDescription(), "Description should have changed to what we set it");
        Assertions.assertEquals(release_year, f.getRelease_year(), "Release year should have changed to what we set it");
        Assertions.assertEquals(language_id, f.getLanguage_id(), "Language id should have changed to what we set it");
        Assertions.assertEquals(l, f.getLanguage(), "Language should have changed to what we set it");
        Assertions.assertEquals(original_langauge_id, f.getOriginal_language_id(), "Original language id should have changed to what we set it");
        Assertions.assertEquals(l, f.getOriginal_language(), "Original language should have changed to what we set it");
        Assertions.assertEquals(rental_duration, f.getRental_duration(), "Rental duration should have changed to what we set it");
        Assertions.assertEquals(rental_rate, f.getRental_rate(), "Rental rate should have changed to what we set it");
        Assertions.assertEquals(length, f.getLength(), "Length should have changed to what we set it");
        Assertions.assertEquals(replacement_cost, f.getReplacement_cost(), "Replacement cost should have changed to what we set it");
        Assertions.assertEquals(rating, f.getRating(), "Rating should have changed to what we set it");
        Assertions.assertEquals(actorSet, f.getActor(), "Actor set should have changed to what we set it");
        Assertions.assertEquals(categorySet, f.getCategory(), "Category set should have changed to what we set it");
    }

}
