package data_model;

public class Passager extends Personne{

    private String numPasseport;

    /**
     * Constructeur pour un passager
     * @param numPasseport
     * @param prenom
     * @param nom
     * @param adresse
     * @param noTelephone
     */
    public Passager(String numPasseport, String prenom, String nom, String adresse, String noTelephone) {
        super(prenom,nom,adresse,noTelephone);
        this.numPasseport = numPasseport;
    }

    public Passager(String passeport) {
    	this.numPasseport = passeport;
	}

	/**
     * Getter du numéro de passeport
     * @return String
     */
    public String getNumPasseport() {
        return numPasseport;
    }

    /**
     * Setter du numero de passeport
     * @param numPasseport
     */
    public void setNumPasseport(String numPasseport) {
        this.numPasseport = numPasseport;
    }
}
