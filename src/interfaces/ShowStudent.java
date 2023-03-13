package interfaces;

import models.Student;
import views.Display;

public class ShowStudent implements Runnable{

	Display display;
	Student student;
	
	public  ShowStudent(Display display, Student student) {
		this.display = display;
		this.student = student;
	}

	
	@Override
	public void run() {
		display.setStudent(student.getImage(), 
						   student.getName(), 
						   student.getLastName(),
						   student.getControlNumber()
						   );
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {e.printStackTrace();}
		
		display.resetStudent();
	}
	
	
	
	
}
