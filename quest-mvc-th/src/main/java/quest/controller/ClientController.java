package quest.controller;

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

import quest.model.Client;
import quest.model.ClientId;
import quest.model.TypeClient;
import quest.service.ClientService;

@Controller
@RequestMapping("/client")
public class ClientController {

	@Autowired
	private ClientService clientSrv;
	
	@GetMapping("")
	public String listeDeClient(Model model) {
		model.addAttribute("clients", clientSrv.findAll());
		return "client/list";
	}
	
	@GetMapping("/add")
	public String nouveauClient(Model model) {
		model.addAttribute("client", new Client());
		model.addAttribute("types", TypeClient.values());
		model.addAttribute("mode", "add");
		return "client/form";
	}
	
	@GetMapping("/edit")
	public String editerClient(@ModelAttribute ClientId id, Model model) {
		model.addAttribute("client", clientSrv.findById(id));
		model.addAttribute("types", TypeClient.values());
		model.addAttribute("mode", "edit");
		return "client/form";
	}
	
	@PostMapping("")
	public String enregistrer(@ModelAttribute @Valid Client client, BindingResult result, Model model, @RequestParam String mode) {
		if(result.hasErrors()) {
			model.addAttribute("types", TypeClient.values());
			model.addAttribute("mode", mode);
			
			return "client/form"; 
		}
		
		try {
		clientSrv.create(client);
		}catch (Exception e) {
			model.addAttribute("error", true);
			return "client/form";
		}
		return "redirect:/client";
	}
	
	@GetMapping("/delete")
	public String suppression(@ModelAttribute ClientId id) {
		clientSrv.delete(id);
		return "redirect:/client";
		
	}
}
