package data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data_model.RapportPilote;
import data_model.Vol;

public class RapportPiloteDAO extends DAO<RapportPilote> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    RapportPiloteDAO(Connection connexion) {
            super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un RapportPilote dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(RapportPilote obj) throws SQLException {
        /* si le départ n'existe pas on ne n'ajoute pas le rapport */
        if( new VolDAO(connexion).find(new Vol(obj.getIdDepart(), 0, "")) == null)
            throw new SQLException("--Erreur-- Le départ n'existe pas");
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="insert into RapportPilote values (?,?,(select dateDepart from Depart where id=?),?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getIdPilote());
            statement.setInt(2, obj.getIdDepart());
            statement.setInt(3, obj.getIdDepart());
            statement.setString(4, obj.getRapport());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un RapportPilote existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(RapportPilote obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="delete from RapportPilote values where idPilote=? and idDepart=? and dateDepart=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getIdPilote());
            statement.setInt(2, obj.getIdDepart());
            statement.setDate(3, obj.getDateDepart());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un RapportPilote existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(RapportPilote obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "update RapportPilote set rapport=? where idPilote=? and idDepart=? and dateDepart=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getRapport());
            statement.setInt(2, obj.getIdPilote());
            statement.setInt(3, obj.getIdDepart());
            statement.setDate(4, obj.getDateDepart());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un RapportPilote existant dans la base de données en utilisant l'identifiant
     * du pilote, l'identifiant et la date du départ
     * @param obj
     * @return rapportpilote
     * @throws SQLException
     */
    @Override
    public RapportPilote find(RapportPilote obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from RapportPilote where idPilote=? and idDepart=? and dateDepart=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, (int)obj.getIdPilote());
            statement.setInt(2, (int)obj.getIdDepart());
            statement.setDate(3, (Date)obj.getDateDepart());
            try(ResultSet result = statement.executeQuery();){
            	/* si on a trouvé un élément : on le renvoie */
                if(result.first())
                    return new RapportPilote(result.getInt("idPilote"),result.getInt("idDepart"),result.getDate("dateDepart"),result.getString("rapport"));
                return null;
            }
        }
    }
}
