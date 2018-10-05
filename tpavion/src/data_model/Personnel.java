package data_model;

public class Personnel {
	private int id;
	private String motDePasse; 
	
	Personnel(int id, String motDePasse)
	{
		this.id = id;
		this.motDePasse = motDePasse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
}
