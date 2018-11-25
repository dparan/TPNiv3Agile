package decorator;


public class DecorateurMenuDepart extends Decorateur{
    /**
     * Constructeur d'un DecorateurMenuDepart
     * @param a
     */
    public DecorateurMenuDepart(Abstraction a) {
        super(a);
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    @Override
    public void affichage() {
        a.affichage();
        System.out.println(" 3 --> Ajouter un nouveau départ");
        System.out.println(" 4 --> Modifier un départ existant");
        System.out.println(" 5 --> Supprimer un départ existant");
        System.out.println(" 6 --> Rechercher un départ existant");
        System.out.println(" 7 --> Programmer un vol en un départ :");
        System.out.println(" 8 --> Modifier la programmation d'un départ existant :");
        System.out.println(" 9 --> Supprimer la programmation d'un départ existant :");
        System.out.println(" 10 --> Rechercher la programmation d'un depart existant :");
        System.out.println(" 11 -> Retour au menu précèdent...");
    }
}