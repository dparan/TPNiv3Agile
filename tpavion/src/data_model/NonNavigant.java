package data_model;

public class NonNavigant {

	private int id;
	private int typeNonNavigant;
	
	NonNavigant(int id, int typeNonNavigant) {
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
	
	public int getTypeNonNavigant() {
		return typeNonNavigant;
	}
	
	public void setTypeNonNavigant(int typeNonNavigant) {
		this.typeNonNavigant = typeNonNavigant;
	}
	
	
}
