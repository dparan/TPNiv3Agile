package data_model;

import java.sql.Date;

public class RapportPilote {

    private int idPilote;
    private int idDepart;
    private Date dateDepart;
    private String rapport;

    /**
     * Constructeur d'un RapportPilote
     * @param idPilote
     * @param idDepart
     * @param dateDepart
     * @param rapport
     */
    public RapportPilote(int idPilote,int idDepart,Date dateDepart, String rapport){
        this.idPilote = idPilote;
        this.dateDepart = dateDepart;
        this.idDepart = idDepart;
        this.rapport = rapport;
    }

    /**
     * Getter de l'id du pilote
     * @return
     */
    public int getIdPilote() {
        return idPilote;
    }

    /**
     * Setter de l'id du pilote
     * @param idPilote
     */
    public void setIdPilote(int idPilote) {
        this.idPilote = idPilote;
    }

    /**
     * Getter de l'id de depart
     * @return int
     */
    public int getIdDepart() {
        return idDepart;
    }

    /**
     * Setter de l'id de depart
     * @param idDepart
     */
    public void setIdDepart(int idDepart) {
        this.idDepart = idDepart;
    }

    /**
     * Getter de la date de depart
     * @return Date
     */
    public Date getDateDepart() {
        return dateDepart;
    }

    /**
     * Setter de la date de depart
     * @param dateDepart
    */
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }

    /**
     * Getter du rapport
     * @return String
     */
    public String getRapport() {
            return rapport;
    }

    /**
     * Setter du rapport
     * @param rapport
     */
    public void setRapport(String rapport) {
        this.rapport = rapport;
    }
}
