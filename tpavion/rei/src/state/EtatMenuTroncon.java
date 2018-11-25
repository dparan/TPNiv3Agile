package state;

import java.sql.Time;
import java.util.Scanner;
import java.util.logging.Level;

import data_model.Troncon;
import data_model.Vol;
import data_model.VolTroncon;
import decorator.DecorateurMenuVolTroncon;
import decorator.DecorateurNonNavigant;
import decorator.Implementation;
import systeme.SystemeGestion;


public class EtatMenuTroncon extends Etat{

    private static final String CONSTANTEID = "Veuillez saisir l'identifiant du vol";
	private static final String DEPART = "Veuillez saisir la ville de départ du tronçon";
	private static final String ARRIVEE = "Veuillez saisir la ville d'arrivée du tronçon";
	private static final String ELEMENTNONTROUVE = "Element non trouvé...";

	/**
     * Méthode qui contient toutes les actions qu'un membre du service gestionnaire peut faire en relation avec la gestion des vols/tronçon
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurMenuVolTroncon d = new DecorateurMenuVolTroncon(new DecorateurNonNavigant(new Implementation()));
        d.affichage();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        switch (choix){
            case 1 :
                deconnexion(systemeGestion);
                break;
            case 2 :
                ajoutPassager(systemeGestion);
                break;    
            case 3:
                log.log(Level.INFO,"Ajout d'un nouveau vol :");
                systemeGestion.getSystemeGestionVol().ajouteVol(new Vol(
                    saisirInt("Veuillez saisir la fréquence du vol:"),
                    saisirString("Unité (heure, jour, semaine, mois, an):")));
                    break;
            case 4:
                log.log(Level.INFO,"Modification d'un vol existant (par identifiant) :");
                systemeGestion.getSystemeGestionVol().majVol(new Vol(
                    saisirInt("Veuillez saisir l'identifiant du vol cible"),
                    saisirInt("Veuillez saisir la nouvelle fréquence du vol:"),
                    saisirString("Unité (heure, jour, semaine, mois, an):")));
                    break;
            case 5:
                log.log(Level.INFO,"Suppression d'un vol existant (par identifiant) :");
                systemeGestion.getSystemeGestionVol().supprimerVol(new Vol(
                    saisirInt(CONSTANTEID),0,""));
                break;
            case 6:
                log.log(Level.INFO,"Recherche d'un vol existant (par identifiant) :");
                Vol v15 = systemeGestion.getSystemeGestionVol().rechercherVol(new Vol(
                    saisirInt(CONSTANTEID), 0,""));
                String msg="";
                if(v15!=null) {
                	msg=v15.toString();
                }
                else {
                	msg=ELEMENTNONTROUVE;
                }
                log.log(Level.INFO, msg);
                break;
            case 7:
                log.log(Level.INFO,"Ajout d'un nouveau tronçon :");
                systemeGestion.getSystemeGestionVol().ajouteTroncon(new Troncon(
                    saisirString(DEPART),
                    saisirString(ARRIVEE),
                    saisirInt("Veuillez saisir la distance du tronçon")));
                break;
            case 8:
                log.log(Level.INFO,"Modification un tronçon existant (par identifiant) :");
                systemeGestion.getSystemeGestionVol().majTroncon(new Troncon(
                    saisirString(DEPART), 
                    saisirString(ARRIVEE), 
                    saisirInt("Veuillez saisir la nouvelle distance du tronçon")));
                    break;
            case 9:
                log.log(Level.INFO,"Suppression un tronçon existant (par identifiant) :");
                systemeGestion.getSystemeGestionVol().supprimerTroncon(new Troncon(
                        saisirString(DEPART), 
                        saisirString(ARRIVEE),  0));
                break;
            case 10:
                log.log(Level.INFO,"Recherche un tronçon existant (par identifiant) :");
                Troncon t17 = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString(DEPART), 
                        saisirString(ARRIVEE),  0));
                String message="";
                if(t17!=null) {
                	message=t17.toString();
                }
                else {
                	message=ELEMENTNONTROUVE;
                }
                log.log(Level.INFO, message);
                break;
            case 11:
                log.log(Level.INFO,"Association d'un vol à un tronçon :");
                systemeGestion.getSystemeGestionVol().associerVolTroncon(
                    new Vol(saisirInt(CONSTANTEID), 0, ""),//SEUL L'IDENTIFIANT SERA UTILISE
                    systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                            saisirString("Veuillez saisir la nouvelle ville de départ du tronçon"), 
                            saisirString("Veuillez saisir la nouvelle ville d'arrivée du tronçon"),  0)),//SEUL L'IDENTIFIANT SERA UTILISE
                    Time.valueOf(saisirString("Veuillez saisir l'heure de départ (format : hh:mm:ss)")), 
                    Time.valueOf(saisirString("Veuillez saisir l'heure d'arrivée (format : hh:mm:ss)")));
                break;
            case 12://MODIFIER LE TEXTE
                log.log(Level.INFO,"Modification d'une association d'un vol à un tronçon :");
                systemeGestion.getSystemeGestionVol().majVolTroncon(
                    new Vol(saisirInt(CONSTANTEID), 0, ""),//SEUL L'IDENTIFIANT SERA UTILIS�
                    systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                            saisirString(DEPART), 
                            saisirString(ARRIVEE),  0)),//SEUL L'IDENTIFIANT SERA UTILIS�
                    Time.valueOf(saisirString("Veuillez saisir la nouvelle heure de départ (format : hh:mm:ss)")), 
                    Time.valueOf(saisirString("Veuillez saisir la nouvelle heure d'arrivée (format : hh:mm:ss)")));
                break;
            case 13:
                log.log(Level.INFO,"Supression d'une association d'un vol à un tronçon :");
                systemeGestion.getSystemeGestionVol().supprimerVolTroncon(
                    new Vol(saisirInt(CONSTANTEID), 0, ""),//SEUL L'IDENTIFIANT SERA UTILIS�
                    systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                            saisirString(DEPART), 
                            saisirString(ARRIVEE),  0)),null, null);
                break;
            case 14:
                log.log(Level.INFO,"Recherche d'une association d'un vol à un tronçon :");
                VolTroncon v18 = systemeGestion.getSystemeGestionVol().rechercherVolTroncon(
                    new Vol(saisirInt(CONSTANTEID), 0, ""), 
                    systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                            saisirString(DEPART), 
                            saisirString(ARRIVEE),  0)), null, null);

                String messag="";
                if(v18!=null) {
                	messag=v18.toString();
                }
                else {
                	messag=ELEMENTNONTROUVE;
                }
                log.log(Level.INFO, messag);
                break;
            case 15:
                systemeGestion.retourMenuPrecedent();
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
        systemeGestion.setState(this);
    }
}
