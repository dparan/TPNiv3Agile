package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data_model.Role;

public class RoleDAO extends DAO<Role> {

	/**
	 * Constructeur appelant le constructeur de la super classe
	 * @param connexion
	 */
	RoleDAO(Connection connexion) {
		super(connexion);
	}

	/**
	 * Fonction permettant l'insertion d'un Role dans la base de données
	 * @param obj
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean create(Role obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
		String requete = "insert into Role values (?,?);";
		try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
			statement.setString(1, obj.getRole());
			statement.setString(2, obj.getType().getType());
			/* retourne true si la requete s'est bien effectué */
			return statement.executeUpdate() > 0;
		}
	}

	/**
	 * Fonction permettant la suppression d'un Role existant dans la base de données
	 * @param obj
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean delete(Role obj) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
		String requete = "delete from Role where role=?;";
		try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
			statement.setString(1, obj.getRole());
			/* retourne true si la requete s'est bien effectué */
			return statement.executeUpdate() > 0;
		}
	}

	/**
	 * Fonction permettant la mise à jour d'un Role existant dans la base de données
	 * @param obj
	 * @return boolean
	 * @throws SQLException
	 */
	@Override
	public boolean update(Role obj) throws SQLException {
		/* modification impossible */
		return false;
	}

	/**
	 * Fonction permettant la récupération d'un Role existant dans la base de données en utilisant le rôle
	 * @param obj
	 * @return role
	 * @throws SQLException
	 */
	@Override
	public Role find(Role obj) throws SQLException {
		/* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Role where role=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, obj.getRole());
            try(ResultSet result = statement.executeQuery();){
            	/* si on a trouvé un élément : on le renvoie */
                if(result.first())
                    return new Role(result.getString("type"),result.getString("Role"));
                return null;
            }
        }
	}
        
    public Role find(String role) throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Role where role=?;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
            statement.setString(1, role);
            try(ResultSet result = statement.executeQuery();){
            	/* si on a trouvé un élément : on le renvoie */
                if(result.first())
                    return new Role(result.getString("type"),result.getString("Role"));
                return null;
            }
        }
    }
        
	
	public List<Role> findRoles() throws SQLException {
        /* declaration de la requête qui sera executée sur la bdd */
        String requete = "select * from Role;";
        try(PreparedStatement statement = super.connexion.prepareStatement(requete);){
             try(ResultSet result = statement.executeQuery();){
                ArrayList<Role> roles = new ArrayList<>();
            	/* ajoute les résultats potentiels à la liste des résultats */
                while(result.next())
                    roles.add(new Role(result.getString("type"),result.getString("Role")));
                return roles;
            }
        }
	}
}
