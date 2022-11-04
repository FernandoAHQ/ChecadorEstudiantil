package services;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utilities {

	
	public static String getDate(){
		
		try {
			JSONObject date = (JSONObject) (new JSONParser().parse(HttpService.getDate()));
			return (String) date.get("date");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return "";
	}
}
