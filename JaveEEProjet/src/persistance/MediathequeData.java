package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import utilisateur.UtilisateurMediatek;

import document.Documents;
import mediatek2022.*;

// classe mono-instance  dont l'unique instance est connue de la m�diatheque
// via une auto-d�claration dans son bloc static

public class MediathequeData implements PersistentMediatheque {

	static {
		Mediatheque.getInstance().setData(new MediathequeData());

	}

	private MediathequeData() {
	}

	// renvoie la liste de tous les documents disponibles de la m�diath�que
	@Override
	public List<Document> tousLesDocumentsDisponibles() {
		return null;
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public UtilisateurMediatek getUser(String login, String password) {
		String url = "jdbc:mysql://localhost:3306/mediatek";
		String user = "root";
		String motdepasse = "";
		UtilisateurMediatek utilisateur = null;
		
		try {
			Connection connect = connexion(url, user, motdepasse);

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

	// va r�cup�rer le document de numéro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
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
	
	public static Connection connexion (String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connect;
		connect = DriverManager.getConnection(url, user, password);
		
		return connect; 
	}
	
	

	public static ResultSet consulterDocuments(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Connection connect = connexion(url, user, password); 
		
		String allDocuments = "SELECT * FROM document"; 
		Statement st = connect.createStatement();
		ResultSet documents = st.executeQuery(allDocuments);
		
		return documents;
	}

}

