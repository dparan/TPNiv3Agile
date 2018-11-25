package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import data_model.TempsVolType;
import java.sql.Time;

public class TempsVolTypeDAO extends DAO<TempsVolType> {
    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    TempsVolTypeDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un TempsVolType dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(TempsVolType obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="insert into TempsVolType values (?,?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getType());
            statement.setTime(3, obj.getNombreHeure());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un TempsVolType existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(TempsVolType obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="delete from TempsVolType where pilote=? and typeAvion=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getType());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un TempsVolType existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(TempsVolType obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="update TempsVolType set nombreHeure=? where id=? and type=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setTime(1, obj.getNombreHeure());
            statement.setInt(2, obj.getId());
            statement.setString(3, obj.getType());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un TempsVolType existant dans la base de données en utilisant l'identifiant du pilote et
     * le type d'avion
     * @param obj
     * @return tempsvoltype
     * @throws SQLException
     */
    @Override
    public TempsVolType find(TempsVolType obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from TempsVolType where id=? and type=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            statement.setString(2, obj.getType());
            try(ResultSet result = statement.executeQuery();){
            	/* si on a trouvé un élément : on le renvoie */
                if(result.first())
                    return new TempsVolType(result.getInt("id"),result.getString("type"),result.getTime("nombreHeure"));
                return null;
            }
        }
    }
    
    /**
     * Fonction permettant la mise à jour du temps de vol d'un pilote selon le départ 
     * @param idPilote
     * @param tempsVol
     * @param typeAvion
     * @return boolean
     * @throws SQLException
     */
    public boolean updateOrInsertTempsVolType(int idPilote, Time tempsVol, String typeAvion) throws SQLException {
        /*
        On cherche d'abord à savoir si le pilote a déjà piloter un avion du type correspondant au départ
        Cela déterminera si c'est un update ou un instert à faire dans la table TempsVolType
        */
        TempsVolType tvt = new TempsVolType(idPilote, typeAvion, tempsVol);
        return (this.find(tvt) == null)? this.create(tvt) : this.update(tvt); 
    }
}
