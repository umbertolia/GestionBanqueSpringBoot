/**
 * 
 */
package hdn.examples.banque.metier.service;

import hdn.examples.banque.dao.CompteRepository;
import hdn.examples.banque.dao.OperationRepository;
import hdn.examples.banque.entites.Compte;
import hdn.examples.banque.entites.CompteCourant;
import hdn.examples.banque.entites.Operation;
import hdn.examples.banque.entites.Retrait;
import hdn.examples.banque.entites.Versement;
import hdn.examples.banque.exception.BanqueException;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Auteur HDN Crée le Dec 6, 2018
 * 
 * Cette classe permet de ...
 */

@Service
@Transactional
public class GestionBanqueImpl implements IGestionBanque {

	@Autowired
	private CompteRepository compteRepository;

	@Autowired
	private OperationRepository operationRepository;

	@Override
	public Compte consulterCompte(String codeCpte) {
		Optional<Compte> compte = compteRepository.findById(codeCpte);

		if (compte.isPresent()) {
			return compte.get();
		}
		throw new BanqueException("Compte introuvable");
	}

	@Override
	public void verser(String codeCpte, double montant) {
		Compte compte = consulterCompte(codeCpte);
		Versement versement = new Versement(new Date(), montant, compte);

		operationRepository.save(versement);
		compte.setSolde(compte.getSolde() + montant);
		compteRepository.save(compte);
	}

	@Override
	public void retirer(String codeCpte, double montant) {
		Compte compte = consulterCompte(codeCpte);

		if (compte instanceof CompteCourant) {
			CompteCourant cc = (CompteCourant) compte;
			if ((compte.getSolde() + cc.getDecouvert() < montant)) {
				throw new BanqueException("Retrait impossible : fonds insuffisants");
			}
		}
		Retrait retrait = new Retrait(new Date(), montant, compte);
		operationRepository.save(retrait);
		compte.setSolde(compte.getSolde() - montant);
		compteRepository.save(compte);
	}

	@Override
	public void virement(String codeCpte1, String codeCpte2, double montant) {
		retirer(codeCpte1, montant);
		verser(codeCpte2, montant);

	}

	@Override
	public Page<Operation> listeOperations(String codeCpte, int page, int size) {
		return operationRepository.listerOperations(codeCpte, PageRequest.of(page, size));
		
	}

}
