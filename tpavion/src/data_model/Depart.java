package data_model;

import java.sql.Date;

public class Depart {
    /* id -> id du vol */
    private Vol id;
    private Date dateDepart;

    /**
     * Constructeur pour un depart
     * @param id
     * @param dateDepart
     */
    public Depart(Vol id,Date dateDepart){
        this.id = id;
        this.dateDepart = dateDepart;
    }

    /**
     * Getter de l'id
     * @return int
     */
    public Vol getId() {
        return id;
    }

    /**
     * Setter de l'id
     * @param id
     */
    public void setId(Vol id) {
        this.id = id;
    }

    /**
     * Getter de la date
     * @return Date
     */
    public Date getDateDepart() {
        return dateDepart;
    }

    /**
     * Setter de la date
     * @param dateDepart
     */
    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }
    
    /**
     * toString du départ
     */
    public String toString()
    {
    	return "Depart [id="+id.toString()+", date de depart="+dateDepart.toString()+"]";
    }
}
