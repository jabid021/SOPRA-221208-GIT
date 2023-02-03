package quest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		model.addAttribute("types", TypeClient.values());
		return "client/add";
	}
	
	@PostMapping("")
	public String enregistrer(@ModelAttribute Client client,Model model) {
		try {
		clientSrv.create(client);
		}catch (Exception e) {
			model.addAttribute("error", true);
			return "client/add";
		}
		return "redirect:/client";
	}
	
	@GetMapping("/delete")
	public String suppression(@ModelAttribute ClientId id) {
		clientSrv.delete(id);
		return "redirect:/client";
		
	}
}
