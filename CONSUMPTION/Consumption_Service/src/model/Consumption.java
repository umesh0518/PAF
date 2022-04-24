package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Consumption {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertConsumption(String Account_No, String C_Reading, String P_Reading, String Bill) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into consumption(`Consumption_ID`,`Account_No`,`C_Reading`,`P_Reading`,`Bill`)" + " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Account_No);
			preparedStmt.setString(3, C_Reading);
			preparedStmt.setString(4, P_Reading);
			preparedStmt.setString(5, Bill);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Consumption.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readConsumption() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Consumption ID</th><th>Customer Name</th><th>C_Reading</th><th>P_Reading</th><th>Bill</th></tr>";
			String query = "select * from consumption";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Consumption_ID = Integer.toString(rs.getInt("Consumption_ID"));
				String Account_No = rs.getString("Account_No");
				String C_Reading = rs.getString("C_Reading");
				String P_Reading = rs.getString("P_Reading");
				String Bill = rs.getString("Bill");

				output += "<tr><td>" + Consumption_ID + "</td>";
				output += "<td>" + Account_No + "</td>";
				output += "<td>" + C_Reading + "</td>";
				output += "<td>" + P_Reading + "</td>";
				output += "<td>" + Bill + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Consumption.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateConsumption(String Consumption_ID, String Account_No, String C_Reading, String P_Reading, String Bill) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE consumption SET Account_No=?,C_Reading=?,P_Reading=?,Bill=? WHERE Consumption_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Account_No);
			preparedStmt.setString(2, C_Reading);
			preparedStmt.setString(3, P_Reading);
			preparedStmt.setString(4, Bill);
			preparedStmt.setInt(5, Integer.parseInt(Consumption_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Consumption.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteConsumption(String Consumption_ID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from consumption where Consumption_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Consumption_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Consumption.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
