package interfaces;

import views.Display;

public class ShowError implements Runnable{

	Display display;
	
	public  ShowError(Display display) {
		this.display = display;
	}

	
	@Override
	public void run() {
		display.setError();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {e.printStackTrace();}
		
		display.resetStudent();
	}
	
	
	
	
}
