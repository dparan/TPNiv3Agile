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
        System.out.println("----------MENU----------");
        System.out.println(" Connexion : ");
        int id = saisirInt(" Identifiants :");
        String motDePasse = saisirString(" Mot de passe :");
        /* on vérifie si l'utilisateur s'est bien connecté */
        if(systemeGestion.getSystemeGestionUtilisateur().connexion(id, motDePasse)) {
            log.log(Level.INFO,"La connexion est un succès.");
            /* si la connexion est un succés */
            systemeGestion.setState(new EtatMenuConnecte());
        }else {
            /* si la connexion est un echec */
            log.log(Level.INFO,"La connexion a échouée, veuillez réessayer.");
            systemeGestion.afficherInterface();
        }
    }
}
