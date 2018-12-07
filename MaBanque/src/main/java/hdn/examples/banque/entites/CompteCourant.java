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
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{

	private static final long serialVersionUID = 6265173768358631522L;

	private double decouvert;

	/**
	 * 
	 */
	public CompteCourant() {
		super();
	}

	/**
	 * @param codeCompte
	 * @param dateCreation
	 * @param solde
	 * @param client
	 */
	public CompteCourant(String codeCompte, Date dateCreation, double solde,
			Client client) {
		super(codeCompte, dateCreation, solde, client);
	}

	/**
	 * @return the decouvert
	 */
	public double getDecouvert() {
		return this.decouvert;
	}

	/**
	 * @param decouvert the decouvert to set
	 */
	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}
	
	
	
}
