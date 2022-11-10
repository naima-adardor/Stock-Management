package ecommerce;

public class CATEGORIE {

	private int id;
	private String nom;
	public CATEGORIE(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	public CATEGORIE(String nom) {
		this.nom=nom;
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
	

}
