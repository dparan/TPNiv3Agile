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
	/* définition des constantes courrament utilisées dans les menu */
    private static final String CONSTANTEID = "Veuillez saisir l'identifiant du vol";
	private static final String DEPART = "Veuillez saisir la ville de départ du tronçon";
	private static final String ARRIVEE = "Veuillez saisir la ville d'arrivée du tronçon";
	private static final String ERR = "Erreur...";

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
        if(choix == 1){
            deconnexion(systemeGestion);
        } else if(choix == 2){
            ajoutPassager(systemeGestion);
        } else if(choix > 2 && choix < 7){
            erreur = gestionOptionVol(choix, systemeGestion, d, erreur);
        } else if(choix > 6 && choix < 10){
            erreur = gestionOptionTroncon(choix, systemeGestion, d, erreur);
        } else if(choix > 9 && choix < 15){
            erreur = gestionOptionVolTroncon(choix, systemeGestion, d, sc, erreur);
        } else if(choix == 15){
            systemeGestion.retourMenuPrecedent();
        } else {
            log.log(Level.INFO,ERR);
        }

        if(erreur)
        	log.log(Level.INFO,ERR);
        systemeGestion.setState(this);
    }

    
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     */
    private boolean gestionOptionVol(int choix, SystemeGestion systemeGestion, DecorateurMenuVolTroncon d, boolean erreur){
        switch (choix){   
            case 3:
            	System.out.println("Ajout d'un nouveau vol :");
                erreur = chekAjoutVol(systemeGestion);
                break;
            case 4:
            	System.out.println("Modification d'un vol existant :");
            	erreur = checkModifVol(systemeGestion);
                break;
            case 5:
            	System.out.println("Suppression d'un vol existant :");
                erreur = checkSupprVol(systemeGestion);
                break;
            case 6:
            	System.out.println("Recherche d'un vol existant :");
            	/** récupération des résultats dans un liste en vue d'un affichage 
            	 * l'id est -1, pour indiquer à la méthode rechercherVol qu'on souhaite utiliser findAll()
            	 **/
                List<Vol> v = systemeGestion.getSystemeGestionVol().rechercherVol(new Vol(
                    -1, 0,""));
                /* affichage des résultats de la recherche des vols */ 
                d.affichageListeVol(v);
                break;
            default:
                log.log(Level.INFO,ERR);
                break;
        }
        return erreur;
    }
    
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     */
    private boolean gestionOptionTroncon(int choix, SystemeGestion systemeGestion, DecorateurMenuVolTroncon d, boolean erreur){
        switch (choix){
            case 7:
                System.out.println("Ajout d'un nouveau tronçon :");
                erreur = checkAjoutTroncon(systemeGestion);
                break;            
            case 8:
                System.out.println("Modification d'un tronçon existant :");
                erreur = checkMajTroncon(systemeGestion);
                break;
            case 9:
                System.out.println("Suppression un tronçon existant :");
                erreur = checkSupprTroncon(systemeGestion);

                break;
                case 10:
                System.out.println("Recherche d'un tronçon existant :");
                /* récupération des résultats dans un liste en vue d'un affichage  */
                List<Troncon> listeTroncon = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString(DEPART), 
                        saisirString(ARRIVEE),  0));
                 /* affichage des résultats de la recherche des tronçons */ 
                d.affichageListeTroncon(listeTroncon);
                break;
            default:
                log.log(Level.INFO,ERR);
                break;
        }
        return erreur;
    }
    
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     */
    private boolean gestionOptionVolTroncon(int choix, SystemeGestion systemeGestion, DecorateurMenuVolTroncon d, Scanner sc, boolean erreur){
        switch (choix){
            case 11:
            	System.out.println("Association d'un tronçon à un vol :");
            	List<Troncon> listeTronconAssociation = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString(DEPART), 
                        saisirString(ARRIVEE)));
            	erreur = checkAssoVolTroncon(listeTronconAssociation,systemeGestion);
            	
                break;            
            case 12:
                System.out.println("Modification d'une association d'un tronçon à un vol :");
                List<Troncon> listeTronconModification = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString(DEPART), 
                        saisirString(ARRIVEE)));
            	erreur = checkModifVolTroncon(listeTronconModification,systemeGestion);
                
                break;            
            case 13:
                System.out.println("Supression d'une association d'un tronçon à un vol :");
                List<Troncon> listeTronconSuppression = systemeGestion.getSystemeGestionVol().rechercherTroncon(new Troncon(
                        saisirString(DEPART), 
                        saisirString(ARRIVEE)));
            	Troncon tronconSuppression= null;
            	/* si le tronçon existe (permet d'éviter de laisser des associations d'objets inexistants
            	 * , il faut supprimer d'abord l'association puis le tronçon */
            	if(listeTronconSuppression != null && !listeTronconSuppression.isEmpty())
            	{
            		tronconSuppression = listeTronconSuppression.get(0);
	                /* si la suppression s'est bien effectuée */
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
            	rechercheVolTroncon(systemeGestion, sc,d);
                break;

            default:
                log.log(Level.INFO,ERR);
                break;
        }
        return erreur;
    }
    
    
    
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     * vérifie si le tronçon existe avant de d'effecuer la modification 
     * renvoie un booléen correspondant au bon déroulement de l'action.
     */
    private boolean checkModifVolTroncon(List<Troncon> listeTronconModification, SystemeGestion systemeGestion) {
        boolean erreur = false;
        Troncon tronconModification = null;
        if(listeTronconModification != null && !listeTronconModification.isEmpty()){
            tronconModification = listeTronconModification.get(0);
            if(systemeGestion.getSystemeGestionVol().majVolTroncon(
                new Vol(saisirInt(CONSTANTEID), 0, ""),
                tronconModification,
                Time.valueOf(saisirHeure("Veuillez saisir la nouvelle heure de départ (format : hh:mm:ss)")), 
                Time.valueOf(saisirHeure("Veuillez saisir la nouvelle heure d'arrivée (format : hh:mm:ss)"))))
                log.log(Level.INFO, "L'association a bien été prise en modifiée");
            else
                erreur = true;
        } else
            erreur = true;
        return erreur;
    }

    /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     * vérifie si le tronçon existe avant de de rajouter l'association 
     * renvoie un booléen correspondant au bon déroulement de l'action.
     */
	private boolean checkAssoVolTroncon(List<Troncon> listeTronconAssociation,SystemeGestion systemeGestion) {
		boolean erreur = false;
		Troncon tronconAssociation = null;
    	if(listeTronconAssociation != null && !listeTronconAssociation.isEmpty())
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
    	return erreur;
        
	}

    /* 
     * méthode déclarée pour réduire la complexité cyclomatique 
     * permet la suppression d'une association vol - tronçon
     * renvoie un booléen correspondant au bon déroulement de la suppression.
     */
	private boolean checkSupprTroncon(SystemeGestion systemeGestion) {
		boolean erreur = false;
		if(systemeGestion.getSystemeGestionVol().supprimerTroncon(new Troncon(
                saisirString(DEPART), 
                saisirString(ARRIVEE),  0)))
        	log.log(Level.INFO, "Le tronçon a bien été supprimé");
        else
        	erreur = true;
		return erreur;
	}

    /* 
     * méthode déclarée pour réduire la complexité cyclomatique 
     * permet la recherche d'une association vol - tronçon
     * affiche l'ensemble des résultats selon le critére de choix de l'utilisateur.
     */
	private void rechercheVolTroncon(SystemeGestion systemeGestion,Scanner sc, DecorateurMenuVolTroncon d) {
		List<VolTroncon> listeVolTroncon = null;
    	int choix = sc.nextInt();
        switch (choix){
            case 1 :
            	/* si le vol n'est pas null, la méthode rechercherVlTroncon va utiliser ce vol comme critére de recherche */
            	listeVolTroncon = systemeGestion.getSystemeGestionVol().rechercherVolTroncon(
            			new Vol(saisirInt(CONSTANTEID), 0, ""), null, null, null);
            	break;
            case 2 :
            	/* si le vol est null, la méthode rechercherVlTroncon va utiliser le tronçon comme critére de recherche */
            	listeVolTroncon = systemeGestion.getSystemeGestionVol().rechercherVolTroncon(
            			null, new Troncon(
                                saisirString(DEPART), 
                                saisirString(ARRIVEE),  0), null, null);
            	break;
            default:
                log.log(Level.INFO,ERR);
                break;
        }
        d.affichageListeVolTroncon(listeVolTroncon);	
	}

    /* 
     * méthode déclarée pour réduire la complexité cyclomatique 
     * permet la mise à jour d'un tronçon
     * renvoie un booléen correspondant au bon déroulement de l'action.
     */
	private boolean checkMajTroncon(SystemeGestion systemeGestion) {
		boolean erreur = false;
		if(systemeGestion.getSystemeGestionVol().majTroncon(new Troncon(
                saisirString(DEPART), 
                saisirString(ARRIVEE), 
                saisirInt("Veuillez saisir la nouvelle distance du tronçon"))))
            	log.log(Level.INFO, "Le tronçon a bien été modifé");
            else
            	erreur = true;
		return erreur;
	}

    /* 
     * méthode déclarée pour réduire la complexité cyclomatique 
     * permet l'ajout d'un tronçon
     * renvoie un booléen correspondant au bon déroulement de l'action.
     */
	private boolean checkAjoutTroncon(SystemeGestion systemeGestion) {
        boolean erreur = false;
		if(systemeGestion.getSystemeGestionVol().ajouteTroncon(new Troncon(
                saisirString(DEPART),
                saisirString(ARRIVEE),
                saisirInt("Veuillez saisir la distance du tronçon"))))
            	log.log(Level.INFO, "Le tronçon a bien été ajouté");
            else
            	erreur = true;
        return erreur;
	}
	
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique 
     * permet la suppression d'un vol
     * renvoie un booléen correspondant au bon déroulement de l'action.
     */
	private boolean checkSupprVol(SystemeGestion systemeGestion) {
		boolean erreur = false;
		if(systemeGestion.getSystemeGestionVol().supprimerVol(new Vol(
                saisirInt(CONSTANTEID),0,"")))
            	log.log(Level.INFO, "Le vol a bien été supprimé");
            else
            	erreur = true;
		return erreur;
	}

    /* 
     * méthode déclarée pour réduire la complexité cyclomatique 
     * permet la modification d'un vol
     * renvoie un booléen correspondant au bon déroulement de l'action.
     */
	private boolean checkModifVol(SystemeGestion systemeGestion) {
		boolean erreur=false;
		if(systemeGestion.getSystemeGestionVol().majVol(new Vol(
                saisirInt("Veuillez saisir l'identifiant du vol cible"),
                saisirInt("Veuillez saisir la nouvelle fréquence du vol:"),
                saisirString("Nouvelle unité (heure, jour, semaine, mois, an):")))
        			)
        		log.log(Level.INFO, "Le vol a bien été modifié");
            else
            	erreur = true;
		return erreur;
	}
	
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique 
     * permet l'ajout d'un vol
     * renvoie un booléen correspondant au bon déroulement de l'action.
     */
	private boolean chekAjoutVol(SystemeGestion systemeGestion) {
		boolean erreur=false;
		if(systemeGestion.getSystemeGestionVol().ajouteVol(new Vol(
                saisirInt("Veuillez saisir la fréquence du vol:"),
                saisirString("Unité (heure, jour, semaine, mois, an):")))
            		)
            	log.log(Level.INFO, "Le vol a bien été ajouté");
            else
            	erreur = true;
		return erreur;
	}
}
