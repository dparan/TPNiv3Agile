package state;

import java.util.Scanner;
import java.util.logging.Level;

import decorator.DecorateurMenuGestionnaire;
import decorator.DecorateurNonNavigant;
import decorator.Implementation;
import systeme.SystemeGestion;

public class EtatMenuGestionnaire extends Etat{

    /**
     * Méthode qui contient toutes les actions qu'un membre du service gestionnaire peut faire
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurMenuGestionnaire d = new DecorateurMenuGestionnaire(new DecorateurNonNavigant(new Implementation()));
        d.affichage();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        switch (choix){
            case 1 :
                deconnexion(systemeGestion);
                break;                
            case 2:
                ajoutPassager(systemeGestion);
                break;
            case 3:
                systemeGestion.setState(new EtatMenuTroncon());
                break;
            case 4:
                System.out.println("Modifier un utilisateur");
                systemeGestion.setState(new EtatMenuDepart());
                break;
            case 5:
                systemeGestion.retourMenuPrecedent();
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
        systemeGestion.setState(this);
    }
}
