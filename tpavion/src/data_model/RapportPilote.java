package data_model;

public class RapportPilote {

		private int pilote;
		private int depart;
		private String rapport;
		
		RapportPilote(int pilote,int depart,String rapport){
			this.pilote = pilote;
			this.depart = depart;
			this.rapport = rapport;
		}

		public int getPilote() {
			return pilote;
		}

		public void setPilote(int pilote) {
			this.pilote = pilote;
		}

		public int getDepart() {
			return depart;
		}

		public void setDepart(int depart) {
			this.depart = depart;
		}

		public String getRapport() {
			return rapport;
		}

		public void setRapport(String rapport) {
			this.rapport = rapport;
		}
}
