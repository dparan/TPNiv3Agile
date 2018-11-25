package decorator;

import java.util.List;
import data_model.DepartAvion;
import data_model.Troncon;
import data_model.Vol;
import data_model.VolTroncon;

public class DecorateurMenuVolTroncon extends Decorateur{
    private static final String RES = "Résultats de la recherche :";
	private static final String FLE = " --> ";

	/**
     * Constructeur d'un DecorateurMenuVolTroncon
     * @param a
     */
    public DecorateurMenuVolTroncon(Abstraction a) {
            super(a);
    }

    /**
     * Méthode permettant l'affichage du menu
     */
    @Override
    public void affichage() {
        a.affichage();
        System.out.println(" 3 --> Ajouter un nouveau vol");
        System.out.println(" 4 --> Modifier un vol existant");
        System.out.println(" 5 --> Supprimer un vol existant");
        System.out.println(" 6 --> Rechercher un vol existant");
        System.out.println(" 7 --> Ajouter un nouveau tronçon");
        System.out.println(" 8 --> Modifier un tronçon existant");
        System.out.println(" 9 --> Supprimer un tronçon existant");
        System.out.println(" 10 --> Rechercher un tronçon existant");
        System.out.println(" 11 --> Associer un vol à un tronçon");
        System.out.println(" 12 --> Modifier une association vol-tronçon");
        System.out.println(" 13 --> Supprimer une association vol-tronçon");
        System.out.println(" 14 --> Rechercher une association vol-tronçon");
        System.out.println(" 15 -> Retour au menu précédent...");
    }

    /**
     * Méthode permettant l'affichage de la liste des vols
     */
    public void affichageListeVol(List<Vol> vols) {
    	if(vols != null && !vols.isEmpty())
    	{
    		System.out.println(RES);
	        for(int i = 0; i<vols.size();i++) {
	        	 String chaine = "  "+(i+1)+FLE+vols.get(i).toString();
	        	 System.out.println(chaine);
	        }
    	}
    	else
    		System.out.println("Aucun vol trouvé");
    }
    
	public void affichageListeDepartAvion(List<DepartAvion> departsAvion) {
		if(departsAvion != null && !departsAvion.isEmpty())
    	{
    		System.out.println(RES);
	        for(int i = 0; i<departsAvion.size();i++) {
	        	 String chaine = "  "+(i+1)+FLE+departsAvion.get(i).toString();
	        	 System.out.println(chaine);
	        }
    	}
    	else
    		System.out.println("Aucun départAvion trouvé");	
	}

	public void affichageListeVolTroncon(List<VolTroncon> listeVolTroncon) {
		if(listeVolTroncon != null && !listeVolTroncon.isEmpty())
    	{
    		System.out.println(RES);
	        for(int i = 0; i<listeVolTroncon.size();i++) {
	        	 String chaine = "  "+(i+1)+FLE+listeVolTroncon.get(i).toString();
	        	 System.out.println(chaine);
	        }
    	}
    	else
    		System.out.println("Aucun volTronçon trouvé");	
	}

	public void affichageListeTroncon(List<Troncon> listeTroncon) {
		if(listeTroncon != null && !listeTroncon.isEmpty())
    	{
    		System.out.println(RES);
	        for(int i = 0; i<listeTroncon.size();i++) {
	        	 String chaine = "  "+(i+1)+FLE+listeTroncon.get(i).toString();
	        	 System.out.println(chaine);
	        }
    	}
    	else
    		System.out.println("Aucun tronçon trouvé");
	}
}