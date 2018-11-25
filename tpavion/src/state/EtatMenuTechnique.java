package state;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import data_model.Avion;
import data_model.TypeAvion;
import decorator.*;
import systeme.SystemeGestion;

public class EtatMenuTechnique extends Etat {
    /**
     * Méthode qui contient toutes les actions qu'un membre du service technique peut effectuer
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurMenuTechnique d = new DecorateurMenuTechnique(new DecorateurNonNavigant(new Implementation()));
        d.affichage();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        if(choix == 1){
            deconnexion(systemeGestion);
        } else if(choix == 2){
            ajoutPassager(systemeGestion);
        } else if(choix > 2 && choix < 7){
            gestionOptionAvion(choix, systemeGestion, d);
        } else if(choix == 7){
            System.out.println("Supprimer un type d'avion");
            /* récupération des résultats dans un liste en vue d'un affichage */
            List<TypeAvion> types = systemeGestion.getSystemeGestionAvion().rechercherTypes();
            /* affichage des résultats de la recherche des types d'avion : */
            d.affichageListeType(types);
            /* après avoir vu tous la liste des avions, le technicien saisi le type cible : */
            int type = saisirInt(" Type :");
            /* si le type est valide, et si la suppression a fonctionnée : */
            if(type <= types.size() && systemeGestion.getSystemeGestionAvion().supprimerTypeAvion(types.get(type-1)))
                log.log(Level.INFO,"Type d'avion supprimé.");
            else
                log.log(Level.INFO,"Erreur lors de la suppression");
        } else if(choix == 8){
            systemeGestion.retourMenuPrecedent();
        } else {
            log.log(Level.INFO,"Erreur...");
        }
        
        systemeGestion.setState(this);
    }
    
    /* 
     * méthode déclarée pour réduire la complexité cyclomatique
     */
    private void  gestionOptionAvion(int choix, SystemeGestion systemeGestion, DecorateurMenuTechnique d){
        switch (choix){
            case 3:
            	System.out.println("Ajout d'un nouvel avion");
                ajoutAvion(systemeGestion,d);
                break;
            case 4:
            	System.out.println("Ajout d'un nouveau type d'avion");
                ajoutTypeAvion(systemeGestion);
                break;
            case 5:
            	System.out.println("Modifier un avion");
                systemeGestion.setState(new EtatModificationTechnique());
                break;
            case 6:
            	System.out.println("Supprimer un avion");
            	/* récupération des résultats dans un liste en vue d'un affichage */
                List<Avion> avions = systemeGestion.getSystemeGestionAvion().rechercherAvions();
                /* affichage des résultats de la recherche des avions */
                d.afficherAvions(avions);
                /* après avoir vu tous la liste des avions, le technicien saisi l'immatriculation de l'avion cible : */
                String immatriculation = saisirString(" Immatriculation :");
            	/* si la suppression s'est bien effectuée : */   
                if(systemeGestion.getSystemeGestionAvion().supprimerAvion(new Avion(immatriculation)))
                    log.log(Level.INFO,"Avion supprimé .");
                else
                    log.log(Level.INFO,"Erreur lors de la suppression");
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
    }
    
    /**
     * Méthode contenant l'ajout d'un type d'avion
     * @param systemeGestion
     */
    private void ajoutTypeAvion(SystemeGestion systemeGestion) {
        String type = saisirString(" Type d'avion :");
    	/* si l'ajout s'est bien effectué : */
	    if(systemeGestion.getSystemeGestionAvion().ajouterTypeAvion(new TypeAvion(type)))
	            log.log(Level.INFO,"Ajout de l'avion effectué.");
	    else
	            log.log(Level.INFO,"Erreur lors de l'ajout ...");
    }

    /**
     * Méthode permettant l'ajout d'un avion
     * @param systemeGestion
     */
    private void ajoutAvion(SystemeGestion systemeGestion, DecorateurTechnique d) {
        String immatriculation = saisirString(" Immatriculation:");
        int capacite = saisirInt(" Capacite :");
    	/* récupération des résultats dans un liste en vue d'un affichage */
        List<TypeAvion> types = systemeGestion.getSystemeGestionAvion().rechercherTypes();
        /* affichage des résultats de la recherche des avions : */
        d.affichageListeType(types);
        int type = saisirInt(" Type :");
    	/* si l'ajout s'est bien effectué : */
        if(type <= types.size() && systemeGestion.getSystemeGestionAvion().ajouterAvion(new Avion(immatriculation,capacite,types.get(type - 1))))
            log.log(Level.INFO,"Ajout de l'avion effectué.");
        else
            log.log(Level.INFO,"Erreur lors de l'ajout ...");
        }
}
