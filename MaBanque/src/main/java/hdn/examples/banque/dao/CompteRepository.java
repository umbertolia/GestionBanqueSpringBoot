/**
 * 
 */
package hdn.examples.banque.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hdn.examples.banque.entites.Compte;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */

@Repository  
public interface CompteRepository extends JpaRepository<Compte, String>{

}
