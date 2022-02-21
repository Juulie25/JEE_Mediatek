package document;

public class Documents {
	
	private String type; 
	private String titre; 
	private String auteur; 
	private boolean emprunt; 
	
	public Documents(String typeD, String titreD, String auteurD, boolean empruntD) {
		this.type = typeD; 
		this.titre = titreD; 
		this.auteur = auteurD; 
		this.emprunt = empruntD; 
	}

	public String getType() {
		return type;
	}

	public String getTitre() {
		return titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public boolean isEmprunt() {
		return emprunt;
	}
	
	@Override
	public String toString() {
		return "Type : " + this.type + " - Titre : " + this.titre + " - Auteur : " + this.auteur + " - Etat : " + this.emprunt + "\n";
	}
}
