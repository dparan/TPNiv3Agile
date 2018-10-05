package data_model;

public class TypeNonNavigant {
	
	private int id;
	private String typeNonNavigant;
	
	TypeNonNavigant(int id, String typeNonNavigant) {
		super();
		this.id = id;
		this.typeNonNavigant = typeNonNavigant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypeNonNavigant() {
		return typeNonNavigant;
	}

	public void setTypeNonNavigant(String typeNonNavigant) {
		this.typeNonNavigant = typeNonNavigant;
	}
	
	

}
