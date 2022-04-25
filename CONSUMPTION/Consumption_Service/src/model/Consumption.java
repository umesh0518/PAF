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

	public String insertConsumption(String Account_No, String Reading, String Date) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into consumption(`Account_No`,`Reading`,`Date`)" + " values ( ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
//			preparedStmt.setInt(1, 0);
			preparedStmt.setString(1, Account_No);
			preparedStmt.setString(2, Reading);
			preparedStmt.setString(3, Date);
			

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
//			output = "Error while inserting the Consumption.";
			output = e.getMessage();
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
			output = "<table border=\"1\"><tr><th>Consumption ID</th><th>Customer Account ID </th><th>Reading</th><th>Date</th></tr>";
			String query = "select * from consumption";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Consumption_ID = Integer.toString(rs.getInt("Consumption_ID"));
				String Account_No = rs.getString("Account_No");
				String Reading = rs.getString("Reading");
				String Date = rs.getString("Date");
				

				output += "<tr><td>" + Consumption_ID + "</td>";
				output += "<td>" + Account_No + "</td>";
				output += "<td>" + Reading + "</td>";
				output += "<td>" + Date + "</td>";
				
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Consumption.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateConsumption(String Consumption_ID, String Account_No, String Reading, String Date) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE consumption SET Account_No=?,Reading=?,Date=? WHERE Consumption_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Account_No);
			preparedStmt.setString(2, Reading);
			preparedStmt.setString(3, Date);
			preparedStmt.setInt(4, Integer.parseInt(Consumption_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Consumption." + e.getMessage();
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
