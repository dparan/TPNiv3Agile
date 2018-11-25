package decorator;


public class DecorateurMenuVolTroncon extends Decorateur{
    /**
     * Constructeur d'un DecorateurMenuVolTroncon
     * @param a
     */
    public DecorateurMenuVolTroncon(Abstraction a) {
            super(a);
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    @Override
    public void affichage() {
        a.affichage();
        System.out.println(" 3 --> Ajouter un nouveau vol");
        System.out.println(" 4 --> Modifier un vol existant");
        System.out.println(" 5 --> Supprimer un vol existant");
        System.out.println(" 6 --> Rechercher un vol existant");
        System.out.println(" 7 --> Ajouter un nouveau tron�on");
        System.out.println(" 8 --> Modifier un tronçon existant");
        System.out.println(" 9 --> Supprimer un tronçon existant");
        System.out.println(" 10 --> Rechercher un tronçon existant");
        System.out.println(" 11 --> Associer un vol à un troncon");
        System.out.println(" 12 --> Modifier une association vol-tronçon");
        System.out.println(" 13 --> Supprimer une association vol-tronçon");
        System.out.println(" 14 --> Rechercher une association vol-tronçon");
        System.out.println(" 15 -> Retour au menu précèdent...");
    }
}