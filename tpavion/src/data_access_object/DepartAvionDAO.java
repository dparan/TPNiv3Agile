package data_access_object;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import data_model.DepartAvion;

public class DepartAvionDAO extends DAO<DepartAvion, Integer[]> {

	DepartAvionDAO(Connection connexion) {
		super(connexion);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean create(DepartAvion obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(DepartAvion obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(DepartAvion obj) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public DepartAvion find(Integer[] id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
