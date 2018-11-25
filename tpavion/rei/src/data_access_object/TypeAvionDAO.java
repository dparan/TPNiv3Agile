package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_model.TypeAvion;

public class TypeAvionDAO extends DAO<TypeAvion> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    TypeAvionDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un TypeAvion dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(TypeAvion obj) throws SQLException {
        String requete = "insert into TypeAvion values (?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getType());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un TypeAvion existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(TypeAvion obj) throws SQLException {
        String requete = "delete from TypeAvion where type=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getType());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un TypeAvion existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(TypeAvion obj) throws SQLException {
        return false;
    }

    /**
     * Fonction permettant la récupération d'un TypeAvion existant dans la base de données en utilisant son type
     * @param obj
     * @return typeavion
     * @throws SQLException
     */
    @Override
    public TypeAvion find(TypeAvion obj) throws SQLException {
        String requete = "select * from TypeAvion where type=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getType());
            try(ResultSet result = statement.executeQuery();){
                if(result.first()) {
                	return new TypeAvion(result.getString("type"));
                }
                return null;
            }
        }
    }

    public List<TypeAvion> findAll() throws SQLException{
        String requete = "select * from TypeAvion";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            try(ResultSet result = statement.executeQuery();){
                ArrayList<TypeAvion> retour = new ArrayList<>();
                while (result.next()){
                        retour.add(new TypeAvion(result.getString("type")));
                }
                return retour;
            }
        }
    }
}
