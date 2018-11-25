package decorator;

import java.util.logging.Level;
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
		 log.log(Level.INFO,"----------MENU-----------");
		 log.log(Level.INFO," 1 --> Déconnexion");
    }

}
