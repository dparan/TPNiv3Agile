package data_model;

public class DepartAvion {
	
	private int depart;
	private int avion;
	private int qteCarburant;
	
	DepartAvion(int depart,int avion, int qteCarburant){
		this.depart = depart;
		this.avion = avion;
		this.qteCarburant = qteCarburant;
	}

	public int getDepart() {
		return depart;
	}

	public void setDepart(int depart) {
		this.depart = depart;
	}

	public int getAvion() {
		return avion;
	}

	public void setAvion(int avion) {
		this.avion = avion;
	}

	public int getQteCarburant() {
		return qteCarburant;
	}

	public void setQteCarburant(int qteCarburant) {
		this.qteCarburant = qteCarburant;
	}
	
}
