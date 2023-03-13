package controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	Properties prop;
	String configFilePath = "src/config.properties";
	
	public Config() {
		
		try {
			FileInputStream propsInput = new FileInputStream(configFilePath);
			prop = new Properties();
			prop.load(propsInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getIP() {
		return prop.getProperty("server-ip");
	}
	
	public  String getToken() {
		return prop.getProperty("access-token");
	}
	
	public void setIP(String ip) {
	    try {
			prop.setProperty("server-ip", ip);
			FileOutputStream fos = new FileOutputStream(configFilePath);
			prop.store(fos, null);
		    fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	public String getID(int side) {
		String concat = "left";
		if(side == 1) concat = "right";
		return prop.getProperty("ep-id-"+concat);
	}
	
	public void setID(String id) {
	    try {
			prop.setProperty("ep-id", id);
			FileOutputStream fos = new FileOutputStream(configFilePath);
			prop.store(fos, null);
		    fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getName(int side) {
		String concat = "left";
		if(side == 1) concat = "right";
		return prop.getProperty("name-"+concat);
	}
	
	public void setName(String name) {
	    try {
			prop.setProperty("name", name);
			FileOutputStream fos = new FileOutputStream(configFilePath);
			prop.store(fos, null);
		    fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public String getPassword() {
		return prop.getProperty("password");
	}
	
	public void setPassword(String password) {
	    try {
			prop.setProperty("password", password);
			FileOutputStream fos = new FileOutputStream(configFilePath);
			prop.store(fos, null);
		    fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
