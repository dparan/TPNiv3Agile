package data_model;

public class TypeNavigant {

	private int id;
	private String typeNavigant; 
	
	TypeNavigant(int id, String typeNavigant)
	{
		this.id = id;
		this.typeNavigant = typeNavigant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeNavigant() {
		return typeNavigant;
	}

	public void getTypeNavigant(String typeNavigant) {
		this.typeNavigant = typeNavigant;
	}
}
