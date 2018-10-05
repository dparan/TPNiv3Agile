package data_model;

public class Navigant {
	
	private int id;
	private int typeNavigant;
	
	Navigant(int id,int typeNavigant){
		this.id = id;
		this.typeNavigant = typeNavigant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeNavigant() {
		return typeNavigant;
	}

	public void setTypeNavigant(int typeNavigant) {
		this.typeNavigant = typeNavigant;
	}
}
