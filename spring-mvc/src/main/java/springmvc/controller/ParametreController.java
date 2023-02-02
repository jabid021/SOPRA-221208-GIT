package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/parametre")
public class ParametreController {

	@GetMapping("")
	public String demoParametre(@RequestParam(name = "prenom",required = false,defaultValue = "un prenom") String prenom ) {
		System.out.println("le prenom recu:"+prenom);
		return "parametre/parametre";
	}
}
