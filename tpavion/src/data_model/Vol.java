package data_model;

public class Vol {

	private int id;
	private int vol;
	
	Vol(int id,int vol){
		this.id = id;
		this.vol = vol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}
}
