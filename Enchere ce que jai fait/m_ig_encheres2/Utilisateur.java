package m_ig_encheres2;

public class Utilisateur {
	public static UtilisateurDAO uDAO;
	
	private String nomUtil;
	private String mailUtil;
	private String mdp;
	

	public Utilisateur(String nomUtil, String mailUtil, String mdp) {
		this.nomUtil = nomUtil;
		this.mailUtil = mailUtil;
		this.mdp = mdp;
		
	}


	
}
