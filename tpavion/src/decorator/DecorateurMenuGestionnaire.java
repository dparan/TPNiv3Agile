package decorator;


public class DecorateurMenuGestionnaire extends Decorateur{
    /**
     * Constructeur d'un DecorateurMenuGestionnaire
     * @param a
     */
    public DecorateurMenuGestionnaire(Abstraction a) {
        super(a);
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    @Override
    public void affichage() {
        a.affichage();
        System.out.println(" 3 --> Gestion des vols");
        System.out.println(" 4 --> Gestion des départs");
        System.out.println(" 5 --> Retour au menu précèdent...");
    }
}
