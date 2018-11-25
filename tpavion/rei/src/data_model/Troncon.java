package data_model;

public class Troncon {

    private String villeDepart;
    private String villeArrivee;
    private int distance;

    /**
     * Constructeur pour un tronçon
     * @param id
     * @param villeDepart
     * @param villeArrivee
     * @param distance
     */
    public Troncon(String villeDepart, String villeArrivee) {
            this.villeDepart = villeDepart;
            this.villeArrivee = villeArrivee;
    }

    /**
     * Constructeur pour un tronçon
     * @param villeDepart
     * @param villeArrivee
     * @param distance
     */
    public Troncon(String villeDepart, String villeArrivee, int distance) {
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.distance = distance;
    }

    @Override
	public String toString() {
		return "Troncon [villeDepart=" + villeDepart + ", villeArrivee=" + villeArrivee + ", distance=" + distance
				+ "]";
	}

	/**
     * Getter de la ville de depart
     * @return String
     */
    public String getVilleDepart() {
        return villeDepart;
    }

    /**
     * Setter de la ville de depart
     * @param villeDepart
     */
    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    /**
     * Getter de la ville d'arrivee
     * @return String
     */
    public String getVilleArrivee() {
        return villeArrivee;
    }

    /**
     * Setter de la ville d'arrivee
     * @param villeArrivee
     */
    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    /**
     * Getter de la distance
     * @return int
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Setter de la distance
     * @param distance
     */
    public void setDistance(int distance) {
            this.distance = distance;
    }
}
