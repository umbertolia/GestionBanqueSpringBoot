/**
 * 
 */
package hdn.examples.banque.entites;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */
@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{

	private static final long serialVersionUID = 6509120367932067106L;
	
	private double taux;

	/**
	 * 
	 */
	public CompteEpargne() {
		super();
	}

	/**
	 * @param codeCompte
	 * @param dateCreation
	 * @param solde
	 * @param client
	 */
	public CompteEpargne(String codeCompte, Date dateCreation, double solde,
			Client client) {
		super(codeCompte, dateCreation, solde, client);
	}

	/**
	 * @return the taux
	 */
	public double getTaux() {
		return this.taux;
	}

	/**
	 * @param taux the taux to set
	 */
	public void setTaux(double taux) {
		this.taux = taux;
	}
	
	
	
}
