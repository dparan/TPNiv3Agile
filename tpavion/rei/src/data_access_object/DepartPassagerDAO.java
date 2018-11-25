package data_access_object;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import data_model.Depart;
import data_model.DepartPassager;
import data_model.Passager;
import data_model.Vol;

public class DepartPassagerDAO extends DAO<DepartPassager> {

    /**
     * Constructeur appelant le constructeur de la super classe
     *
     * @param connexion
     */
    DepartPassagerDAO(Connection connexion) {
        super(connexion);
    }

    /**
     * Fonction permettant l'insertion d'un DepartPassager dans la base de données
     *
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean create(DepartPassager obj) throws SQLException {
        String requete = "insert into DepartPassager values (?,?,?,?);";
        try (PreparedStatement statement = super.connexion.prepareStatement(requete);) {
            statement.setString(1, obj.getPassager().getNumPasseport());
            statement.setInt(2, obj.getDepart().getId().getId());
            statement.setDate(3, obj.getDepart().getDateDepart());
            statement.setInt(4, obj.getNumPlace());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la suppression d'un DepartPassager existant dans la base de données
     *
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean delete(DepartPassager obj) throws SQLException {
        String requete = "delete from DepartPassager where numPasseport=? and id=? and dateDepart=?;";
        try (PreparedStatement statement = super.connexion.prepareStatement(requete);) {
            statement.setString(1, obj.getPassager().getNumPasseport());
            statement.setInt(2, obj.getDepart().getId().getId());
            statement.setDate(3, obj.getDepart().getDateDepart());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la mise à jour d'un DepartPassager existant dans la base de données
     *
     * @param obj
     * @return boolean
     * @throws SQLException
     */
    @Override
    public boolean update(DepartPassager obj) throws SQLException {
        String requete = "update DepartPassager set numPlace=? where numPasseport=? and id=? and dateDepart=?;";
        try (PreparedStatement statement = super.connexion.prepareStatement(requete);) {
            statement.setInt(1, obj.getNumPlace());
            statement.setString(2, obj.getPassager().getNumPasseport());
            statement.setInt(3, obj.getDepart().getId().getId());
            statement.setDate(4, obj.getDepart().getDateDepart());
            /* retourne true si la requete s'est bien effectué */
            return statement.executeUpdate() > 0;
        }
    }

    /**
     * Fonction permettant la récupération d'un DepartPassager existant dans la base de données en utilisant le numéro de passeport
     * du passager, l'identifiant et la date du départ
     *
     * @param obj
     * @return departpassager
     * @throws SQLException
     */
    @Override
    public DepartPassager find(DepartPassager obj) throws SQLException {
        String requete = "select * from DepartPassager where numPasseport=? and id=? and dateDepart=?;";
        try (PreparedStatement statement = super.connexion.prepareStatement(requete);) {
            statement.setString(1, obj.getPassager().getNumPasseport());
            statement.setInt(2, obj.getDepart().getId().getId());
            statement.setDate(3, obj.getDepart().getDateDepart());
            try (ResultSet result = statement.executeQuery();) {
                if (result.first()) {
                	Passager passager = new PassagerDAO(connexion).find(new Passager(result.getString("numPasseport")));
                	Vol vol = new VolDAO(connexion).find(new Vol(result.getInt("id"),0, ""));
                	Depart depart = new DepartDAO(connexion).find(new Depart( vol, result.getDate("dateDepart")));
                	return new DepartPassager(passager, depart, result.getInt("numPlace"));
                }
                return null;
            }
        }
    }

	public int findNextPlace(Depart depart) throws SQLException {
        String requete = "select noPlace from DepartPassager where id=? and dateDepart=? order by noPlace DESC;";
        try (PreparedStatement statement = super.connexion.prepareStatement(requete);) {
            statement.setInt(1, depart.getId().getId());
            statement.setDate(2, depart.getDateDepart());
            try (ResultSet result = statement.executeQuery();) {
                if (result.first())
                    return result.getInt("numPlace")+1;
                return 1;
            }
        }
		
	}
}