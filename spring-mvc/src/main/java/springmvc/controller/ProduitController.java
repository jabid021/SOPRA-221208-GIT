package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/produit")
public class ProduitController {

	@GetMapping("")
	public String produit() {
		return "produit/produit";
	}
	
	@GetMapping("/edit")
	public String edit() {
		return "produit/editProduit";
	}
}
