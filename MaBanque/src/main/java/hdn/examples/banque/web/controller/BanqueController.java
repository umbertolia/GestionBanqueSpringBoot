package hdn.examples.banque.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private final String PAGE_FIRST = "0";
	
	private final String PAGE_SIZE_LIST_DEFAULT_VALUE = "4";
	
	

	@GetMapping("/operations")
	public String operations() {
		return "comptes";

	}
	
	@GetMapping("/comptes")
	public String comptes() {
		// TODO à implementer
		return "comptes";
	}

	@GetMapping("/clients")
	public String clients() {
	// TODO à implementer
		return "comptes";

	}

	@GetMapping("/consultercompte")
	public String consulterCompte(Model model, String codeCpte, 
			@RequestParam(name="page", defaultValue=PAGE_FIRST) int page, 
			@RequestParam(name="size", defaultValue=PAGE_SIZE_LIST_DEFAULT_VALUE) int size) {

		try {
			Compte compte = service.consulterCompte(codeCpte);
			Page<Operation> operationsPaginee = service.listeOperations(codeCpte, page, size);
			model.addAttribute("compte", compte);
			model.addAttribute("listeOperations", operationsPaginee.getContent());
			int[] pages = new int[operationsPaginee.getTotalPages()];
			model.addAttribute("pages", pages);
		} catch (BanqueException banqueException) {
			logger.info("Aucun compte trouvé pour le n° de compte " + codeCpte);
			model.addAttribute("exception", banqueException);

		}
		return "comptes";
	}

	@PostMapping("/operationcompte")
	public String creerOperation(Model model, String codeCpte, String codeCpt2, String typeOperation, String montant) {
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
		return consulterCompte(model, codeCpte, Integer.valueOf(PAGE_FIRST),  Integer.valueOf(PAGE_SIZE_LIST_DEFAULT_VALUE));
	}

	@GetMapping("/listeoperations")
	public String listerOperationsCompte(Model model, String codeCpte) {

		return "comptes";
	}
	
	
	// param envoye depuis le handler
	@GetMapping("/403")
   public String error403(Model model, @RequestParam(name="nomAction") String actionName) {
		model.addAttribute("nomAction", actionName);
		return "403";
   }

}
