package decorator;


public class DecorateurMenuTechnique extends DecorateurTechnique {
    /**
     * Constructeur d'un DecorateurMenuTechnique
     * @param a
     */
    public DecorateurMenuTechnique(Abstraction a) {
        super(a);
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    @Override
    public void affichage() {
        a.affichage();
        System.out.println(" 3 --> Ajouter un nouvel avion");
        System.out.println(" 4 --> Ajouter un nouveau type d'avion");
        System.out.println(" 5 --> Modifier un avion existant");
        System.out.println(" 6 --> Supprimer un avion existant");
        System.out.println(" 7 --> Supprimer un type d'avion existant");
        System.out.println(" 8 --> Retour au menu précèdent...");
    }
}
