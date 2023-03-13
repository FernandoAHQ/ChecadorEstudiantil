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
	String entryPointName;

	SocketClient(String ip, String token, Display display, int i){
		this.display = display;
		this.entryPointName = new Config().getName(i);
	 
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
		     	
		    	JSONObject resp = (JSONObject) args[0];
		    	display.updateCode(resp.getString("new_value"));
		    	
		    	System.out.println("SOCKET ANSWER");
		    	System.out.println(resp);

		    	if((Boolean)resp.get("status") == true) {
		    		System.out.println("ACCESS GRANTED");
		    		if((JSONObject)resp.get("user")!=null) {
			    		JSONObject user = resp.getJSONObject("user");
				   
			    		 	display.displayStudent(new Student((String)user.get("image"), (String)user.get("names"),  user.getString("lastNameFather") + " " + user.getString("lastNameMother"), (String)user.get("controlNo")));		    		
			    	}
			    	
		    	}else {
		    		display.displayError();
		    	}
		    }
		});
		
		
		socket.on("resp-qr", new Emitter.Listener() {
		    @Override
		    public void call(Object... args) {
		        // ...
				System.out.println("ENTERING NEW RESP QR");
		    	System.out.println("ARGS: " + args[0].toString());
		    	
		    	JSONObject resp = (JSONObject) args[0];
		    	display.updateCode(resp.getString("new_value"));

		    	 
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
	
	public void requestRenewal() {
		System.out.println("REQUESTING RENEWAL");
		socket.emit("qr-renewal", entryPointName);
	}
	
	
	public void validateCard(String id, String code) {
		System.out.println("SENDING CARD ID");
		JSONObject payload = new JSONObject();
		payload.put("card", code);
		payload.put("checador", id);
		socket.emit("validar-credencial", payload);
	}
	

	public void connect() {
		socket.connect();
	}
	
	
	
}

