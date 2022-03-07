package persistance.document;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import mediatek2022.Document;
import mediatek2022.Utilisateur;

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
		boolean contenuAdulte = false; 
        try {
			Connection connect = connexion();

            String getDoc = "SELECT * FROM document WHERE IdDoc ="+this.idDoc+";";
            
            Statement st = connect.createStatement();        
            ResultSet doc = st.executeQuery(getDoc);
            
            while(doc.next()) {
                contenuAdulte = doc.getBoolean("Adulte"); 
            }
            
            Object[] attributs = u.data(); 
            int ageUtilisateur = (int) attributs[5];
            
            if(contenuAdulte && ageUtilisateur > 18 || !contenuAdulte) {
                PreparedStatement ps = connect.prepareStatement("UPDATE document set Emprunt = 1 WHERE IdDoc = ?;");
                ps.setInt(1, this.idDoc);
               
                PreparedStatement sp = connect.prepareStatement("INSERT INTO Emprunt(IdUser, IdDoc, DateEmprunt) VALUES (?,?, CURRENT_DATE);");
    			sp.setInt(1, (int) u.data()[0]);
    			sp.setInt(2, this.idDoc);
    			
    			 ps.executeUpdate();
    			 sp.executeUpdate();
            }
            else {
                return; 
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Erreur lors de l'execution d'une requête : " + e);
        }
	}

	@Override
	public void retour() {
		try {
			Connection connect = connexion();
			
			PreparedStatement st = connect.prepareStatement("UPDATE document set Emprunt = 0 WHERE IdDoc = ?;");
			st.setInt(1, this.idDoc);
			
			PreparedStatement sp = connect.prepareStatement("DELETE FROM emprunt WHERE IdDoc = ?;");
			sp.setInt(1, this.idDoc);
			
	        st.executeUpdate();
			sp.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution d'une requête : " + e);
		}
	}
	
	public static Connection connexion () throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/mediatek";
		String user = "root";
		String password = "";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connect;
		connect = DriverManager.getConnection(url, user, password);
		
		return connect; 
	}
}
