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
					if (username.equals(rs.getString("Username")) && password.equals(getDecryptedPassword(rs.getString("Password")))) {
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
		String cryptedPassword = getCryptedPassword(password);
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			c = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Mehmet\\Documents\\Java Workspace\\finapp\\Finapp\\database.accdb");
			if (c != null) {
				Statement s = c.createStatement();
				String sql = "INSERT INTO User [(UserNr, Username, Password)] VALUES (1,'" + username + "','" + cryptedPassword + "')";
				s.executeUpdate(sql);
				c.close();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// verschlüsselt das Password und gibt es aus
	public static String getCryptedPassword (String password) {
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String cryptedPassword = new String();
		int [] passwordIndexArray = new int[password.length()];
		for (int j = 0; j < password.length(); j++) {
			for (int i = 0; i < alphabet.length(); i++) {
				if (password.charAt(j) == alphabet.charAt(i)) {
					passwordIndexArray[j] = i;
				}
			}
		}
		for (int k = 0; k < password.length(); k++) {
			int x = (3 * passwordIndexArray[k] + 2)% 26;
			cryptedPassword = cryptedPassword + alphabet.charAt(x);
		}
		return cryptedPassword;
	}
	
	// entschlüsselt das Password und gibt es aus
	public static String getDecryptedPassword(String cryptedPassword) {
		String key = "cfiloruxadgjmpsvybehknqtwz";
		String alphabet = "abcdefghijklmnopqrstuvwxyz";
		String decryptedPassword = new String();
		int [] cryptedPasswordIndexArray = new int[cryptedPassword.length()];
		for (int j = 0; j < cryptedPassword.length(); j++) {
			for (int i = 0; i < key.length(); i++) {
				if (cryptedPassword.charAt(j) == key.charAt(i)) {
					cryptedPasswordIndexArray[j] = i;
					decryptedPassword = decryptedPassword + alphabet.charAt(cryptedPasswordIndexArray[j]);
				}
			}
		}
		return decryptedPassword;
	}
	
}