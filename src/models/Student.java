package models;

public class Student {
	
	String image, firstName, lastName, controlNumber;
	
	public Student(String img, String f, String l, String c) {
		this.image = img;
		this.firstName = f;
		this.lastName = l;
		this.controlNumber = c;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}
	
	
	
	
}
