package login;

import java.sql.*;

public class LoginModel {
	
	public static String currentUser;
	public static int currentUserID;
	Connection c = null;
	
	// Verifiziert Username und Passwort Eingabe des Benutzers und gibt true raus,
	// falls die richtige Kombination in der Datenbank vorhanden ist
	public boolean verifyUsernamePassword (String username, String password) {
		boolean flag = false;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			c = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Mehmet\\Documents\\Java Workspace\\finapp\\Finapp\\database.accdb");			
			if (c != null) {
				Statement s = c.createStatement();
				String sql = "SELECT * FROM User";
				ResultSet rs = s.executeQuery(sql);
				while (rs.next()) {
					if (username.equals(rs.getString("Username")) && password.equals(rs.getString("Password"))) {
						flag = true;
						currentUser = rs.getString("Username");
						currentUserID = rs.getInt("UserNr");
						break;
					}else {
						flag = false;
					}
				}
			}
			c.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	// Speichert die Login Daten eines Users in der Datenbank
	public void insertUser(String username, String password) {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			c = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Mehmet\\Documents\\Java Workspace\\finapp\\Finapp\\database.accdb");
			if (c != null) {
				Statement s = c.createStatement();
				String sql = "INSERT INTO User [(UserNr, Username, Password)] VALUES (1,'" + username + "','" + password + "')";
				s.executeUpdate(sql);
				c.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
}
}