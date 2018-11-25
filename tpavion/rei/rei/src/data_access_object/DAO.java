package data_access_object;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAO<T> {

    protected Connection connexion;

    /**
     * Constructeur de la classe générique, Toutes les méthodes utilise connexion
     * @param connexion
     */
    DAO(Connection connexion) {
            this.connexion = connexion;
    }

    /**
     * Fonction abstraite qui permettra l'insertion d'un objet dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    public abstract boolean create(T obj) throws SQLException;

    /**
     * Fonction abstraite qui permettra la suppression d'un objet dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    public abstract boolean delete(T obj) throws SQLException;

    /**
     * Fonction abstraite qui permettra la mise à jour d'un objet dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    public abstract boolean update(T obj) throws SQLException;

    /**
     * Fonction abstraite qui permettra la récupération d'un objet dans la base de données
     * @param obj
     * @return T
     * @throws SQLException
     */
    public abstract T find(T obj) throws SQLException;
	
}
