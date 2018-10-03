package m_ig_encheres2;

import java.sql.Date;

public class Produit {
	private String titre;
	private String description;
	private String mailVendeur;
	private Enchere enchere;//unique
	private int idProduit;
	public static ProduitDAO pDAO;
	public Produit(String titre, String description, String mailVendeur, Enchere enchere)
	{
		this.titre = titre;
		this.description = description;
		this.mailVendeur = mailVendeur;
		this.enchere = enchere;
	}
	public String donneTitre() {
		// TODO Auto-generated method stub
		return this.titre;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMailVendeur() {
		return mailVendeur;
	}
	public void setMailVendeur(String mailVendeur) {
		this.mailVendeur = mailVendeur;
	}
	public Enchere getEnchere() {
		return enchere;
	}
	public void setEnchere(Enchere enchere) {
		this.enchere = enchere;
	}
	public int getIdProduit() {
		return idProduit;
	}
	public void setIdProduit(int idProduit) {
		this.idProduit = idProduit;
	}
	
	
}
