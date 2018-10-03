package m_ig_encheres2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class EnchereDAO extends DAO<Enchere>  {

	public EnchereDAO(){//Connection conn) {
		//super(conn);
		// TODO Auto-generated constructor stub
	}


	public Enchere create(Enchere obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Enchere obj) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public Enchere find(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	//ALTER TABLE document MODIFY COLUMN document_id INT auto_increment
	public void enregistrerEnchere(Enchere e, int idProduit) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("sauvegarde Enchere...");
		Statement stmt = DAO.conn.createStatement();
		
        System.out.println(" select max(e.idEnchere) from enchere e");
        ResultSet result = stmt.executeQuery("select max(e.idEnchere) from enchere e");
        result.next();
        int idEnchere = result.getInt("MAX(E.IDENCHERE)")+1;
        e.setIdEnchere(idEnchere);
        
    	System.out.println(" insert into " + "enchere" + " values ('"+ e.getMailVendeur()
    	+"','" + e.getPrixActuel() + "','" + e.getDureeRestante()+ "', " + idProduit + ", " + idEnchere + ");");
         
    	System.out.println("insert into " + "enchere" + " values ('"+ e.getMailVendeur()
    	+"','" + e.getPrixActuel() + "','" + e.getDureeRestante()+ "', " + idProduit + ", " + idEnchere + ");");

        stmt.close();
        System.out.println("  Enchere sauvegardée");
        for(Offre offre : e.getListeOffres())
        {
        	Offre.oDAO.enregistrerOffre(offre, idEnchere);
        	offre.setIdEnchere(idEnchere);
        }
	}

	
}
