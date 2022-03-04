package persistance;


import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnexionBDD {

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
	
	public static void afficherDocuments(ResultSet docs) throws SQLException {
		while(docs.next()) {
			String typeDoc = docs.getString("TypeDoc");
			String titreDoc = docs.getString("TitreDoc");
			String auteurDoc = docs.getString("AuteurDoc");
			Boolean emprunt = docs.getBoolean("Emprunt");
			
			System.out.println("Type : " + typeDoc + " - Titre : " + titreDoc + ", Auteur : " + auteurDoc + " (Etat : " + emprunt + ")");
		}
	}

}
