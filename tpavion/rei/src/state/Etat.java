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
		log.log(Level.INFO,"Ajout d'un passager à un départ");
		log.log(Level.INFO,"Création / Recherche du passager :");
		String passeport = saisirString(" numéro de passeport du passagé :");
		Passager passager = systemeGestion.getSystemeGestionUtilisateur().rechercherPassager(new Passager(passeport));
		if(passager == null) {
			String prenom = saisirString(" prénom :");
			String nom = saisirString(" nom :");
			String adresse = saisirString(" adresse :");
			String noTel = saisirString(" numéro de téléphone :");
			passager = new Passager(passeport,nom,prenom,adresse,noTel);
			systemeGestion.getSystemeGestionUtilisateur().creerPassager(passager);
		}
		log.log(Level.INFO," Recherche du depart : ");
		Date date = Date.valueOf(saisirString("Veuillez saisir la date du départ (format : yyyy-[m]m-[d]d)"));
		String villeDepart = saisirString(" ville de départ :");
		String villeArrivee = saisirString(" ville d'arrivée :");
		Time heureDepart = Time.valueOf(saisirString("Veuillez saisir la nouvelle heure de départ (format : hh:mm:ss)"));
		systemeGestion.getSystemeGestionUtilisateur().associerPassagerDepart(passager,date,villeDepart,villeArrivee,heureDepart);
		systemeGestion.setState(this);
    }

    /**
     * Méthode permettant de saisir une valeur numérique
     * @param intitule
     * @return int
     */
    public int saisirInt(String intitule) {
        log.log(Level.INFO,intitule);
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
    	log.log(Level.INFO,intitule);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    
    public String saisirDate(String s)
    {
    	s = saisirString(s);
    	if(s.split("-").length != 3)
    	{
    		log.log(Level.INFO,"La date saisie est incorrecte"+s.split("-").length);
    		return null;
    	}
    	try {
	    	for(String elt : s.split("-"))
	    	{
	    		Integer.parseInt(elt);
	    	}
    	}
    	catch(NumberFormatException e){
    		log.log(Level.INFO,"La date saisie est incorrecte");						//VERIFIER
    		return null;
    	}
    	return s;
    }


}
