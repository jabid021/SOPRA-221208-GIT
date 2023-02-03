package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import quest.model.Formateur;
import quest.service.FormateurService;

@Controller
@RequestMapping("/formateur")
public class FormateurController {

	@Autowired
	private FormateurService formateurSrv;

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("formateurs", formateurSrv.findAll());
		return "formateur/list";
	}

	@GetMapping("/add")
	public String add() {
		return "formateur/edit";
	}

	@GetMapping("/update")
	public String update(@RequestParam Integer id, Model model) {
		model.addAttribute("formateur", formateurSrv.findById(id));
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

	@GetMapping("/delete")
	public String delete(@RequestParam Integer id) {
		formateurSrv.delete(id);
		return "redirect:/formateur";
	}
}
