package document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import mediatek2022.Document;
import mediatek2022.Utilisateur;
import persistance.MediathequeData;


public class DocumentsMediatek implements Document {
	private int idDoc;
	private String type; 
	private String titre; 
	private String auteur; 
	private boolean emprunt; 
	private boolean adulte;
	
	public DocumentsMediatek(int idDocD, String typeD, String titreD, String auteurD, boolean empruntD, boolean adulteD) {
		this.idDoc = idDocD;
		this.type = typeD; 
		this.titre = titreD; 
		this.auteur = auteurD; 
		this.emprunt = empruntD;
		this.adulte = adulteD;
		
	}

	public int getIdDoc() {
		return idDoc;
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
		return  idDoc + "/ " + type + "/ " + titre + "/ " + auteur + "/ " + emprunt
				+ "/ " + adulte + "/ ";
	}



	@Override
	public void emprunt(Utilisateur u) throws Exception {
		try {
			Connection connect = MediathequeData.connexion();
			
			PreparedStatement st = connect.prepareStatement("UPDATE document set Emprunt = 0 WHERE IdDoc = ?;");
			st.setInt(1, this.idDoc);
			
	        st.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void retour() {
		
		try {
			Connection connect = MediathequeData.connexion();
			
			PreparedStatement st = connect.prepareStatement("UPDATE document set Emprunt = 0 WHERE IdDoc = ?;");
			st.setInt(1, this.idDoc);
			
	        st.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
