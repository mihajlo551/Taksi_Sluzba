package funkcionalnosti;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import model.Voznja;
import model.PoslovanjeSluzbe;


public class VoznjaFunkcionalnosti {
	
	
public PoslovanjeSluzbe sluzba = new PoslovanjeSluzbe();
	
	public VoznjaFunkcionalnosti() {
		super();
		
		sluzba.ucitajVoznje();
	}

	
	public void dodajVoznju(Voznja v) {
		
		
		sluzba.getVoznje().add(v);
		
		
		String voznjaStr = "";
		
		voznjaStr = v.getId() + "|" + v.getTermin()+ "|" +  v.getAdresaPolaska() + "|" + v.getAdresaDestinacije() + "|" + v.getMusterija().getId() + "|" + 
				v.getVozac().getId() + "|" + v.getBrPredjenihKm() + "|" + v.getTrajanjeVoznje() + "|"  + v.getStatus() + "|" + v.getObrisan()  +  "\n";
		
		
		
		try {
			
			Files.write(Paths.get("src/fajlovi/voznje.txt"), voznjaStr.getBytes(), StandardOpenOption.APPEND);
			
		}catch(IOException e){
			System.out.println("Greska prilikom dodavanja u datoteku: " + e.getMessage());
		}
		
	}
	
	
	
	
	public void izmjeniVoznju(Voznja v) {
		
		Voznja voznjaIzListe = sluzba.getVoznje().get(v.getId());
		
		voznjaIzListe.setTermin(v.getTermin());
		voznjaIzListe.setAdresaPolaska(v.getAdresaPolaska());
		voznjaIzListe.setAdresaDestinacije(v.getAdresaDestinacije());
		voznjaIzListe.setMusterija(v.getMusterija());
		voznjaIzListe.setVozac(v.getVozac());
		voznjaIzListe.setBrPredjenihKm(v.getBrPredjenihKm());
		voznjaIzListe.setTrajanjeVoznje(v.getTrajanjeVoznje());
		voznjaIzListe.setStatus(v.getStatus());
		voznjaIzListe.setObrisan(v.getObrisan());
		
		
		
		 try {

	        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/voznje.txt"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;


	        while ((line = file.readLine()) != null) {
	            
	        	if (line.startsWith(Integer.toString(v.getId()))) {
	        		
	        		
	        		
	        		line = v.getId() + "|" + v.getTermin()+ "|" +  v.getAdresaPolaska() + "|" + v.getAdresaDestinacije() + "|" + v.getMusterija().getId() + "|" + 
	        				v.getVozac().getId() + "|" + v.getBrPredjenihKm() + "|" + v.getTrajanjeVoznje() + "|"  + v.getStatus() + "|" + v.getObrisan()  +  "\n";
	        		
	        		
	        	}
	        	
	        	inputBuffer.append(line);
	            inputBuffer.append('\n');
	        	
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();

	   
	        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/voznje.txt");
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();

		  }catch (Exception e) {
		        System.out.println("Problem pri citanju fajla.");
		  }
		
	}
	
	 public void izbrisiVoznju(Voznja v) {
		 
			Voznja voznjaIzListe = sluzba.getVoznje().get(v.getId());
			
			voznjaIzListe.setObrisan(true);
			v.setObrisan(true);
			
			
			
			 try {

		        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/voznje.txt"));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;


		        while ((line = file.readLine()) != null) {
		            
		        	if (line.startsWith(Integer.toString(v.getId()))) {
		        		
		        		
		        		
		        		line = v.getId() + "|" + v.getTermin()+ "|" +  v.getAdresaPolaska() + "|" + v.getAdresaDestinacije() + "|" + v.getMusterija().getId() + "|" + 
		        				v.getVozac().getId() + "|" + v.getBrPredjenihKm() + "|" + v.getTrajanjeVoznje() + "|"  + v.getStatus() + "|" + v.getObrisan()  +  "\n";
		        		
		        		
		        	}
		        	
		        	inputBuffer.append(line);
		            inputBuffer.append('\n');
		        	
		        }
		        file.close();
		        String inputStr = inputBuffer.toString();

		   
		        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/voznje.txt");
		        fileOut.write(inputStr.getBytes());
		        fileOut.close();

			  }catch (Exception e) {
			        System.out.println("Problem pri citanju fajla.");
			  }
		 
	 }

}
