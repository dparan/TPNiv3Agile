package decorator;

import java.util.logging.Logger;

public class Implementation implements Abstraction {

    Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * Méthode permettant l'affichage du menu
     */
	@Override
	public void affichage()
    {
		// affichage commun a tous les decorateurs
		 System.out.println("----------MENU-----------");
		 System.out.println(" 1 --> Déconnexion");
    }

}
