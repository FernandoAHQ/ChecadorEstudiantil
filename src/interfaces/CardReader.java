package interfaces;
import controllers.SocketClient;
import views.Display;
import java.awt.event.*;

public class CardReader implements Runnable, KeyListener{
	
	Display display;
	SocketClient socket;
	String code = "";
	String id;
	public CardReader(Display display, SocketClient socket, String id){
		this.display = display;
		this.socket= socket;
		this.id = id;
		display.addKeyListener(this);
	}

	@Override
	public void run() {
		
		while(true) {
		//	code = "";
			
			
			try {	Thread.sleep(10000);	} catch (InterruptedException e) {e.printStackTrace();}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		char c = e.getKeyChar();
	

		if(c == '\n') {
			String completeCode = code;
			System.out.println(code);
			code = "";
			socket.validateCard(id, completeCode);
		}else {
			code+=c;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
}
