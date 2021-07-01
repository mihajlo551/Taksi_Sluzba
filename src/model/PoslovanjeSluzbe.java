package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class PoslovanjeSluzbe {
	
	private ArrayList<Dispeceri> dispeceri;
	private ArrayList<Musterija> musterije;
	private ArrayList<Vozac> vozaci;
	private  ArrayList<Automobil> automobili;
	private ArrayList<Voznja> voznje;
	
	

	public PoslovanjeSluzbe() {
		dispeceri = new ArrayList<Dispeceri>();
		musterije = new ArrayList<Musterija>();
		vozaci = new ArrayList<Vozac>();
		automobili = new ArrayList<Automobil>();
		voznje = new ArrayList<Voznja>();
		
		
	}
	
	public void ucitajDispecere() {
		try {
			File dispeceriFile = new File("src/fajlovi/dispeceri.txt");
			BufferedReader reader = new BufferedReader(new FileReader(dispeceriFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				String jmbg = lineSplit[3];
				String pol = lineSplit[4];
				String adresa = lineSplit[5];
				String brTelefona = lineSplit[6];
				String korisnickoIme = lineSplit[7];
				String lozinka = lineSplit[8];
				String obrisan = lineSplit[9];
				String plata = lineSplit[10];
				String brLinije = lineSplit[11];
				String odjeljenje = lineSplit[12];
				
				Dispeceri d = new Dispeceri(Integer.parseInt(id), ime, prezime,Long.parseLong(jmbg), Pol.valueOf(pol), adresa, Long.parseLong(brTelefona),
						korisnickoIme, lozinka,Boolean.parseBoolean(obrisan),Integer.parseInt(plata),Long.parseLong(brLinije), Odjeljenje.valueOf(odjeljenje));
				this.dispeceri.add(d);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	public void snimiDispecere() {
		String sadrzaj = "";
		for (Dispeceri d : this.dispeceri) {
			sadrzaj += d.getId() + "|" + d.getIme() + "|" +  d.getPrezime() + "|" + d.getJmbg() + "|" + d.getPol() + "|" + 
					d.getAdresa() + "|" + d.getBrTelefona() + "|" + d.getKorisnickoIme() + 
					"|" + d.getLozinka()+ "|" + d.getObrisan() + "|" + d.getPlata() +"|" + d.getBrLinije() +"|" + d.getOdjeljenje() + "\n";
		}
		try {
			File dispeceriFile = new File("src/fajlovi/dispeceri.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(dispeceriFile));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public void ucitajMusterije() {
		try {
			File musterijeFile = new File("src/fajlovi/musterije.txt");
			BufferedReader reader = new BufferedReader(new FileReader(musterijeFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				String jmbg = lineSplit[3];
				String pol = lineSplit[4];
				String adresa = lineSplit[5];
				String brTelefona = lineSplit[6];
				String korisnickoIme = lineSplit[7];
				String lozinka = lineSplit[8];
				String obrisan = lineSplit[9];
				
				
				Musterija m = new Musterija(Integer.parseInt(id), ime, prezime,Long.parseLong(jmbg), Pol.valueOf(pol), adresa, Long.parseLong(brTelefona),
						korisnickoIme, lozinka,Boolean.parseBoolean(obrisan));
				this.musterije.add(m);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
		
	}

	public void snimiMusterije() {
		String sadrzaj = "";
		for (Musterija m : this.musterije) {
			sadrzaj += m.getId() + "|" + m.getIme() + "|" +  m.getPrezime() + "|" + m.getJmbg() + "|" + m.getPol() + "|" + 
					m.getAdresa() + "|" + m.getBrTelefona() + "|" + m.getKorisnickoIme() + 
					"|" + m.getLozinka()+ "|" + m.getObrisan()  + "\n";
		}
		try {
			File musterijeFile = new File("src/fajlovi/musterije.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(musterijeFile));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public void ucitajVozace() {
		try {
			File vozaciFile = new File("src/fajlovi/vozaci.txt");
			BufferedReader reader = new BufferedReader(new FileReader(vozaciFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				String ime = lineSplit[1];
				String prezime = lineSplit[2];
				String jmbg = lineSplit[3];
				String pol = lineSplit[4];
				String adresa = lineSplit[5];
				String brTelefona = lineSplit[6];
				String korisnickoIme = lineSplit[7];
				String lozinka = lineSplit[8];
				String obrisan = lineSplit[9];
				String plata = lineSplit[10];
				String brClanskeKarte = lineSplit[11];
				String idAutomobila = lineSplit[12];
				
				Automobil a = nadjiAutomobil(Integer.parseInt(idAutomobila));
				
				Vozac v = new Vozac(Integer.parseInt(id), ime, prezime,Long.parseLong(jmbg), Pol.valueOf(pol), adresa, Long.parseLong(brTelefona),
						korisnickoIme, lozinka,Boolean.parseBoolean(obrisan),Integer.parseInt(plata),brClanskeKarte,a);
				this.vozaci.add(v);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	public void snimiVozace() {
		String sadrzaj = "";
		for (Vozac v : this.vozaci) {
			sadrzaj += v.getId() + "|" + v.getIme() + "|" +  v.getPrezime() + "|" + v.getJmbg() + "|" + v.getPol() + "|" + 
					v.getAdresa() + "|" + v.getBrTelefona() + "|" + v.getKorisnickoIme() + 
					"|" + v.getLozinka()+ "|" + v.getObrisan() + "|" + v.getPlata() + "|" +v.getBrClanskeKarte() + "|" +v.getAutomobil().getId()  + "\n";
		}
		try {
			File vozaciFile = new File("src/fajlovi/vozaci.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(vozaciFile));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	
	public void ucitajAutomobile() {
		try {
			File automobiliFile = new File("src/fajlovi/automobili.txt");
			BufferedReader reader = new BufferedReader(new FileReader(automobiliFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				String model = lineSplit[1];
				String proizvodjac = lineSplit[2];
				String godinaProizvodnje = lineSplit[3];
				String registracija = lineSplit[4];
				String vrstaAutomobila = lineSplit[5];
				String obrisan = lineSplit[6];
				
				
				Automobil a = new Automobil(Integer.parseInt(id),model, proizvodjac, Integer.parseInt(godinaProizvodnje),registracija,
						VrstaAutomobila.valueOf(vrstaAutomobila),Boolean.parseBoolean(obrisan));
				this.automobili.add(a);
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}

	public void snimiAutomobile() {
		String sadrzaj = "";
		for (Automobil a : this.automobili) {
			sadrzaj += a.getId() + "|" + a.getModel() + "|" +  a.getProizvodjac() + "|" + a.getGodinaProizvodnje() + "|" + a.getRegistracija() + "|" + 
					a.getVrstaAutomobila()+ "|" +   a.getObrisan()   +  "\n";
		}
		try {
			File automobiliFile = new File("src/fajlovi/automobili.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(automobiliFile));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	
	public void ucitajVoznje()   {
		try {
			File voznjeFile = new File("src/fajlovi/voznje.txt");
			BufferedReader reader = new BufferedReader(new FileReader(voznjeFile));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] lineSplit = line.split("\\|");
				String id = lineSplit[0];
				String termin = lineSplit[1];
				String adresaPolaska = lineSplit[2];
				String adresaDestinacije = lineSplit[3];
				String idMusterije = lineSplit[4];
				String idVozaca = lineSplit[5];
				String brPredjenihKm = lineSplit[6];
				String trajanjeVoznje = lineSplit[7];
				String status = lineSplit[8];
				String obrisan = lineSplit[9];
				
				
				
				
				Musterija m = nadjiMusteriju(Integer.parseInt(idMusterije));
				Vozac v = nadjiVozaca(Integer.parseInt(idVozaca));
				
			
				Voznja voznja;
				try {
					
					voznja = new Voznja(Integer.parseInt(id),new SimpleDateFormat("dd/mm/yyyy").parse(termin),adresaPolaska,adresaDestinacije,m,v, Integer.parseInt(brPredjenihKm),
							Integer.parseInt(trajanjeVoznje), Status.valueOf(status),Boolean.parseBoolean(obrisan));
					this.voznje.add(voznja);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			reader.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
	}
	
	public void snimiVoznje() {
		String sadrzaj = "";
		for (Voznja v : this.voznje) {
			
			
			String vozacId = "";
			
			if(v.getVozac()!=null)
				vozacId =  String.valueOf(v.getVozac().getId());
			else
				vozacId = "-1";
			
			sadrzaj += v.getId() + "|" + new SimpleDateFormat("dd/mm/yyyy").format(v.getTermin())  + "|" + v.getAdresaPolaska() + "|" + v.getAdresaDestinacije() + "|" + v.getMusterija().getId() + "|" + 
					vozacId + "|" + v.getBrPredjenihKm() + "|" + v.getTrajanjeVoznje() + "|" + v.getStatus() + "|"+ v.getObrisan()   +  "\n";
		}
		try {
			File voznjeFile = new File("src/fajlovi/voznje.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(voznjeFile));
			writer.write(sadrzaj);
			writer.close();
		}catch(IOException e){
			System.out.println("Greska prilikom ucitavanja datoteke: " + e.getMessage());
		}
		
	}
	
	
	
	public Dispeceri nadjiAdmina(int id) {
		for (Dispeceri admin : dispeceri) {
			if(admin.getId() == id) {
				return admin;
			}
		}
		return null;
	}
	
	public Voznja nadjiVoznje(int id) {
		for (Voznja voznja : voznje) {
			if(voznja.getId() == id) {
				return voznja;
			}
		}
		return null;
	}
	
	public Vozac nadjiServisera(int id) {
		for (Vozac v : vozaci) {
			if(v.getId() == id) {
				return v;
			}
		}
		return null;
	}
	
	public  Automobil nadjiAutomobil(int id) {
		for (Automobil a : automobili) {
			if(a.getId() == id) {
				return a;
			}
		}
		return null;
	}
	
	
	public Musterija nadjiMusteriju(int id) {
		for (Musterija musterija : musterije) {
			if(musterija.getId() == id) {
				return musterija;
			}
		}
		return null;
	}
	
	public Vozac nadjiVozaca(int id) {
		for (Vozac vozac : vozaci) {
			if(vozac.getId() == id) {
				return vozac;
			}
		}
		return null;
	}
	public ArrayList<Dispeceri> getDispeceri() {
		return dispeceri;
	}

	public void setDispeceri(ArrayList<Dispeceri> dispeceri) {
		this.dispeceri = dispeceri;
	}

	public ArrayList<Musterija> getMusterije() {
		return musterije;
	}

	public void setMusterije(ArrayList<Musterija> musterije) {
		this.musterije = musterije;
	}

	public ArrayList<Vozac> getVozaci() {
		return vozaci;
	}

	public void setVozaci(ArrayList<Vozac> vozaci) {
		this.vozaci = vozaci;
	}

	

	public ArrayList<Automobil> getAutomobili() {
		return automobili;
	}

	public void setAutomobili(ArrayList<Automobil> automobili) {
		this.automobili = automobili;
	}

	public ArrayList<Voznja> getVoznje() {
		return voznje;
	}

	public void setVoznje(ArrayList<Voznja> voznje) {
		this.voznje = voznje;
	}

	
	public Dispeceri loginDispecera(String korisnickoIme, String lozinka) {
		for(Dispeceri admin : dispeceri) {
			if(admin.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
					admin.getLozinka().equals(lozinka) && !admin.getObrisan()) {
				return admin;
			}
		}
		
		return null;
		
	}
		
		public Vozac loginVozac(String korisnickoIme, String lozinka) {
			for(Vozac vozac : vozaci) {
				if(vozac.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
						vozac.getLozinka().equals(lozinka) && !vozac.getObrisan()) {
					return vozac;
				}
			}
			
			return null;
	}
		
		
		public Musterija loginMusterije(String korisnickoIme, String lozinka) {
			for(Musterija musterija : musterije) {
				if(musterija.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) &&
						musterija.getLozinka().equals(lozinka) && !musterija.getObrisan()) {
					return musterija;
				}
			}
			
			return null;
	}
	
	public ArrayList<Dispeceri> sviNeobrisaniAdmini() {
		ArrayList<Dispeceri> neobrisani = new ArrayList<Dispeceri>();
		for (Dispeceri admin : dispeceri) {
			if(!admin.getObrisan()) {
				neobrisani.add(admin);
			}
		}
		return neobrisani;
	}
	
	public ArrayList<Vozac> sviNeobrisaniVozaci() {
		ArrayList<Vozac> neobrisani = new ArrayList<Vozac>();
		for (Vozac vozac : vozaci) {
			if(!vozac.getObrisan()) {
				neobrisani.add(vozac);
			}
		}
		return neobrisani;
	}
	
	public ArrayList<Musterija> sveNeobrisaneMusterije() {
		ArrayList<Musterija> neobrisani = new ArrayList<Musterija>();
		for (Musterija musterija : musterije) {
			if(!musterija.getObrisan()) {
				neobrisani.add(musterija);
			}
		}
		return neobrisani;
	}
	
	public ArrayList<Voznja> sveNeobrisaneVoznje() {
		ArrayList<Voznja> neobrisani = new ArrayList<Voznja>();
		for (Voznja voznja : voznje) {
			if(!voznja.getObrisan()) {
				neobrisani.add(voznja);
			}
		}
		return neobrisani;
	}
	
	public ArrayList<Voznja> sveNeobrisaneVoznjeVozaca(int vozacId) {
		ArrayList<Voznja> neobrisani = new ArrayList<Voznja>();
		for (Voznja voznja : voznje) {
			if(!voznja.getObrisan()) {
				if(voznja.getVozac() != null && voznja.getVozac().getId() == vozacId) {
					neobrisani.add(voznja);
				}else if(voznja.getVozac() == null) {
					neobrisani.add(voznja);
				}
			}
		}
		return neobrisani;
	}
	
	public ArrayList<Automobil> sviNeobrisaniAutomobili() {
		ArrayList<Automobil> neobrisani = new ArrayList<Automobil>();
		for (Automobil automobil : automobili) {
			if(!automobil.getObrisan()) {
				neobrisani.add(automobil);
			}
		}
		return neobrisani;
	}
	
	

}
