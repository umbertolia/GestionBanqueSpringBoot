/**
 * 
 */
package hdn.examples.banque.exception;

/**
 * Auteur HDN
 * Crée le Dec 6, 2018
 *
 * Cette classe permet de ...

 */
public class BanqueException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8030031780851068022L;

	private String codeErreur;
	
	private String messageErreur;

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public BanqueException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public BanqueException(String message, Throwable cause) {
		super(message, cause);
		this.messageErreur = message;

	}

	/**
	 * @param message
	 */
	public BanqueException(String message) {
		super(message);
		this.messageErreur = message;
	}

	/**
	 * @param cause
	 */
	public BanqueException(Throwable cause) {
		super(cause);
	}

	/**
	 * @return the codeErreur
	 */
	public String getCodeErreur() {
		return this.codeErreur;
	}

	/**
	 * @param codeErreur the codeErreur to set
	 */
	public void setCodeErreur(String codeErreur) {
		this.codeErreur = codeErreur;
	}

	/**
	 * @return the messageErreur
	 */
	public String getMessageErreur() {
		return this.messageErreur;
	}

	/**
	 * @param messageErreur the messageErreur to set
	 */
	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}
	
	
	
	

}
