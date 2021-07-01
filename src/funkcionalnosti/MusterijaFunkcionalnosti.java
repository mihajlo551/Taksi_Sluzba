package funkcionalnosti;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import model.Musterija;
import model.PoslovanjeSluzbe;

public class MusterijaFunkcionalnosti {
	
public PoslovanjeSluzbe sluzba = new PoslovanjeSluzbe();
	
	public MusterijaFunkcionalnosti() {
		super();
		
		sluzba.ucitajMusterije();
	}

	
	public void dodajMusterije(Musterija m) {
		
		
		sluzba.getMusterije().add(m);
		
		
		String musterijaStr = m.getId() + "|" + m.getIme() + "|" +  m.getPrezime() + "|" + m.getJmbg() + "|" + m.getPol() + "|" + 
				m.getAdresa() + "|" + m.getBrTelefona() + "|" + m.getKorisnickoIme() + 
				"|" + m.getLozinka()+ "|" + m.getObrisan()  + "\n";
		
		
		try {
			
			Files.write(Paths.get("src/fajlovi/musterije.txt"), musterijaStr.getBytes(), StandardOpenOption.APPEND);
			
		}catch(IOException e){
			System.out.println("Greska prilikom dodavanja u datoteku: " + e.getMessage());
		}
		
	}
	
	
	
	
	public void izmjeniMusterije(Musterija m) {
		
		Musterija musterijaIzListe = sluzba.getMusterije().get(m.getId());
		
		musterijaIzListe.setIme(m.getIme());
		musterijaIzListe.setPrezime(m.getPrezime());
		musterijaIzListe.setJmbg(m.getJmbg());
		musterijaIzListe.setPol(m.getPol());
		musterijaIzListe.setAdresa(m.getAdresa());
		musterijaIzListe.setBrTelefona(m.getBrTelefona());
		musterijaIzListe.setKorisnickoIme(m.getKorisnickoIme());
		musterijaIzListe.setLozinka(m.getLozinka());
		musterijaIzListe.setObrisan(m.getObrisan());
		
		
		
		 try {

	        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/musterije.txt"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;


	        while ((line = file.readLine()) != null) {
	            
	        	if (line.startsWith(Integer.toString(m.getId()))) {
	        		
	        		line = m.getId() + "|" + m.getIme() + "|" +  m.getPrezime() + "|" + m.getJmbg() + "|" + m.getPol() + "|" + 
	        				m.getAdresa() + "|" + m.getBrTelefona() + "|" + m.getKorisnickoIme() + 
	        				"|" + m.getLozinka()+ "|" + m.getObrisan() ;
	        		
	        		
	        	}
	        	
	        	inputBuffer.append(line);
	            inputBuffer.append('\n');
	        	
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();

	   
	        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/musterije.txt");
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();

		  }catch (Exception e) {
		        System.out.println("Problem pri citanju fajla.");
		  }
		
	}
	
	 public void izbrisiMusteriju(Musterija m) {
		 
			Musterija musterijaIzListe = sluzba.getMusterije().get(m.getId());
			
			musterijaIzListe.setObrisan(true);
			m.setObrisan(true);
			
			
			
			 try {

		        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/musterije.txt"));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;


		        while ((line = file.readLine()) != null) {
		            
		        	if (line.startsWith(Integer.toString(m.getId()))) {
		        		
		        		line = m.getId() + "|" + m.getIme() + "|" +  m.getPrezime() + "|" + m.getJmbg() + "|" + m.getPol() + "|" + 
		        				m.getAdresa() + "|" + m.getBrTelefona() + "|" + m.getKorisnickoIme() + 
		        				"|" + m.getLozinka()+ "|" + m.getObrisan() ;
		        		
		        		
		        	}
		        	
		        	inputBuffer.append(line);
		            inputBuffer.append('\n');
		        	
		        }
		        file.close();
		        String inputStr = inputBuffer.toString();

		   
		        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/musterije.txt");
		        fileOut.write(inputStr.getBytes());
		        fileOut.close();

			  }catch (Exception e) {
			        System.out.println("Problem pri citanju fajla.");
			  }	 
		 
	 }

}
