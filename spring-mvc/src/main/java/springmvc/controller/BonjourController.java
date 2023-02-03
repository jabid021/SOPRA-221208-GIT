package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import springmvc.model.Personne;

@Controller
@RequestMapping("/bonjour")
public class BonjourController {

	@GetMapping("")
	public String formulaire() {
		return "bonjour/formulaire";
	}

//	@PostMapping("")
//	public String bonjour(@RequestParam String prenom, @RequestParam String nom, Model model) {
//		if (prenom.isBlank()) {
//			model.addAttribute("error", true);
//			return "bonjour/formulaire";
//		}
////		model.addAttribute("prenom", prenom);
////		model.addAttribute("nom", nom);
//		Personne personne = new Personne();
//		personne.setPrenom(prenom);
//		personne.setNom(nom);
//		model.addAttribute("personne", personne);
//		return "bonjour/bonjour";
//	}
	
	@PostMapping("")
	//@ModelAttribute instancie un objet avec constructeur sans parametre (pas possible avec Interface ou class abstaite)
	//balayage des parametres de la requete
	//des qu'on trouve un parametre correspondant à un setter le objet cree =>on le positionne
	public String bonjour(@ModelAttribute Personne personne,Model model) {
		if (personne.getPrenom().isBlank()) {
			model.addAttribute("error", true);
			return "bonjour/formulaire";
		}
		model.addAttribute("personne", personne);
		return "bonjour/bonjour";
	}
}
