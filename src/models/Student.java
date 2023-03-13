package models;

public class Student {
	
	String image, name, controlNumber, lastName;
	
	public Student(String img, String f, String l, String c) {
		this.image = img;
		this.name = f;
		this.controlNumber = c;
		this.lastName = l;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
 
	public String getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}
	
	
	
	
}
