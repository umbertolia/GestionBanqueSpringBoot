package hdn.examples.banque.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

	
/**
 * @author Administrator
 * Auteur HDN
 * Crée le Dec 10, 2018
 *
 * Cette classe permet de personnaliser la page de login

 */
@Controller
public class SecurityController {

	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping(value="/")
	// action par defaut
	public String defaultAction() {
		return "redirect:/operations";
	}
}
