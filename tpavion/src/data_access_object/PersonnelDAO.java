package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data_model.Personnel;

public class PersonnelDAO extends DAO<Personnel> {

    /**
     * Constructeur appelant le constructeur de la super classe
     * @param connexion
     */
    PersonnelDAO(Connection connexion) {
        super(connexion);
    }

    private PreparedStatement preparerStmt(PreparedStatement statement,Personnel obj) throws SQLException {
    	statement.setString(1, obj.getPrenom());
        statement.setString(2, obj.getNom());
        statement.setString(3, obj.getAdresse());
        statement.setString(4, obj.getNoTelephone());
        statement.setString(5, obj.getMotDePasse());
        statement.setString(6, obj.getRole().getRole());
        return statement;
    }
    /**
     * Fonction permettant l'insertion d'un Personnel dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(Personnel obj) throws SQLException {
        if(new RoleDAO(connexion).find(obj.getRole()) == null)
            throw new SQLException(" -- Erreur -- Le rôle n'existe pas.");
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="insert into Personnel (prenom,nom,adresse,noTelephone,motDePasse,role) values (?,?,?,?,?,?);";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            preparerStmt(statement,obj);
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un Personnel existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(Personnel obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="delete from Personnel where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un Personnel existant dans la base de données
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(Personnel obj) throws SQLException {
        if(new RoleDAO(connexion).find(obj.getRole()) == null)
            throw new SQLException(" -- Erreur -- Le rôle n'existe pas.");
        /* declaration de la requête qui sera executée sur la bdd */
        String requete ="update Personnel set prenom=?,nom=?,adresse=?,noTelephone=?,role=?,motDePasse=? where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getPrenom());
            statement.setString(2, obj.getNom());
            statement.setString(3, obj.getAdresse());
            statement.setString(4, obj.getNoTelephone());
            statement.setString(5, obj.getMotDePasse());
            statement.setString(6, obj.getRole().getRole());
            statement.setInt(7, obj.getId());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un Personnel existant dans la base de données en utilisant son identifiant
     * @param obj
     * @return depart
     * @throws SQLException
     */
    @Override
    public Personnel find(Personnel obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Personnel where id=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setInt(1, obj.getId());
            try(ResultSet result = statement.executeQuery();){
            	/* si on a trouvé un élément : on le renvoie */
                if(result.first()) {
                        return new Personnel(result.getInt("id"),result.getString("Prenom"),result.getString("nom"),result.getString("adresse")
                                        ,result.getString("noTelephone"), result.getString("motDePasse"),
                                        new RoleDAO(connexion).find(result.getString("role")));
                }
                return null;
            }
        }
    }

    public List<Personnel> findAll() throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Personnel";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            try(ResultSet result = statement.executeQuery();){
                ArrayList<Personnel> retour = new ArrayList<>();
            	/* ajoute les résultats potentiels à la liste des résultats */
                while (result.next()){
                    retour.add(new Personnel(result.getInt("id"),result.getString("Prenom"),result.getString("nom"),result.getString("adresse")
                                    ,result.getString("noTelephone"),result.getString("motDePasse"),
                                    new RoleDAO(connexion).find(result.getString("role"))));
                }
                return retour;
            }
        }
    }
}
