package data_model;

import java.sql.Time;

public class Pilote 
{
	private int id;
	private Time nombreHeureTotale; 
	
	Pilote(int id, Time nombreHeureTotale)
	{
		this.id = id;
		this.nombreHeureTotale = nombreHeureTotale;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getNombreHeureTotale() {
		return nombreHeureTotale;
	}

	public void setNombreHeureTotale(Time nombreHeureTotale) {
		this.nombreHeureTotale = nombreHeureTotale;
	}
	
	
}
