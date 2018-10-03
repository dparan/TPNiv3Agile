package m_ig_encheres2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OffreDAO extends DAO<Offre>  {

	public OffreDAO(){//Connection conn) {
		//super(conn);
		// TODO Auto-generated constructor stub
	}

	public boolean create(Offre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Offre obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Offre find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void enregistrerOffre(Offre o, int idEnchere) throws SQLException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
			System.out.println("sauvegarde Offre...");
			Statement stmt = DAO.conn.createStatement();
				
		    /*System.out.println(" select max(e.idEnchere) from enchere e");
		    ResultSet result = stmt.executeQuery("select max(e.idEnchere) from enchere e");
		    result.next();
		    int idEnchere = result.getInt("MAX(E.IDENCHERE)")+1;*/
		        
		    System.out.println(" insert into " + "offre" + " values ('"+ o.getMailAcheteur()
		    +"','" + o.getPrix() + "','" + o.getDecompteTemps()+ "', " + idEnchere + ");");

	    	stmt.execute("insert into " + "offre" + " values ('"+ o.getMailAcheteur()
		    +"','" + o.getPrix() + "','" + o.getDecompteTemps()+ "', " + idEnchere + ");");

		    stmt.close();
		    System.out.println("  Offre sauvegardée");

	}

}
