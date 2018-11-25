package state;

import java.util.Scanner;
import java.util.logging.Level;

import decorator.DecorateurMenuConnecte;
import decorator.Implementation;
import systeme.SystemeGestion;

public class EtatMenuConnecte extends Etat {
    /**
     * Méthode qui contient le choix des menus
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
    DecorateurMenuConnecte d = new DecorateurMenuConnecte(new Implementation());
    d.affichage(); // affiche le menu de l'administrateur
    Scanner sc = new Scanner(System.in);
    int choix = sc.nextInt();
    switch (choix){
        case 1 :
            deconnexion(systemeGestion);
            break;
        case 2:
        	System.out.println("Menu Personnel");
            systemeGestion.setState(new EtatMenuPersonnel());
            break;
        case 3:
        	System.out.println("Menu Gestionnaire");
            systemeGestion.setState(new EtatMenuGestionnaire());
            break;
        case 4:
        	System.out.println("Menu Technique");
            systemeGestion.setState(new EtatMenuTechnique());
            break;
        case 5:
        	System.out.println("Menu Pilote");
            systemeGestion.setState(new EtatMenuPilote());
            break;
        default:
            log.log(Level.INFO,"Erreur...");
    }
    systemeGestion.afficherInterface();
    }
}
