package services;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controllers.Config;

public class HttpService {
	
	
	
	public static String getArticles() {
		
		Config config = new Config();
		String baseUrl = config.getIP();


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "api/articles/all"))
                .build();

        HttpResponse<String> response;
		try {
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			
	        return response.body();

		} catch (IOException e) {e.printStackTrace();} catch (InterruptedException e) {e.printStackTrace();}

		return "";
		
		
	}
	
	public static String getDate() {
		
		Config config = new Config();
		String baseUrl = config.getIP();


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "api/date/today"))
                .build();

        HttpResponse<String> response;
		try {
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			
			
	        return response.body();

		} catch (IOException e) {e.printStackTrace();} catch (InterruptedException e) {e.printStackTrace();}

		return "";
		
		
	}
	
	public static JSONObject login(String username, String password) {
		
		Config config = new Config();
		String baseUrl = config.getIP();
		JSONObject authResponse = new JSONObject();
		JSONObject authData = new JSONObject();
		authData.put("name", username);
		authData.put("password", password);
		

		System.out.println(authData.toJSONString());	
		
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tucanes-sv.herokuapp.com/api/auth/login/checador"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(authData.toString()))
                .build();

        HttpResponse<String> response;
		try {
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			
			
	         System.out.println(response.body());
	         
	         try {
	        	 authResponse = (JSONObject) (new JSONParser().parse(response.body()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {e.printStackTrace();} catch (InterruptedException e) {e.printStackTrace();}
			
		return authResponse;	
		
		
	}
	

}
