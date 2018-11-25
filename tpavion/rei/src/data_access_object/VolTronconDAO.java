package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data_model.Troncon;
import data_model.Vol;
import data_model.VolTroncon;

public class VolTronconDAO extends DAO<VolTroncon> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    VolTronconDAO(Connection connexion) {
        super(connexion);
    }

    void checkVolTroncon(VolTroncon obj) throws SQLException {
    	if(new VolDAO(connexion).find(obj.getVol()) == null)
    		throw new SQLException("--Erreur-- Le vol n'existe pas");
    	if(new TronconDAO(connexion).find(obj.getTroncon()) == null)
    		throw new SQLException("--Erreur-- Le tronçon n'existe pas");
    }
    /**
     * Fonction permettant l'insertion d'un VolTroncon dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(VolTroncon obj) throws SQLException {
    	checkVolTroncon(obj);
        String requete = "insert into VolTroncon values (?,?,?,?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getVol().getId());
            statement.setString(2, obj.getTroncon().getVilleDepart());
            statement.setString(3, obj.getTroncon().getVilleArrivee());
            statement.setTime(4, obj.getHeureDepart());
            statement.setTime(5, obj.getHeureArrivee());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un VolTroncon existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(VolTroncon obj) throws SQLException {
    	checkVolTroncon(obj);
        String requete = "delete from VolTroncon where villeDepart=? and villeArrivee=? and id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getTroncon().getVilleDepart());
            statement.setString(2, obj.getTroncon().getVilleArrivee());
            statement.setInt(3, obj.getVol().getId());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un VolTroncon existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(VolTroncon obj) throws SQLException {
    	checkVolTroncon(obj);
    	String requete = "update VolTroncon set id=?,villeDepart=?,villeArrivee=?,heureDepart=?,heureArrivee=? where villeDepart=? and villeArrivee=? and id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getVol().getId());
            statement.setString(2, obj.getTroncon().getVilleDepart());
            statement.setString(3, obj.getTroncon().getVilleArrivee());
            statement.setTime(4, obj.getHeureDepart());
            statement.setTime(5, obj.getHeureArrivee());
            statement.setString(6, obj.getTroncon().getVilleDepart());
            statement.setString(7, obj.getTroncon().getVilleArrivee());
            statement.setInt(8, obj.getVol().getId());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un VolTroncon existant dans la base de données en utilisant l'identifiant du vol et l'identifiant du vol
     * @param obj
     * @return vol
     * @throws SQLException
     */
    @Override
    public VolTroncon find(VolTroncon obj) throws SQLException {
    	checkVolTroncon(obj);
    	String requete = "select * from VolTroncon where villeDepart=? and villeArrivee=? and id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getTroncon().getVilleDepart());
            statement.setString(2, obj.getTroncon().getVilleArrivee());
            statement.setInt(3, obj.getVol().getId());
            try(ResultSet result = statement.executeQuery();){
                if(result.first()) {
                	Troncon t = new TronconDAO(connexion).find(new Troncon(result.getString("villeDepart"),result.getString("villeArrivee")));
                	Vol vol = new VolDAO(connexion).find(new Vol(result.getInt("id"),0, ""));
                    return new VolTroncon(t,vol,result.getTime("heureDepart"),result.getTime("heureArrivee"));
                }
                return null;
            }
        }
    }
    public VolTroncon findWithCities(VolTroncon obj) throws SQLException {
    	checkVolTroncon(obj);
    	String requete = "select * from VolTroncon where villeDepart=? and villeArrivee=? and heureDepart=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getTroncon().getVilleDepart());
            statement.setString(2, obj.getTroncon().getVilleArrivee());
            statement.setTime(1, obj.getHeureDepart());
            try(ResultSet result = statement.executeQuery();){
                if(result.first()) {
                	Troncon t = new TronconDAO(connexion).find(new Troncon(result.getString("villeDepart"),result.getString("villeArrivee")));
                	Vol vol = new VolDAO(connexion).find(new Vol(result.getInt("id"),0, ""));
                    return new VolTroncon(t,vol,result.getTime("heureDepart"),result.getTime("heureArrivee"));
                }
                return null;
            }
        }
    }
    public boolean volEstAttribue(Vol vol) throws SQLException {
    	String requete = "select * from VolTroncon and id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, vol.getId());
            try(ResultSet result = statement.executeQuery();){
                return result.first();
            }
        }
    }
    
    public boolean tronconEstAttribue(Troncon troncon) throws SQLException {
    	String requete = "select * from VolTroncon where villeDepart=? and villeArrivee=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, troncon.getVilleDepart());
            statement.setString(2, troncon.getVilleArrivee());
            try(ResultSet result = statement.executeQuery();){
                return result.first();
            }
        }
    }
}
