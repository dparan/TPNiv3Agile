package state;

import data_model.RapportPilote;
import data_model.TypeAvion;
import java.util.Scanner;
import java.util.logging.Level;

import decorator.DecorateurMenuPilote;
import decorator.Implementation;
import java.sql.Time;
import java.util.List;
import systeme.SystemeGestion;

public class EtatMenuPilote extends Etat {
    /**
     * Méthode qui contient toutes les actions qu'un pilote peut effectuer
     * @param systemeGestion
     */
    @Override
    public void goNext(SystemeGestion systemeGestion) {
        DecorateurMenuPilote d = new DecorateurMenuPilote(new Implementation());
        d.affichage();
        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();
        switch (choix){
            case 1 :
                deconnexion(systemeGestion);
                break;
            case 2:
                /***
                    Récupération des données
                ***/
                //Saisie du départ
                int idDepart = saisirInt("Indiquer l'id du départ: ");
                
                String typeAvion = getType(systemeGestion,d);
               

                //Saisie du temps de vol avec vérification de saisie (bon interval)
                StringBuilder temps = new StringBuilder();
                int heureVol = -1;
                while(heureVol < 0)
                    heureVol = saisirInt("Indiquer le nombre d'heures de vol : ")+1;
                
                int minuteVol = -1;
                while(minuteVol < 0 || minuteVol > 59){
                    minuteVol = saisirInt("Indiquer le nombre de minutes de vol : ");
                }
                
                int secondeVol = -1;
                while(secondeVol < 0 || minuteVol > 59){
                    secondeVol = saisirInt("Indiquer le nombre de seconde de vol : ");
                }
                
                //Assemblage du temps de vol
                Time tempsVol = Time.valueOf(temps.append(heureVol).append(":").append(minuteVol).append(":").append(secondeVol).toString());
                //Saisie du rapport
                String rapport = saisirString("Saisir le rapport de vol: ");
                
                //Récupération du Pilote connecté
                int idPilote = systemeGestion.getSystemeGestionUtilisateur().getUtilisateurConnecte().getId();
                
                /***
                    Enregistrement des données
                ***/
                if(systemeGestion.getSystemeGestionUtilisateur().enregistrerRapportVol(new RapportPilote(idPilote, idDepart, null, rapport)))
                    log.log(Level.INFO,"Le rapport a bien été enregistré.");
                else
                    log.log(Level.INFO,"Erreur lors de l'enregistrement du rapport");
                
                if(systemeGestion.getSystemeGestionUtilisateur().enregistrerTempsVol(idPilote, tempsVol, typeAvion))
                    log.log(Level.INFO,"Vos heures de vol ont bien été mises à jour.");
                else
                    log.log(Level.INFO,"Erreur lors de l'enregistre de vos heures de vol");
                break;
            case 3:
                systemeGestion.retourMenuPrecedent();
                break;
            default:
                log.log(Level.INFO,"Erreur...");
                break;
        }
        systemeGestion.setState(this);
    }

	private String getType(SystemeGestion systemeGestion,DecorateurMenuPilote d) {
		 //Saisie du type d'avion avec boucle de contrôle pour savoir si un typeAvion entré existe
        List<TypeAvion> lta = systemeGestion.getSystemeGestionAvion().rechercherTypes();
        String typeAvion = "";
        boolean typeOk = false;
        while(!typeOk){
            d.affichageListeType(lta);
            typeAvion = saisirString("Type avion : ");
            int i = 0;
            //Recherche du type
            while(i < lta.size() && typeAvion.equals(lta.get(i).getType())){
                ++i;
            }
            typeOk = (i != lta.size());
        }
		return typeAvion;
	}
}
