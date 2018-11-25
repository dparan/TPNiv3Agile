package systeme;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import state.Etat;
import state.EtatInitial;

public class SystemeGestion {

    SystemeGestionUtilisateur sgu;
    SystemeGestionAvion sga;
    SystemeGestionVol sgv;
    SystemeGestionDepart sgd;
    private static final String USER = "tpavion2";
    private static final String PASS = "265fgjoy";
    private static final String DBDRIVER = "jdbc:mysql://tpavion2.cuxgpqum2bql.us-east-2.rds.amazonaws.com:3306/tpavion?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private Connection conn = null;
    /* mauvais impact sur perf DEQUE */
    private LinkedList<Etat> etats;

    /**
     * Constructeur pour un systemeGestion
     */
    public SystemeGestion(){   
        try {
            conn = DriverManager.getConnection(DBDRIVER, USER, PASS);
            sgu = new SystemeGestionUtilisateur(conn); 
            sga = new SystemeGestionAvion(conn);
            sgv = new SystemeGestionVol(conn);
            sgd = new SystemeGestionDepart(conn);
        } catch (SQLException  e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        /* pile contenant tous nos états */
        etats = new LinkedList<>();
        /* on place le premier etat et on lance la méthode afficherInterface */
        setState(new EtatInitial());
    }

    /**
     * méthode permettant l'affichage de l'interface
     */
    public void afficherInterface() {
        etats.peek().goNext(this);
    }

    /**
     * Getter d'un systemeGestionUtilisateur
     * @return SystemeGestionUtilisateur
     */
    public SystemeGestionUtilisateur getSystemeGestionUtilisateur() {
        return sgu;
    }

    /**
     * Getter d'un systemeGestionAvion
     * @return SystemeGestionAvion
     */
    public SystemeGestionAvion getSystemeGestionAvion() {
        return sga;
    }
    
    /**
     * Getter d'un systemeGestionVol
     * @return SystemeGestionVol
     */
    public SystemeGestionVol getSystemeGestionVol() {
            return sgv;
    }
    
    /**
     * Getter d'un systemeGestionDepart
     * @return SystemeGestionVol
     */
    public SystemeGestionDepart getSystemeGestionDepart() {
            return sgd;
    }

    /**
     * Setter pour un état
     * @param etat
     */
    public void setState(Etat etat) {
        //On vérifie si on empile pas 2 fois le même état
        if(!etats.isEmpty()){
            if(!etat.getClass().equals(etats.peek().getClass()))
                etats.push(etat);
        } else {
            etats.push(etat);
        }
        afficherInterface();
    }

    /**
     * Méthode permettant le retour é un menu précédent (é un état précédent)
     */
    public void retourMenuPrecedent() {
        etats.pop();
        afficherInterface();
    }

    /**
     * Méthode permettant la deconnexion
     */
    public void deconnexion() {
        sgu.deconnexion();
        etats.clear();
        etats.push(new EtatInitial());
        afficherInterface();
    }
}
