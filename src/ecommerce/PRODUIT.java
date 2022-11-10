package ecommerce;

import java.sql.Blob;
public class PRODUIT {
	private  int Id;
	private String Designation;
	private String Description;
	private int Quantite;
	private Float prix;
	private Float oldprice;
	private String promotion;
	private String Size;
	private String categorie;
	private String Subcategorie;
	private String Brand;
	private String color;
	private Blob image1;
	private Blob image2;
	private Blob image3;
	
 public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

public PRODUIT(String designation, int quantite, Float prix, String categorie) {
		super();
		Designation = designation;
		Quantite = quantite;
		this.prix = prix;
		this.categorie = categorie;
	}
 
	public PRODUIT(String designation, String description, int quantite, Float prix, Float oldprice, String promotion,
		String size, String categorie, String subcategorie, String brand,String color) {
	super();
	Designation = designation;
	Description = description;
	Quantite = quantite;
	this.prix = prix;
	this.oldprice = oldprice;
	this.promotion = promotion;
	Size = size;
	this.categorie = categorie;
	Subcategorie = subcategorie;
	Brand = brand;
	this.color = color;
}

	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getDesignation() {
		return Designation;
	}
	public void setDesignation(String designation){
		Designation = designation;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description){
		Description = description;
	}
	public int getQuantite() {
		return Quantite;
	}
	public void setQuantite(int quantite) {
		Quantite = quantite;
	}
	public Float getPrix() {
		return prix;
	}
	public void setPrix(Float prix) {
		this.prix = prix;
	}
	public Float getOldprice() {
		return oldprice;
	}
	public void setOldprice(Float oldprice) {
		this.oldprice = oldprice;
	}
	
	public String getSize() {
		return Size;
	}
	public void setSize(String size) {
		Size = size;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	public String getSubcategorie() {
		return Subcategorie;
	}
	public void setSubcategorie(String subcategorie) {
		Subcategorie = subcategorie;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public Blob getImage1() {
		return image1;
	}
	public void setImage1(Blob image1) {
		this.image1 = image1;
	}
	public Blob getImage2() {
		return image2;
	}
	public void setImage2(Blob image2) {
		this.image2 = image2;
	}
	public Blob getImage3() {
		return image3;
	}
	public void setImage3(Blob image3) {
		this.image3 = image3;
	}
	
}
