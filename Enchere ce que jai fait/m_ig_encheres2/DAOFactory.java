package m_ig_encheres2;

import java.sql.Connection;

public class DAOFactory {
	public static final Connection conn = null;   
 



	public static DAO getProduitDAO(){
	  return new ProduitDAO();//conn);
	}
	
	
	public static DAO getEnchereDAO(){
	  return new EnchereDAO();//conn);
	}
	
	
	public static DAO getOffreDAO(){
	  return new OffreDAO();//conn);
	}


	public static UtilisateurDAO getUtilisateurDAO() {
		  return new UtilisateurDAO();//conn);
	}   
}