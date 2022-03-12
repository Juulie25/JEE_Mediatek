package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import persistance.utilisateur.UtilisateurMediatek;
import persistance.document.DocumentsMediatek;
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
		Statement st;
		ArrayList<Document> listeDocs = new ArrayList<>();
		
		try {
			connect = DataManage.connexion();
			String allDocuments = "SELECT * FROM document WHERE emprunt=0;"; 
			st = connect.createStatement();
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
			
			st.close();
			connect.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution de la requète " + e);
		} 
		
		return listeDocs;
	}

	// va récupérer le User dans la BD et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public UtilisateurMediatek getUser(String login, String password) {
		Connection connect;
		PreparedStatement st;
		UtilisateurMediatek utilisateur = null;
		
		try {
			connect = DataManage.connexion();

			String getUser = "SELECT * FROM user WHERE Pseudo=? AND MotDePasse=?;"; 
			st = connect.prepareStatement(getUser);
			st.setString(1, login);
			st.setString(2, password);
						
			ResultSet personne = st.executeQuery();
			
			while(personne.next()) {
				int id = personne.getInt("IdUser");
				String nom = personne.getString("Nom");
				String prenom = personne.getString("Prenom");
				String pseudo = personne.getString("Pseudo");
				String role = personne.getString("RoleUser");
				int age = personne.getInt("Age");
				
				utilisateur = new UtilisateurMediatek(id, nom, prenom, pseudo, role, age);
				System.out.println(utilisateur);
			}
			
			st.close();
			connect.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			System.err.println("Erreur lors de l'execution de la requête " + e);
		}
		
		return utilisateur;
	}

	// va récupérer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouvé, renvoie null
	@Override
	public Document getDocument(int numDocument) {
		Document document = null; 
		Connection connect;
		PreparedStatement st; 
		
		try {
			connect = DataManage.connexion();
			String getDoc = "SELECT * FROM document WHERE IdDoc=?"; 
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
			
			st.close();
			connect.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution de la requête " + e);
		} 
		return document; 
	}

	@Override
	public void ajoutDocument(int type, Object... args) {	
		Connection connect;
		PreparedStatement st;
		String typeNom = "";
		
		switch(type) {
		case(1): typeNom = "DVD"; break;
		case(2): typeNom = "CD"; break;
		case(3): typeNom = "Livre"; break;
		}
		
		for(int i = 0; i < args.length; i++){
			System.out.println(args[i]);
			}
		
		
		try {
			connect = DataManage.connexion();
			
			st = connect.prepareStatement("INSERT INTO document(TypeDoc, TitreDoc, AuteurDoc, Emprunt, Adulte) VALUES (?, ?, ?, 0, ?);");
			st.setString(1, typeNom);
			st.setString(2, (String) args[0]);
			st.setString(3, (String) args[1]);
			st.setBoolean(4, (boolean) args[2]);

	        st.executeUpdate();
			
	        st.close();
			connect.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution de la requete " + e);
		} 
	}
	
	@Override
	//consulter tous les documents
	public List<Document> consulterDocuments() {
		List<Document> listeDocs = new ArrayList<>();
		Connection connect;
		Statement st; 
		
		try {
			connect = DataManage.connexion();
			String allDocuments = "SELECT * FROM document"; 
			st = connect.createStatement();
			ResultSet docs= st.executeQuery(allDocuments);
			
			while(docs.next()) {
				int idDoc = docs.getInt("IdDoc");
				String typeDoc = docs.getString("TypeDoc");
				String titreDoc = docs.getString("TitreDoc");
				String auteurDoc = docs.getString("AuteurDoc");
				Boolean emprunt = docs.getBoolean("Emprunt");
				Boolean adulte = docs.getBoolean("Adulte");
				
				listeDocs.add(new DocumentsMediatek(idDoc, typeDoc, titreDoc, auteurDoc, emprunt, adulte ));
			}
			
			st.close();
			connect.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution : " + e);
		}
		return listeDocs;

	}
	
	@Override
	//Liste des documents empruntés par un utilisateur
	public List<Document> consulterDocumentsEmprunt(String pseudo){
		List<Document> listeDocsEmprunt = new ArrayList<>();
		Connection connect;
		PreparedStatement st; 
		
		try {
			connect = DataManage.connexion();
			
			String allDocuments = "SELECT d.IdDoc, d.TypeDoc, d.TitreDoc, d.AuteurDoc, d.Emprunt, d.Adulte FROM emprunt e,user u, document d WHERE e.IdUser = u.IdUser AND e.IdDoc = d.IdDoc AND u.Pseudo=?;";

			st = connect.prepareStatement(allDocuments);
			st.setString(1, pseudo);
			
			ResultSet docs = st.executeQuery();
			
			while(docs.next()) {
				Integer idDoc = docs.getInt("IdDoc");
				String typeDoc = docs.getString("TypeDoc");
				String titreDoc = docs.getString("TitreDoc");
				String auteurDoc = docs.getString("AuteurDoc");
				Boolean emprunt = docs.getBoolean("Emprunt");
				Boolean adulte = docs.getBoolean("Adulte");
				
				listeDocsEmprunt.add(new DocumentsMediatek(idDoc, typeDoc, titreDoc, auteurDoc, emprunt, adulte ));
			}
			
			st.close();
			connect.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erreur lors de l'execution : " + e);
		} 
		
		return listeDocsEmprunt;
	}	
}

