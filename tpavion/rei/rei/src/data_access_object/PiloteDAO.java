package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import data_model.Pilote;

public class PiloteDAO extends DAO<Pilote> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    PiloteDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un Pilote dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(Pilote obj) throws SQLException {
        String requete ="insert into Pilote values (?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            statement.setTime(2, obj.getNombreHeureTotale());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un Pilote existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(Pilote obj) throws SQLException {
        String requete = "delete from Pilote where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un Pilote existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(Pilote obj) throws SQLException {
        String requete ="update Pilote set nombreHeureTotale=? where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setTime(1, obj.getNombreHeureTotale());
            statement.setInt(2, obj.getId());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la r�cup�ration d'un Pilote existant dans la base de données en utilisant son identifiant
     * @param obj
     * @return pilote
     * @throws SQLException
     */
    @Override
    public Pilote find(Pilote obj) throws SQLException {
        String requete = "select * from Pilote where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            try(ResultSet result = statement.executeQuery();){
                if(result.first())
                    return new Pilote(result.getInt("id"),result.getTime("nombreHeureTotale"));
                return null;
            }
        }
    }
}
