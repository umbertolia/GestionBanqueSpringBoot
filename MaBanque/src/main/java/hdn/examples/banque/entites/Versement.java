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
@DiscriminatorValue("VERS")
public class Versement extends Operation{

	private static final long serialVersionUID = 4044129513300431130L;

	/**
	 * 
	 */
	public Versement() {
		super();
	}

	/**
	 * @param numero
	 * @param dateOperation
	 * @param montant
	 * @param compte
	 */
	public Versement(Date dateOperation, double montant,
			Compte compte) {
		super(dateOperation, montant, compte);
	}

	
}
