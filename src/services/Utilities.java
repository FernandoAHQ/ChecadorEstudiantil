package services;

import org.json.simple.JSONObject; 

public class Utilities {

	
	public static String getDate(){
		
		JSONObject date = HttpService.getDate();
		return (String) date.get("date");
	}
}
