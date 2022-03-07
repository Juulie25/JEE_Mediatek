package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataManage {
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
