package funkcionalnosti;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import model.Automobil;
import model.PoslovanjeSluzbe;

public class AutomobilFunkcionalnosti {

public PoslovanjeSluzbe sluzba = new PoslovanjeSluzbe();
	
	public AutomobilFunkcionalnosti() {
		super();
		
		sluzba.ucitajAutomobile();
	}

	
	public void dodajAutomobil(Automobil a) {
		
		
		sluzba.getAutomobili().add(a);
		
		
		String automobilStr = a.getId() + "|" + a.getModel() + "|" +  a.getProizvodjac() + "|" + a.getGodinaProizvodnje() + "|" + a.getRegistracija() + "|" + 
				a.getVrstaAutomobila()+ "|"  +  a.getObrisan() ;
		
		
		try {
			
			Files.write(Paths.get("src/fajlovi/automobili.txt"), automobilStr.getBytes(), StandardOpenOption.APPEND);
			
		}catch(IOException e){
			System.out.println("Greska prilikom dodavanja u datoteku: " + e.getMessage());
		}
		
	}
	
	
	
	
	public void izmjeniAutomobil(Automobil a) {
		
		Automobil automobilIzListe = sluzba.getAutomobili().get(a.getId());
		
		automobilIzListe.setModel(a.getModel());
		automobilIzListe.setProizvodjac(a.getProizvodjac());
		automobilIzListe.setGodinaProizvodnje(a.getGodinaProizvodnje());
		automobilIzListe.setRegistracija(a.getRegistracija());
		automobilIzListe.setVrstaAutomobila(a.getVrstaAutomobila());
		automobilIzListe.setObrisan(a.getObrisan());
		
		
		
		 try {

	        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/automobili.txt"));
	        StringBuffer inputBuffer = new StringBuffer();
	        String line;


	        while ((line = file.readLine()) != null) {
	            
	        	if (line.startsWith(Integer.toString(a.getId()))) {
	        		
	        		line =  a.getId() + "|" + a.getModel() + "|" +  a.getProizvodjac() + "|" + a.getGodinaProizvodnje() + "|" + a.getRegistracija() + "|" + 
	        				a.getVrstaAutomobila()+ "|"  +  a.getObrisan() ;
	        		
	        		
	        	}
	        	
	        	inputBuffer.append(line);
	            inputBuffer.append('\n');
	        	
	        }
	        file.close();
	        String inputStr = inputBuffer.toString();

	   
	        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/automobili.txt");
	        fileOut.write(inputStr.getBytes());
	        fileOut.close();

		  }catch (Exception e) {
		        System.out.println("Problem pri citanju fajla.");
		  }
		
	}
	
	 public void izbrisiAutomobil(Automobil a) {
		 
			Automobil automobilIzListe = sluzba.getAutomobili().get(a.getId());
			
			automobilIzListe.setObrisan(true);
			a.setObrisan(true);
			
			
			
			 try {

		        BufferedReader file = new BufferedReader(new FileReader("src/fajlovi/automobili.txt"));
		        StringBuffer inputBuffer = new StringBuffer();
		        String line;


		        while ((line = file.readLine()) != null) {
		            
		        	if (line.startsWith(Integer.toString(a.getId()))) {
		        		
		        		line =  a.getId() + "|" + a.getModel() + "|" +  a.getProizvodjac() + "|" + a.getGodinaProizvodnje() + "|" + a.getRegistracija() + "|" + 
		        				a.getVrstaAutomobila()+ "|"  +  a.getObrisan() ;
		        		
		        		
		        	}
		        	
		        	inputBuffer.append(line);
		            inputBuffer.append('\n');
		        	
		        }
		        file.close();
		        String inputStr = inputBuffer.toString();

		   
		        FileOutputStream fileOut = new FileOutputStream("src/fajlovi/automobili.txt");
		        fileOut.write(inputStr.getBytes());
		        fileOut.close();

			  }catch (Exception e) {
			        System.out.println("Problem pri citanju fajla.");
			  }
		 
		 
		 
		 
		 
		 
		 
	 }
	 
	
}
