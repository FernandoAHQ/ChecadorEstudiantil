package controllers;

import java.io.IOException;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import interfaces.CardReader;
import interfaces.Clock;
import interfaces.DateRequester;
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
	
	public MainLogic(int side) {
		
		config = new Config();
	
		
  		try {
			data = HttpService.login(config.getName(side), config.getPassword());
			System.out.println(data);
  		
			accessToken = (String) data.get("accessToken");
			code = (String) ((JSONObject)data.get("checador")).get("value");
 	 		id = (String) ((JSONObject)data.get("checador")).get("_id");
 			System.out.println(accessToken);
 		
  		} catch (IOException | InterruptedException e) {
			System.out.println(e);
			
		}	
 		
	
  		
  		mainDisplay = new Display(id, side);

  		
		socket = new SocketClient(config.getIP(), accessToken, mainDisplay, side);
			socket.connect();
		
	
  		
 	 	
		mainDisplay.updateCode(code);
	
		
			
		
		//mainDisplay.displayStudent(new Student(null, null, null, null));
		
		//mainDisplay.setVisible(false);
		
	
		Thread clock = new Thread(new Clock(mainDisplay));
		clock.start();
		
		Thread dateTracker = new Thread(new DateRequester(mainDisplay));
		dateTracker.start();
		Thread qrGenerator = new Thread(new QRTimer(15, socket));
		qrGenerator.start();
		
		Thread cardReader = new Thread(new CardReader(mainDisplay, socket, id));
		cardReader.start();
		
	}
	
	

}
