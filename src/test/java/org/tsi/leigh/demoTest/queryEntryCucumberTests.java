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

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class queryEntryCucumberTests
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


    int id;


    // Query by id ------
    @Given("We have an id of {int} chosen")
    public void prepFilmIdQuery(Integer id)
    {
        setup();
        this.id = id;
    }


    // Query film by id
    @When("We try to get a film by id")
    public void getFilmById()
    {
        controller.getAllFilmsById(id);
    }

    @Then("The db receives a call to get all films matching our id")
    public void checkFilmById()
    {
        // Verify that the query occurred
        ArgumentCaptor<ArrayList> idArgumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(filmRepository).findAllById(idArgumentCaptor.capture());
        idArgumentCaptor.getValue();
    }

    // Query Actor by id
    @When("We try to get a actor by id")
    public void getActorById()
    {
        controller.getAllActorsById(id);
    }

    @Then("The db receives a call to get all actors matching our id")
    public void checkActorById()
    {
        // Verify that the query occurred
        ArgumentCaptor<ArrayList> idArgumentCaptor = ArgumentCaptor.forClass(ArrayList.class);
        verify(actorRepository).findAllById(idArgumentCaptor.capture());
        idArgumentCaptor.getValue();
    }

    //
}
