package guiDodavanjeIIzmjena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import funkcionalnosti.VozacFunkcionalnosti;
import model.Automobil;
import model.Pol;
import model.PoslovanjeSluzbe;
import model.Vozac;

import net.miginfocom.swing.MigLayout;

public class VozaciForma extends JFrame {
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblIme = new  JLabel("Ime");
	private JTextField txtIme = new JTextField(20);
	private JLabel lblPrezime = new JLabel("Prezime");
	private JTextField txtPrezime = new JTextField(20);
	private JLabel lblJmbg = new JLabel("JMBG");
	private JTextField txtJmbg = new JTextField(20);
	private JLabel lblPol = new JLabel("Pol");
	private JComboBox<Pol> cbPol= new JComboBox<Pol>(Pol.values());
	private JLabel lblAdresa = new JLabel("Adresa");
	private JTextField txtAdresa = new JTextField(20);
	private JLabel lblBrTelefona = new JLabel("Broj Telefona");
	private JTextField txtBrTelefona = new JTextField(20);
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko Ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblLozinka = new JLabel("Lozinka");
	private JPasswordField txtLozinka = new JPasswordField(20);
	private JLabel lblPlata = new JLabel("Plata");
	private JTextField txtPlata = new JTextField(20);
	private JLabel lblBrClanskeKarte = new JLabel("Broj Clanske Karte");
	private JTextField txtBrClanskeKarte = new JTextField(20);
	private JLabel lblAutomobil = new  JLabel("Automobil");
	private JTextField txtAutomobil = new JTextField(20);
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Vozac vozac;
	private PoslovanjeSluzbe ps;
	private VozacFunkcionalnosti vf;
	
	
	public VozaciForma(PoslovanjeSluzbe ps, Vozac vozac) {
		this.ps = ps;
		this.vozac = vozac;
		this.vf = new VozacFunkcionalnosti();
		this.vf.sluzba = ps;
		if(vozac == null) {
			setTitle("Dodavanje vozaca");
		}else {
			setTitle("Izmjena podataka - " + vozac.getId());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][][][][][]20[]");
		setLayout(layout);
		
		if(vozac != null) {
			
			txtID.setEnabled(false);
			popuniPolja();
		}
	
		add(lblID);
		add(txtID);
		add(lblIme);
		add(txtIme);
		add(lblPrezime);
		add(txtPrezime);
		add(lblJmbg);
		add(txtJmbg);
		add(lblPol);
		add(cbPol);
		add(lblAdresa);
		add(txtAdresa);
		add(lblBrTelefona);
		add(txtBrTelefona);
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblLozinka);
		add(txtLozinka);
		add(lblPlata);
		add(txtPlata);
		add(lblBrClanskeKarte);
		add(txtBrClanskeKarte);
		add(lblAutomobil);
		add(txtAutomobil);
		
