/**
 * 
 */
// =======================hiding the message div by default======================

$(document).ready(function()
{
	if ($("#alertSuccess").text().trim() == "")
 	{
 		$("#alertSuccess").hide();
 	}
 	$("#alertError").hide();
 	
});

// ==============CLIENT- model validation ===================
function consumptionFormValidation()
{
	
		// account number null validation
		if ($("#Account_No").val().trim() == "")
 		{
			return "Insert Account Number.";
 		}
 		
		// Reading number null validation
		if ($("#Reading").val().trim() == "")
 		{
 			return "Insert the reading .";
 		}
 		
		// Date number null validation
		if ($("#Date").val().trim() == "")
 		{
 			return "Insert the date.";
 		}

		return true;
}