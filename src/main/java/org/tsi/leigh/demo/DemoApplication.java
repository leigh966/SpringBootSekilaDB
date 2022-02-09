package org.tsi.leigh.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/home")
public class DemoApplication {

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private ActorRepository ar;

	@Autowired
	private FilmRepository fr;

	public DemoApplication(LanguageRepository languageRepository, ActorRepository actorRepo)
	{
		this.languageRepository = languageRepository;
		this.ar = actorRepo;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/all_languages")
	public @ResponseBody
	Iterable<Language> getAllLanguages()
	{
		return languageRepository.findAll();
	}

	@GetMapping("/all_actors")
	public @ResponseBody
	Iterable<Actor> getAllActors() {return ar.findAll();}

	@GetMapping("/all_films")
	public @ResponseBody
	Iterable<Film> getAllFilms()
	{
		return fr.findAll();
	}

}
