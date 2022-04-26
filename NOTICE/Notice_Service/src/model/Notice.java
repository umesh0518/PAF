package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Notice {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, noticename, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/new","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertNotice(String Notice_Title, String Notice_Time, String Notice_Date, String Area, String Description) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into notice(`Notice_ID`,`Notice_Title`,`Notice_Time`,`Notice_Date`,`Area`,`Description`)" + " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, Notice_Title);
			preparedStmt.setString(3, Notice_Time);
			preparedStmt.setString(4, Notice_Date);
			preparedStmt.setString(5, Area);
			preparedStmt.setString(6, Description);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Notice.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readNotice() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Notice ID</th><th>Notice Title</th><th>Notice Time</th><th>Notice Date</th><th>Area</th><th>Description</th></tr>";
			String query = "select * from notice";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String Notice_ID = Integer.toString(rs.getInt("Notice_ID"));
				String Notice_Title = rs.getString("Notice_Title");
				String Notice_Time = rs.getString("Notice_Time");
				String Notice_Date = rs.getString("Notice_Date");
				String Area = rs.getString("Area");
				String Description = rs.getString("Description");

				output += "<tr><td>" + Notice_ID + "</td>";
				output += "<td>" + Notice_Title + "</td>";
				output += "<td>" + Notice_Time + "</td>";
				output += "<td>" + Notice_Date + "</td>";
				output += "<td>" + Area + "</td>";
				output += "<td>" + Description + "</td>";
			}
			con.close();

			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Notice.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateNotice(String Notice_ID, String Notice_Title, String Notice_Time, String Notice_Date, String Area, String Description) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE notice SET Notice_Title=?,Notice_Time=?,Notice_Date=?,Area=?,Description=? WHERE Notice_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, Notice_Title);
			preparedStmt.setString(2, Notice_Time);
			preparedStmt.setString(3, Notice_Date);
			preparedStmt.setString(4, Area);
			preparedStmt.setString(5, Description);
			preparedStmt.setInt(6, Integer.parseInt(Notice_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Notice.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteNotice(String Notice_ID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from notice where Notice_ID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(Notice_ID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Notice.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
