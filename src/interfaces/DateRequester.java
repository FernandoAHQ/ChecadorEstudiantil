package interfaces;

import java.time.LocalTime;

import services.Utilities;
import views.Display;

public class DateRequester implements Runnable{

	String time = "";
	Display display;
	
	public DateRequester(Display display){
		this.display = display;
	}
	
	@Override
	public void run() {
 		while(true) {
		
			try {
			 	String date = Utilities.getDate();
				display.DisplayDate(date);
				System.out.println("Displaying Date: " + date);
				Thread.sleep(3600000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	
	
}
