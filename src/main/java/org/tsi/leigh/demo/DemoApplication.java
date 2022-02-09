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
	private ActorRepository actorRepository;

	@Autowired
	private FilmRepository filmRepository;


	public DemoApplication(LanguageRepository languageRepository, ActorRepository actorRepo, FilmRepository filmRepo)
	{
		this.languageRepository = languageRepository;
		this.actorRepository = actorRepo;
		this.filmRepository = filmRepo;
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
	Iterable<Actor> getAllActors() {
		return actorRepository.findAll();
	}

	@GetMapping("actors_text")
	public @ResponseBody
	String getAllActorsText()
	{
		Iterable<Actor> actorIterable =  actorRepository.findAll();
		String output = "";
		for(Actor a : actorIterable)
		{
			String line = "Name: " + a.getFirst_name() + " " + a.getLast_name() + ", " + "Films: {";
			Iterable<Film> films = a.getFilms();
			for(Film f : films)
			{
				line += f.getTitle() + ",";
			}
			output += line + "}<br><br>";
		}
		return output;
	}

	@GetMapping("/all_films")
	public @ResponseBody
	Iterable<Film> getAllFilms()
	{
		return filmRepository.findAll();
	}


	@PostMapping("/add_actor")
	public @ResponseBody
	String addActor(@RequestParam String first_name, @RequestParam String last_name)
	{
		Actor a = new Actor(first_name, last_name);
		actorRepository.save(a);
		return "saved";
	}

/*
	@GetMapping("/actors_to_movies")
	public @ResponseBody
	Iterable<String[]> getActorsToMovies()
	{

	}
*/

}
