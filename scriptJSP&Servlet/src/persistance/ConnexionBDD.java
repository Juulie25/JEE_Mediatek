package persistance;


import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import document.Documents;

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
	
	public static ArrayList<Documents> listeDocuments(ResultSet docs) throws SQLException {
		ArrayList<Documents> listeDocs = new ArrayList<>();
		while(docs.next()) {
			String typeDoc = docs.getString("TypeDoc");
			String titreDoc = docs.getString("TitreDoc");
			String auteurDoc = docs.getString("AuteurDoc");
			Boolean emprunt = docs.getBoolean("Emprunt");
			
			listeDocs.add(new Documents(typeDoc, titreDoc, auteurDoc,emprunt));
		}
		return listeDocs;
	}
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		String url = "jdbc:mysql://localhost:3306/mediatek";
		String user = "root";
		String password = "";
		
		ResultSet docs  = consulterDocuments(url, user, password); 
		System.out.println(listeDocuments(docs));
	}

}
