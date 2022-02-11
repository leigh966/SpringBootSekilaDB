package org.tsi.leigh.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/home")
public class DemoApplication {

    DbController controller;


    public DemoApplication(LanguageRepository languageRepository, ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository catRepo)
    {
        controller = new DbController(languageRepository, actorRepo, filmRepo, catRepo);
    }

}
