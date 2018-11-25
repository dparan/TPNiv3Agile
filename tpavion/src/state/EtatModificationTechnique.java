package state;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import data_model.Avion;
import data_model.TypeAvion;
import decorator.DecorateurModificationAvion;
import decorator.DecorateurNonNavigant;
import decorator.Implementation;
import systeme.SystemeGestion;

public class EtatModificationTechnique extends Etat {
    /**
     * Méthode qui contient les modifications réalisables pour un avion
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurModificationAvion d = new DecorateurModificationAvion(new DecorateurNonNavigant(new Implementation()));
        List<Avion> avions = systemeGestion.getSystemeGestionAvion().rechercherAvions();
        d.afficherAvions(avions);
        log.log(Level.INFO,"Immatriculation de l'avion : ");
        Scanner sc = new Scanner(System.in);
        String immatriculation = sc.nextLine();
        /*recherche de l'avion  à traiter */
        Avion avion = systemeGestion.getSystemeGestionAvion().rechercherAvion(new Avion(immatriculation));
        /* si on a trouvé aucun avion correspondant à l'immatriculation : */
        if(avion==null) {
            log.log(Level.INFO," ID incorrect : retour au menu précèdent");
            systemeGestion.retourMenuPrecedent();
        } else {
            /* affichage des informations de l'avion à traiter */
            d.affichage();
            int value = saisirInt("");
            boolean erreur = false;
            if(value == 1){
                deconnexion(systemeGestion);
            } else if(value == 2){
                ajoutPassager(systemeGestion);
            } else if(value > 2 && value < 5){
                erreur = gestionOptionModifTechnique(value, avion, systemeGestion, d, erreur);
            } else {
                log.log(Level.INFO,"Erreur lors de la saisie ... ");
                systemeGestion.afficherInterface();
            }
            /* si la modification s'est bien effectuée : */
            if(!erreur && systemeGestion.getSystemeGestionAvion().majAvion(avion))
                log.log(Level.INFO,"Modification effectuée.");
            else 
                log.log(Level.INFO,"Erreur lors de la modification");

            systemeGestion.afficherInterface();
        }
    }
    
         /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     */
    private boolean gestionOptionModifTechnique(int choix, Avion avion, SystemeGestion systemeGestion, DecorateurModificationAvion d, boolean erreur){
        switch (choix){   
            case 3:
                int capacite = saisirInt(" Capacité : ");
                avion.setCapacite(capacite);
                break;
            case 4:
                    /* recherche des types */
                List<TypeAvion> types = systemeGestion.getSystemeGestionAvion().rechercherTypes();
                /* affichage des différents types */
                d.affichageListeType(types);
                int type = saisirInt(" Type :");
                /* si le type existe */
                if(type <= types.size())
                    avion.setType(types.get(type-1));
                else
                    erreur = true;
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
        return erreur;
    }
}
