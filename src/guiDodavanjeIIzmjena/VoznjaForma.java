package guiDodavanjeIIzmjena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import funkcionalnosti.VoznjaFunkcionalnosti;
import model.Dispeceri;
import model.Automobil;
import model.Korisnik;
import model.Musterija;
import model.Voznja;
import model.PoslovanjeSluzbe;
import model.Vozac;
import model.Status;
import net.miginfocom.swing.MigLayout;

public class VoznjaForma extends JFrame {
	
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblTermin = new JLabel("Termin");
	private JTextField txtTermin = new JTextField(20);
	private JLabel lblAdresaPolaska = new JLabel("Adresa Polaska");
	private JTextField txtAdresaPolaska = new JTextField(20);
	private JLabel lblAdresaDestinacije = new JLabel("Adresa Destinacije");
	private JTextField txtAdresaDestinacije = new JTextField(20);
	private JLabel lblMusterija = new JLabel("Musterija");
	private JTextField txtMusterija = new JTextField(20);
	private JLabel lblVozac = new JLabel("Vozac");
	private JTextField txtVozac = new JTextField(20);
	private JLabel lblBrPredjenihKm = new JLabel("Broj Predjenih Km");
	private JTextField txtBrPredjenihKm = new JTextField(20);
	private JLabel lblTrajanjeVoznje = new JLabel("Trajanje Voznje");
	private JTextField txtTrajanjeVoznje = new JTextField(20);
	private JLabel lblStatus = new JLabel("Status");
	private JComboBox<Status> cbStatus = new JComboBox<Status>(Status.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Voznja voznja;
	private PoslovanjeSluzbe ps;
	private VoznjaFunkcionalnosti vf;
	private boolean adminLogovan, vozacLogovan, musterijaLogovana = false;
	private int musterijaId;
	
	public VoznjaForma(PoslovanjeSluzbe ps, Voznja voznja, Korisnik prijavljeniKorisnik) {
		this.ps = ps;
		this.voznja = voznja;
		this.vf = new VoznjaFunkcionalnosti();
		this.vf.sluzba = ps;
		if(voznja == null) {
			setTitle("Dodavanje voznje");
		}else {
			setTitle("Izmjena podataka - " + voznja.getId());
		}
		provjeraLogovanogKorisnika(prijavljeniKorisnik);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void provjeraLogovanogKorisnika(Korisnik korisnik) {
		Dispeceri prijavljeniDispecer = ps.loginDispecera(korisnik.getKorisnickoIme(), korisnik.getLozinka());
		if(prijavljeniDispecer!=null) {
			adminLogovan = true;
		}
		
		Vozac prijavljeniVozac = ps.loginVozac(korisnik.getKorisnickoIme(), korisnik.getLozinka());
		if(prijavljeniVozac!=null) {
			vozacLogovan = true;
		}
		
		Musterija prijavljeniMusterija = ps.loginMusterije(korisnik.getKorisnickoIme(), korisnik.getLozinka());
		if(prijavljeniMusterija!=null) {
			musterijaLogovana = true;
			musterijaId = prijavljeniMusterija.getId();
		}
	}

	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][][][][]20[]");
		setLayout(layout);
		
		if(voznja != null) {		
			txtID.setEnabled(false);
			
			if(vozacLogovan) {
				if(voznja.getVozac() != null) {
					txtVozac.setEnabled(false);
					txtMusterija.setEnabled(false);
					txtAdresaPolaska.setEnabled(false);
					txtAdresaDestinacije.setEnabled(false);
					cbStatus.setEnabled(false);
					
					
				}
			}
			
			popuniPolja();
		}else {
			cbStatus.setSelectedItem(Status.Dodijeljena);
			cbStatus.setEnabled(false);
		}
		
		if(musterijaLogovana) {
			txtTermin.setEnabled(false);
			txtBrPredjenihKm.setEnabled(false);
			txtTrajanjeVoznje.setEnabled(false);
			txtVozac.setEnabled(false);
			cbStatus.setEnabled(false);
			
		}
		
		add(lblID);
		add(txtID);
		add(lblTermin);
		add(txtTermin);
		add(lblAdresaPolaska);
		add(txtAdresaPolaska);
		add(lblAdresaDestinacije);
		add(txtAdresaDestinacije);
		add(lblMusterija);
		add(txtMusterija);
		add(lblVozac);
		add(txtVozac);
		add(lblBrPredjenihKm);
		add(txtBrPredjenihKm);
		add(lblTrajanjeVoznje);
		add(txtTrajanjeVoznje);
		add(lblStatus);
		add(cbStatus);
	
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
					String termin = "";
					Date date1 = new Date();
					String adresaPolaska = txtAdresaPolaska.getText().trim();
					String adresaDestinacije = txtAdresaDestinacije.getText().trim();
					String musterijaID = "";
					String vozacID = "";
					int brPredjenihKm = 0 ;
					int trajanjeVoznje = 0;
					String status = "Dodijeljena";
					Musterija musterija = new Musterija();
					Vozac vozac = new Vozac();
					double cijena = 0;
					
					if(!musterijaLogovana) {
						vozacID = txtVozac.getText().trim();
						termin = txtTermin.getText().trim();
						
						try {
							date1 = new SimpleDateFormat("dd/mm/yyyy").parse(termin);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						
						
						status = cbStatus.getSelectedItem().toString();
						vozac = ps.nadjiVozaca(Integer.parseInt(vozacID));	
						brPredjenihKm = Integer.parseInt(txtBrPredjenihKm.getText().trim());
						trajanjeVoznje = Integer.parseInt(txtTrajanjeVoznje.getText().trim());
					}
					
					
					if(voznja == null) { 
						Voznja novi = new Voznja(id, date1, adresaPolaska, adresaDestinacije, musterija, vozac, brPredjenihKm, trajanjeVoznje, Status.valueOf(status), false);
						vf.dodajVoznju(novi);
					}else {
						voznja.setTermin((date1));
						voznja.setAdresaPolaska(adresaPolaska);
						voznja.setAdresaDestinacije(adresaDestinacije);
						voznja.setMusterija(musterija);
						voznja.setVozac(vozac);
						voznja.setBrPredjenihKm(brPredjenihKm);
						voznja.setTrajanjeVoznje(trajanjeVoznje);
						voznja.setStatus(Status.valueOf(status));
						
						
					}
					ps.snimiVoznje();
					VoznjaForma.this.dispose();
					VoznjaForma.this.setVisible(false);
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VoznjaForma.this.dispose();
				VoznjaForma.this.setVisible(false);
			}
		});
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(voznja.getId()));
		txtTermin.setText(String.valueOf(new SimpleDateFormat("dd/MM/yyyy").format(voznja.getTermin())));
		txtAdresaPolaska.setText(voznja.getAdresaPolaska());
		txtAdresaDestinacije.setText(voznja.getAdresaDestinacije());
		if(voznja.getMusterija()!=null)
			txtMusterija.setText(String.valueOf(voznja.getMusterija().getId()));
		if(voznja.getVozac()!=null)
			txtVozac.setText(String.valueOf(voznja.getVozac().getId()));
		txtBrPredjenihKm.setText(String.valueOf(voznja.getBrPredjenihKm()));
		txtTrajanjeVoznje.setText(String.valueOf(voznja.getTrajanjeVoznje()));
		cbStatus.setSelectedItem(Status.valueOf(String.valueOf(voznja.getStatus())));	
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate uneti ID\n";
			ok = false;
		}else if(voznja == null) {
			String id = txtID.getText().trim();
			Voznja pronadjen = ps.nadjiVoznje(Integer.parseInt(id));
			if(pronadjen != null) {
				poruka += "- Voznja sa unetim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(!musterijaLogovana) {
			try {
				Integer.parseInt(txtBrPredjenihKm.getText().trim());
			}catch (NumberFormatException e) {
				poruka += "- Broj predjenih km mora biti broj\n";
				ok = false;
			}
			
			try {
				Integer.parseInt(txtTrajanjeVoznje.getText().trim());
			}catch (NumberFormatException e) {
				poruka += "- Trajanje voznje mora biti broj\n";
				ok = false;
			}
			
			try {
				Integer.parseInt(txtVozac.getText().trim());
				Vozac pronadjenVozac = ps.nadjiVozaca(Integer.parseInt(txtVozac.getText()));
				if(pronadjenVozac==null) {
					poruka += "- Ne postoji vozac sa datim ID-ijem\n";
					ok = false;
				}
			}catch (NumberFormatException e) {
				poruka += "- Id vozac mora biti broj\n";
				ok = false;
			}
			
			if(txtTermin.getText().trim().equals("")) {
				poruka += "- Morate uneti termin\n";
				ok = false;
			}
			
			if(voznja==null) {
				try {
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date unijetDatum = sdf.parse(txtTermin.getText());
					Date  trenutniDatum = sdf.parse(sdf.format(new Date()));
					Date sad = new Date();
					
					if(unijetDatum.before(trenutniDatum)) {
						poruka += "- Termin mora biti u buducnosti\n";
						ok = false;
					}
				}catch(ParseException e) {
					poruka += "- Termin mora biti u formatu dd/MM/yyyy\n";
					ok = false;
				}
			}
			
			
			
			if(cbStatus.getSelectedItem().equals("")) {
				poruka += "- Morate uneti status\n";
				ok = false;
			}
			
		}
		
		if(txtAdresaPolaska.getText().trim().equals("")) {
			poruka += "- Morate uneti adresu polaska\n";
			ok = false;
		}else if(provjeraBroja(txtAdresaPolaska.getText())) {
			poruka += "- Adresa polaska ne moze biti broj\n";
			ok = false;
		}
		
		if(txtAdresaDestinacije.getText().trim().equals("")) {
			poruka += "- Morate uneti adresu destinacije\n";
			ok = false;
		}else if(provjeraBroja(txtAdresaDestinacije.getText())) {
			poruka += "- Adresa destinacije ne moze biti broj\n";
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
