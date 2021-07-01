package model;

public abstract class Korisnik {
	
	protected int id;
	protected String ime;
	protected String prezime;
	protected long jmbg;
	protected Pol pol;
	protected String adresa;
	protected long brTelefona;
	protected String korisnickoIme;
	protected String lozinka;
	protected boolean obrisan;
	
	public Korisnik(int id, String ime, String prezime, long jmbg, Pol pol, String adresa, long brTelefona,
			String korisnickoIme, String lozinka, boolean obrisan) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.pol = pol;
		this.adresa = adresa;
		this.brTelefona = brTelefona;
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.obrisan = obrisan;
	}
	
	public Korisnik() {
		super();
		this.id = -1;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public long getJmbg() {
		return jmbg;
	}

	public void setJmbg(long jmbg) {
		this.jmbg = jmbg;
	}

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public long getBrTelefona() {
		return brTelefona;
	}

	public void setBrTelefona(long brTelefona) {
		this.brTelefona = brTelefona;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public boolean getObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

}
