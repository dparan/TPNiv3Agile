package state;

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
		/* on vérifie si le passager que l'on souhaite créer existe ou non dans la bdd
		 * si il n'existe pas, on le créé */
		if(passager == null) {
			String prenom = saisirString(" prénom :");
			String nom = saisirString(" nom :");
			String adresse = saisirString(" adresse :");
			String noTel = saisirString(" numéro de téléphone :");
			passager = new Passager(passeport,nom,prenom,adresse,noTel);
			/* si la création s'est bien effectuée */
			if(systemeGestion.getSystemeGestionUtilisateur().creerPassager(passager))
				log.log(Level.INFO,"passager créé");
			else
				erreur = true;
		}
		/* si le processus s'est bien déroulé, il faut ensuite ajouter le passager à un vol */
		if(!erreur)
		{
			log.log(Level.INFO,"passager trouvé");
			System.out.println(" Recherche du depart : ");
			String dateDepart = saisirDate("Veuillez saisir la date du départ (format : yyyy-[m]m-[d]d)");
			Date date = Date.valueOf(dateDepart);
			String villeDepart = saisirString(" ville de départ :");
			String villeArrivee = saisirString(" ville d'arrivée :");
			String heureDepart = saisirHeure("Veuillez saisir l'heure de départ (format : hh:mm:ss)");
			/* si l'associatin s'et bien déroulée */
			if(systemeGestion.getSystemeGestionUtilisateur().associerPassagerDepart(passager,date,villeDepart,villeArrivee,Time.valueOf(heureDepart)))
				log.log(Level.INFO,"Association passager / départ effectuée");
			else
				erreur = true;
		}
		if(erreur)
		{
			log.log(Level.INFO,"Erreur....");
		}
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
    
    /**
     * Méthode permettant de saisir une date
     * @param string
     * @return String
     */
    public String saisirDate(String s)
    {
    	s = saisirString(s);
    	String erreur = "La date saisie est incorrecte";
    	/* on vérifie si il y a bien 3 champs séparés par le bon séparateur ("-") */
    	if(s.split("-").length != 3)
    	{
    		log.log(Level.INFO,erreur);
    		return saisirDate(s);
    	}
    	try {
    		int cpt = 0;
    		/* pour chacun des champs, on vérifie le nombre de caractère, ainsi que le type de donnée */
	    	for(String elt : s.split("-"))
	    	{
	    		boolean verifDate = cpt == 0 && elt.length() != 4;
				boolean verifFin =  ((cpt != 0 && cpt < 3) && elt.length() > 2);
				/* si un des champs est incorrect : */
	    		if(verifDate || verifFin)
	    		{
	    			log.log(Level.INFO,erreur);
	        		return saisirDate(s);
	    		}
	    		/* on vérifie le type de donnée (int), sinon renvoie une erreur qui sera attrapée */
	    		Integer.parseInt(elt);
	    		cpt++;
	    	}
    	}
    	/* cas où les champs contiennent autre chose que des int */
    	catch(NumberFormatException e){
    		log.log(Level.INFO,erreur);
    		return saisirDate(s);
    	}
    	return s;
    }
    
    /**
     * Méthode permettant de saisir une heure
     * @param string
     * @return String
     */
    public String saisirHeure(String s)
    {
    	s = saisirString(s);
    	String erreur = "L'heure saisie est incorrecte";
    	/* on vérifie si il y a bien 3 champs séparés par le bon séparateur (":") */
    	if(s.split(":").length != 3)
    	{
    		log.log(Level.INFO,erreur);
    		return saisirHeure(s);
    	}
    	try {
    		/* pour chacun des champs, on vérifie le nombre de caractère, ainsi que le type de donnée */
	    	for(String elt : s.split(":"))
	    	{
	    		/* si un des champs ne contient pas le bon nombre de caractères : */
	    		if(elt.length() != 2)
	    		{
	    			log.log(Level.INFO,erreur);
	        		return saisirHeure(s);
	    		}
	    		/* on vérifie le type de donnée (int), sinon renvoie une erreur qui sera attrapée */
	    		Integer.parseInt(elt);
	    	}
    	}
    	/* cas où les champs contiennent autre chose que des int */
    	catch(NumberFormatException e){
    		log.log(Level.INFO,erreur);
    		return saisirHeure(s);
    	}
    	return s;
    }


}
