package model;



import java.util.Date;

import funkcionalnosti.DispeceriFunkcionalnosti;
import gui.LoginProzor;

public class Test {
	
public static void main(String[] args) {
		
		PoslovanjeSluzbe poslovanjeSluzbe = new PoslovanjeSluzbe();
		
		//Dispeceri admin = new Dispeceri(0,"Nenad","Marinkovic",1842773,Pol.Muski,"Nikole Tesle 12",
		//									066432563,"nenad123","neso123",false,60000,0215321,Odjeljenje.Reklamacije);
		//poslovanjeSluzbe.getDispeceri().add(admin);
		//poslovanjeSluzbe.snimiDispecere();
		
		//Dispeceri admin2 = new Dispeceri(1,"Nenad","Marinkovic",1842773,Pol.Muski,"Nikole Tesle 12",
		//		066432563,"nenad123","neso123",false,60000,0215321,Odjeljenje.Reklamacije);
		
		//poslovanjeSluzbe.getDispeceri().add(admin2);
		//poslovanjeSluzbe.snimiDispecere();
		
		
		
		
		
		//Musterija musterija = new Musterija(1,"Nikola","Nikolic",145667443,Pol.Muski,"Antona Cehova 4",
		//									064325675,"nikola123","nix123",false);
		
		//poslovanjeSluzbe.getMusterije().add(musterija);
		//poslovanjeSluzbe.snimiMusterije();
		
		
		
		
		
		//Automobil auto = new Automobil(1,"307","Peugeot",2020,"630-M-520",VrstaAutomobila.Kombi,false) ;
		
		//poslovanjeSluzbe.getAutomobili().add(auto);
		//poslovanjeSluzbe.snimiAutomobile();	
		
		//Vozac vozac = new Vozac(1,"Djordje","Gospavic",1234678923,Pol.Muski,"Vuka Karadzica 56",
		//								1125420,"djordje123","djole123",false,45000,"65M",auto);
		
		//poslovanjeSluzbe.getVozaci().add(vozac);
		//poslovanjeSluzbe.snimiVozace();
		
		
		
		
		
		
		//Automobil auto2 = new Automobil(2,musterija,MarkaAutomobila.BMW,ModelAutomobila.Coupe,2020,3.0,300,VrstaGoriva.Benzin,false);
		
		
		//servisAutomobila.getAutomobili().add(auto2);
		//servisAutomobila.snimiAutomobile();
		
		
		
		//Voznja voznja = new Voznja(1,new Date(),"Pariske Komune","Nemjanina",musterija,vozac,500,200,Status.Dodijeljena,false);
		//poslovanjeSluzbe.getVoznje().add(voznja);
		//poslovanjeSluzbe.snimiVoznje();
		
		
		
		
		poslovanjeSluzbe.ucitajDispecere();
		poslovanjeSluzbe.ucitajMusterije();
		poslovanjeSluzbe.ucitajAutomobile();
		poslovanjeSluzbe.ucitajVozace();	
		poslovanjeSluzbe.ucitajVoznje();
		
		//System.out.println(poslovanjeSluzbe.getDispeceri() + "\n");
		//System.out.println(poslovanjeSluzbe.getMusterije() + "\n");
		//System.out.println(poslovanjeSluzbe.getVozaci() + "\n");
		//System.out.println(poslovanjeSluzbe.getAutomobili() + "\n");
		//System.out.println(poslovanjeSluzbe.getVoznje() + "\n");
		
		
		//System.out.println(poslovanjeSluzbe.getDispeceri() + "\n");
	
		//DispeceriFunkcionalnosti af = new DispeceriFunkcionalnosti();
		
		//Dispeceri d = poslovanjeSluzbe.getDispeceri().get(1);
	
		//d.setIme("DRAGAN");
		
		//af.izmjeniDispecera(d);
		
		//DispeceriFunkcionalnosti af = new DispeceriFunkcionalnosti();
		
		//af.izbrisiDispecera(d);
	
		//Dispeceri a1 = new Dispeceri(9,"Nenad","Marinkovic",1842773,Pol.Muski,"Nikole Tesle 12",
		//									066432563,"nenad123","neso123",false,60000,0215321,Odjeljenje.Reklamacije);
	
		//af.izbrisiDispecera(a1);
		
		//af.dodajDispecera(a1);
		
		
		
		LoginProzor lp = new LoginProzor(poslovanjeSluzbe);
		lp.setVisible(true);
				
	}

}
