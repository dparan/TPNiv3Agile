package m_ig_encheres2;

import java.sql.Connection;


public abstract class DAO<T> {

	static Connection conn;
	
	public DAO()//Connection conn)
	{
		//this.conn = conn;
	}
	
	   

	  //public abstract T create(T obj);

	  /**
	  * Méthode pour effacer
	  * @param obj
	  * @return boolean 
	  */
	  public abstract boolean delete(T obj);

	  /**
	  * Méthode de mise à jour
	  * @param obj
	  * @return boolean
	  */
	  public abstract T find(int id);


	
}
