package state;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import data_model.Avion;
import data_model.Depart;
import data_model.DepartAvion;
import data_model.Personnel;
import data_model.Role;
import data_model.Vol;
import decorator.DecorateurMenuDepart;
import decorator.DecorateurMenuPersonnel;
import decorator.DecorateurNonNavigant;
import decorator.Implementation;
import systeme.SystemeGestion;

public class EtatMenuDepart extends Etat{
	private static final String CONSTANTEID = "Veuillez saisir l'identifiant du départ";
	private static final String CONSTANTEDATE ="Veuillez saisir la date du départ (format : yyyy-[m]m-[d]d)";
	private static final String CONSTANTEAVION = "Veuillez entrer l'immatriculation de l'avion qui effectuera le vol";
    /**
     * Méthode qui contient toutes les actions qu'un membre du service gestionnaire peut faire en relation avec la gestion des départs
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurMenuDepart d = new DecorateurMenuDepart(new DecorateurNonNavigant(new Implementation()));
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
                log.log(Level.INFO,"Ajout d'un nouveau depart :");
                String dateAjoutDepart = saisirDate(CONSTANTEDATE);
                System.out.println("date : "+dateAjoutDepart);
                if(dateAjoutDepart != null)
                	{
                	systemeGestion.getSystemeGestionDepart().ajouteDepart(new Depart(
                       new Vol(saisirInt(CONSTANTEID), 0, ""), 
                       		Date.valueOf("2015-02-2")));
                	}
                    
                break;
            case 4:
                systemeGestion.setState(new EtatModificationDepart());
                /*log.log(Level.INFO,"Modification un depart existant :");
                String dateModificationDepart = saisirString(CONSTANTEDATE);
                if(dateModificationDepart != null)
                {
                	systemeGestion.getSystemeGestionDepart().majDepart(new Depart(
                    new Vol(saisirInt("Veuillez saisir l'identifiant du départ cible"), 0, ""), 
                    Date.valueOf(dateModificationDepart)));
                }*/
                break;
            case 5:
                log.log(Level.INFO,"Suppression un depart existant :");
                System.out.println("res : "+systemeGestion.getSystemeGestionDepart().supprimerDepart(new Depart(
                    new Vol(saisirInt(CONSTANTEID), 0, ""), 
                    null)) );
                break;
            case 6:
                log.log(Level.INFO,"Recherche d'un depart existant :");
                Depart d16 = systemeGestion.getSystemeGestionDepart().rechercherDepart(new Depart(
                    new Vol(saisirInt(CONSTANTEID), 0, ""),
                    Date.valueOf(saisirString(CONSTANTEDATE))));
                d16.toString();
                break;
            case 7:
                log.log(Level.INFO,"Programmation d'un vol en un départ :");
                Vol v =  systemeGestion.getSystemeGestionVol().rechercherVol(new Vol(saisirInt("Veuillez saisir l'identifiant du vol à programmer"),0, ""));
                Depart dateDepart = new Depart(v,Date.valueOf(saisirString(CONSTANTEDATE)));
                systemeGestion.getSystemeGestionDepart().ajouteDepartAvion(new DepartAvion(dateDepart,
                    new Avion(saisirString(CONSTANTEAVION)),
                    saisirInt("Veuillez entrer la quantit� de carburant nécéssaire pour le vol")));
                break;
            case 8:
                systemeGestion.setState(new EtatModificationDepartAvion());
                /*log.log(Level.INFO,"Modification de la programmation d'un départ existant :");
                Vol vol =  systemeGestion.getSystemeGestionVol().rechercherVol(new Vol(saisirInt("Veuillez saisir l'identifiant du vol à programmer"), 0, ""));
                Depart dateModif = new Depart(vol,Date.valueOf(saisirString(CONSTANTEDATE)));
                systemeGestion.getSystemeGestionDepart().majDepartAvion(new DepartAvion(dateModif,
                        new Avion(saisirString(CONSTANTEAVION)),
                        saisirInt("Veuillez entrer la quantit� de carburant nécéssaire pour le vol")));*/
                break;
            case 9:
                log.log(Level.INFO,"Suppression de la programmation d'un départ existant :");
                Vol volSuppr =  systemeGestion.getSystemeGestionVol().rechercherVol(new Vol(saisirInt("Veuillez saisir l'identifiant du vol à supprimer"), 0, ""));
                Depart departSuppr = new Depart(volSuppr,Date.valueOf(saisirString(CONSTANTEDATE)));
                
                systemeGestion.getSystemeGestionDepart().supprimerDepartAvion(new DepartAvion(
                    departSuppr,
                    new Avion(saisirString(CONSTANTEAVION)),
                    0));
                break;
            case 10:
                log.log(Level.INFO,"Recherche de la programmation d'un depart existant :");
                Vol volRech =  systemeGestion.getSystemeGestionVol().rechercherVol(new Vol(saisirInt("Veuillez saisir l'identifiant du vol à supprimer"), 0, ""));
                Depart departRech = new Depart(volRech,Date.valueOf(saisirString(CONSTANTEDATE)));
              
                DepartAvion d9 = systemeGestion.getSystemeGestionDepart().rechercherDepartAvion(new DepartAvion(
                        departRech,
                        new Avion(saisirString(CONSTANTEAVION)),
                    0));
                d9.toString();
                break;
            case 11:
                systemeGestion.retourMenuPrecedent();
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
        systemeGestion.setState(this);
    }
    
    /**
     * Méthode permettant l'ajout d'un utilisateur
     * @param systemeGestion
     */
    private void ajoutDepart(SystemeGestion systemeGestion, DecorateurMenuDepart d) {
        String nom = saisirString("Nom :");
        String prenom = saisirString("Prenom :");
        String adresse = saisirString("Adresse :");
        String noTelephone = saisirString("Numéro de téléphone :");
        String motDePasse = saisirString("Mot de passe :");
        
        //d.affichageTypeRole();
        /*int t = saisirInt("Type :");
        //String type = creerType(t);
        
        List<Role> roles = systemeGestion.getSystemeGestionUtilisateur().getRoles();
        //d.affichageListeRoles(roles);
        int role = saisirInt("Rôle :");
        */
        Personnel personnel = null;
        /*if(role<=roles.size()) {
            Role r = new Role(type, roles.get(role - 1).getRole());
            personnel = new Personnel(nom,prenom,adresse,noTelephone,motDePasse,r);
        }*/
        if(personnel==null || !systemeGestion.getSystemeGestionUtilisateur().ajouterUtilisateur(personnel))
            log.log(Level.INFO,"erreur lors de l'ajout");
        else
            log.log(Level.INFO,"Ajout effectué");
    }
    
    
    
    
}
