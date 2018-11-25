package data_access_object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data_model.Personne;

public class PersonneDAO extends DAO<Personne, Integer> {

	PersonneDAO(Connection connexion) {
		super(connexion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Personne obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("insert into personne values ('"+obj.getId()+","+obj.getPrenom()+","+obj.getNom()+","+obj.getAdresse()+","+obj.getNoTelephone()+ ");");
	}

	@Override
	public boolean delete(Personne obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("delete from personne" + " where id='"+obj.getId()+"';");
	}

	@Override
	public boolean update(Personne obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("update personne set id='"+ obj.getId()+"', prenom='"+obj.getPrenom()+
				"', nom='"+obj.getNom()+"', adresse='"+obj.getAdresse()+"', noTelephone='"+obj.getNoTelephone()+"' where id='"+obj.getId()+"';");
	}

	@Override
	public Personne find(Integer id) throws SQLException {
		Statement stmt = super.connexion.createStatement();
        ResultSet result = stmt.executeQuery("select * from personne where id='"+id+"';");
        result.first();
        return new Personne(result.getInt("id"),result.getString("prenom"),result.getString("nom"),result.getString("adresse"),result.getBigDecimal("noTelephone").toBigInteger());
	}

}
