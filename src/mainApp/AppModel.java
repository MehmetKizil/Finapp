package mainApp;

import java.sql.*;

import login.LoginModel;

public class AppModel {
	
	Connection c = null;
	
	// Speichert die eingegebenen Daten (Income und Outgoing) in der Datenbank
	public void addData(int UserNr, String description, String amountIncome, String amountOutgoing){
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			c = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Mehmet\\Documents\\Java Workspace\\finapp\\Finapp\\database.accdb");
			if (c != null) {
				Statement s = c.createStatement();
				String sql = "INSERT INTO userdata ( UserNr, description, amountIncome, amountOutgoing)"
						+ "VALUES (" + UserNr + ",'" + description + "'," + amountIncome + "," + amountOutgoing + ")";
				s.executeUpdate(sql);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public String [][] getDataFromDB(String amountType){
		String [][] data = null;
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
			c = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\Mehmet\\Documents\\Java Workspace\\finapp\\Finapp\\database.accdb");
			if (c != null) {
				Statement s = c.createStatement();
				String sql = "SELECT * FROM userdata WHERE " + amountType + " != 0 AND UserNr = " + LoginModel.currentUserID;
				String sql1 = "SELECT COUNT(*) FROM (" + sql + ")";
				ResultSet rs = s.executeQuery(sql);
				ResultSet rs1 = s.executeQuery(sql1);
				rs1.next();
				int rows = rs1.getInt("C1");
				data = new String[rows][2];
				for (int i = 0; rs.next(); i++) {
					data[i][0] = rs.getString("description");
					data[i][1] = Integer.toString(rs.getInt(amountType));
				}
			}
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
}
