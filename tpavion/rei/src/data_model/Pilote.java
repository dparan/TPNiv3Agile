package data_model;

import java.sql.Time;

public class Pilote {
    
    private int id;
    private Time nombreHeureTotale;

    /**
     * Constructeur d'un pilote
     * @param id
     * @param nombreHeureTotale
     */
    public Pilote(int id, Time nombreHeureTotale){
        this.id = id;
        this.nombreHeureTotale = nombreHeureTotale;
    }

    /**
     * Getter pour l'id
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter du nombre d'heure totale
     * @return Time
     */
    public Time getNombreHeureTotale() {
        return nombreHeureTotale;
    }

    /**
     * Setter du nombre d'heure totale
     * @param nombreHeureTotale
     */
    public void setNombreHeureTotale(Time nombreHeureTotale) {
        this.nombreHeureTotale = nombreHeureTotale;
    }
}
