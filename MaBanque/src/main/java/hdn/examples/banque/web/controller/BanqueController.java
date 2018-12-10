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
import hdn.examples.banque.metier.service.TypeOp;

/**
 * Auteur HDN Crée le Dec 7, 2018
 *
 * Cette classe permet de ...
 * 
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
	public String consulterCompte(Model model, String codeCpte) {

		try {
			Compte compte = service.consulterCompte(codeCpte);
			Page<Operation> operationsPaginee = service.listeOperations(codeCpte, 0, 4);
			model.addAttribute("compte", compte);
			model.addAttribute("listeOperations", operationsPaginee.getContent());
		} catch (BanqueException banqueException) {
			logger.info("Aucun compte trouvé pour le n° de compte " + codeCpte);
			model.addAttribute("exception", banqueException);

		}
		return "comptes";
	}

	@RequestMapping("/operationcompte")
	public String lancerOperationSurCompte(Model model, String codeCpte, String codeCpt2, String typeOperation, String montant) {
		try {
			TypeOp typeOp = TypeOp.valueOf(typeOperation);
			switch (typeOp) {
				case RETR: {
					logger.info("Lancement d'une operation de retrait");
					service.retirer(codeCpte, Double.valueOf(montant));
					break;
				}
				case VERS: {
					logger.info("Lancement d'une operation de versement");
					service.verser(codeCpte, Double.valueOf(montant));break;
		
				}
				case VIRM: {
					logger.info("Lancement d'une operation de virement");
					service.virement(codeCpte, codeCpt2, Double.valueOf(montant));
					break;
		
				}
			}
		}
		catch (BanqueException banqueException) {
			Throwable excOrigin = banqueException.getCause();
			if (excOrigin != null) {
				banqueException.setMessageErreur(banqueException.getMessageErreur() + excOrigin.getMessage());
			}
			model.addAttribute("operationexception", banqueException);
		}
		catch (Exception exception) {
			// pb avec la recup du type de virement
			BanqueException banqueException = new BanqueException("type operation non trouvee");
			model.addAttribute("operationexception", banqueException);
		}
		return consulterCompte(model, codeCpte);
	}

	@RequestMapping("/listeoperations")
	public String listerOperationsCompte(Model model, String codeCpte) {

		return "comptes";
	}

}
