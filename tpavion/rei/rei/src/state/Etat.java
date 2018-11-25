package state;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import data_model.Passager;
import systeme.SystemeGestion;

public abstract class Etat 
{
	Logger log = Logger.getLogger(getClass().getName());
    /**
     * Méthode qui contient toutes les actions é effectuer
     * @param systemeGestion
     */
    public abstract void goNext(SystemeGestion systemeGestion);
    // méthode qui sera utilisée pour effectuer des actions et si il y a des états
    // qui suivent l'etat courant alors on déterminera l'etat suivant dans cette méthode

    /**
     * Méthode permettant la déconnexion
     * @param systemeGestion
     */
    void deconnexion(SystemeGestion systemeGestion) {
		log.log(Level.INFO,"Déconnexion ...");
		systemeGestion.deconnexion();
    }

    /**
     * Méthode permettant l'ajout d'un passager à un vol
     * @param systemeGestion
     */
    void ajoutPassager(SystemeGestion systemeGestion) {
    	System.out.println("Ajout d'un passager à un départ");
		System.out.println("Création / Recherche du passager :");
		String passeport = saisirString(" numéro de passeport du passager :");
		Passager passager = systemeGestion.getSystemeGestionUtilisateur().rechercherPassager(new Passager(passeport));
		boolean erreur = false;
		if(passager == null) {
			String prenom = saisirString(" prénom :");
			String nom = saisirString(" nom :");
			String adresse = saisirString(" adresse :");
			String noTel = saisirString(" numéro de téléphone :");
			passager = new Passager(passeport,nom,prenom,adresse,noTel);
			if(systemeGestion.getSystemeGestionUtilisateur().creerPassager(passager))
				log.log(Level.INFO,"passager créé");
			else
				erreur = true;
		}
		if(!erreur)
		{
			log.log(Level.INFO,"passager trouvé");
			System.out.println(" Recherche du depart : ");
			String dateDepart = saisirDate("Veuillez saisir la date du départ (format : yyyy-[m]m-[d]d)");
			Date date = Date.valueOf(dateDepart);
			String villeDepart = saisirString(" ville de départ :");
			String villeArrivee = saisirString(" ville d'arrivée :");
			String heureDepart = saisirHeure("Veuillez saisir l'heure de départ (format : hh:mm:ss)");
			if(heureDepart != null & systemeGestion.getSystemeGestionUtilisateur().associerPassagerDepart(passager,date,villeDepart,villeArrivee,Time.valueOf(heureDepart)))
				log.log(Level.INFO,"Association passager / départ effectuée");
			else
				erreur = true;
		}
		if(erreur)
		{
			log.log(Level.INFO,"Erreur....");
		}
		systemeGestion.setState(this);
    }

    /**
     * Méthode permettant de saisir une valeur numérique
     * @param intitule
     * @return int
     */
    public int saisirInt(String intitule) {
    	System.out.println(intitule);
        try{
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        } catch (InputMismatchException e){
            log.log(Level.INFO,"Erreur de saisie (type de donnée non conforme");
            return saisirInt(intitule);
        }
    }

    /**
     * Méthode permettant de saisir une chaine de caractéres
     * @param intitule
     * @return String
     */
    public String saisirString(String intitule) {
    	System.out.println(intitule);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    public String saisirDate(String s)
    {
    	s = saisirString(s);
    	String erreur = "La date saisie est incorrecte";
    	if(s.split("-").length != 3)
    	{
    		log.log(Level.INFO,erreur);
    		return saisirDate(s);
    	}
    	try {
    		int cpt = 0;
	    	for(String elt : s.split("-"))
	    	{
	    		if((cpt == 0 & elt.length() != 4) | ((cpt != 0 & cpt < 3) & elt.length() > 2))
	    		{
	    			log.log(Level.INFO,erreur);
	        		return saisirDate(s);
	    		}
	    		Integer.parseInt(elt);
	    		cpt++;
	    	}
    	}
    	catch(NumberFormatException e){
    		log.log(Level.INFO,erreur);						//VERIFIER
    		return saisirDate(s);
    	}
    	return s;
    }
    
    public String saisirHeure(String s)
    {
    	s = saisirString(s);
    	String erreur = "L'heure saisie est incorrecte";
    	if(s.split(":").length != 3)
    	{
    		log.log(Level.INFO,erreur);
    		return saisirHeure(s);
    	}
    	try {
    		int cpt = 0;
	    	for(String elt : s.split(":"))
	    	{
	    		if(elt.length() != 2)
	    		{
	    			log.log(Level.INFO,erreur);
	        		return saisirHeure(s);
	    		}
	    		Integer.parseInt(elt);
	    		cpt++;
	    	}
    	}
    	catch(NumberFormatException e){
    		log.log(Level.INFO,erreur);						//VERIFIER
    		return saisirHeure(s);
    	}
    	return s;
    }


}
