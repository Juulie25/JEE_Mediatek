package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utilisateur.UtilisateurMediatek;

import document.DocumentsMediatek;
import mediatek2022.*;

// classe mono-instance  dont l'unique instance est connue de la médiatheque
// via une auto-déclaration dans son bloc static

public class MediathequeData implements PersistentMediatheque {

	static {
		Mediatheque.getInstance().setData(new MediathequeData());

	}

	private MediathequeData() {
	}

	// renvoie la liste de tous les documents disponibles de la médiathèque
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		return null;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public UtilisateurMediatek getUser(String login, String password) {

		UtilisateurMediatek utilisateur = null;
		
		try {
			Connection connect = connexion();

			String getUser = "SELECT * FROM user WHERE Pseudo=? AND MotDePasse=?;"; 
			PreparedStatement st = null;
			st = connect.prepareStatement(getUser);
			//Statement st;
			st.setString(1, login);
			st.setString(2, password);
			
			//st = connect.createStatement();
			
			ResultSet personne = st.executeQuery();
			
			while(personne.next()) {
				String nom = personne.getString("Nom");
				String prenom = personne.getString("Prenom");
				String pseudo = personne.getString("Pseudo");
				String role = personne.getString("RoleUser");
				int age = personne.getInt("Age");
				
				utilisateur = new UtilisateurMediatek(nom, prenom, pseudo, role, age);
				System.out.println(utilisateur);
			}
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Erreur lors de l'execution de la requete " + e);
		}
		
		return utilisateur;
	}

	// va récupérer le document de numÃ©ro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		return null;
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
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
	
	

	public static ArrayList<DocumentsMediatek> consulterDocuments() throws ClassNotFoundException, SQLException {
	
		Connection connect = connexion(); 
		
		String allDocuments = "SELECT * FROM document"; 
		Statement st = connect.createStatement();
		ResultSet docs = st.executeQuery(allDocuments);
		ArrayList<DocumentsMediatek> listeDocs = new ArrayList<>();
		
		while(docs.next()) {
			String typeDoc = docs.getString("TypeDoc");
			String titreDoc = docs.getString("TitreDoc");
			String auteurDoc = docs.getString("AuteurDoc");
			Boolean emprunt = docs.getBoolean("Emprunt");
			Boolean adulte = docs.getBoolean("Adulte");
			
			listeDocs.add(new DocumentsMediatek(typeDoc, titreDoc, auteurDoc, emprunt, adulte ));
		}
		return listeDocs;

	}
	
	public static ArrayList<DocumentsMediatek> consulterDocumentsEmprunt(String pseudo) throws ClassNotFoundException, SQLException{
		
		Connection connect = connexion(); 
		
		String allDocuments = "SELECT d.TypeDoc, d.TitreDoc, d.AuteurDoc, d.Emprunt, d.Adulte FROM emprunt e,user u, document d WHERE e.IdUser = u.IdUser AND e.IdDoc = d.IdDoc AND u.Pseudo=?;";

		PreparedStatement st = null;
		st = connect.prepareStatement(allDocuments);
		st.setString(1, pseudo);
		
		ResultSet docs = st.executeQuery();
		
		ArrayList<DocumentsMediatek> listeDocsEmprunt = new ArrayList<>();
		
		while(docs.next()) {
			String typeDoc = docs.getString("TypeDoc");
			String titreDoc = docs.getString("TitreDoc");
			String auteurDoc = docs.getString("AuteurDoc");
			Boolean emprunt = docs.getBoolean("Emprunt");
			Boolean adulte = docs.getBoolean("Adulte");
			
			listeDocsEmprunt.add(new DocumentsMediatek(typeDoc, titreDoc, auteurDoc, emprunt, adulte ));
		}
		return listeDocsEmprunt;
	}
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
	
		ArrayList<DocumentsMediatek> documents = consulterDocuments();
		
		ArrayList<String> reponseTab = new ArrayList<>();
		
		for(int i = 0; i < documents.size(); i++){
			if(documents.get(i).disponible()) {
				reponseTab.add(documents.get(i).getType()+ " - "+ documents.get(i).getTitre()+" - "+documents.get(i).getAuteur() +" - "+ documents.get(i).disponible());
			}
			else {
				reponseTab.add(documents.get(i).getType()+ " - "+documents.get(i).getTitre()+" - "+documents.get(i).getAuteur() + " - "+ documents.get(i).disponible());
			}
		}
		
		for(int i = 0; i < reponseTab.size(); i++){
			System.out.println(reponseTab.get(i));
		}
	}


}

