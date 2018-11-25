package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_model.Troncon;

public class TronconDAO extends DAO<Troncon> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    TronconDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un Troncon dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(Troncon obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "insert into Troncon values (?,?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getVilleDepart());
            statement.setString(2, obj.getVilleArrivee());
            statement.setInt(3, obj.getDistance());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un Troncon existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(Troncon obj) throws SQLException {
    	/* on vérifie si le tronçon existe avant de le supprimer */
    	if(find(obj) == null)
    		throw new SQLException("--Erreur-- Le tronçon n'existe pas");
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "delete from Troncon where villeDepart=? and villeArrivee=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getVilleDepart());
            statement.setString(2, obj.getVilleArrivee());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un Troncon existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(Troncon obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "update Troncon set villeDepart=?, villeArrivee=?, distance=? where villeDepart=? and villeArrivee=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getVilleDepart());
            statement.setString(2, obj.getVilleArrivee());
            statement.setInt(3, obj.getDistance());
            statement.setString(4, obj.getVilleDepart());
            statement.setString(5, obj.getVilleArrivee());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un Troncon existant dans la base de données en utilisant son identifiant
     * @param obj
     * @return troncon
     * @throws SQLException
     */
    @Override
    public Troncon find(Troncon obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Troncon where villeDepart=? and villeArrivee=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getVilleDepart());
            statement.setString(2, obj.getVilleArrivee());
            try(ResultSet result = statement.executeQuery();){
            	/* si on a trouvé un élément : on le renvoie */
                if(result.first())
                    return new Troncon(result.getString("villeDepart"),result.getString("villeArrivee"),result.getInt("distance"));
                return null;
            }
        }
    }

    /**
     * Fonction permettant la récupération des Tronçons existant dans la base de données en utilisant l'identifiant
     * @param obj
     * @return Troncon
     * @throws SQLException
     */
    public List<Troncon> findAll(Troncon obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Troncon where villeDepart=? and villeArrivee=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getVilleDepart());	
            statement.setString(2, obj.getVilleArrivee());	
            try(ResultSet result = statement.executeQuery();){
            	ArrayList<Troncon> retour = new ArrayList<>();
            	/* ajoute les résultats potentiels à la liste des résultats */
                while (result.next()){
                        retour.add(new TronconDAO(connexion).find(new Troncon(result.getString("villeDepart"), result.getString("villeArrivee"), result.getInt("distance"))));
                }
                return retour;
            }
        }
    }

}
