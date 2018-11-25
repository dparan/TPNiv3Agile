package systeme;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import data_access_object.DAOFactory;
import data_access_object.DepartAvionDAO;
import data_access_object.DepartDAO;

import data_model.Depart;
import data_model.DepartAvion;
import data_model.DepartPassager;

public class SystemeGestionDepart {
	
    private DAOFactory factory;

    /**
     * Constructeur du Systeme de Gestion des departs
     * @param conn
     */
    public SystemeGestionDepart(Connection conn) {
            factory = new DAOFactory(conn);
    }

    /**
     * Méthode permettant l'ajout d'un depart
     * @param Depart
     * @return boolean
     */
    public boolean ajouteDepart(Depart d) {
        try {
            return factory.createDepartDAO().create(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant la suppression d'un depart
     * @param Depart
     * @return boolean
     */
    public boolean supprimerDepart(Depart d) {
        try {
            return factory.createDepartDAO().delete(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant de voir si un depart existe dans la base de données
     * @param Depart
     * @return Depart
     */
    public List<Depart> rechercherDepart(Depart d) {
        try {
            return ((DepartDAO)factory.createDepartDAO()).findAll(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionAvion.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return new ArrayList<>();
    }
    
    /**
     * Méthode mettant à jour un Depart
     * @param avion
     * @return boolean
     */
    public boolean majDepart(Depart d, Date date) {
        try {
            return ((DepartDAO)factory.createDepartDAO()).update(d, date);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant l'ajout d'un departAvion
     * @param DepartAvion
     * @return boolean
     */
    public boolean ajouteDepartAvion(DepartAvion d) {
        try {
            return factory.createDepartAvionDAO().create(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant la suppression d'un departAvion
     * @param DepartAvion
     * @return boolean
     */
    public boolean supprimerDepartAvion(DepartAvion d) {
        try {
            return factory.createDepartAvionDAO().delete(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant de voir si un departAvion existe dans la base de données
     * @param DepartAvion
     * @return DepartAvion
     */
    public List<DepartAvion> rechercherDepartAvion(DepartAvion d) {
        try {
            return ((DepartAvionDAO)factory.createDepartAvionDAO()).findAll(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionAvion.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return new ArrayList<>();
    }

    /**
     * Méthode mettant à jour un departAvion
     * @param DepartAvion
     * @return boolean
     */
    public boolean majDepartAvion(DepartAvion d) {
        try {
            return factory.createDepartAvionDAO().update(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant l'ajout d'un departPassager
     * @param DepartPassager
     * @return boolean
     */
    public boolean ajouteDepartPassager(DepartPassager d) {
        try {
            return factory.createDepartPassagerDAO().create(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant la suppression d'un departPassager
     * @param DepartPassager
     * @return boolean
     */
    public boolean supprimerDepartPassager(DepartPassager d) {
        try {
            return factory.createDepartPassagerDAO().delete(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant de voir si un departPassager existe dans la base de données
     * @param DepartPassager
     * @return DepartPassager
     */
    public DepartPassager rechercherDepartPassager(DepartPassager d) {
        try {
            return factory.createDepartPassagerDAO().find(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionAvion.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return null;
    }

    /**
     * Méthode mettant à jour un departPassager
     * @param DepartPassager
     * @return boolean
     */
    public boolean majDepartPassager(DepartPassager d) {
        try {
            return factory.createDepartPassagerDAO().update(d);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }	
}
