package model;

public class Vozac extends Korisnik {
	
	protected int plata;
	protected String brClanskeKarte;
	protected Automobil automobil;
	
	
	public Vozac(int id, String ime, String prezime, long jmbg, Pol pol, String adresa, long brTelefona,
			String korisnickoIme, String lozinka, boolean obrisan, int plata, String brClanskeKarte,Automobil automobil) {
		super(id, ime, prezime, jmbg, pol, adresa, brTelefona, korisnickoIme, lozinka, obrisan);
		this.plata = plata;
		this.brClanskeKarte =brClanskeKarte;
		this.automobil = automobil;
	}
	
	public Vozac() {
		super();
	}
	
	public int getPlata() {
		return plata;
	}
	public void setPlata(int plata) {
		this.plata = plata;
	}
	public String getBrClanskeKarte() {
		return brClanskeKarte;
	}
	public void setBrClanskeKarte(String brClanskeKarte) {
		this.brClanskeKarte = brClanskeKarte;
	}
	
	public Automobil getAutomobil() {
		return automobil;
	}
	public void setAutomobil(Automobil automobil) {
		this.automobil = automobil;
	}
	
	
	
	@Override
	public String toString() {
		return "Vozac [id="+ id + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", pol=" + pol
				+ ", adresa=" + adresa + ", brTelefona=" + brTelefona + ", korisnickoIme=" + korisnickoIme + ", lozinka=" + lozinka
				+ ", obrisan=" + obrisan + ",plata=" + plata + ",brClanskeKarte=" + brClanskeKarte + ",automobil=" + automobil + "]";
	}

}
		