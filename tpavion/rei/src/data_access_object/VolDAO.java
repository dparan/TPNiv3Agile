package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import data_model.Vol;

public class VolDAO extends DAO<Vol> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    VolDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un Vol dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(Vol obj) throws SQLException {
        String requete = "insert into Vol (frequence, uniteFrequence) values (?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getFrequence());
            statement.setString(2, obj.getUniteFrequence());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un Vol existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(Vol obj) throws SQLException {
    	if(this.find(obj)==null)
  	      throw new SQLException(" --Erreur-- Le vol n'existe pas.");
        String requete = "delete from Vol where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un Vol existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(Vol obj) throws SQLException {
    	if(this.find(obj)==null)
    	      throw new SQLException(" --Erreur-- Le vol n'existe pas.");
        String requete = "update Vol set frequence=? and uniteFrequence=?  where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getFrequence());
            statement.setString(2, obj.getUniteFrequence());
            statement.setInt(3, obj.getId());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un Vol existant dans la base de données en utilisant son identifiant
     * @param obj
     * @return vol
     * @throws SQLException
     */
    @Override
    public Vol find(Vol obj) throws SQLException {
        String requete ="select * from Vol where id=?";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            try(ResultSet result = statement.executeQuery();){
                if(result.first())
                    return new Vol(result.getInt("id"),result.getInt("frequence"), result.getString("uniteFrequence"));
                return null;
            }
        }
    }
}
