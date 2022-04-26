package com;

import model.Payment;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PayService {
	Payment PayObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCard() {
		return PayObj.readCard();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCard(
			@FormParam("Card_Holder") String Card_Holder,
			@FormParam("Card_Number") String Card_Number,
			@FormParam("Expire_Date") String Expire_Date,
			@FormParam("CVV") String CVV) {
		String output = PayObj.insertCard(Card_Holder, Card_Number, Expire_Date, CVV);
		return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)

	public String updateCard(String CardData) {
		
		JsonObject PaymentObject = new JsonParser().parse(CardData).getAsJsonObject();
		
		String CardID = PaymentObject.get("CardID").getAsString();
		String Card_Holder = PaymentObject.get("Card_Holder").getAsString();
		String Card_Number = PaymentObject.get("Card_Number").getAsString();
		String Expire_Date = PaymentObject.get("Expire_Date").getAsString();
		String CVV = PaymentObject.get("CVV").getAsString();
		
		String output = PayObj.updateCard(CardID, Card_Holder, Card_Number, Expire_Date, CVV);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCard(String CardData) {
		
		Document doc = Jsoup.parse(CardData, "", Parser.xmlParser());

		String CardID = doc.select("CardID").text();
		String output = PayObj.deleteCard(CardID);
		return output;
		
	}
}
