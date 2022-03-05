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
		Connection connect;
		ArrayList<Document> listeDocs = new ArrayList<>();
		
		try {
			connect = connexion();
			String allDocuments = "SELECT * FROM document WHERE emprunt=0;"; 
			Statement st = connect.createStatement();
			ResultSet docs = st.executeQuery(allDocuments);
			
			
			while(docs.next()) {
				int idDoc = docs.getInt("IdDoc");
				String typeDoc = docs.getString("TypeDoc");
				String titreDoc = docs.getString("TitreDoc");
				String auteurDoc = docs.getString("AuteurDoc");
				Boolean emprunt = docs.getBoolean("Emprunt");
				Boolean adulte = docs.getBoolean("Adulte");
				
				listeDocs.add(new DocumentsMediatek(idDoc, typeDoc, titreDoc, auteurDoc, emprunt, adulte ));
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution de la requête " + e);
		} 
		
		return listeDocs;
		
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
		Document document = null; 
		Connection connect;
		try {
			connect = connexion();
			String getDoc = "SELECT * FROM document WHERE IdDoc=?"; 
			PreparedStatement st = null;
			st = connect.prepareStatement(getDoc);
			st.setInt(1, numDocument);
					
			ResultSet doc = st.executeQuery();
			while(doc.next()) {
				int idDoc = doc.getInt("IdDoc");
				String typeDoc = doc.getString("TypeDoc");
				String titreDoc = doc.getString("TitreDoc");
				String auteurDoc = doc.getString("AuteurDoc");
				Boolean emprunt = doc.getBoolean("Emprunt");
				Boolean adulte = doc.getBoolean("Adulte");
				
				document = new DocumentsMediatek(idDoc, typeDoc, titreDoc, auteurDoc, emprunt, adulte );
			}
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution de la requete " + e);
		} 
		
		return document; 
		
	}

	@Override
	public void ajoutDocument(int type, Object... args) {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc... variable suivant le type de document
		
		Connection connect;
		try {
			connect = connexion();
			String nouveauDocument = "INSERT INTO document(TypeDoc, TitreDoc, AuteurDoc, Emprunt, Adulte) VALUES (?, ?, ?, ?, ?);"; 
			PreparedStatement st = null;
			st = connect.prepareStatement(nouveauDocument);
			st.setString(1, (String) args[0]);
			st.setString(2, (String) args[1]);
			st.setString(3, (String) args[2]);
			st.setBoolean(4, (boolean) args[3]);
			st.setBoolean(5, (boolean) args[4]);

			ResultSet addDoc = st.executeQuery();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution de la requete " + e);
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
	
	

	public static ArrayList<DocumentsMediatek> consulterDocuments() throws ClassNotFoundException, SQLException {
		Connection connect = connexion(); 
		
		String allDocuments = "SELECT * FROM document"; 
		Statement st = connect.createStatement();
		ResultSet docs = st.executeQuery(allDocuments);
		ArrayList<DocumentsMediatek> listeDocs = new ArrayList<>();
		
		while(docs.next()) {
			int idDoc = docs.getInt("IdDoc");
			String typeDoc = docs.getString("TypeDoc");
			String titreDoc = docs.getString("TitreDoc");
			String auteurDoc = docs.getString("AuteurDoc");
			Boolean emprunt = docs.getBoolean("Emprunt");
			Boolean adulte = docs.getBoolean("Adulte");
			
			listeDocs.add(new DocumentsMediatek(idDoc, typeDoc, titreDoc, auteurDoc, emprunt, adulte ));
		}
		return listeDocs;

	}
	
	//Liste des documents empruntés par un utilisateur
	public static ArrayList<DocumentsMediatek> consulterDocumentsEmprunt(String pseudo) throws ClassNotFoundException, SQLException{
		
		Connection connect = connexion(); 
		
		String allDocuments = "SELECT d.IdDoc, d.TypeDoc, d.TitreDoc, d.AuteurDoc, d.Emprunt, d.Adulte FROM emprunt e,user u, document d WHERE e.IdUser = u.IdUser AND e.IdDoc = d.IdDoc AND u.Pseudo=?;";

		PreparedStatement st = null;
		st = connect.prepareStatement(allDocuments);
		st.setString(1, pseudo);
		
		ResultSet docs = st.executeQuery();
		
		ArrayList<DocumentsMediatek> listeDocsEmprunt = new ArrayList<>();
		
		while(docs.next()) {
			Integer idDoc = docs.getInt("IdDoc");
			String typeDoc = docs.getString("TypeDoc");
			String titreDoc = docs.getString("TitreDoc");
			String auteurDoc = docs.getString("AuteurDoc");
			Boolean emprunt = docs.getBoolean("Emprunt");
			Boolean adulte = docs.getBoolean("Adulte");
			
			listeDocsEmprunt.add(new DocumentsMediatek(idDoc, typeDoc, titreDoc, auteurDoc, emprunt, adulte ));
		}
		return listeDocsEmprunt;
	}

}

