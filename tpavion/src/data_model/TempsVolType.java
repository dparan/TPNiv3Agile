package data_model;

import java.sql.Time;

public class TempsVolType {

	private int pilote;
	private int typeAvion;
	private Time temps;
	
	TempsVolType(int pilote, int typeAvion, Time temps) {
		super();
		this.pilote = pilote;
		this.typeAvion = typeAvion;
		this.temps = temps;
	}

	public int getPilote() {
		return pilote;
	}

	public void setPilote(int pilote) {
		this.pilote = pilote;
	}

	public int getTypeAvion() {
		return typeAvion;
	}

	public void setTypeAvion(int typeAvion) {
		this.typeAvion = typeAvion;
	}

	public Time getTemps() {
		return temps;
	}

	public void setTemps(Time temps) {
		this.temps = temps;
	}
	
}
