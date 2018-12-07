/**
 * 
 */
package hdn.examples.banque.entites;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = -7132597131649072146L;
	
	@Id @GeneratedValue
	private Long code;
	
	private String nom;
	
	private String email;
	
	@OneToMany (mappedBy="client", fetch=FetchType.LAZY)
	private Collection<Compte> comptes;

	
	
	/**
	 * 
	 */
	public Client() {
		super();
	}

	/**
	 * @param nom
	 * @param email
	 */
	public Client(String nom, String email) {
		super();
		this.nom = nom;
		this.email = email;
	}

	/**
	 * @return the code
	 */
	public Long getCode() {
		return this.code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @return the nom
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the comptes
	 */
	public Collection<Compte> getComptes() {
		return this.comptes;
	}

	/**
	 * @param comptes the comptes to set
	 */
	public void setComptes(Collection<Compte> comptes) {
		this.comptes = comptes;
	}
	

	
	
}
