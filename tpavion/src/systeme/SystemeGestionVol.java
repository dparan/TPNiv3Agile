package systeme;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import data_access_object.DAOFactory;
import data_access_object.TronconDAO;
import data_access_object.VolTronconDAO;
import data_access_object.VolDAO;
import data_model.Troncon;
import data_model.Vol;
import data_model.VolTroncon;

public class SystemeGestionVol {

    private DAOFactory factory;

    /**
     * Constructeur du Systeme de Gestion des volTronçon (départ?)
     * @param conn
     */
    public SystemeGestionVol(Connection conn) {
            factory = new DAOFactory(conn);
    }

    /**
     * Méthode permettant l'ajout d'un vol
     * @param Vol
     * @return boolean
     */
    public boolean ajouteVol(Vol v) {
        try {
            return factory.createVolDAO().create(v);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }


    /**
     * Méthode permettant l'ajout d'un tronçon
     * @param Troncon
     * @return boolean
     */
    public boolean ajouteTroncon(Troncon t) {
        try {
            return factory.createTronconDAO().create(t);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant la suppression d'un vol
     * @param Vol
     * @return boolean
     */
    public boolean supprimerVol(Vol v) {
        try {
        	if(((VolTronconDAO)factory.createVolTronconDAO()).volEstAttribue(v))
        		throw new SQLException("--Erreur-- Le vol est encore attribué");
            return factory.createVolDAO().delete(v);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }


    /**
     * Méthode permettant la suppression d'un tronçon
     * @param Troncon
     * @return boolean
     */
    public boolean supprimerTroncon(Troncon t) {
        try {
        	if(((VolTronconDAO)factory.createVolTronconDAO()).tronconEstAttribue(t))
        		throw new SQLException("--Erreur-- Le tronçon est encore attribué");
            return factory.createTronconDAO().delete(t);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }


    /**
     * Méthode permettant de voir si un vol existe dans la base de données
     * @param Vol
     * @return List<Vol>
     */
    public List<Vol> rechercherVol(Vol v) {
        try {
        	List<Vol> vol = new ArrayList<>();
        	if(v.getId() != -1)
        		vol = ((VolDAO)factory.createVolDAO()).findAll();
        	else
        		vol.add(((VolDAO)factory.createVolDAO()).find(v));
	        if(vol == null || vol.get(0) == null)
	            throw new SQLException("--Erreur-- Le vol n'existe pas.");
	        return vol;
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionAvion.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return new ArrayList<>();
    }


    /**
     * Méthode permettant de voir si un tronçon existe dans la base de données
     * @param Troncon
     * @return Troncon
     */
    public List<Troncon> rechercherTroncon(Troncon t) {
        try {
            return ((TronconDAO)factory.createTronconDAO()).findAll(t);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionAvion.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Méthode mettant à jour un Vol
     * @param avion
     * @return
     */
    public boolean majVol(Vol v) {
        try {
            return factory.createVolDAO().update(v);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode mettant à jour un tronçon
     * @param Troncon
     * @return
     */
    public boolean majTroncon(Troncon t) {
        try {
            return factory.createTronconDAO().update(t);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    public boolean associerVolTroncon(Vol v, Troncon t, Time heureDepart, Time heureSortie){
        try {
            return factory.createVolTronconDAO().create(new VolTroncon(t, v, heureDepart, heureSortie));
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    public boolean supprimerVolTroncon(Vol v, Troncon t, Time heureDepart, Time heureSortie){
      try {
            return factory.createVolTronconDAO().delete(new VolTroncon(t, v, heureDepart, heureSortie));
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    public boolean majVolTroncon(Vol v, Troncon t, Time heureDepart, Time heureSortie){
        try {
            return factory.createVolTronconDAO().update(new VolTroncon(t, v, heureDepart, heureSortie));
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    public List<VolTroncon> rechercherVolTroncon(Vol v, Troncon t, Time heureDepart, Time heureSortie){
        try {
            return ((VolTronconDAO)factory.createVolTronconDAO()).findAll(new VolTroncon(t, v, heureDepart, heureSortie));
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return new ArrayList<>();
    }
   
}
