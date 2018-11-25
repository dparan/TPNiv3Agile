package state;

import java.sql.Time;
import java.util.List;
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
        boolean erreur = false;
        switch (choix){
            case 1 :
                deconnexion(systemeGestion);
                break;
            case 2 :
                ajoutPassager(systemeGestion);
                break;    
            case 3:
            	System.out.println("Ajout d'un nouveau vol :");
                if(systemeGestion.getSystemeGestionVol().ajouteVol(new Vol(
                    saisirInt("Veuillez saisir la fréquence du vol:"),
                    saisirString("Unité (heure, jour, semaine, mois, an):")))
                		)
                	log.log(Level.INFO, "Le vol a bien été ajouté");
                else
                	erreur = true;
                break;
            case 4:
            	System.out.println("Modification d'un vol existant :");
            	if(systemeGestion.getSystemeGestionVol().majVol(new Vol(
                    saisirInt("Veuillez saisir l'identifiant du vol cible"),
                    saisirInt("Veuillez saisir la nouvelle fréquence du vol:"),
                    saisirString("Nouvelle unité (heure, jour, semaine, mois, an):")))
            			)
            		log.log(Level.INFO, "Le vol a bien été modifié");
                else
                	erreur = true;
                break;
            case 5:
            	System.out.println("Suppression d'un vol existant :");
                if(systemeGestion.getSystemeGestionVol().supprimerVol(new Vol(
                    saisirInt(CONSTANTEID),0,"")))
                	log.log(Level.INFO, "Le vol a bien été supprimé");
                else
                	erreur = true;
                break;
            case 6:
            	System.out.println("Recherche d'un vol existant :");
                List<Vol> v = systemeGestion.getSystemeGestionVol().rechercherVol(new Vol(
                    0, 0,""));
                d.affichageListeVol(v);
                break;
            case 7:
            	System.out.println("Ajout d'un nouveau tronçon :");
                if(systemeGestion.getSystemeGestionVol().ajouteTroncon(new Troncon(
                    saisirString(DEPART),
                    saisirString(ARRIVEE),
                    saisirInt("Veuillez saisir la distance du tronçon"))))
                	log.log(Level.INFO, "Le tronçon a bien été ajouté");
                else
                	erreur = true;
                break;            case 8:
            	System.out.println("Modification d'un tronçon existant :");
                if(systemeGestion.getSystemeGestionVol().majTroncon(new Troncon(
                    saisirString(DEPART), 
                    saisirString(ARRIVEE), 
                    saisirInt("Veuillez saisir la nouvelle distance du tronçon"))))
                	log.log(Level.INFO, "Le tronçon a bien été modifé");
                else
                	erreur = true;
                break;
            case 9:
            	System.out.println("Suppression un tronçon existant :");
                if(systemeGestion.getSystemeGestionVol().supprimerTroncon(new Troncon(
                        saisirString(DEPART), 
                        saisirString(ARRIVEE),  0)))
                	log.log(Level.INFO, "Le tronçon a bien été supprimé");
                else
                	erreur = true;
                break;
                case 10:
            	System.out.println("Recherche d'un tronçon existant :");
                List<Troncon> listeTroncon = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString(DEPART), 
                        saisirString(ARRIVEE),  0));
                d.affichageListeTroncon(listeTroncon);
                break;
            case 11:
            	System.out.println("Association d'un tronçon à un vol :");
            	List<Troncon> listeTronconAssociation = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString("Veuillez saisir la ville de départ du tronçon"), 
                        saisirString("Veuillez saisir la ville d'arrivée du tronçon")));
            	Troncon tronconAssociation = null;
            	if(listeTronconAssociation != null & listeTronconAssociation.size() != 0)
            	{
            		tronconAssociation = listeTronconAssociation.get(0);
            		if(systemeGestion.getSystemeGestionVol().associerVolTroncon(
                            new Vol(saisirInt(CONSTANTEID), 0, ""),
                            tronconAssociation,
                            Time.valueOf(saisirHeure("Veuillez saisir l'heure de départ (format : hh:mm:ss)")), 
                            Time.valueOf(saisirHeure("Veuillez saisir l'heure d'arrivée (format : hh:mm:ss)"))))
        	                log.log(Level.INFO, "Le vol a bien été associé au tronçon");
                        else
                        	erreur = true;
            	}
            	else
            		erreur = true;
                
                break;            
            case 12:
                System.out.println("Modification d'une association d'un tronçon à un vol :");
                List<Troncon> listeTronconModification = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString("Veuillez saisir la ville de départ du tronçon"), 
                        saisirString("Veuillez saisir la ville d'arrivée du tronçon")));
            	Troncon tronconModification = null;
            	if(listeTronconModification != null & listeTronconModification.size() != 0)
            	{
            		tronconModification = listeTronconModification.get(0);
	                if(systemeGestion.getSystemeGestionVol().majVolTroncon(
	                    new Vol(saisirInt(CONSTANTEID), 0, ""),
	                    tronconModification,
	                    Time.valueOf(saisirHeure("Veuillez saisir la nouvelle heure de départ (format : hh:mm:ss)")), 
	                    Time.valueOf(saisirHeure("Veuillez saisir la nouvelle heure d'arrivée (format : hh:mm:ss)"))))
		                log.log(Level.INFO, "L'association a bien été prise en modifiée");
		                else
		                	erreur = true;
            	}
            	else
            		erreur = true;
                break;            
            case 13:
                System.out.println("Supression d'une association d'un tronçon à un vol :");
                List<Troncon> listeTronconSuppression = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString("Veuillez saisir la ville de départ du tronçon"), 
                        saisirString("Veuillez saisir la ville d'arrivée du tronçon")));
            	Troncon tronconSuppression= null;
            	if(listeTronconSuppression != null & listeTronconSuppression.size() != 0)
            	{
            		tronconSuppression = listeTronconSuppression.get(0);
	                if(systemeGestion.getSystemeGestionVol().supprimerVolTroncon(
	                    new Vol(saisirInt(CONSTANTEID), 0, ""),
	                    tronconSuppression,
	                    null, null))
	                	log.log(Level.INFO, "L'association a bien été supprimé");
	                else
	                	erreur = true;
            	}
            	else
            		erreur = true;
                break;            
            case 14:
            	System.out.println("Recherche d'une association d'un tronçon à un vol :");
            	System.out.println(" 1 --> Par Id de Vol");
            	System.out.println(" 2 --> Par villes depart/arrivee du Tronçon");
            	List<VolTroncon> listeVolTroncon = null;
            	choix = sc.nextInt();
                switch (choix){
                    case 1 :
                    	listeVolTroncon = systemeGestion.getSystemeGestionVol().rechercherVolTroncon(
                    			new Vol(saisirInt(CONSTANTEID), 0, ""), null, null, null);
                    	break;
                    case 2 :
                    	listeVolTroncon = systemeGestion.getSystemeGestionVol().rechercherVolTroncon(
                    			null, new Troncon(
                                        saisirString(DEPART), 
                                        saisirString(ARRIVEE),  0), null, null);
                    	break;
                    default:
                        log.log(Level.INFO,"Erreur...");
                        break;
                }
                d.affichageListeVolTroncon(listeVolTroncon);
                break;
            case 15:
                systemeGestion.retourMenuPrecedent();
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
        if(erreur)
        	log.log(Level.INFO,"Erreur...");
        systemeGestion.setState(this);
    }
}
