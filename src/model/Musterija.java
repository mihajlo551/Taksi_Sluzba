package model;

public class Musterija extends Korisnik {

	public Musterija(int id, String ime, String prezime, long jmbg, Pol pol, String adresa, long brTelefona,
			String korisnickoIme, String lozinka, boolean obrisan) {
		super(id, ime, prezime, jmbg, pol, adresa, brTelefona, korisnickoIme, lozinka, obrisan);
		}
		
		
		public Musterija() {
		 super();
	}


		@Override
		public String toString() {
			return "Serviser [id="+ id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol=" + pol
					+ ", adresa=" + adresa + ", brTelefona=" + brTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka
					+ ", obrisan=" + obrisan + "]";
		}	
	

}
