package data_model;

public class Passager {
	
	private int id;
	private String numPasseport;
	
	Passager(int id,String numPasseport)
	{
		this.id = id;
		this.numPasseport = numPasseport;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumPasseport() {
		return numPasseport;
	}

	public void setNumPasseport(String numPasseport) {
		this.numPasseport = numPasseport;
	}

}
