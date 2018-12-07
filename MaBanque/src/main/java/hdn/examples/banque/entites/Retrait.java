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
@DiscriminatorValue("RETR")
public class Retrait extends Operation {

	private static final long serialVersionUID = -8517663888419264047L;

	/**
	 * 
	 */
	public Retrait() {
		super();
	}

	/**
	 * @param numero
	 * @param dateOperation
	 * @param montant
	 * @param compte
	 */
	public Retrait(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
	}
	
	

}
