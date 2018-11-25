package data_access_object;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data_model.Passager;

public class PassagerDAO extends DAO<Passager> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    PassagerDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un Passager dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(Passager obj) throws SQLException {
        String requete ="insert into Passager values (?,?,?,?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getNumPasseport());
            statement.setString(2, obj.getPrenom());
            statement.setString(3, obj.getNom());
            statement.setString(4, obj.getAdresse());
            statement.setString(5, obj.getNoTelephone());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un Passager existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(Passager obj) throws SQLException {		
        String requete ="delete from Passager where numPasseport=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getNumPasseport());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise é jour d'un Passager existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(Passager obj) throws SQLException {
        String requete ="update Passager set prenom=?, nom=?, adresse=?, noTelephone=? where numPasseport=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getPrenom());
            statement.setString(2, obj.getNom());
            statement.setString(3, obj.getAdresse());
            statement.setString(4, obj.getNoTelephone());
            statement.setString(5,obj.getNumPasseport());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un Passager existant dans la base de données en utilisant son numéro de passeport
     * @param obj
     * @return passager
     * @throws SQLException
     */
    @Override
    public Passager find(Passager obj) throws SQLException {
        String requete ="select * from Passager where numPasseport=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getNumPasseport());
            try(ResultSet result = statement.executeQuery();){
                if(result.first())
                    return new Passager(result.getString("numPasseport"),result.getString("prenom"),result.getString("nom"),result.getString("adresse"),result.getString("noTelephone"));
                return null;
            }
        }
    }

}
