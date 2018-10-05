package data_model;

import java.sql.Time;

public class VolTroncon {

	private int troncon;
	private int vol;
	private Time heureDepart;
	private Time heureSortie;
	
	VolTroncon(int troncon,int vol,Time heureDepart,Time heureSortie){
		this.troncon = troncon;
		this.vol = vol;
		this.heureDepart = heureDepart;
		this.heureSortie = heureSortie;
	}

	public int getTroncon() {
		return troncon;
	}

	public void setTroncon(int troncon) {
		this.troncon = troncon;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}

	public Time getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(Time heureDepart) {
		this.heureDepart = heureDepart;
	}

	public Time getHeureSortie() {
		return heureSortie;
	}

	public void setHeureSortie(Time heureSortie) {
		this.heureSortie = heureSortie;
	}
}
