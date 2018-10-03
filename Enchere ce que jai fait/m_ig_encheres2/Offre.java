package m_ig_encheres2;

public class Offre {
	public static OffreDAO oDAO;
	private int prix;
	private String mailAcheteur;
	private double decompteTemps;
	private int idEnchere;
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public String getMailAcheteur() {
		return mailAcheteur;
	}
	public void setMailAcheteur(String mailAcheteur) {
		this.mailAcheteur = mailAcheteur;
	}
	public double getDecompteTemps() {
		return decompteTemps;
	}
	public void setDecompteTemps(double decompteTemps) {
		this.decompteTemps = decompteTemps;
	}
	public int getIdEnchere() {
		return idEnchere;
	}
	public void setIdEnchere(int idEnchere) {
		this.idEnchere = idEnchere;
	}
	public Offre(String mailAcheteur, int prix, double decompteTemps, int idEnchere)//		RETIRER
	{
		this.mailAcheteur = mailAcheteur;
		this.prix = prix;
		this.decompteTemps = decompteTemps;
		this.idEnchere = idEnchere;
	}
	
	public Offre(String mailAcheteur, int prix, double decompteTemps)
	{
		this.mailAcheteur = mailAcheteur;
		this.prix = prix;
		this.decompteTemps = decompteTemps;
	}
}
