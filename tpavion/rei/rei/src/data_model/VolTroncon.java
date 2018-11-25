package data_model;

import java.sql.Time;

public class VolTroncon {

    private Troncon troncon;
    private Vol vol;
    private Time heureDepart;
    private Time heureArrivee;

    /**
     * Constructeur pour un VolTroncon
     * @param troncon
     * @param vol
     * @param heureDepart
     * @param heureArrivee
     */
    public VolTroncon(Troncon troncon,Vol vol,Time heureDepart,Time heureArrivee){
        this.troncon = troncon;
        this.vol = vol;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
    }

    public VolTroncon(Troncon troncon, Time heureDepart) {
    	this.troncon=troncon;
    	this.heureDepart=heureDepart;
	}

	/**
     * Getter d'un tronçon
     * @return int
     */
    public Troncon getTroncon() {
        return troncon;
    }

    /**
     * Setter d'un tronçon
     * @param troncon
     */
    public void setTroncon(Troncon troncon) {
        this.troncon = troncon;
    }

    /**
     * Getter d'un vol
     * @return int
     */
    public Vol getVol() {
        return vol;
    }

    /**
     * Setter d'un vol
     * @param vol
     */
    public void setVol(Vol vol) {
        this.vol = vol;
    }

    /**
     * Getter de l'heure de départ
     * @return Time
     */
    public Time getHeureDepart() {
        return heureDepart;
    }

    /**
     * Setter de l'heure de départ
     * @param heureDepart
     */
    public void setHeureDepart(Time heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Getter de l'heure de sortie
     * @return Time
     */
    public Time getHeureArrivee() {
        return heureArrivee;
    }

    @Override
	public String toString() {
		return "VolTroncon [troncon=" + troncon + ", vol=" + vol + ", heureDepart=" + heureDepart + ", heureArrivee="
				+ heureArrivee + "]";
	}

	/**
     * Setter de l'heure de sortie
     * @param heureSortie
     */
    public void setHeureArrivee(Time heureArrivee) {
        this.heureArrivee = heureArrivee;
    }
}
