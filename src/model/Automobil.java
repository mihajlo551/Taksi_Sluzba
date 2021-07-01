package model;

public class Automobil {
	
	private int id;
	private String model;
	private String proizvodjac;
	private int godinaProizvodnje;
	private String registracija;
	private VrstaAutomobila vrstaAutomobila;
	private boolean obrisan;
	
	public Automobil(
			int id,
			String model,
			String proizvodjac,
			int godinaProizvodnje,
			String registracija,
			VrstaAutomobila vrstaAutomobila,
			boolean obrisan
			) {
		
		super();
		this.id = id;
		this.model = model;
		this.proizvodjac = proizvodjac;
		this.godinaProizvodnje = godinaProizvodnje;
		this.registracija = registracija;
		this.vrstaAutomobila = vrstaAutomobila;
		this.obrisan = obrisan;
	}
	
	public Automobil() {
		this.id = -1; 
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getProizvodjac() {
		return proizvodjac;
	}
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	
	public int getGodinaProizvodnje() {
		return godinaProizvodnje;
	}
	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}
	public String getRegistracija() {
		return registracija;
	}
	public void setRegistracija(String registracija) {
		this.registracija = registracija;
	}
	
	public VrstaAutomobila getVrstaAutomobila() {
		return vrstaAutomobila;
	}
	public void setVrstaAutomobila(VrstaAutomobila vrstaAutomobila) {
		this.vrstaAutomobila = vrstaAutomobila;
	}
	public boolean getObrisan() {
		return obrisan;
	}
	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}
	
	@Override
	public String toString() {
		return "Automobil [id="+ id + ", model=" + model + ", proizvodjac=" + proizvodjac + ", godinaProizvodnje=" + godinaProizvodnje
				+ ", registracija=" + registracija + ",  vrstaAutomobila=" + vrstaAutomobila + ", obrisan=" + obrisan +  "]";
	}
	


}
