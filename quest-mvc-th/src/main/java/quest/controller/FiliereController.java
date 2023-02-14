package quest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import quest.controller.validator.FiliereValidator;
import quest.model.Filiere;
import quest.model.Formateur;
import quest.service.FiliereService;
import quest.service.FormateurService;

@Controller
@RequestMapping("/filiere")
public class FiliereController {

	@Autowired
	private FiliereService filiereService;
	
	@Autowired
	private FormateurService formateurService;

	@GetMapping("") // ETAPE 1 : RECEPTION DE LA REQUETE
	public String list(Model model) {
		// ETAPE 2 : RECUPERATION DES DONNEES
		List<Filiere> filieres = filiereService.findAll();

		// ETAPE 3 : RENSEIGNER LE MODEL
		model.addAttribute("filieres", filieres);

		// ETAPE 4 : APPEL DE LA VIEW
		return "filiere/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("filiere", new Filiere());
		model.addAttribute("formateurs", formateurService.findAll());

		return "filiere/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Integer id, Model model) {
		model.addAttribute("filiere", filiereService.findById(id));
		model.addAttribute("formateurs", formateurService.findAll());

		return "filiere/form";
	}

	@PostMapping("")
	public String save(@ModelAttribute("filiere") @Valid Filiere filiere, BindingResult result, @RequestParam(required = false) Integer idReferent) {
		new FiliereValidator().validate(filiere, result);
		
		if(result.hasErrors()) {
			return "filiere/form";
		}
		
		if(idReferent != null) {
			Formateur formateur = formateurService.findById(idReferent);
			filiere.setReferent(formateur);
		} else {
			filiere.setReferent(null);
		}
		
		if (filiere.getId() == null) {
			filiereService.create(filiere);
		} else {
			filiereService.update(filiere);
		}
		return "redirect:filiere";
	}

	@GetMapping("/cancel")
	public String cancel() {

		return "forward:/filiere";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		filiereService.delete(id);

		return "redirect:filiere";
	}
}
