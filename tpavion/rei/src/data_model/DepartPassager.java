package data_model;

public class DepartPassager {
	
    private Passager passager;
    private Depart depart;
    private int numPlace;

    /**
     * Constructeur pour un departPassager
     * @param numPasseport
     * @param id
     * @param dateDepart
     * @param numPlace
     */
    public DepartPassager(Passager passager, Depart depart, int numPlace) {
        this.passager = passager;
        this.depart=depart;
        this.numPlace = numPlace;
    }

    /**
     * Getter de le numéro de passeport
     * @return String
     */
    public Passager getPassager() {
        return passager;
    }

    /**
     * Setter de le numéro de passeport
     * @param numPasseport
     */
    public void setNumPasseport(Passager passager) {
        this.passager = passager;
    }

    /**
     * Getter de la date de depart
     * @return
     */
    public Depart getDepart() {
        return depart;
    }

    /**
     * Setter de la date de depart
     * @param dateDepart
     */
    public void setDepart(Depart depart) {
        this.depart = depart;
    }

    /**
     * Getter du numero de la place
     * @return int
     */
    public int getNumPlace() {
        return numPlace;
    }

    /**
     * Setter du numero de la place
     * @param numPlace
     */
    public void setNumPlace(int numPlace) {
        this.numPlace = numPlace;
    }
}
