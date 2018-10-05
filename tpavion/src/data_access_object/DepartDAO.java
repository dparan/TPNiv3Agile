package data_access_object;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data_model.Avion;
import data_model.Depart;

public class DepartDAO extends DAO<Depart, Integer> {

	DepartDAO(Connection connexion) {
		super(connexion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(Depart obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("insert into avion values ('"+obj.getId()+","+obj.getDateDepart()+","+obj.getVol()+");");
	}

	@Override
	public boolean delete(Depart obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("delete from avion" + " where id='"+obj.getId()+"'");
	}

	@Override
	public boolean update(Depart obj) throws SQLException {
		Statement stmt = super.connexion.createStatement();
		return stmt.execute("update depart set id='"+ obj.getId()+"', dateDepart='"+obj.getDateDepart()+
				"', vol='"+obj.getVol()+"';");
	}

	@Override
	public Depart find(Integer id) throws SQLException {
		Statement stmt = super.connexion.createStatement();
        ResultSet result = stmt.executeQuery("select * from depart where id='"+id+"';");
        result.first();
        return new Depart(result.getInt("id"),result.getDate("dateDepart"),result.getInt("vol"));
	}

}
