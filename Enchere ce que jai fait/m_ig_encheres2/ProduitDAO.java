package m_ig_encheres2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class ProduitDAO extends DAO<Produit> {
	
	public ProduitDAO()//Connection conn)
	{
		//super(conn);
	}

	public Produit create(Produit obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Produit obj) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public Produit find(int id) {
		// TODO Auto-generated method stub
		/*try {
		      ResultSet result = this.conn.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE,
		        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM produit WHERE produit_ = " + id);
		      if(result.first())
		        eleve = new Eleve(
		          id,
		          result.getString("elv_nom"),
		          result.getString("elv_prenom"
		        ));         
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }*/
		return null;
	}
	
	
	public boolean enregistrerProduit(Produit p) throws Exception {
        System.out.println("sauvegarde Produit...");
        Statement stmt = DAO.conn.createStatement();
        System.out.println(" select max(p.idProduit) from produit p");
        ResultSet result = stmt.executeQuery("select max(p.idProduit) from produit p");
        /*ResultSetMetaData resultMeta = result.getMetaData();
        
  		System.out.println("\n**********************************");
  		//On affiche le nom des colonnes
  		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
  		{
  			System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
  		}*/
        result.next();
        int idProduit = result.getInt("MAX(P.IDPRODUIT)")+1;
        p.setIdProduit(idProduit);

  		
        	System.out.println(" insert into " + "produit" + " values ('"+ p.getTitre()
        	+"','" + p.getDescription() + "','" + p.getMailVendeur()+ "', " + idProduit + ");");

        	
        	stmt.execute("insert into " + "produit" + " values ('"+ p.getTitre()
        	+"','" + p.getDescription() + "','" + p.getMailVendeur()+ "', " + idProduit + ");");
            System.out.println("  Produit sauvegardé");
            Enchere.eDAO.enregistrerEnchere(p.getEnchere(), idProduit);
            
         Utilisateur u = new Utilisateur ("nomutil", "mailUtil", "mdp");   
            
         
         stmt.close();
         return true;

		
	}
}
