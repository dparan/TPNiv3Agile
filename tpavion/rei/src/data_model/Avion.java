package data_model;

public class Avion {
    private String immatriculation;
    private int capacite;
    private TypeAvion type;

    /**
     * Constructeur d'un avion, utilisé lors de la selection d'un avion (AvionDAO) 
     * @param immatriculation
     * @param capacite
     * @param type
     */
    public Avion(String immatriculation, int capacite,TypeAvion type) {
        this.immatriculation = immatriculation;
        this.capacite = capacite;
        this.type = type;
    }

    /**
     * Constructeur d'un avion
     * @param immatriculation
     */
    public Avion(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * Getter de l'immatriculation
     * @return string
     */
    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Setter de l'immatriculation
     * @param immatriculation
     */
    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    /**
     * Getter de la capacite
     * @return int
     */
    public int getCapacite() {
        return capacite;
    }

    /**
     * Setter de la capacite
     * @param capacite
     */
    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    /**
     * Getter de le type d'avion
     * @return TypeAvion
     */
    public TypeAvion getType() {
        return type;
    }

    /**
     * Setter de le type d'avion
     * @param type
     */
    public void setType(TypeAvion type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "immatriculation : "+immatriculation+", capacite : "+capacite+", type : "+type.getType();
    }
}
