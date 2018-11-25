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
        Personnel personnel = systemeGestion.getSystemeGestionUtilisateur().rechercherUtilisateur(new Personnel(id));
        if(personnel==null) {
            log.log(Level.INFO," ID incorrect : retour au menu précèdent");
            systemeGestion.retourMenuPrecedent();
        } else {
            d.setPersonnel(personnel);
            d.affichage();
            boolean erreur = false;
            
            id = saisirInt("");
            switch(id) {
                case 1:
                    deconnexion(systemeGestion);
                    break;
                case 2:
                    ajoutPassager(systemeGestion);
                    break;
                case 3:
                    systemeGestion.retourMenuPrecedent();
                    break;
                case 4:
                    String nom = saisirString(" Nom :");
                    personnel.setNom(nom);
                    break;
                case 5:
                    String prenom = saisirString(" Pr�nom :");
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
                    d.affichageTypeRole();
                    int t = saisirInt("Type :");
                    String type = creerType(t);
                    List<Role> roles = systemeGestion.getSystemeGestionUtilisateur().getRoles();
                    d.affichageListeRoles(roles);
                    int role = saisirInt(" Rôle :");
                    if(role<=roles.size()) {
                        personnel.setRole(new Role(type, roles.get(role - 1).getRole()));
                    }else{
                        erreur = true;
                    }
                    break;
                default:
                    log.log(Level.INFO,"Erreur lors de la saisie ... ");
                    systemeGestion.afficherInterface();
                    break;
            }
            if(!erreur && systemeGestion.getSystemeGestionUtilisateur().majUtilisateur(personnel))
                log.log(Level.INFO,"Modification effectuée.");
            else 
                log.log(Level.INFO,"Erreur lors de la modification");
            systemeGestion.afficherInterface();
        }
    }
}
