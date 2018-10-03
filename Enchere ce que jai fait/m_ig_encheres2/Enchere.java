package m_ig_encheres2;

import java.sql.SQLException;
import java.util.ArrayList;

public class Enchere {//une enchere n'a pas de clé primaire.
	public static EnchereDAO eDAO;
	private int idProduit;
	private int idEnchere;
	private double dureeRestante;
	private double prixActuel;
	private String mailVendeur;
	private ArrayList <Offre> listeOffres;
	/*public Enchere(double dureeRestante, double prixDepart, String mailVendeur, int idProduit)		//RETIRER
	{
		this.dureeRestante = dureeRestante;
		this.prixActuel = prixDepart;
		this.mailVendeur = mailVendeur;
		this.idProduit = idProduit;
		this.listeOffres = new ArrayList <>();
	}*/
	
	//demarrerEnchere
	public Enchere(double dureeRestante, double prixDepart, String mailVendeur)
	{
		this.dureeRestante = dureeRestante;
		this.prixActuel = prixDepart;
		this.mailVendeur = mailVendeur;
		this.listeOffres = new ArrayList <>();
		this.idEnchere = -1;
		this.idProduit = -1;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	public double getDureeRestante() {
		return dureeRestante;
	}
	public void setDureeRestante(double dureeRestante) {
		this.dureeRestante = dureeRestante;
	}
	public double getPrixActuel() {
		return prixActuel;
	}
	public void setPrixActuel(double prixActuel) {
		this.prixActuel = prixActuel;
	}
	public String getMailVendeur() {
		return mailVendeur;
	}
	public void setMailVendeur(String mailVendeur) {
		this.mailVendeur = mailVendeur;
	}
	public ArrayList<Offre> getListeOffres() {
		return listeOffres;
	}
	public void setListeOffres(ArrayList<Offre> listeOffres) {
		this.listeOffres = listeOffres;
	}
	
	public void ajouterOffre(Offre o) throws SQLException
	{
		this.listeOffres.add(o);
		if(this.idEnchere != -1)
			Offre.oDAO.enregistrerOffre(o, idEnchere);
	}
	public int getIdEnchere() {
		return idEnchere;
	}
	public void setIdEnchere(int idEnchere) {
		this.idEnchere = idEnchere;
	}


}
