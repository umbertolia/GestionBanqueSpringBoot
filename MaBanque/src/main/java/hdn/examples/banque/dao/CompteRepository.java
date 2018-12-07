/**
 * 
 */
package hdn.examples.banque.dao;

import hdn.examples.banque.entites.Compte;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */
public interface CompteRepository extends JpaRepository<Compte, String>{

}
