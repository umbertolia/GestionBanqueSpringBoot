/**
 * 
 */
package hdn.examples.banque.metier.service;

import hdn.examples.banque.entites.Compte;
import hdn.examples.banque.entites.Operation;
import hdn.examples.banque.exception.BanqueException;

import org.springframework.data.domain.Page;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */
public interface IGestionBanque {
	
	public Compte consulterCompte(String codeCpte) throws BanqueException;
	
	public void verser(String codeCpte, double montant);
	
	public void retirer(String codeCpte, double montant);
	
	public void virement(String codeCpte1, String codeCpte2, double montant);
	
	public Page<Operation> listeOperations(String codeCpte, int page, int size);

}
