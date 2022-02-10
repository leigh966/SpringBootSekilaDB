package org.tsi.leigh.demo;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockitoTest
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
        sekila = new DemoApplication(languageRepo, actorRepository, filmRepository);
    }

    @Test
    public void testAddLanguage()
    {
        Language savedLanguage = new Language("test language");
        String expected = "saved";
        String actual = sekila.addLanguage(savedLanguage.getName());
       ArgumentCaptor<Language> languageArgumentCaptor = ArgumentCaptor.forClass(Language.class);

        verify(languageRepo).save(languageArgumentCaptor.capture());
        languageArgumentCaptor.getValue();
        Assertions.assertEquals(expected, actual, "Save failed");
    }
}
