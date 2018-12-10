package hdn.examples.banque.metier.service;

public enum TypeOp {
	
	VERS("VERS"), RETR("RETR"), VIRM("VIRM");
	
	private String libelle;

	private TypeOp(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	
	
	

}
