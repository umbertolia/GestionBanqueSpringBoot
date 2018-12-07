/**
 * 
 */
package hdn.examples.banque.dao;

import hdn.examples.banque.entites.Operation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */
public interface OperationRepository extends JpaRepository<Operation, Long>{
	
	
	@Query ("select o from Operation o where o.compte.codeCompte=:x order by o.dateOperation  desc")
	public Page<Operation> listerOperations(@Param("x")String codeCpte, Pageable pageable);
	

}
