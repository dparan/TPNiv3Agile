package state;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import data_model.Avion;
import data_model.Depart;
import data_model.DepartAvion;
import data_model.TypeAvion;
import data_model.Vol;
import decorator.DecorateurMenuDepart;
import decorator.DecorateurModificationAvion;
import decorator.DecorateurModificationDepartAvion;
import decorator.DecorateurNonNavigant;
import decorator.Implementation;
import systeme.SystemeGestion;

public class EtatModificationDepartAvion extends Etat {
    /**
     * Méthode qui contient les modifications réalisables pour un avion
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurModificationDepartAvion d = new DecorateurModificationDepartAvion(new DecorateurNonNavigant(new Implementation()));
        List<Avion> avions = systemeGestion.getSystemeGestionAvion().rechercherAvions();
        System.out.println("Veuillez saisir l'identifiant du départ : ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        List<DepartAvion> listeDepartAvion = systemeGestion.getSystemeGestionDepart().rechercherDepartAvion(new DepartAvion(new Depart(new Vol(id, 0, ""), Date.valueOf(saisirDate("Veuillez saisir la date de départ"))), null, 0));
        if(listeDepartAvion==null | listeDepartAvion.size() == 0) {
            log.log(Level.INFO," ID incorrect : retour au menu précèdent");
            systemeGestion.retourMenuPrecedent();
        } else {
        	DepartAvion departAvion = listeDepartAvion.get(0);
        	d.setDepart(departAvion);
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
                	System.out.println("Modification de l'immatriculation du départ");
                    String immatriculation = saisirString(" Nouvelle immatriculation : ");
                    Avion a = systemeGestion.getSystemeGestionAvion().rechercherAvion(new Avion(immatriculation));
                    if(a != null)
                    	departAvion.setImmatriculation(a);
                    else
                    	erreur = true;
                    break;
                case 4:
                	departAvion.setQteCarburant(-1);
	                System.out.println("Modification de la quantité de carburant");
	                int qte = saisirInt(" Nouvelle quantité : ");
	                departAvion.setQteCarburant(qte);
                    break;
                default:
                    log.log(Level.INFO,"Erreur lors de la saisie ... ");
                    systemeGestion.afficherInterface();
                    break;
            }
            if(!erreur && systemeGestion.getSystemeGestionDepart().majDepartAvion(departAvion))
                log.log(Level.INFO,"Modification effectuée.");
            else 
            {
            	log.log(Level.INFO,"Erreur lors de la modification");
                systemeGestion.afficherInterface();
            }
        }
    }
}
