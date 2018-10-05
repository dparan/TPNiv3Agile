package data_model;

public class TypeAvion {

	private int id;
	private String typeAvion;
	
	TypeAvion(int id, String typeAvion){
		this.id = id;
		this.typeAvion = typeAvion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeAvion() {
		return typeAvion;
	}

	public void setTypeAvion(String typeAvion) {
		this.typeAvion = typeAvion;
	}
	
}
