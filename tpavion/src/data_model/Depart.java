package data_model;

import java.sql.Date;

public class Depart {

	private int id;
	private Date dateDepart;
	private int vol;
	
	public Depart(int id,Date dateDepart,int vol){
		this.id = id;
		this.dateDepart = dateDepart;
		this.vol = vol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public int getVol() {
		return vol;
	}

	public void setVol(int vol) {
		this.vol = vol;
	}
	
}
