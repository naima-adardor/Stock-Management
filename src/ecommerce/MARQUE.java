package ecommerce;

import java.sql.Blob;

public class MARQUE {
	private int id;
	private String nom;
	
	private Blob image;
	public MARQUE(int id, String nom,  Blob image) {
		super();
		this.id = id;
		this.nom = nom;
		
		this.image = image;
	}
	
	public MARQUE(String nom, Blob image) {
		super();
		this.nom = nom;
		this.image = image;
	}

	public MARQUE(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}

	public MARQUE(String nom) {
		super();
		this.nom = nom;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	
	
}
