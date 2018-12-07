/**
 * 
 */
package hdn.examples.banque.entites;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE", discriminatorType=DiscriminatorType.STRING, length=2)
public abstract class Compte implements Serializable{

	private static final long serialVersionUID = 2478562777147864320L;

	@Id
	private String codeCompte;
	
	private Date dateCreation;
	
	private double solde;

	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	
	private Client client;
	
	@OneToMany(mappedBy="compte", fetch=FetchType.LAZY)
	private Collection<Operation> operations;

	/**
	 * 
	 */
	public Compte() {
		super();
	}
	
	

	/**
	 * @param codeCompte
	 * @param dateCreation
	 * @param solde
	 * @param client
	 */
	public Compte(String codeCompte, Date dateCreation, double solde,
			Client client) {
		super();
		this.codeCompte = codeCompte;
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}



	/**
	 * @return the codeCompte
	 */
	public String getCodeCompte() {
		return this.codeCompte;
	}

	/**
	 * @param codeCompte the codeCompte to set
	 */
	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}

	/**
	 * @return the dateCreation
	 */
	public Date getDateCreation() {
		return this.dateCreation;
	}

	/**
	 * @param dateCreation the dateCreation to set
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * @return the solde
	 */
	public double getSolde() {
		return this.solde;
	}

	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}

	/**
	 * @return the client
	 */
	public Client getClient() {
		return this.client;
	}

	/**
	 * @param client the client to set
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	
	
}
