package decorator;

import data_model.Avion;

public class DecorateurModificationAvion extends DecorateurTechnique{

    private Avion avion = null;

    /**
     * Constructeur d'un DecorateurModificationAvion
     * @param a
     * @param avion
     */
    public DecorateurModificationAvion(Abstraction a) {
        super(a);
    }
    
    public void setAvion(Avion avion){
        this.avion = avion;
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    public void affichage() {
        a.affichage();
        System.out.println(" 3 --> Modifier la capacité (capacite actuelle : "+avion.getCapacite()+" )");
        System.out.println(" 4 --> Modifier le type (type actuel : "+avion.getType()+" )");
    }
}
