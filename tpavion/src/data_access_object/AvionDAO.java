package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_model.Avion;
import data_model.TypeAvion;

public class AvionDAO extends DAO<Avion> {
    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    AvionDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un avion dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(Avion obj) throws SQLException {
        /* si le type n'existe pas on ne tente pas de créer l'avion */
        if( new TypeAvionDAO(connexion).find(obj.getType()) == null) 
            throw new SQLException(" --Erreur-- Le type n'existe pas.");
        /* declaration de la requête qui sera executée sur la bdd */
        String requete =
        "insert into Avion (immatriculation,capacite,type)" +
        "values (?,?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getImmatriculation());
            statement.setInt(2, obj.getCapacite());
            statement.setString(3, obj.getType().getType());
            /* retourne true si l'insert s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un avion existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(Avion obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete =
        "delete from Avion where immatriculation=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getImmatriculation());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise é jour d'un avion existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(Avion obj) throws SQLException {
        /* si le type n'existe pas on ne tente pas de modifier l'avion */
        if( new TypeAvionDAO(connexion).find(obj.getType()) == null) 
            throw new SQLException(" --Erreur-- Le type n'existe pas.");
        /* declaration de la requête qui sera executée sur la bdd */
        String requete =
            "update Avion set type=?, capacite=? where immatriculation=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getType().getType());
            statement.setInt(2, obj.getCapacite());
            statement.setString(3, obj.getImmatriculation());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un avion existant dans la base de données en utilisant son immatriculation
     * @param obj
     * @return avion
     * @throws SQLException
     */
    @Override
    public Avion find(Avion obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Avion where immatriculation=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1,obj.getImmatriculation());
            try(ResultSet result = statement.executeQuery();){
            	/* si on a trouvé un élément : on le renvoie */
                if(result.first())
                    return new Avion(result.getString("immatriculation"),result.getInt("capacite"),new TypeAvion(result.getString("type")));
                return null;
            }
        }
    }

    /**
     * Fonction permettant de savoir si un type d'avion est encore utilisé
     * @param typeAvion
     * @return boolean
     * @throws SQLException
     */
    public boolean findType(TypeAvion typeAvion) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Avion where type=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1,typeAvion.getType());
            try(ResultSet result = statement.executeQuery();){
            	/* renvoie true si la requête contient au moins 1 élément */
                return result.first();
            }
        }
    }

    public List<Avion> findAll() throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Avion";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            try(ResultSet result = statement.executeQuery();){
                ArrayList<Avion> retour = new ArrayList<>();
            	/* ajoute les résultats potentiels à la liste des résultats */
                while (result.next())
                    retour.add(new Avion(result.getString("immatriculation"),result.getInt("capacite"),new TypeAvion(result.getString("type"))));
                return retour;
            }
        }
    }
}
