package data_model;

public class Avion {

	private int id;
	private String nom;
	private int capacite;
	private int typeAvion;
	
	public Avion(int id, String nom, int capacite, int typeAvion) {
		super();
		this.id = id;
		this.nom = nom;
		this.capacite = capacite;
		this.typeAvion = typeAvion;
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
	
	public int getCapacite() {
		return capacite;
	}
	
	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}
	
	public int getTypeAvion() {
		return typeAvion;
	}
	
	public void setTypeAvion(int typeAvion) {
		this.typeAvion = typeAvion;
	}
	
}
