package data_model;


public class Personnel extends Personne {

    private int id;
    private String motDePasse;
    private Role role;

    /**
     * Construteur pour un personnel
     * @param prenom
     * @param nom
     * @param adresse
     * @param noTelephone
     * @param motDePasse
     * @param role
     */
    public Personnel(String prenom, String nom, String adresse, String noTelephone, String motDePasse,
                     Role role) {
        super(prenom, nom, adresse, noTelephone);
        this.motDePasse = motDePasse;
        this.role = role;
    }

    /**
     * Constructeur pour un personnel, utilisé lors de la selection
     * @param id
     * @param prenom
     * @param nom
     * @param adresse
     * @param noTelephone
     * @param motDePasse
     * @param role
     */
    public Personnel(int id, String prenom, String nom, String adresse, String noTelephone, String motDePasse,
                     Role role) {
        super(prenom, nom, adresse, noTelephone);
        this.id = id;
        this.motDePasse = motDePasse;
        this.role = role;
    }

    /**
     * Constructeur pour un personnel
     * @param id
     */
    public Personnel(int id) {
        this.id = id;
    }

    /**
     * Getter de l'id
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
     * Setter du mot de passe
     * @param motDePasse
     */
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    /**
     * Getter du mot de passe
     * @return String
     */
    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Getter du role
     * @return Role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setter du rôle
     * @param role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "id : "+id+", prenom : "+getPrenom()+", nom : "+getNom()+", adresse : "+getAdresse()+", noTelephone : "+getNoTelephone()+", type de rôle : "+getRole().getType()+", rôle : "+getRole().getRole();
    }
}

