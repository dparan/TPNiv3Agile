package data_access_object;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_model.Depart;
import data_model.Vol;

public class DepartDAO extends DAO<Depart> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    DepartDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un Depart dans la base de donn�es
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(Depart obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="insert into Depart values (?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId().getId());
            statement.setDate(2, obj.getDateDepart());			
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un Depart existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(Depart obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
    String requete = "delete from Depart where id=? and dateDepart = ?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId().getId());
            statement.setDate(2, obj.getDateDepart());
            /* retourne true si la requete s'est bien effectuée */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un Depart existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(Depart obj) throws SQLException {
        /* on ne doit pas modifier les rows de cette table */
        return false;
    }
    
    /**
     * Fonction permettant la mise à jour d'un Depart existant dans la base de données
     * @param obj, Date
     * @return boolean
     * @throws SQLException
     */
    public boolean update(Depart obj, Date d) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "update Depart set dateDepart=? where id=? and dateDepart=?;";
        try (PreparedStatement statement = super.connexion.prepareStatement(requete);) {
            statement.setDate(1, d);
            statement.setInt(2, obj.getId().getId());
            statement.setDate(3, obj.getDateDepart());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un Depart existant dans la base de données en utilisant son identifiant
     * @param obj
     * @return depart
     * @throws SQLException
     */
    @Override
    public Depart find(Depart obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Depart where id=? and dateDepart=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId().getId());
            statement.setDate(2, obj.getDateDepart());		
            try(ResultSet result = statement.executeQuery();){
            	/* si on a trouvé un élément : on le renvoie */
                if(result.first()) {
                    Vol vol = new VolDAO(connexion).find(new Vol(result.getInt("id"), 0, ""));
                    return new Depart(vol,result.getDate("dateDepart"));
                }
                return null;
            }
        }
    }
    
    /**
     * Fonction permettant la récupération des Departs existant dans la base de données en utilisant l'identifiant
     * @param obj
     * @return Depart
     * @throws SQLException
     */
    public List<Depart> findAll(Depart obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Depart where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId().getId());	
            try(ResultSet result = statement.executeQuery();){
            	ArrayList<Depart> retour = new ArrayList<>();
            	/* ajoute les résultats potentiels à la liste des résultats */
                while (result.next()){
                    Vol vol = new VolDAO(connexion).find(new Vol(result.getInt("id"), 0, ""));
                        retour.add(new Depart(vol,result.getDate("dateDepart")));
                }
                return retour;
            }
        }
    }

}
