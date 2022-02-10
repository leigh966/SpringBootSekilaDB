package org.tsi.leigh.demo;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class menuCucumberStepsDef
{
    private DemoApplication sekila;
    @Mock
    private LanguageRepository languageRepo; // Fake language table

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ActorRepository actorRepository;

    @BeforeEach
    void setup()
    {
        languageRepo = mock(LanguageRepository.class);
        actorRepository = mock(ActorRepository.class);
        filmRepository = mock(FilmRepository.class);
        sekila = new DemoApplication(languageRepo, actorRepository, filmRepository);
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
        actual = sekila.addLanguage(savedLanguage.getName());
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
}
