package state;

import java.util.logging.Level;
import systeme.SystemeGestion;

public class EtatMenuConnecte extends Etat {
    /**
     * Méthode qui contient le choix des menus
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
    	
    /* redirection en fonction du role de l'utilisateur */
    switch (systemeGestion.getSystemeGestionUtilisateur().getUtilisateurConnecte().getRole().getRole()){
    	case "Pilote":
    		/* c'est un pilote le menu pilote s'affiche */
    		System.out.println("Menu Pilote");
    		systemeGestion.setState(new EtatMenuPilote());
    		break;
    	case "Service Gestionnaire":
    		/* c'est un personnel de gestion on affiche le menu gestionnaire */
        	System.out.println("Menu Gestionnaire");
            systemeGestion.setState(new EtatMenuGestionnaire());
            break;
    	case "Service Personnel":
    		/* c'est un membre du service personnel on affiche le menu personnel */
        	System.out.println("Menu Personnel");
            systemeGestion.setState(new EtatMenuPersonnel());
            break;
    	case "Service Technique":
    		/* c'est un membre du service technique on affiche le menu technique */
        	System.out.println("Menu Technique");
            systemeGestion.setState(new EtatMenuTechnique());
            break;
        default:
            log.log(Level.INFO,"Erreur...");
            systemeGestion.setState(new EtatInitial());
            break;
    		
    }
    systemeGestion.afficherInterface();
    }
}
