package quest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import quest.model.Adresse;
import quest.model.Civilite;
import quest.model.Formateur;
import quest.service.FormateurService;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

	@Autowired
	private FormateurService formateurSrv;

	@GetMapping("")
	public String list(Model model) {
		List<Formateur> formateurs = formateurSrv.findAll();

		model.addAttribute("formateurs", formateurs);

		return "formateur/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		return goForm(model, new Formateur());
	}

	@GetMapping("/update")
	public String update(@RequestParam Integer id, Model model) {
		return goForm(model, formateurSrv.findById(id));
	}

	private String goForm(Model model, Formateur formateur) {
		model.addAttribute("formateur", formateur);
		model.addAttribute("civilites", Civilite.values());
		return "formateur/edit";
	}

	@PostMapping("")
	public String save(@ModelAttribute Formateur formateur) {
		if (formateur.getId() == null) {
			formateurSrv.create(formateur);
		} else {
			formateurSrv.update(formateur);
		}
		return "redirect:/formateur";
	}

	@PostMapping("/bis")
	public String save(@RequestParam Integer id, @RequestParam String nom, @RequestParam String prenom, @RequestParam String rue, @RequestParam String complement, @RequestParam String codePostal, @RequestParam String ville) {
		Formateur formateur = new Formateur();
		formateur.setId(id);
		formateur.setNom(nom);
		formateur.setPrenom(prenom);
		
		Adresse adresse = new Adresse();
		adresse.setRue(rue);
		adresse.setComplement(complement);
		adresse.setCodePostal(codePostal);
		adresse.setVille(ville);
		
		formateur.setAdresse(adresse);
		

		if (formateur.getId() == null) {
			formateurSrv.create(formateur);
		} else {
			formateurSrv.update(formateur);
		}
		return "redirect:/formateur";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		formateurSrv.delete(id);
		return "redirect:/formateur";
	}
}
