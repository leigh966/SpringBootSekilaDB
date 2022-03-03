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
public class deleteEntryCucumberTests
{

    private DbController controller;
    @Mock
    private LanguageRepository languageRepo; // Fake language table

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ActorRepository actorRepository;



    void setup()
    {
        languageRepo = mock(LanguageRepository.class);
        actorRepository = mock(ActorRepository.class);
        filmRepository = mock(FilmRepository.class);
        controller = new DbController(languageRepo, actorRepository, filmRepository);
    }

    int id;
    String actual;


    // Delete Actor
    @Given("We have the id of the actor we want to delete")
    public void chooseActorId()
    {
        setup();
        id = 10;
    }

    @When("We delete the actor")
    public void deleteActor()
    {
        actual = controller.deleteActor(id);
    }

    @Then("The database should receive a call to delete the actor")
    public void checkActorDelete()
    {
        // Check that the function has told us the actor has been deleted
        String expected = "deleted";
        Assertions.assertEquals(expected, actual, "Delete failed");

        // Verify that the delete call occured
        ArgumentCaptor<Integer> actorArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(actorRepository).deleteById(actorArgumentCaptor.capture());
        actorArgumentCaptor.getValue();
    }



    // Delete Film
    @Given("We have the id of a film we want to delete")
    public void chooseFilmId()
    {
        setup();
        id = 10;
    }

    @When("We delete the film")
    public void deleteFilm()
    {
        actual = controller.deleteFilm(id);
    }

    @Then("The database should receive a call to delete the film")
    public void checkFilmDelete()
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
