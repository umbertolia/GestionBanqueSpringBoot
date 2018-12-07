package hdn.examples.banque.web.controller;

import hdn.examples.banque.metier.service.IGestionBanque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Auteur HDN
 * Crée le Dec 7, 2018
 *
 * Cette classe permet de ...

 */

@Controller
public class BanqueController {
	
	@Autowired
	private IGestionBanque service;
	
	@RequestMapping("comptes")
	public String index() {
			return "comptes";
		
	}

}
