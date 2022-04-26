package com;

import model.Notice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Notice")
public class NoticeService {
	Notice NoticeObj = new Notice();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readNotice() {
		return NoticeObj.readNotice();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertNotice(
			@FormParam("Notice_Title") String Notice_Title,
			@FormParam("Notice_Time") String Notice_Time,
			@FormParam("Notice_Date") String Notice_Date,
			@FormParam("Area") String Area,
			@FormParam("Description") String Description) {
		String output = NoticeObj.insertNotice(Notice_Title, Notice_Time, Notice_Date, Area, Description);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateNotice(String noticeData) {
		
		JsonObject NoticeObject = new JsonParser().parse(noticeData).getAsJsonObject();
		
		String Notice_ID = NoticeObject.get("Notice_ID").getAsString();
		String Notice_Title = NoticeObject.get("Notice_Title").getAsString();
		String Notice_Time = NoticeObject.get("Notice_Time").getAsString();
		String Notice_Date = NoticeObject.get("Notice_Date").getAsString();
		String Area = NoticeObject.get("Area").getAsString();
		String Description = NoticeObject.get("Description").getAsString();
		
		String output = NoticeObj.updateNotice(Notice_ID, Notice_Title, Notice_Time, Notice_Date, Area, Description);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteNotice(String noticeData) {
		
		Document doc = Jsoup.parse(noticeData, "", Parser.xmlParser());

		String Notice_ID = doc.select("Notice_ID").text();
		String output = NoticeObj.deleteNotice(Notice_ID);
		return output;
		
	}
}
