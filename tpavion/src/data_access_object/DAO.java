package data_access_object;

import java.sql.Connection;
import java.sql.SQLException;

abstract class DAO<T,V> {

	protected Connection connexion;

	DAO(Connection connexion) {
		this.connexion = connexion;
	}
	
	public abstract boolean create(T obj) throws SQLException;
	
	public abstract boolean delete(T obj) throws SQLException;
	
	public abstract boolean update(T obj) throws SQLException;
	
	/* 
	 * nous sommes obligés de placer un type générique en paramètre de la fonction 
	 * car l'identifiant des tables est différent selon les tables  
	 * */
	public abstract T find(V id) throws SQLException;
	
}
