package state;

import java.util.logging.Level;

public abstract class EtatPersonnel extends Etat{

    public String creerType(int t){
        String type="";
        switch(t) {
            case 1:
                type = "navigant";
                break;
            case 2:
                type = "nonnavigant";
                break;
            default :
                log.log(Level.INFO,"Erreur de saisie");
                break;
        }
        return type;
    }
}
