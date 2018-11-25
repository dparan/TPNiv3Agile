package decorator;

import data_model.Personnel;
import data_model.Role;

import java.util.List;
import java.util.logging.Level;

public abstract class DecorateurPersonnel extends Decorateur{
    
    /**
     * Constructeur de la méthode abstraite "Decorateur".
     *
     * @param a the a
     */
    public DecorateurPersonnel(Abstraction a){
        super(a);
    }

    /**
     * Méthode permettant l'affichage de la liste des rôles
     * @param roles
     */
    public void affichageListeRoles(List<Role> roles) {
        for(int i = 0; i<roles.size();i++) {
        	String chaine= (i+1)+" --> "+roles.get(i).getType()+" - "+roles.get(i).getRole();
        	System.out.println(chaine);
        }
    }

    /**
     * Méthode permettant l'affichage des types de rôles
     */
    public void affichageTypeRole() {
    	System.out.println(" Type de rôle ");
    	System.out.println(" 1 --> Navigant");
    	System.out.println(" 2 --> Non navigant");
    }
    
    /**
     * Méthode permettant l'affichage le personnel
     */
    public void afficherPersonnels(List<Personnel> personnel) {
        for (Personnel p : personnel)
        	System.out.println(p.toString());
    }
}
