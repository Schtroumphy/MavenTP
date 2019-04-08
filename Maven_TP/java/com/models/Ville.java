package com.models;

public class Ville {
	String code, code_postal;
	String nom, libelle, ligne_5;
	String latitude, longitude;
	
	public Ville() {
		
	}
	
	public Ville(String code, String nom, String code_postal,String libelle, String ligne_5, String latitude,
			String longitude) {
		super();
		this.code = code;
		this.code_postal = code_postal;
		this.nom = nom;
		this.libelle = libelle;
		this.ligne_5 = ligne_5;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	
	public Ville(String code, String nom,  String code_postal) {
		super();
		this.code = code;
		this.code_postal = code_postal;
		this.nom = nom;
	}


	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode_postal() {
		return code_postal;
	}
	public void setCode_postal(String code_postal) {
		this.code_postal = code_postal;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public String getLigne_5() {
		return ligne_5;
	}
	public void setLigne_5(String ligne_5) {
		this.ligne_5 = ligne_5;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	
}
