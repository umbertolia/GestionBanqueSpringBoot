package hdn.examples.banque.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hdn.examples.banque.MaBanqueApplication;
import hdn.examples.banque.entites.Compte;
import hdn.examples.banque.exception.BanqueException;
import hdn.examples.banque.metier.service.IGestionBanque;

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
	
	private Logger logger = LoggerFactory.getLogger(BanqueController.class);
	
	
	@RequestMapping("/comptes")
	public String accueil() {
		
			
			return "comptes";
		
	}

	@RequestMapping("/consultercompte")
	public String accueil(Model model,  String codeCpte) {
		
			try {
				Compte compte = service.consulterCompte(codeCpte);
				model.addAttribute("compte", compte);
			}
			catch (BanqueException banqueException) {
				logger.info("Aucun compte trouvé pour le n° de compte " +codeCpte);
				model.addAttribute("exception", banqueException);
				
			}
			return "comptes";
		
	}
	
}
