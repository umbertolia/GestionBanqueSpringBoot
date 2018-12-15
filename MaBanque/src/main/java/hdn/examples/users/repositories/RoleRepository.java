/**
 * 
 */
package hdn.examples.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hdn.examples.users.entities.Role;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
