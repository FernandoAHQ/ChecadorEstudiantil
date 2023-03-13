package interfaces;

import java.time.LocalTime;

import views.Display;

public class Clock implements Runnable{

	String time = "";
	Display display;
	
	public Clock(Display display){
		this.display = display;
	}
	
	@Override
	public void run() {
 		while(true) {
		
			try {
	 			
				//":" + String.format("%02d", java.time.LocalTime.now().getSecond()) + 
				if(java.time.LocalTime.now().getHour() > 12)
					time = String.format("%02d", java.time.LocalTime.now().getHour()-12) + ":" + String.format("%02d", java.time.LocalTime.now().getMinute()) + " PM";
				else
					time = String.format("%02d", java.time.LocalTime.now().getHour()) + ":" + String.format("%02d", java.time.LocalTime.now().getMinute()) + "AM";
				
				display.setTime(time);
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	
	
}
