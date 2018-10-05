package data_model;

public class Troncon {

	private int id;
	private String villeDepart;
	private String villeArrivee;
	private int distance;
	
	Troncon(int id, String villeDepart, String villeArrivee, int distance) {
		super();
		this.id = id;
		this.villeDepart = villeDepart;
		this.villeArrivee = villeArrivee;
		this.distance = distance;
	}
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVilleDepart() {
		return villeDepart;
	}

	public void setVilleDepart(String villeDepart) {
		this.villeDepart = villeDepart;
	}

	public String getVilleArrivee() {
		return villeArrivee;
	}

	public void setVilleArrivee(String villeArrivee) {
		this.villeArrivee = villeArrivee;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

}
