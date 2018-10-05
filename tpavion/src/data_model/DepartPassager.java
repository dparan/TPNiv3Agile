package data_model;

public class DepartPassager {
	
	private int passager;
	private int depart;
	private int noDepart;
	
	DepartPassager(int passager,int depart, int noDepart){
		this.passager = passager;
		this.depart = depart;
		this.noDepart = noDepart;
	}

	public int getPassager() {
		return passager;
	}

	public void setPassager(int passager) {
		this.passager = passager;
	}

	public int getDepart() {
		return depart;
	}

	public void setDepart(int depart) {
		this.depart = depart;
	}

	public int getNoDepart() {
		return noDepart;
	}

	public void setNoDepart(int noDepart) {
		this.noDepart = noDepart;
	}

}
