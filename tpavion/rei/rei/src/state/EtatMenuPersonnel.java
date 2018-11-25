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
        switch (choix){
            case 1 :
                deconnexion(systemeGestion);
                break;
            case 2:
                ajoutPassager(systemeGestion);
                break;
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
                if(systemeGestion.getSystemeGestionUtilisateur().supprimerUtilisateur(new Personnel(id)))
                    log.log(Level.INFO,"L'utilisateur a bien été supprimé.");
                else
                    log.log(Level.INFO,"Erreur lors de la suppression");
                    break;
            case 7:
                systemeGestion.retourMenuPrecedent();
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
        systemeGestion.setState(this);
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
        
        d.affichageTypeRole();
        int t = saisirInt("Type :");
        String type = creerType(t);
        
        List<Role> roles = systemeGestion.getSystemeGestionUtilisateur().getRoles();
        d.affichageListeRoles(roles);
        int role = saisirInt("Rôle :");
        
        Personnel personnel = null;
        if(role<=roles.size()) {
            Role r = new Role(type, roles.get(role - 1).getRole());
            personnel = new Personnel(nom,prenom,adresse,noTelephone,motDePasse,r);
        }
        if(personnel==null || !systemeGestion.getSystemeGestionUtilisateur().ajouterUtilisateur(personnel))
            log.log(Level.INFO,"erreur lors de l'ajout");
        else
            log.log(Level.INFO,"Ajout effectué");
        }
}
