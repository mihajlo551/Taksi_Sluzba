package funkcionalnosti;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import model.PoslovanjeSluzbe;
import model.Vozac;

public class VozacFunkcionalnosti {
	
	
public PoslovanjeSluzbe sluzba = new PoslovanjeSluzbe();
	
	public VozacFunkcionalnosti() {
		super();
		
		sluzba.ucitajVozace();
	}

	
	public void dodajVozaca(Vozac v) {
		
		
		sluzba.getVozaci().add(v);
		
		
		String adminStr = v.getId() + "|" + v.getIme() + "|" +  v.getPrezime() + "|" + v.getJmbg() + "|" + v.getPol() + "|" + 
				v.getAdresa() + "|" + v.getBrTelefona() + "|" + v.getKorisnickoIme() + 
				"|" + v.getLozinka()+ "|" + v.getObrisan() + "|" + v.getPlata() + "|" + v.getBrClanskeKarte() + "|" + v.getAutomobil().getId() +  "\n";
		
		
		try {
			
			Files.write(Paths.get("src/fajlovi/vozaci.txt"), adminStr.getBytes(), StandardOpenOption.APPEND);
			
		}catch(IOException e){
			System.out.println("Greska prilikom dodavanja u datoteku: " + e.getMessage());
		}
		
	}
	
	
	
	
	public void izmjeniVozaca(Vozac v) {
		
		Vozac vozacIzListe = sluzba.getVozaci().get(v.getId());
		
		vozacIzListe.setIme(v.getIme());
		vozacIzListe.setPrezime(v.getPrezime());
		vozacIzListe.setJmbg(v.getJmbg());
		vozacIzListe.setPol(v.getPol());
		vozacIzListe.setAdresa(v.getAdresa());
		vozacIzListe.setBrTelefona(v.getBrTelefona());
		vozacIzListe.setKorisnickoIme(v.getKorisnickoIme());
		vozacIzListe.setLozinka(v.getLozinka());
		vozacIzListe.setObrisan(v.getObrisan());
		vozacIzListe.setPlata(v.getPlata());
		vozacIzListe.setBrClanskeKarte(v.getBrClanskeKarte());
		vozacIzListe.setAutomobil(v.getAutomobil());
		
		
		 try {

	        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/vozaci.txt"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;


	        while ((line = file.readLine()) != null) {
	            
	        	if (line.startsWith(Integer.toString(v.getId()))) {
	        		
	        		line = v.getId() + "|" + v.getIme() + "|" +  v.getPrezime() + "|" + v.getJmbg() + "|" + v.getPol() + "|" + 
	        				v.getAdresa() + "|" + v.getBrTelefona() + "|" + v.getKorisnickoIme() + 
	        				"|" + v.getLozinka()+ "|" + v.getObrisan() + "|" + v.getPlata() + "|" + v.getBrClanskeKarte() + "|" + v.getAutomobil().getId() + "\n";
	        		
	        		
	        	}
	        	
	        	inputBuffer.append(line);
	            inputBuffer.append('\n');
	        	
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();

	   
	        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/vozaci.txt");
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();

		  }catch (Exception e) {
		        System.out.println("Problem pri citanju fajla.");
		  }
		
	}
	
	 public void izbrisiVozaca(Vozac v) {
		 
			Vozac vozacIzListe = sluzba.getVozaci().get(v.getId());
			
			vozacIzListe.setObrisan(true);
			v.setObrisan(true);
			
			
			
			 try {

		        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/vozaci.txt"));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;


		        while ((line = file.readLine()) != null) {
		            
		        	if (line.startsWith(Integer.toString(v.getId()))) {
		        		
		        		line = v.getId() + "|" + v.getIme() + "|" +  v.getPrezime() + "|" + v.getJmbg() + "|" + v.getPol() + "|" + 
		        				v.getAdresa() + "|" + v.getBrTelefona() + "|" + v.getKorisnickoIme() + 
		        				"|" + v.getLozinka()+ "|" + v.getObrisan() + "|" + v.getPlata() + "|" + v.getBrClanskeKarte() +  "|" +  "|" + v.getAutomobil().getId() + "\n";
		        		
		        		
		        	}
		        	
		        	inputBuffer.append(line);
		            inputBuffer.append('\n');
		        	
		        }
		        file.close();
		        String inputStr = inputBuffer.toString();

		   
		        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/vozaci.txt");
		        fileOut.write(inputStr.getBytes());
		        fileOut.close();

			  }catch (Exception e) {
			        System.out.println("Problem pri citanju fajla.");
			  }
		 
	 }
	

}
