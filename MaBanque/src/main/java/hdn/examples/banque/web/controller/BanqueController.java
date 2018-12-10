package hdn.examples.banque.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import hdn.examples.banque.entites.Compte;
import hdn.examples.banque.entites.Operation;
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
	
	
	@RequestMapping("/operations")
	public String operations() {
			return "comptes";
		
	}
	
	@RequestMapping("/comptes")
	public String comptes() {
			return "comptes";
		
	}
	
	@RequestMapping("/clients")
	public String clients() {
			return "comptes";
		
	}
	
	
	

	@RequestMapping("/consultercompte")
	public String consulterCompte(Model model,  String codeCpte) {
		
			try {
				Compte compte = service.consulterCompte(codeCpte);
				Page<Operation> operationsPaginee = service.listeOperations(codeCpte, 0, 2);
				model.addAttribute("compte", compte);
				model.addAttribute("listeOperations", operationsPaginee.getContent());
			}
			catch (BanqueException banqueException) {
				logger.info("Aucun compte trouvé pour le n° de compte " +codeCpte);
				model.addAttribute("exception", banqueException);
				
			}
			return "comptes";
	}
	
	@RequestMapping("/operationcompte")
	public String lancerOperationSurCompte(Model model,  String codeCpte) {
			
			return "comptes";
	}
	
	@RequestMapping("/listeoperations")
	public String listerOperationsCompte(Model model,  String codeCpte) {
			
			return "comptes";
	}
	
}
