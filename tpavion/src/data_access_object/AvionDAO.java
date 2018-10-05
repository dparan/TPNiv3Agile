package data_access_object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data_model.Avion;
import data_model.Personne;

public class AvionDAO extends DAO<Avion, Integer> {

	AvionDAO(Connection connexion) {
		super(connexion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Avion obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("insert into avion values ('"+obj.getId()+","+obj.getNom()+","+obj.getTypeAvion()+","+obj.getCapacite()+");");
	}

	@Override
	public boolean delete(Avion obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("delete from avion" + " where id='"+obj.getId()+"' ;");
	}

	@Override
	public boolean update(Avion obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("update avion set id='"+ obj.getId()+"', nom='"+obj.getNom()+
				"', typeAvion='"+obj.getTypeAvion()+"', capacite='"+obj.getCapacite()+"';");
	
	}

	@Override
	public Avion find(Integer id) throws SQLException {
		Statement stmt = super.connexion.createStatement();
        ResultSet result = stmt.executeQuery("select * from avion where id='"+id+"';");
        result.first();
        return new Avion(result.getInt("id"),result.getString("nom"),result.getInt("typeAvion"),result.getInt("capacite"));
	
	}

}
