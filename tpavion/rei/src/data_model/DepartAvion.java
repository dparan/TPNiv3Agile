package data_model;


public class DepartAvion {
    /* id -> id de depart */
    private Depart id;
    private Avion immatriculation;
    private int qteCarburant;

    /**
     * Constructeur pour un DepartAvion
     * @param id
     * @param dateDepart
     * @param immatriculation
     * @param qteCarburant
     */
    public DepartAvion(Depart id, Avion immatriculation, int qteCarburant){
        this.id = id;
        this.immatriculation = immatriculation;
        this.qteCarburant = qteCarburant;
    }

    /**
     * Getter de l'id
     * @return int
     */
    public Depart getId() {
        return id;
    }

    /**
     * Setter de l'id
     * @param id
     */
    public void setId(Depart id) {
        this.id = id;
    }

    /**
     * Getter de l'immatriculation
     * @return String
     */
    public Avion getImmatriculation() {
        return immatriculation;
    }

    /**
     * Setter de l'immatriculation
     * @param immatriculation
     */
    public void setImmatriculation(Avion immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * Getter de la quantite de carburant
     * @return int
     */
    public int getQteCarburant() {
        return qteCarburant;
    }

    /**
     * Setter de la quantite de carburant
     * @param qteCarburant
     */
    public void setQteCarburant(int qteCarburant) {
        this.qteCarburant = qteCarburant;
    }

}
