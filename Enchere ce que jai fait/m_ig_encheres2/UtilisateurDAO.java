package m_ig_encheres2;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilisateurDAO extends DAO{

	@Override
	public boolean delete(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void enregistrerUtlilisateur(Utilisateur u)
	{
		
	}
	
	public boolean rechercheUtilisateur(String mailUtil) throws Exception {
		
		System.out.println("recherche utilisateur "+mailUtil);
		Statement stmt = DAO.conn.createStatement();
		System.out.println(" select u.mailUtil from utilisateur u where u.mailUtil = '" + mailUtil + "';");
		        ResultSet result = stmt.executeQuery("select u.mailUtil from utilisateur u where u.mailUtil = '" + mailUtil + "';");
		        /*ResultSetMetaData resultMeta = result.getMetaData();
		        
		  		System.out.println("\n**********************************");
		  		//On affiche le nom des colonnes
		  		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		  		{
		  			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
		  		}
		        result.next();*/
		        try{
		        	String resultat = result.getString("MAILUTIL");
		        	return true;
		        } catch(SQLException s)
		        {
		        	return false;
		        }
		  //System.out.println(result.get);
	}

}