		add(new JLabel());
		add(btnOK, "split 2");
		add(btnCancel);
	
	}
	
	private void initActions() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija()) {
					int id =Integer.parseInt(txtID.getText().trim());
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					Long jmbg = Long.parseLong(txtJmbg.getText().trim());
					Pol pol = (Pol)cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					long brTelefona = Long.parseLong(txtBrTelefona.getText().trim());
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = txtLozinka.getText().trim();
					int plata = Integer.parseInt(txtPlata.getText().trim());
					String brClanskeKarte = txtBrClanskeKarte.getText().trim();
					String automobilID = txtAutomobil.getText().trim();
					Automobil automobil = new Automobil();
					
					
					if(vozac == null) { 
						Vozac novi = new Vozac(id, ime, prezime, jmbg, pol, adresa, brTelefona, korisnickoIme, lozinka, false, plata, brClanskeKarte,automobil);
						vf.dodajVozaca(novi);
					}else {
						vozac.setIme(ime);
						vozac.setPrezime(prezime);
						vozac.setJmbg(jmbg);
						vozac.setPol(pol);
						vozac.setAdresa(adresa);
						vozac.setBrTelefona(brTelefona);
						vozac.setKorisnickoIme(korisnickoIme);
						vozac.setLozinka(lozinka);
						vozac.setPlata(plata);
						vozac.setBrClanskeKarte(brClanskeKarte);
						vozac.setAutomobil(automobil);
						
						
					}
					ps.snimiVozace();
					VozaciForma.this.dispose();
					VozaciForma.this.setVisible(false);
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VozaciForma.this.dispose();
				VozaciForma.this.setVisible(false);
			}
		});
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(vozac.getId()));
		txtIme.setText(vozac.getIme());
		txtPrezime.setText(vozac.getPrezime());
		txtJmbg.setText(String.valueOf(vozac.getJmbg()));
		cbPol.setSelectedItem(vozac.getPol());
		txtAdresa.setText(vozac.getAdresa());
		txtBrTelefona.setText(String.valueOf(vozac.getBrTelefona()));
		txtKorisnickoIme.setText(vozac.getKorisnickoIme());
		txtLozinka.setText(vozac.getLozinka());
		txtPlata.setText(String.valueOf(vozac.getPlata()));
		if(vozac.getAutomobil()!=null)
			txtAutomobil.setText(String.valueOf(vozac.getAutomobil().getId()));
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate unijeti ID\n";
			ok = false;
		}else if(vozac == null) {
			String id = txtID.getText().trim();
			Vozac pronadjen = ps.nadjiVozaca(Integer.parseInt(id));
			if(pronadjen != null) {
				poruka += "- Vozac sa unijetim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(txtIme.getText().trim().equals("")) {
			poruka += "- Morate unijeti ime\n";
			ok = false;
		}else if(provjeraBroja(txtIme.getText())) {
			poruka += "- Ime ne moze biti broj\n";
			ok = false;
		}
		
		if(txtPrezime.getText().trim().equals("")) {
			poruka += "- Morate unijeti prezime\n";
			ok = false;
		}else if(provjeraBroja(txtPrezime.getText())) {
			poruka += "- Prezime ne moze biti broj\n";
			ok = false;
		}
		
		if(txtJmbg.getText().length() < 13) {
			poruka += "- JMBG mora da sadrzi 13 cifara\n";
			ok = false;
		}
		
		try {
			long jmbg = Long.parseLong(txtJmbg.getText().trim());
			if( jmbg < 0) {
				poruka += "- JMBG ne moze biti negativan broj\n";
				ok = false;	
			}
		}catch (NumberFormatException e) {
			poruka += "- JMBG mora biti broj\n";
			ok = false;
		}
		
		if(cbPol.getSelectedItem().equals("")) {
			poruka += "- Morate unijeti pol";
			ok = false;
		}
		
		if(txtAdresa.getText().trim().equals("")) {
			poruka += "- Morate unijeti adresu";
			ok = false;
		}else if(provjeraBroja(txtAdresa.getText())) {
			poruka += "- Adresa ne moze biti broj\n";
			ok = false;
		}
		
		try {
			long broj = Long.parseLong(txtBrTelefona.getText().trim());
			if(broj < 0) {
				poruka += "- Broj telefona ne moze biti negativan broj\n";
				ok = false;	
			}
		}catch (NumberFormatException e) {
			poruka += "- Broj telefona mora biti broj\n";
			ok = false;
		}
		
		if(txtKorisnickoIme.getText().trim().equals("")) {
			poruka += "- Morate unijeti korisnicko ime";
			ok = false;
		}else if(provjeraBroja(txtKorisnickoIme.getText())) {
			poruka += "- Korisnicko ime ne moze biti broj\n";
			ok = false;
		}
		
		if(txtLozinka.getText().trim().equals("")) {
			poruka += "- Morate unijeti lozinku";
			ok = false;
		}
		try {
			int plata = Integer.parseInt(txtPlata.getText().trim());
			if(plata < 0) {
				poruka += "- Plata ne moze biti negativan broj\n";
				ok = false;	
			}
		}catch (NumberFormatException e) {
			poruka += "- Plata mora biti broj\n";
			ok = false;
		}
		if(txtBrClanskeKarte.getText().trim().equals("")) {
			poruka += "- Morate unijeti broj clanske karte";
			ok = false;
		}
		
		
		if(ok == false) {
			JOptionPane.showMessageDialog(null, poruka, "Neispravni podaci", JOptionPane.WARNING_MESSAGE);
		}
		return ok;
	}
	
	public boolean provjeraBroja(String str) { 
		try {  
		    Integer.parseInt(str);  
		    return true;
		 } catch(NumberFormatException e){  
		    return false;  
		 }  
	}

}
