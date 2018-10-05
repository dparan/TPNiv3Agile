package data_model;

import java.math.BigInteger;

public class Personne {

	private int id;
	private String prenom;
	private String nom;
	private String adresse;
	private BigInteger noTelephone;
	
	Personne(int id, String prenom, String nom, String adresse, BigInteger noTelephone) {
		super();
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.adresse = adresse;
		this.noTelephone = noTelephone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public BigInteger getNoTelephone() {
		return noTelephone;
	}

	public void setNoTelephone(BigInteger noTelephone) {
		this.noTelephone = noTelephone;
	}
}
