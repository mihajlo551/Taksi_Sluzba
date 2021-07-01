package funkcionalnosti;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import model.Dispeceri;
import model.PoslovanjeSluzbe;

public class DispeceriFunkcionalnosti {
	
public PoslovanjeSluzbe sluzba = new PoslovanjeSluzbe();
	
	public DispeceriFunkcionalnosti() {
		super();
		
		sluzba.ucitajDispecere();
	}

	
	public void dodajDispecera(Dispeceri d) {
		
		
		sluzba.getDispeceri().add(d);
		
		
		String adminStr = d.getId() + "|" + d.getIme() + "|" +  d.getPrezime() + "|" + d.getJmbg() + "|" + d.getPol() + "|" + 
				d.getAdresa() + "|" + d.getBrTelefona() + "|" + d.getKorisnickoIme() + 
				"|" + d.getLozinka()+ "|" + d.getObrisan() + "|" + d.getPlata() +  "|" + d.getBrLinije() + "|" + d.getOdjeljenje() +"\n";
		
		
		try {
			
			Files.write(Paths.get("src/fajlovi/dispeceri.txt"), adminStr.getBytes(), StandardOpenOption.APPEND);
			
		}catch(IOException e){
			System.out.println("Greska prilikom dodavanja u datoteku: " + e.getMessage());
		}
		
	}
	
	
	
	
	public void izmjeniDispecera(Dispeceri d) {
		
		Dispeceri adminIzListe = sluzba.getDispeceri().get(d.getId());
		
		adminIzListe.setIme(d.getIme());
		adminIzListe.setPrezime(d.getPrezime());
		adminIzListe.setJmbg(d.getJmbg());
		adminIzListe.setPol(d.getPol());
		adminIzListe.setAdresa(d.getAdresa());
		adminIzListe.setBrTelefona(d.getBrTelefona());
		adminIzListe.setKorisnickoIme(d.getKorisnickoIme());
		adminIzListe.setLozinka(d.getLozinka());
		adminIzListe.setObrisan(d.getObrisan());
		adminIzListe.setPlata(d.getPlata());
		adminIzListe.setBrLinije(d.getBrLinije());
		adminIzListe.setOdjeljenje(d.getOdjeljenje());
		
		
		 try {

	        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/dispeceri.txt"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;


	        while ((line = file.readLine()) != null) {
	            
	        	if (line.startsWith(Integer.toString(d.getId()))) {
	        		
	        		line = d.getId() + "|" + d.getIme() + "|" +  d.getPrezime() + "|" + d.getJmbg() + "|" + d.getPol() + "|" + 
	        				d.getAdresa() + "|" + d.getBrTelefona() + "|" + d.getKorisnickoIme() + 
	        				"|" + d.getLozinka()+ "|" + d.getObrisan() + "|" + d.getPlata() + "|" + d.getBrLinije() + "|" + d.getOdjeljenje();
	        		
	        		
	        	}
	        	
	        	inputBuffer.append(line);
	            inputBuffer.append('\n');
	        	
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();

	   
	        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/dispeceri.txt");
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();

		  }catch (Exception e) {
		        System.out.println("Problem pri citanju fajla.");
		  }
		
	}
	
	 public void izbrisiDispecera(Dispeceri d) {
		 
			Dispeceri adminIzListe = sluzba.getDispeceri().get(d.getId());
			
			adminIzListe.setObrisan(true);
			d.setObrisan(true);
			
			
			
			 try {

		        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/dispeceri.txt"));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;


		        while ((line = file.readLine()) != null) {
		            
		        	if (line.startsWith(Integer.toString(d.getId()))) {
		        		
		        		line = d.getId() + "|" + d.getIme() + "|" +  d.getPrezime() + "|" + d.getJmbg() + "|" + d.getPol() + "|" + 
		        				d.getAdresa() + "|" + d.getBrTelefona() + "|" + d.getKorisnickoIme() + 
		        				"|" + d.getLozinka()+ "|" + d.getObrisan() + "|" + d.getPlata() + "|" + d.getBrLinije() + "|" + d.getOdjeljenje();
		        		
		        		
		        	}
		        	
		        	inputBuffer.append(line);
		            inputBuffer.append('\n');
		        	
		        }
		        file.close();
		        String inputStr = inputBuffer.toString();

		   
		        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/dispeceri.txt");
		        fileOut.write(inputStr.getBytes());
		        fileOut.close();

			  }catch (Exception e) {
			        System.out.println("Problem pri citanju fajla.");
			  }
		 
	 }

}
