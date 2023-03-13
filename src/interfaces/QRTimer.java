package interfaces;
import controllers.SocketClient;
import views.Display;

public class QRTimer implements Runnable{
	
	int seconds;
	SocketClient socket;
	
	public QRTimer(int seconds, SocketClient socket) {
		this.seconds = seconds;
		this.socket= socket;
	}

	@Override
	public void run() {
		
		while(true) {
			
			//display.updateCode(java.time.LocalDateTime.now().toString());
			socket.requestRenewal();
			
			try {	Thread.sleep(seconds * 1000);	} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}

	
	
}
