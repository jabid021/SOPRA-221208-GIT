package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping("")
	public String home() {
		return "home";
	}
	
	//@RequestMapping("/produit")
	//@RequestMapping(path = "/produit",method = RequestMethod.GET)
//	@GetMapping("/produit")
//	public String produit() {
//		return "produit";
//	}
	
}
