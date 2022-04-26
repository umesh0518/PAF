package com;

import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/User")
public class UserService {
	User UserObj = new User();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUser() {
		return UserObj.readUser();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUser(
			@FormParam("Username") String Username,
			@FormParam("Email") String Email,
			@FormParam("Phoneno") String Phoneno,
			@FormParam("Nic") String Nic) {
		String output = UserObj.insertUser(Username, Email, Phoneno, Nic);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateUser(String userData) {
		
		JsonObject UserObject = new JsonParser().parse(userData).getAsJsonObject();
		
		String Userid = UserObject.get("Userid").getAsString();
		String Username = UserObject.get("Username").getAsString();
		String Email = UserObject.get("Email").getAsString();
		String Phoneno = UserObject.get("Phoneno").getAsString();
		String Nic = UserObject.get("Nic").getAsString();
		
		String output = UserObj.updateUser(Userid, Username, Email, Phoneno, Nic);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData) {
		
		Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

		String Userid = doc.select("Userid").text();
		String output = UserObj.deleteUser(Userid);
		return output;
		
	}
}
