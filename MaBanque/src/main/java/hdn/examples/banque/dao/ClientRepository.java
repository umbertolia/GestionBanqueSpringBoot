/**
 * 
 */
package hdn.examples.banque.dao;

import hdn.examples.banque.entites.Client;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */
public interface ClientRepository extends JpaRepository<Client, Long> {

}
