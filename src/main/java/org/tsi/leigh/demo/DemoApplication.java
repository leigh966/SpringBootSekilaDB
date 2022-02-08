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

	public DemoApplication(LanguageRepository languageRepository)
	{
		this.languageRepository = languageRepository;
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


}
