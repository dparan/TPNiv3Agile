package decorator;


public class DecorateurMenuPilote extends DecorateurTechnique {
    /**
     * Constructeur d'un DecorateurMenuPilote
     * @param a
     */
    public DecorateurMenuPilote(Abstraction a) {
            super(a);
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    @Override
    public void affichage() {
        a.affichage();
        System.out.println("2 --> Saisir un rapport de vol");
        System.out.println("3 --> Retour au menu précèdent...");
    }
}
