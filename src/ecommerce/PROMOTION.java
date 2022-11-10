package ecommerce;

import java.sql.Date;

public class PROMOTION  {
	
	private int id;
	private String nom;
	private Date dateD;
	private Date dateF;
	private  float discount;
	
	
	public PROMOTION(int id) {
		super();
		this.id = id;
	}
	
	
	public PROMOTION(String nom, float discount) {
		super();
		this.nom = nom;
		this.discount = discount;
	}


	public PROMOTION(String nom, float remise, Date D, Date  F) {
		super();
		this.nom = nom;
		this. dateD=D;
		this.dateF = F;
		this.discount =  remise;
	}
	


	public PROMOTION(int id, String nom, Date dateD, Date dateF, float discount) {
		super();
		this.id = id;
		this.nom = nom;
		this.dateD = dateD;
		this.dateF = dateF;
		this.discount = discount;
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
	public Date getDateD() {
		return dateD;
	}
	public void setDateD(Date dateD) {
		this.dateD = dateD;
	}
	public Date getDateF() {
		return dateF;
	}
	public void setDateF(Date dateF) {
		this.dateF = dateF;
	}
	public float getDiscount() {
		return discount;
	}
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	
}
