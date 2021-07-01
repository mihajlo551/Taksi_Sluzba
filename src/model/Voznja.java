package model;

import java.util.Date;
import java.util.ArrayList;


public class Voznja {
	
	private int id;
	private Date termin;
	private String adresaPolaska;
	private String adresaDestinacije;
	private Musterija musterija;
	private Vozac vozac;
	private int brPredjenihKm;
	private int trajanjeVoznje;
	private Status status;
	private boolean obrisan;
	
	
	public Voznja(int id, Date termin, String adresaPolaska, String adresaDestinacije, Musterija musterija,
			Vozac vozac, int brPredjenihKm, int trajanjeVoznje, Status status, boolean obrisan) {
		super();
		this.id = id;
		this.termin = termin;
		this.adresaPolaska = adresaPolaska;
		this.adresaDestinacije = adresaDestinacije;
		this.musterija = musterija;
		this.vozac = vozac;
		this.brPredjenihKm = brPredjenihKm;
		this.trajanjeVoznje = trajanjeVoznje;
		this.status = status;
		this.obrisan = obrisan;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getTermin() {
		return termin;
	}


	public void setTermin(Date termin) {
		this.termin = termin;
	}


	public String getAdresaPolaska() {
		return adresaPolaska;
	}


	public void setAdresaPolaska(String adresaPolaska) {
		this.adresaPolaska = adresaPolaska;
	}


	public String getAdresaDestinacije() {
		return adresaDestinacije;
	}


	public void setAdresaDestinacije(String adresaDestinacije) {
		this.adresaDestinacije = adresaDestinacije;
	}


	public Musterija getMusterija() {
		return musterija;
	}


	public void setMusterija(Musterija musterija) {
		this.musterija = musterija;
	}


	public Vozac getVozac() {
		return vozac;
	}


	public void setVozac(Vozac vozac) {
		this.vozac = vozac;
	}


	public int getBrPredjenihKm() {
		return brPredjenihKm;
	}


	public void setBrPredjenihKm(int brPredjenihKm) {
		this.brPredjenihKm = brPredjenihKm;
	}


	public int getTrajanjeVoznje() {
		return trajanjeVoznje;
	}


	public void setTrajanjeVoznje(int trajanjeVoznje) {
		this.trajanjeVoznje = trajanjeVoznje;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public boolean getObrisan() {
		return obrisan;
	}

	public void setObrisan(boolean obrisan) {
		this.obrisan = obrisan;
	}

	@Override
	public String toString() {
		return "Voznja [id=" + id + ", termin=" + termin + ", adresaPolaska=" + adresaPolaska + ", adresaDestinacije="
				+ adresaDestinacije + ", musterija=" + musterija + ", vozac=" + vozac + ", brPredjenihKm="
				+ brPredjenihKm + ", trajanjeVoznje=" + trajanjeVoznje + ", status=" + status + ", obrisan=" + obrisan
				+ "]";
	}
	
	
	
	

}
