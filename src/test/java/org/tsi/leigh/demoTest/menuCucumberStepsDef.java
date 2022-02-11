package org.tsi.leigh.demoTest;


import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.tsi.leigh.demo.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class menuCucumberStepsDef
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

    @BeforeEach
    void setup()
    {
        languageRepo = mock(LanguageRepository.class);
        actorRepository = mock(ActorRepository.class);
        filmRepository = mock(FilmRepository.class);
        categoryRepo = mock(CategoryRepository.class);
        controller = new DbController(languageRepo, actorRepository, filmRepository, categoryRepo);
        app = new DemoApplication(languageRepo, actorRepository, filmRepository, categoryRepo);
    }

    Language savedLanguage;

    @Given("We have a language to add")
    public void choose_language()
    {
        setup();
        savedLanguage = new Language("test language");
    }

    String actual;

    @When("We add the language")
    public void add_language()
    {
        actual = controller.addLanguage(savedLanguage.getName());
    }

    @Then("The language should be added and we should return that it was saved")
    public void check_language_added()
    {
        // Check that the function has told us the new language has been saved
        String expected = "saved";
        Assertions.assertEquals(expected, actual, "Save failed");

        // Verify that the save occured
        ArgumentCaptor<Language> languageArgumentCaptor = ArgumentCaptor.forClass(Language.class);
        verify(languageRepo).save(languageArgumentCaptor.capture());
        languageArgumentCaptor.getValue();
    }


    Actor savedActor;

    @Given("We have an actor to add")
    public void choose_actor()
    {
        setup();
        savedActor = new Actor("Example", "Guy");
    }

    @When("We add the actor")
    public void add_actor()
    {
        actual = controller.addActor(savedActor.getFirst_name(), savedActor.getLast_name());
    }

    @Then("The actor should be added and we should return that it was saved")
    public void check_actor_added()
    {
        // Check that the function has told us the new language has been saved

        String expected = "saved";
        Assertions.assertEquals(expected, actual, "Save failed");

        // Verify that the save occured
        ArgumentCaptor<Actor> actorArgumentCaptor = ArgumentCaptor.forClass(Actor.class);
        verify(actorRepository).save(actorArgumentCaptor.capture());
        actorArgumentCaptor.getValue();
    }


    Film savedFilm;

    @Given("We have a film to add")
    public void choose_film()
    {
        setup();
        savedFilm = new Film("Example Film", "It is a film", 1, null, 10, 9.99f, 180, 23.00f, "G", null);
    }

    @When("We add the film")
    public void add_film()
    {
        actual = controller.addFilm(savedFilm.getTitle(), savedFilm.getDescription(), savedFilm.getLanguage_id(), savedFilm.getOriginal_language_id(), savedFilm.getRental_duration(), savedFilm.getRental_rate(), savedFilm.getLength(), savedFilm.getReplacement_cost(), savedFilm.getRating(), savedFilm.getSpecial_features());
    }

    @Then("The film will be added and we should return that it was saved")
    public void check_film()
    {
        // Check that the function has told us the new language has been saved
        String expected = "saved";
        Assertions.assertEquals(expected, actual, "Save failed");

        // Verify that the save occured
        ArgumentCaptor<Film> filmArgumentCaptor = ArgumentCaptor.forClass(Film.class);
        verify(filmRepository).save(filmArgumentCaptor.capture());
        filmArgumentCaptor.getValue();
    }


    Category cat;

    @Given("We have a category to add")
    public void choose_category()
    {
        setup();
        cat = new Category("Just weird");
    }

    @When("We add the category")
    public void add_category()
    {
        actual = controller.addCategory(cat.getName());
    }

    @Then("The category will be added and we should return that it was saved")
    public void check_category()
    {
        // Check that the function has told us the new Category has been saved
        String expected = "saved";
        Assertions.assertEquals(expected, actual, "Save failed");

        // Verify that the save call occured
        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(categoryRepo).save(categoryArgumentCaptor.capture());
        categoryArgumentCaptor.getValue();
    }


    int id;

    @Given("We have the id of the actor we want to delete")
    public void choose_actor_id()
    {
        setup();
        id = 10;
    }

    @When("We delete the actor")
    public void delete_actor()
    {
        actual = controller.deleteActor(id);
    }

    @Then("The database should receive a call to delete the actor")
    public void check_actor_delete()
    {
        // Check that the function has told us the actor has been deleted
        String expected = "deleted";
        Assertions.assertEquals(expected, actual, "Delete failed");

        // Verify that the delete call occured
        ArgumentCaptor<Integer> actorArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(actorRepository).deleteById(actorArgumentCaptor.capture());
        actorArgumentCaptor.getValue();
    }


    @Given("We have the id of a film we want to delete")
    public void choose_film_id()
    {
        setup();
        id = 10;
    }

    @When("We delete the film")
    public void delete_film()
    {
        actual = controller.deleteFilm(id);
    }

    @Then("The database should receive a call to delete the film")
    public void check_film_delete()
    {
        // Check that the function has told us the actor has been deleted
        String expected = "deleted";
        Assertions.assertEquals(expected, actual, "Delete failed");

        // Verify that the delete call occured
        ArgumentCaptor<Integer> actorArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(filmRepository).deleteById(actorArgumentCaptor.capture());
        actorArgumentCaptor.getValue();
    }


}