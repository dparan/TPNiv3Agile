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
        Avion avion = systemeGestion.getSystemeGestionAvion().rechercherAvion(new Avion(immatriculation));

        if(avion==null) {
            log.log(Level.INFO," ID incorrect : retour au menu précèdent");
            systemeGestion.retourMenuPrecedent();
        } else {
            d.affichage();
            int value = saisirInt("");
            boolean erreur = false;
            switch(value) {
                case 1:
                    deconnexion(systemeGestion);
                    break;
                case 2:
                    ajoutPassager(systemeGestion);
                    break;
                case 3:
                    int capacite = saisirInt(" Capacit� : ");
                    avion.setCapacite(capacite);
                    break;
                case 4:
                    List<TypeAvion> types = systemeGestion.getSystemeGestionAvion().rechercherTypes();
                    d.affichageListeType(types);
                    int type = saisirInt(" Type :");
                    if(type <= types.size())
                        avion.setType(types.get(type-1));
                    else
                        erreur = true;
                        break;
                default:
                    log.log(Level.INFO,"Erreur lors de la saisie ... ");
                    systemeGestion.afficherInterface();
                    break;
            }
            if(!erreur && systemeGestion.getSystemeGestionAvion().majAvion(avion))
                log.log(Level.INFO,"Modification effectu�e.");
            else 
                log.log(Level.INFO,"Erreur lors de la modification");

            systemeGestion.afficherInterface();
        }
    }
}
