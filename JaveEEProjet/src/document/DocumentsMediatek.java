package document;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

public class DocumentsMediatek implements Document {
	
	private String type; 
	private String titre; 
	private String auteur; 
	private boolean emprunt; 
	private boolean adulte;
	
	public DocumentsMediatek(String typeD, String titreD, String auteurD, boolean empruntD, boolean adulteD) {
		this.type = typeD; 
		this.titre = titreD; 
		this.auteur = auteurD; 
		this.emprunt = empruntD;
		this.adulte = adulteD;
		
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
	
	@Override
	public boolean disponible() {
		return !emprunt;
	}

	public boolean isAdulte() {
		return adulte;
	}

	

	@Override
	public String toString() {
		return "Documents [type=" + type + ", titre=" + titre + ", auteur=" + auteur + ", emprunt=" + emprunt
				+ ", adulte=" + adulte + "]";
	}



	@Override
	public void emprunt(Utilisateur u) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retour() {
		// TODO Auto-generated method stub
		
	}
	
	
}
