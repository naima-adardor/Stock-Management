package ecommerce;

import java.sql.Date;

import javafx.scene.control.Button;

public class RECLAMATION{
	private int id;
	private String email;
	private String message;

	
	public RECLAMATION(int id, String email,  String message) {
		super();
		this.id = id;
		this.email = email;
		
		this.message = message;
		
	}
	
	public RECLAMATION(String email,String message) {
		super();
		this.email = email;
		this.message = message;
		
	}

	



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
