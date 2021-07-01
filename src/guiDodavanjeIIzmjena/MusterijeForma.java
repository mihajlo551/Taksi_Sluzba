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

import funkcionalnosti.MusterijaFunkcionalnosti;
import model.Musterija;
import model.Pol;
import model.PoslovanjeSluzbe;
import net.miginfocom.swing.MigLayout;

public class MusterijeForma extends JFrame{
	
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
	
	
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Musterija musterija;
	private PoslovanjeSluzbe ps;
	private MusterijaFunkcionalnosti mf;
	
	public MusterijeForma(PoslovanjeSluzbe ps, Musterija musterija) {
		this.ps = ps;
		this.musterija = musterija;
		this.mf = new MusterijaFunkcionalnosti();
		this.mf.sluzba = ps;
		if(musterija == null) {
			setTitle("Dodavanje musterije");
		}else {
			setTitle("Izmjena podataka - " + musterija.getId());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][][]20[]");
		setLayout(layout);
		
		if(musterija != null) {
			
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
					long jmbg = Long.parseLong(txtJmbg.getText().trim());
					Pol pol = (Pol)cbPol.getSelectedItem();
					String adresa = txtAdresa.getText().trim();
					long brojTelefona = Long.parseLong(txtBrTelefona.getText().trim());
					String korisnickoIme = txtKorisnickoIme.getText().trim();
					String lozinka = txtLozinka.getText().trim();
					
					if(musterija == null) { 
						Musterija novi = new Musterija(id, ime, prezime, jmbg, pol, adresa, brojTelefona, korisnickoIme, lozinka, false);
						mf.dodajMusterije(novi);
					}else {
						musterija.setIme(ime);
						musterija.setPrezime(prezime);
						musterija.setJmbg(jmbg);
						musterija.setPol(pol);
						musterija.setAdresa(adresa);
						musterija.setBrTelefona(brojTelefona);
						musterija.setKorisnickoIme(korisnickoIme);
						musterija.setLozinka(lozinka);
						
						
					}
					ps.snimiMusterije();
					MusterijeForma.this.dispose();
					MusterijeForma.this.setVisible(false);
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijeForma.this.dispose();
				MusterijeForma.this.setVisible(false);
			}
		});
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(musterija.getId()));
		txtIme.setText(musterija.getIme());
		txtPrezime.setText(musterija.getPrezime());
		txtJmbg.setText(String.valueOf(musterija.getJmbg()));
		cbPol.setSelectedItem(musterija.getPol());
		txtAdresa.setText(musterija.getAdresa());
		txtBrTelefona.setText(String.valueOf(musterija.getBrTelefona()));
		txtKorisnickoIme.setText(musterija.getKorisnickoIme());
		txtLozinka.setText(musterija.getLozinka());
		
		
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate unijeti ID\n";
			ok = false;
		}else if(musterija == null) {
			String id = txtID.getText().trim();
			Musterija pronadjen = ps.nadjiMusteriju(Integer.parseInt(id));
			if(pronadjen != null) {
				poruka += "- Musterija sa unijetim ID vec postoji\n";
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
