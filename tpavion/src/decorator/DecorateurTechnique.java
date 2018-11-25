package decorator;

import data_model.Avion;
import data_model.TypeAvion;

import java.util.List;

public abstract class DecorateurTechnique extends Decorateur {
    /**
     * Constructeur de la méthode abstraite "Decorateur".
     *
     * @param a the a
     */
    public DecorateurTechnique(Abstraction a) {
        super(a);
    }

    /**
     * Méthode permettant l'affichage de la liste des rôles
     */
    public void affichageListeType(List<TypeAvion> types) {
        for(int i = 0; i<types.size();i++) {
        	 String chaine = (i+1)+" --> "+types.get(i).getType();
        	 System.out.println(chaine);
        }
    }
    
    /**
     * Méthode permettant l'affichage de la liste des avions
     */
    public void afficherAvions(List<Avion> avions){
        for(Avion a : avions)
        	 a.toString();
    }
}
