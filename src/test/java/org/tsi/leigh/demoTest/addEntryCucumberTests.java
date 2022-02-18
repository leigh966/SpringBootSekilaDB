package org.tsi.leigh.demoTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tsi.leigh.demo.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class addEntryCucumberTests
{

    private DbController controller;
    @Mock
    private LanguageRepository languageRepo; // Fake language table

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ActorRepository actorRepository;

    @Mock
    private CategoryRepository categoryRepo;

    String actual;
    void setup()
    {
        languageRepo = mock(LanguageRepository.class);
        actorRepository = mock(ActorRepository.class);
        filmRepository = mock(FilmRepository.class);
        categoryRepo = mock(CategoryRepository.class);
        controller = new DbController(languageRepo, actorRepository, filmRepository, categoryRepo);
    }


    // Add Language
    Language savedLanguage;

    @Given("We have a language to add")
    public void chooseLanguage()
    {
        setup();
        savedLanguage = new Language("test language");
    }

    @When("We add the language")
    public void addLanguage()
    {
        actual = controller.addLanguage(savedLanguage.getName());
    }

    @Then("The language should be added and we should return that it was saved")
    public void checkLanguageIsAdded()
    {
        // Check that the function has told us the new language has been saved
        String expected = "saved";
        Assertions.assertEquals(expected, actual, "Save failed");

        // Verify that the save occurred
        ArgumentCaptor<Language> languageArgumentCaptor = ArgumentCaptor.forClass(Language.class);
        verify(languageRepo).save(languageArgumentCaptor.capture());
        languageArgumentCaptor.getValue();
    }


    // Add Actor
    Actor savedActor;
    @Given("We have an actor to add")
    public void chooseActor()
    {
        setup();
        savedActor = new Actor("Example", "Guy");
    }

    @When("We add the actor")
    public void addActor()
    {
        actual = controller.addActor(savedActor.getFirst_name(), savedActor.getLast_name());
    }

    @Then("The actor should be added and we should return that it was saved")
    public void checkActorAdded()
    {
        // Check that the function has told us the new language has been saved

        String expected = "saved";
        Assertions.assertEquals(expected, actual, "Save failed");

        // Verify that the save occured
        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
        verify(actorRepository).save(actorArgumentCaptor.capture());
        actorArgumentCaptor.getValue();
    }


    // Add Film
    Film savedFilm;

    @Given("We have a film to add")
    public void chooseFilm()
    {
        setup();
        savedFilm = new Film("Example Film", "It is a film", 1, null, 10, 9.99f, 180, 23.00f, "G");
    }

    @When("We add the film")
    public void addFilm()
    {
        actual = controller.addFilm(savedFilm.getTitle(), savedFilm.getDescription(), savedFilm.getLanguage_id(), savedFilm.getOriginal_language_id(), savedFilm.getRental_duration(), savedFilm.getRental_rate(), savedFilm.getLength(), savedFilm.getReplacement_cost(), savedFilm.getRating());
    }

    @Then("The film will be added and we should return that it was saved")
    public void checkFilm()
    {
        // Check that the function has told us the new language has been saved
        String expected = "saved";
        Assertions.assertEquals(expected, actual, "Save failed");

        // Verify that the save occured
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);
        verify(filmRepository).save(filmArgumentCaptor.capture());
        filmArgumentCaptor.getValue();
    }

    // Add Category
    Category cat;

    @Given("We have a category to add")
    public void chooseCategory()
    {
        setup();
        cat = new Category("Just weird");
    }

    @When("We add the category")
    public void addCategory()
    {
        actual = controller.saveCategory(cat.getName());
    }

    @Then("The category will be added and we should return that it was saved")
    public void checkCategory()
    {
        // Check that the function has told us the new Category has been saved
        String expected = "saved";
        Assertions.assertEquals(expected, actual, "Save failed");

        // Verify that the save call occured
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepo).save(categoryArgumentCaptor.capture());
        categoryArgumentCaptor.getValue();
    }
}
