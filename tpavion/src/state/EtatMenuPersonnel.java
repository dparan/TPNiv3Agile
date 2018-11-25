package state;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import data_model.Personnel;
import data_model.Role;
import decorator.DecorateurMenuPersonnel;
import decorator.DecorateurNonNavigant;
import decorator.Implementation;
import systeme.SystemeGestion;

public class EtatMenuPersonnel extends EtatPersonnel {
    /**
     * Méthode qui contient toutes les actions qu'un membre du service personnel peut effectuer
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurMenuPersonnel d = new DecorateurMenuPersonnel(new DecorateurNonNavigant(new Implementation()),systemeGestion.getSystemeGestionUtilisateur().getRoles());
        d.affichage();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        if(choix == 1){
            deconnexion(systemeGestion);
        } else if(choix == 2){
            ajoutPassager(systemeGestion);
        } else if(choix > 2 && choix < 7){
            gestionOptionPersonnel(choix, systemeGestion, d);
        } else if(choix == 7){
            systemeGestion.retourMenuPrecedent();
        } else {
            log.log(Level.INFO,"Erreur...");
        }

        systemeGestion.setState(this);
    }
    
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     */
    private void  gestionOptionPersonnel(int choix, SystemeGestion systemeGestion, DecorateurMenuPersonnel d){
        switch (choix){
            case 3:
            	System.out.println("Ajout d'un nouvel utilisateur");
                ajoutUtilisateur(systemeGestion, d);
                break;
            case 4:
                System.out.println("Ajout d'un nouveau rôle");
                ajoutRole(systemeGestion);
                break;
            case 5:
                systemeGestion.setState(new EtatModificationPersonnel());
                break;
            case 6:
            	System.out.println("Supprimer un utilisateur");
                d.afficherPersonnels(systemeGestion.getSystemeGestionUtilisateur().getPersonnels());
                int id = saisirInt("id du membre :");
            	/* si la suppression s'est bien effectuée : */
                if(systemeGestion.getSystemeGestionUtilisateur().supprimerUtilisateur(new Personnel(id)))
                    log.log(Level.INFO,"L'utilisateur a bien été supprimé.");
                else
                    log.log(Level.INFO,"Erreur lors de la suppression");
                    break;
            default:
                log.log(Level.INFO,"Erreur...");
        }
    }

    /**
     * Méthode permettant l'ajout d'un rôle
     * @param systemeGestion
     */
    private void ajoutRole(SystemeGestion systemeGestion) {
                    String role = saisirString("Role :");
                    String type = saisirString("Type :");
            if(systemeGestion.getSystemeGestionUtilisateur().ajouterRole(new Role(type,role)))
                    log.log(Level.INFO,"Rôle ajouté");
            else
                    log.log(Level.INFO,"Erreur lors de l'ajout");
    }

    /**
     * Méthode permettant l'ajout d'un utilisateur
     * @param systemeGestion
     */
    private void ajoutUtilisateur(SystemeGestion systemeGestion, DecorateurMenuPersonnel d) {
        String nom = saisirString("Nom :");
        String prenom = saisirString("Prenom :");
        String adresse = saisirString("Adresse :");
        String noTelephone = saisirString("Numéro de téléphone :");
        String motDePasse = saisirString("Mot de passe :");
    	/* récupération des résultats dans un liste en vue d'un affichage */
        List<Role> roles = systemeGestion.getSystemeGestionUtilisateur().getRoles();
        /* affichage des résultats de la recherche des rôles */
        d.affichageListeRoles(roles);
        int role = saisirInt("Rôle :");
        
        Personnel personnel = null;
        /* si le rôle existe */
        if(role<=roles.size()) {
            personnel = new Personnel(nom,prenom,adresse,noTelephone,motDePasse,roles.get(role - 1));
        }
        /* si l'ajout s'est mal effectué : */
        if(personnel==null || !systemeGestion.getSystemeGestionUtilisateur().ajouterUtilisateur(personnel))
            log.log(Level.INFO,"erreur lors de l'ajout");
        else
            log.log(Level.INFO,"Ajout effectué");
        }
}
