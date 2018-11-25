package state;

import java.util.logging.Level;

import systeme.SystemeGestion;

public class EtatInitial extends Etat {
    /**
     * Méthode qui contient la connexion
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        log.log(Level.INFO,"----------MENU----------");
        log.log(Level.INFO," Connexion : ");
        int id = saisirInt(" Identifiants :");
        String motDePasse = saisirString(" Mot de passe :");
        /* on vérifie si l'utilisateur s'est bien connecté */
        if(systemeGestion.getSystemeGestionUtilisateur().connexion(id, motDePasse)) {
            log.log(Level.INFO,"La connexion est un succès.");
            /* si la connexion est un succés */
            systemeGestion.setState(new EtatMenuConnecte());
        }else {
            log.log(Level.INFO,"La connexion a échouée, veuillez réessayer.");
            systemeGestion.afficherInterface();
        }
    }
}
