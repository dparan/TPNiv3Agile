package systeme;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.*;

import data_access_object.*;
import data_model.Role;
import data_model.Troncon;
import data_model.VolTroncon;
import data_model.Depart;
import data_model.DepartPassager;
import data_model.Passager;
import data_model.Personnel;
import data_model.RapportPilote;

public class SystemeGestionUtilisateur {

    private Connection conn;
    private Personnel utilisateurConnecte = null;
    private List<String> typeUtilisateur;
    private DAOFactory factory;

    /**
     * Constructeur d'un Systeme de gestion utilisateur
     * @param
     */
    public SystemeGestionUtilisateur(Connection conn) {
        this.setConn(conn);
        this.typeUtilisateur = new ArrayList<>();
        this.factory = new DAOFactory(conn);
    }

    /**
     * Méthode permettant la connexion
     * @param id
     * @param mdp
     * @return
     */
    public boolean connexion(int id, String mdp) {
        DAO<Personnel> personnelDAO = factory.createPersonnelDAO();
        try {
            utilisateurConnecte =((PersonnelDAO) personnelDAO).find(new Personnel(id));
            return (utilisateurConnecte != null && utilisateurConnecte.getMotDePasse().equals(mdp));
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant la deconnexion
     */
    public void deconnexion() {
        utilisateurConnecte = null;
        typeUtilisateur.clear();
    }

    /**
     * Getter de l'utilisateur actuellement connecté
     * @return
     */
    public Personnel getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    /**
     * Méthode permettant l'ajout d'un membre du personnel
     * @param personnel
     * @return
     */
    public boolean ajouterUtilisateur(Personnel personnel) {
        DAO<Personnel> personnelDAO = factory.createPersonnelDAO();
        try {
            return personnelDAO.create(personnel);
        }
        catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant l'encodage d'une chaine de caractères
     * @param message
     * @return
     */
    @SuppressWarnings("unused")
	private String encoderMessage(String message) {
        byte[] byteChaine = null;
        MessageDigest md=null;
        try {
            byteChaine = message.getBytes("UTF-8");
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getMessage());
        }
        if(md!=null) {
        	byte[] hash = md.digest(byteChaine);
        	return Arrays.toString(hash);
        }
        return "";
    }

    /**
     * Méthode permettant l'ajout d'un rôle
     * @param role
     * @return
     */
    public boolean ajouterRole(Role role) {
        DAO<Role> roleDAO = factory.createRoleDAO();
        try {
               return roleDAO.create(role);
        }
        catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;	
    }

    /**
     * Méthode permettant la suppression d'un utilisateur
     * @param personnel
     * @return
     */
    public boolean supprimerUtilisateur(Personnel personnel) {
        DAO<Personnel> personnelDAO = factory.createPersonnelDAO();
        try {
            if(personnel.getId()==utilisateurConnecte.getId())
                throw new SQLException(" Vous ne pouvez pas vous supprimer vous-mêmes de la base de données.");
            return personnelDAO.delete(personnel);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode permettant de voir si un utilisateur existe dans la base de données
     * @param personnel
     * @return
     */
    public Personnel rechercherUtilisateur(Personnel personnel) {
        try {
            return factory.createPersonnelDAO().find(personnel);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return null;
    }

    /**
     * Méthode permettant la mise à jour d'un utilisateur
     * @param personnel
     * @return
     */
    public boolean majUtilisateur(Personnel personnel) {
        try {
            return factory.createPersonnelDAO().update(personnel);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    /**
     * Méthode retournant tous les rôles
     * @return
     */
    public List<Role> getRoles(){
        try {
            return ((RoleDAO) factory.createRoleDAO()).findRoles();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return new ArrayList<>();
    }


    /**
     * Méthode retournant tous les membres du personnel
     * @return
     */
    public List<Personnel> getPersonnels(){
        try {
            return ((PersonnelDAO) factory.createPersonnelDAO()).findAll();
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return new ArrayList<>();
    }

    public Passager rechercherPassager(Passager passager) {
        try {
            return factory.createPassagerDAO().find(passager);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return null;
    }

    public boolean creerPassager(Passager passager) {
        try {
            return factory.createPassagerDAO().create(passager);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

    public boolean associerPassagerDepart(Passager passager, Date date, String villeDepart, String villeArrivee, Time heureDepart) {
        try {
            Troncon troncon = factory.createTronconDAO().find(new Troncon(villeDepart,villeArrivee));
            if(troncon==null)
                throw new SQLException("--Erreur-- Le troncon est introuvable.");

            VolTroncon voltroncon = ((VolTronconDAO) factory.createVolTronconDAO()).findWithCities(new VolTroncon(troncon, heureDepart));
            if(voltroncon==null)
                throw new SQLException("--Erreur-- Le vol est introuvable.");

            Depart depart = factory.createDepartDAO().find(new Depart(voltroncon.getVol(),date));
            if(depart==null)
                throw new SQLException("--Erreur-- Le vol est introuvable.");

            DAO<DepartPassager> factoryDP = factory.createDepartPassagerDAO();
            int nextPlace = ((DepartPassagerDAO)factoryDP).findNextPlace(depart);
            return factoryDP.create(new DepartPassager(passager,depart,nextPlace));
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }
    
    public boolean enregistrerTempsVol(int idPilote, Time tempsVol, String typeAvion){
        try {
            return ((TempsVolTypeDAO) factory.createTempsVolTypeDAO()).updateOrInsertTempsVolType(idPilote, tempsVol, typeAvion);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }
    
    public boolean enregistrerRapportVol(RapportPilote rp){
        try {
            return ((RapportPiloteDAO) factory.createRapportPiloteDAO()).create(rp);
        } catch (SQLException e) {
            Logger logger = Logger.getLogger(SystemeGestionUtilisateur.class.getName());
            logger.log(Level.SEVERE, e.getSQLState()+" - "+e.getMessage());
        }
        return false;
    }

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
    
    
}
