package m_ig_encheres2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;



public class test {

	public static void nettoyerBDD(Connection conn) throws SQLException
	{
		Statement stmt = conn.createStatement();
        System.out.println(" DELETE FROM produit; DELETE FROM enchere; DELETE FROM offre; DELETE FROM utilisateur;");
        stmt.execute("DELETE FROM produit;");
        stmt.execute("DELETE FROM enchere;");
        stmt.execute("DELETE FROM offre;");
        stmt.execute("DELETE FROM utilisateur;");
	}
	
	public static void main(String[] args) throws Exception {
		
		String user = "rei_lp";
		String pass = "265fgjoy";
		String dbClass = "com.mysql.jdbc.Driver";
		String dbDriver = "jdbc:mysql://db4free.net:3306/test_enchere";
		Connection conn = null;

		    //load driver
		    try {
		        Class.forName(dbClass).newInstance();
		        System.out.println("driver loaded"); // THIS IS BEING RETURNED
		    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
		        System.err.println(ex);
		    }
		    // Connection
		    try {
		        conn = DriverManager.getConnection(dbDriver, user, pass);
		        DAO.conn = conn;
		        System.out.println("connected");
		        
		        ProduitDAO pDAO = (ProduitDAO) DAOFactory.getProduitDAO();
		        Produit.pDAO = pDAO;
		        OffreDAO oDAO = (OffreDAO)DAOFactory.getOffreDAO();
		        Offre.oDAO = oDAO;
	        	EnchereDAO eDAO = (EnchereDAO)DAOFactory.getEnchereDAO();
	        	Enchere.eDAO = eDAO;
	        	UtilisateurDAO uDAO = (UtilisateurDAO)DAOFactory.getUtilisateurDAO();
	        	Utilisateur.uDAO = uDAO;
		        
	        	//nettoyerBDD(conn);
	        	
		        /*Enchere e = new Enchere(100,1,"mail@gmail.fr");
		        e.ajouterOffre(new Offre("mailAcheteur1", 1, 100));
		        Produit.pDAO.enregistrerProduit(new Produit("titre", "description", "mailVendeur", e));
				e.ajouterOffre(new Offre("mailAch2", 1,100));*/
	        	
		        /*Statement stmt = conn.createStatement();
		        ResultSet result = stmt.executeQuery("SELECT * FROM `table_test` WHERE col1=5;");
		        ResultSetMetaData resultMeta = result.getMetaData();
		        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	      		{
	      			System.out.print("\t" + resultMeta.getColumnName(i) + "\t *");
	      		}
		        System.out.println("-> "+result);
		        while(result.next())
	      		{         
	        		for(int i = 1; i <= resultMeta.getColumnCount(); i++)
	        		{
	        			System.out.print("\t" + result.getObject(i).toString() + "\t |");
	        		}
	        		System.out.println("\n---------------------------------");																										
	      		}*/
	        	
	        	Utilisateur util = new Utilisateur("nomUtil", "mailUtil", "mdp");
	        	System.out.println(Utilisateur.uDAO.rechercheUtilisateur("mail"));
		        

		    } catch (SQLException ex) {
		        System.out.println("SQLException: " + ex.getMessage());
		    }
		    

	}

}

