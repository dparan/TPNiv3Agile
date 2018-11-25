package decorator;


public class DecorateurMenuConnecte extends Decorateur{
    /**
     * Constructeur du décorateur Menu Connecte
     * @param a
     */
    public DecorateurMenuConnecte (Abstraction a) {
        super(a);
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    @Override
    public void affichage(){
        a.affichage(); // affichage de l'abstraction.
        /* a voir plus tard en fonction des droits de l'utilisateur (menuGestion)*/
        System.out.println(" 2 --> Menu Personnel");
        System.out.println(" 3 --> Menu Gestionnaire");
        System.out.println(" 4 --> Menu Technique");
        System.out.println(" 5 --> Menu Pilote");
    }
}
