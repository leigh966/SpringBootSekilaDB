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

	@Autowired
	private CategoryRepository categoryRepository;


	public DemoApplication(LanguageRepository languageRepository, ActorRepository actorRepo, FilmRepository filmRepo, CategoryRepository catRepo)
	{
		this.languageRepository = languageRepository;
		this.actorRepository = actorRepo;
		this.filmRepository = filmRepo;
		this.categoryRepository = catRepo;
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


	@GetMapping("/all_categories")
	public @ResponseBody
	Iterable<Category> getAllCategories()
	{
		return categoryRepository.findAll();
	}

	@PostMapping("add_category")
	public @ResponseBody
			String addCategory(String name)
	{
		Category cat = new Category(name);
		categoryRepository.save(cat);
		return "saved";
	}


	@PostMapping("/add_actor")
	public @ResponseBody
	String addActor(@RequestParam String first_name, @RequestParam String last_name)
	{
		Actor a = new Actor(first_name, last_name);
		actorRepository.save(a);
		return "saved";
	}

	@PostMapping("/add_language")
	public @ResponseBody
	String addLanguage(@RequestParam String name)
	{
		Language a = new Language(name);
		languageRepository.save(a);
		return "saved";
	}

	@PostMapping("add_film")
	public @ResponseBody
	String addFilm(@RequestParam String title,
				   @RequestParam String description,
				   @RequestParam int language_id,
				   @RequestParam Integer original_language_id,
				   @RequestParam int rental_duration,
				   @RequestParam float rental_rate,
				   int length,
				   float replacement_cost,
				   String rating,
				   String special_features)
	{
		Film f = new Film(title, description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features);
		filmRepository.save(f);
		return "saved";
	}

	@PostMapping("add_film_no_original_language")
	public @ResponseBody
	String addFilm(@RequestParam String title,
				   @RequestParam String description,
				   @RequestParam int language_id,
				   @RequestParam int rental_duration,
				   @RequestParam float rental_rate,
				   int length,
				   float replacement_cost,
				   String rating,
				   String special_features)
	{
		Film f = new Film(title, description, language_id, null, rental_duration, rental_rate, length, replacement_cost, rating, special_features);
		filmRepository.save(f);
		return "saved";
	}

	@PostMapping("add_film_min")
	public @ResponseBody
	String addFilm(@RequestParam String title,
				   @RequestParam String description,
				   @RequestParam int language_id,
				   @RequestParam int rental_duration,
				   @RequestParam float rental_rate,
				   int length,
				   float replacement_cost,
				   String rating)
	{
		Film f = new Film(title, description, language_id, null, rental_duration, rental_rate, length, replacement_cost, rating, null);
		filmRepository.save(f);
		return "saved";
	}
	
	@PostMapping("add_film_no_special_features")
	String addFilm(@RequestParam String title,
				   @RequestParam String description,
				   @RequestParam int language_id,
				   @RequestParam Integer original_language_id,
				   @RequestParam int rental_duration,
				   @RequestParam float rental_rate,
				   int length,
				   float replacement_cost,
				   String rating)
	{
		Film f = new Film(title, description, language_id, original_language_id, rental_duration, rental_rate, length, replacement_cost, rating, null);
		filmRepository.save(f);
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
