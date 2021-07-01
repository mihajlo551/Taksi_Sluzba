package model;

public class Dispeceri extends Korisnik{
	
	
	protected int plata;
	protected long brLinije;
	protected Odjeljenje odjeljenje;

	public Dispeceri(
			int id,
			String ime,
			String prezime,
			long jmbg,
			Pol pol,
			String adresa,
			long brTelefona,
			String korisnickoIme,
			String lozinka,
			boolean obrisan,
			int plata,
			long brLinije,
			Odjeljenje odjeljenje
			) {
		super(id, ime, prezime, jmbg, pol, adresa, brTelefona, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
		this.brLinije = brLinije;
		this.odjeljenje = odjeljenje;
	}

	public int getPlata() {
		return plata;
	}

	public void setPlata(int plata) {
		this.plata = plata;
	}
	
	public long getBrLinije() {
		return brLinije;
	}

	public void setBrLinije(long brLinije) {
		this.brLinije = brLinije;
	}
	
	public Odjeljenje getOdjeljenje() {
		return odjeljenje;
	}

	public void setOdjeljenje(Odjeljenje odjeljenje) {
		this.odjeljenje = odjeljenje;
	}
	
	@Override
	public String toString() {
		return "Administrator [id="+ id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol=" + pol
				+ ", adresa=" + adresa + ", brTelefona=" + brTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka
				+ ", obrisan=" + obrisan + ",plata=" + plata + ",brLinije=" + brLinije + ",odjeljenje=" + odjeljenje + "]";
	}

}
