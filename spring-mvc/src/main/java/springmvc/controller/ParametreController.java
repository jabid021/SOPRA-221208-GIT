package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/parametre")
public class ParametreController {

	@GetMapping("")
	public String demoParametre(
			@RequestParam(name = "prenom", required = false, defaultValue = "un prenom") String prenom,
			@RequestParam String nom, @RequestParam(name = "age", required = false, defaultValue = "0") Integer age,
			Model model) {
		// @RequestParam sans parametre=>le nom du parametre doit etre le meme que
		// l'argument de la methode
		System.out.println("le prenom recu:" + prenom);
		System.out.println("le nom recu:" + nom);
		System.out.println("age:" + age);

		model.addAttribute("prenom", prenom);
		model.addAttribute("nom", nom);
		model.addAttribute("age", age);

		return "parametre/parametre";
	}
}
