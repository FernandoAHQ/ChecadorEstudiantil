package controllers;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;
import models.Student;
import views.Display;

import java.util.Arrays;

import org.json.JSONObject;

//import org.json.simple.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;


public class SocketClient {
	

	Socket socket;
	Display display;
	

	SocketClient(String ip, String token, Display display){
		this.display = display;
		
		
		//new JSONObject();
		//JSONObject query = new JSONObject();
		//query.put("accessToken", "Bearer " + token);
		//System.out.println(query);
		//query.keySet();
		
		IO.Options options = IO.Options.builder()
			    .setTransports(new String[] { WebSocket.NAME })
			    .setForceNew(true)
				.setQuery("accessToken=Bearer " + token)
				.setSecure(true)
				.setReconnection(true)
			//	.setTransports(WebSocket.NAME)
	            .build();
		
		
		URI uri = URI.create(ip);


		socket = IO.socket(uri, options);
		
		
		System.out.println("SOCKET CREATED - ");
		
		socket.on("connect",  new Emitter.Listener() {
		    @Override
		    public void call(Object... args) {
		    	System.out.println("CONNECTED");
		    }
		});
		
		socket.on("resp-validar", new Emitter.Listener() {
		    @Override
		    public void call(Object... args) {
		        // ...
		    	System.out.println("NEW ENTRY");
		    	System.out.println("ARGS: " + args[0].toString());
		    	
		    	JSONObject resp = (JSONObject) args[0];
		    	
		    	if((Boolean)resp.get("status") == true) {
		    		if((JSONObject)resp.get("user")!=null) {
			    		JSONObject user = resp.getJSONObject("user");
				    	display.displayStudent(new Student((String)user.get("image"), (String)user.get("f_name"), (String)user.get("l_name"), (String)user.get("No_control")));		    		
			    	}
			    	
			    	display.updateCode(resp.getString("new_value"));
		    	}
		    }
		});

		socket.on(("connect_error"), new Emitter.Listener() {
		    @Override
		    public void call(Object... args) {
		        // ...
		    	System.out.println();
		    }
		});
		
		
		socket.on(("disconnect"), new Emitter.Listener() {
		    @Override
		    public void call(Object... args) {
		        // ...
		    	System.out.println("DISCONNECT");
		    }
		});
		
		
	}

	public void connect() {
		socket.connect();
	}
	
	
	
}

