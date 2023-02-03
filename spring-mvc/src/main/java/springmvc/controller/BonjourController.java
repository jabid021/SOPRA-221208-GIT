package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/bonjour")
public class BonjourController {

	@GetMapping("")
	public String formulaire() {
		return "bonjour/formulaire";
	}
	
	@PostMapping("")
	public String bonjour(@RequestParam String prenom,Model model) {
		if(prenom.isBlank()) {
			model.addAttribute("error", true);
			return "bonjour/formulaire";
		}
		model.addAttribute("prenom", prenom);
		return "bonjour/bonjour";
	}
}
