package interfaces;
import views.Display;

public class QRTimer implements Runnable{
	
	int seconds;
	Display display;
	
	public QRTimer(int seconds, Display display) {
		this.seconds = seconds;
		this.display = display;
	}

	@Override
	public void run() {
		
		while(true) {
			
		//	display.updateCode(java.time.LocalDateTime.now().toString());
			
			try {	Thread.sleep(seconds * 1000);	} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}

	
	
}
