package services;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
                .GET()
                .build();

        HttpResponse<String> response;
		try {
			response = client.send(request,
			        HttpResponse.BodyHandlers.ofString());
			System.out.println(response);
	        return response.body();

		} catch (IOException e) {e.printStackTrace();} catch (InterruptedException e) {e.printStackTrace();}

		return "";
		
		
	}
	
	public static JSONObject getDate() {
		
		Config config = new Config();
		String ip = config.getIP();

		
		
		String response = get(ip + "api/date/today");
		
        try {
          	 return (JSONObject) (new JSONParser().parse(response));
   		} catch (ParseException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}

   		 	
   		return new JSONObject();	
   			
		
	}
	
	public static JSONObject login(String username, String password) throws IOException, InterruptedException {
		
		Config config = new Config();
		String baseUrl = config.getIP();
		JSONObject authResponse = new JSONObject();
		JSONObject authData = new JSONObject();
		authData.put("name", username);
		authData.put("password", password);
		String ip = config.getIP();
		System.out.println(authData);
		
		
		String response = post(ip+"api/auth/login/checador", authData);
		 
        try {
       	 return (JSONObject) (new JSONParser().parse(response));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		 	
		return new JSONObject();	
		
		
	}
	
	private static String get(String ip) {
		
		OkHttpClient client = new OkHttpClient();
	  Request request = new Request.Builder()
	      .url(ip)
	      .get()
	      .build();
	  try (Response response = client.newCall(request).execute()) {
	     return(response.body().string());
	  }
	  catch (IOException e) {
			e.printStackTrace();
		}
	  return null;

	}
	
	private static String post( String ip, JSONObject json ) {
		OkHttpClient client = new OkHttpClient();
	
	  RequestBody body = RequestBody.create(MediaType.get("application/json; charset=utf-8"),  json.toString());
	  Request request = new Request.Builder()
	      .url(ip)
	      .post(body)
	      .build();
	  try (Response response = client.newCall(request).execute()) {
	     return(response.body().string());
	  }
	  catch (IOException e) {
			e.printStackTrace();
		}
	  return null;

	}
	
	
}
