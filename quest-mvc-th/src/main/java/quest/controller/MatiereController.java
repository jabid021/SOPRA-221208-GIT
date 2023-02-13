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

import quest.model.Matiere;
import quest.service.MatiereService;

@Controller
@RequestMapping("/matiere")
public class MatiereController {

	@Autowired
	private MatiereService matiereService;

	@GetMapping("") // ETAPE 1 : RECEPTION DE LA REQUETE
	public String list(Model model) {
		// ETAPE 2 : RECUPERATION DES DONNEES
		List<Matiere> matieres = matiereService.findAll();

		// ETAPE 3 : RENSEIGNER LE MODEL
		model.addAttribute("matieres", matieres);

		// ETAPE 4 : APPEL DE LA VIEW
		return "matiere/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("matiere", new Matiere());

		return "matiere/form";
	}

	@GetMapping("/edit")
	public String edit(@RequestParam Integer id, Model model) {
		model.addAttribute("matiere", matiereService.findById(id));

		return "matiere/form";
	}

	@PostMapping("")
	public String save(@ModelAttribute("matiere") Matiere matiere) {
		if (matiere.getId() == null) {
			matiereService.create(matiere);
		} else {
			matiereService.update(matiere);
		}
		return "redirect:matiere";
	}

	@GetMapping("/cancel")
	public String cancel() {

		return "forward:/matiere";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		matiereService.delete(id);

		return "redirect:matiere";
	}
}
