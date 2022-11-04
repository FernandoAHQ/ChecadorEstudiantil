package controllers;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import interfaces.Clock;
import interfaces.QRTimer;
import interfaces.ShowStudent;
import models.Student;
import services.HttpService;
import services.Utilities;
import views.Display;

public class MainLogic {
	
	Config  config;
	Display mainDisplay;
	SocketClient socket;
	
	String accessToken = "";
	String code = "_";
	String id = "_";
	
	JSONObject data = new JSONObject();
	
	public MainLogic() {
		
		config = new Config();
		
 		System.out.println(config.getIP());
 		data = HttpService.login(config.getName(), config.getPassword());	
 		
 		if((JSONObject)data.get("checador") != null) {
 			accessToken = (String) data.get("accessToken");
 	 		code = (String) ((JSONObject)data.get("checador")).get("value");
 	 		id = (String) ((JSONObject)data.get("checador")).get("_id");
 			System.out.println(accessToken);
 				
 		}
 		
		mainDisplay = new Display(id);
		mainDisplay.updateCode(code);
	
		socket = new SocketClient(config.getIP(), accessToken, mainDisplay);
		socket.connect();
		
			
		
		//mainDisplay.displayStudent(new Student(null, null, null, null));
		
		//mainDisplay.setVisible(false);
		
	
		Thread clock = new Thread(new Clock(mainDisplay));
		clock.start();
		
		//Thread qrGenerator = new Thread(new QRTimer(5, mainDisplay));
		//qrGenerator.start();
	
	}
	
	

}
