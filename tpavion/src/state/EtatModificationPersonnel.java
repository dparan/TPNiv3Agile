package state;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import data_model.Personnel;
import data_model.Role;
import decorator.DecorateurMenuPrecedent;
import decorator.DecorateurModificationPersonnel;
import decorator.DecorateurNonNavigant;
import decorator.Implementation;
import systeme.SystemeGestion;

public class EtatModificationPersonnel extends EtatPersonnel {
    /**
     * Méthode qui contient les actions relatives à la modification d'un membre
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurModificationPersonnel d = new DecorateurModificationPersonnel(new DecorateurMenuPrecedent(new DecorateurNonNavigant(new Implementation())));
        d.afficherPersonnels(systemeGestion.getSystemeGestionUtilisateur().getPersonnels());
        log.log(Level.INFO,"Numero du membre : ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        /*recherche du personnel à traiter */
        Personnel personnel = systemeGestion.getSystemeGestionUtilisateur().rechercherUtilisateur(new Personnel(id));
        /* si on a trouvé aucun personnel correspondant à l'id : */
        if(personnel==null) {
            log.log(Level.INFO," ID incorrect : retour au menu précèdent");
            systemeGestion.retourMenuPrecedent();
        } else {
            d.setPersonnel(personnel);
        	/* affichage des informations du personnel à traiter */
            d.affichage();
            boolean erreur = false;
            id = saisirInt("");
            if(id == 1){
                deconnexion(systemeGestion);
            } else if(id == 2){
                ajoutPassager(systemeGestion);
            } else if(id == 3){
                systemeGestion.retourMenuPrecedent();
            } else if(id > 3 && id < 9){
                erreur = gestionOptionModifPersonnel(id, personnel, systemeGestion, d, erreur);
            } else {
                log.log(Level.INFO,"Erreur lors de la saisie ... ");
                systemeGestion.afficherInterface();
            }

            /* si l'action s'est bien effectuée : */
            if(!erreur && systemeGestion.getSystemeGestionUtilisateur().majUtilisateur(personnel))
                log.log(Level.INFO,"Modification effectuée.");
            else 
                log.log(Level.INFO,"Erreur lors de la modification");
            systemeGestion.afficherInterface();
        }
    }
    
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     */
    private boolean gestionOptionModifPersonnel(int id, Personnel personnel, SystemeGestion systemeGestion, DecorateurModificationPersonnel d, boolean erreur){
        switch(id) {
            case 4:
                String nom = saisirString(" Nom :");
                personnel.setNom(nom);
                break;
            case 5:
                String prenom = saisirString(" Prénom :");
                personnel.setPrenom(prenom);
                break;
            case 6:
                String adresse = saisirString(" Adresse :");
                personnel.setAdresse(adresse);
                break;
            case 7:
                String numeroTelephone = saisirString(" Numéro de téléphone :");
                personnel.setNoTelephone(numeroTelephone);
                break;
            case 8:
                    /* affichage des différents types */
                d.affichageTypeRole();
                int t = saisirInt("Type :");
                String type = creerType(t);
                List<Role> roles = systemeGestion.getSystemeGestionUtilisateur().getRoles();
                /* affichage des différents rôles */
                d.affichageListeRoles(roles);
                int role = saisirInt("Rôle :");
                /* si le rôle existe */
                if(role<=roles.size()) {
                    personnel.setRole(new Role(type, roles.get(role - 1).getRole()));
                }else{
                    erreur = true;
                }
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
        return erreur;
    }
}
