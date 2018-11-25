import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import data_model.Personnel;
import data_model.Role;
import data_model.TypeRole;
import systeme.SystemeGestionUtilisateur;

class testInsertPersonnel {

	private SystemeGestionUtilisateur sgu;
	
	@BeforeEach
	void setUp(){
		final String user = "root";
		final String pass = "";
		final String dbClass = "com.mysql.cj.jdbc.Driver";
		final String dbDriver = "jdbc:mysql://127.0.0.1:3306/tpavion?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		try {
			Class.forName(dbClass).newInstance();
			Connection conn = DriverManager.getConnection(dbDriver, user, pass);
			sgu = new SystemeGestionUtilisateur(conn); 
		} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testInsertionOK() {
		assert(sgu.ajouterUtilisateur(new Personnel("prenom","nom","adresse",BigInteger.valueOf(0),"mdp",new Role("nonnavigant","Service Technique"))));
	}
	
	
	@Test
	void testInsertionErreur() {
		assert(!sgu.ajouterUtilisateur(new Personnel("prenom","nom","adresse",BigInteger.valueOf(0),"mdp",new Role("test","test"))));
	}
	

}
