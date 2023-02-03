package quest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	//@RequestMapping=>Url qui permet d'arriver sur la methode
	//le retour (String)=>la vue à afficher
	
	@RequestMapping("/home")
	public String home() {
		return "home";
	}
}
