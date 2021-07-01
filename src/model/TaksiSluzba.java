package model;

public class TaksiSluzba {
	
	private int id;
	private int pib;
	private String naziv;
	private String adresa;
	private int cijenaStarta;
	private int cijenaKilometra;
	private boolean obrisan;
	
	public TaksiSluzba(
			int id,
			int pib,
			String naziv,
			String adresa,
			int cijenaStrata,
			int cijenaKilometra,
			boolean obrisan) {
		
		super();
		this.id = id;
		this.pib = pib;
		this.naziv = naziv;
		this.adresa = adresa;
		this.cijenaStarta = cijenaStarta;
		this.cijenaKilometra = cijenaKilometra;
		this.obrisan = obrisan;
	}
	
	public TaksiSluzba() {
		this.id = -1; 
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPib() {
		return pib;
	}
	public void setPib(int pib) {
		this.pib = pib;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public int getCijenaStarta() {
		return cijenaStarta;
	}
	public void setCijenaStarta(int cijenaStarta) {
		this.cijenaStarta = cijenaStarta;
	}
	public int getCijenaKilometra() {
		return cijenaKilometra;
	}
	public void setCijenaKilometra(int cijenaKilometra) {
		this.cijenaKilometra = cijenaKilometra;
	}
	
	public boolean getObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	@Override
	public String toString() {
		return "Automobil [id="+ id + ", pib=" + pib + ", naziv=" + naziv + ", adresa=" + adresa + ", cijenaStarta=" + cijenaStarta
				+ ", cijenaKilometra=" + cijenaKilometra + ", obrisan=" + obrisan +  "]";
	}
	

}
