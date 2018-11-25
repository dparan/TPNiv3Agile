package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import systeme.SystemeGestionUtilisateur;

class TestConnexion {

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

	/* connexion réussie */
	@Test
	void testConnexionReussie() {
		assert(sgu.connexion(1, "password"));
	}
	/* identifiant non existant */
	@Test
	void testConnexionEchecId() {
		assert(!sgu.connexion(233, "password"));
	}
	
	/* mot de passe erroné */
	@Test
	void testConnexionEchecMdp() {
		assert(!sgu.connexion(1, "blabla"));
	}
	/* identifiant non existant et mot de passe erroné */
	@Test
	void testConnexionEchecIdMdp() {
		assert(!sgu.connexion(1, "blabla"));
	}
	
	/* connexion réussie la deconnexion doit donc s'effectuer */
	@Test
	void testDeconnexionOk() {
		assert(sgu.connexion(1,"password"));
		assert(sgu.deconnexion());
	}
	/* connexion echec donc deconnexion doit donc être un echec */
	@Test
	void testDeconnexionEchec() {
		assert(!sgu.connexion(1, "blabla"));
		assert(!sgu.deconnexion());
	}
}
