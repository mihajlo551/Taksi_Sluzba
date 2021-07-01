package guiDodavanjeIIzmjena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import funkcionalnosti.AutomobilFunkcionalnosti;
import model.Automobil;
import model.PoslovanjeSluzbe;
import model.VrstaAutomobila;
import net.miginfocom.swing.MigLayout;

public class AutomobiliForma extends JFrame{
	
	private JLabel lblID = new JLabel("ID");
	private JTextField txtID = new JTextField(20);
	private JLabel lblModel = new  JLabel("Model");
	private JTextField txtModel = new JTextField(20);
	private JLabel lblProizvodjac= new  JLabel("Proizvodjac");
	private JTextField txtProizvodjac = new JTextField(20);
	private JLabel lblGodinaProizvodnje = new JLabel("Godina proizvodnje");
	private JTextField txtGodinaProizvodnje = new JTextField(20);
	private JLabel lblRegistracija = new JLabel("Registracija");
	private JTextField txtRegistracija = new JTextField(20);
	private JLabel lblVrstaAutomobila = new JLabel("Vrsta automobila");
	private JComboBox<VrstaAutomobila> cbVrstaAutomobila = new JComboBox<VrstaAutomobila>(VrstaAutomobila.values());
	
	private JButton btnOK = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private Automobil automobil;
	private PoslovanjeSluzbe ps;
	private AutomobilFunkcionalnosti af;
	
	
	public AutomobiliForma(PoslovanjeSluzbe ps, Automobil automobil) {
		this.ps = ps;
		this.automobil = automobil;
		this.af = new AutomobilFunkcionalnosti();
		this.af.sluzba = ps;
		if(automobil == null) {
			setTitle("Dodavanje automobila");
		}else {
			setTitle("Izmjena podataka - " + automobil.getId());
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		setResizable(false);
		pack();
	}
	
	private void initGUI() {
		MigLayout layout = new MigLayout("wrap 2", "[][]", "[][][][][][]20[]");
		setLayout(layout);
		
		if(automobil != null) {
			
			txtID.setEnabled(false);
			popuniPolja();
		}
		
		add(lblID);
		add(txtID);
		add(lblModel);
		add(txtModel);
		add(lblProizvodjac);
		add(txtProizvodjac);
		add(lblGodinaProizvodnje);
		add(txtGodinaProizvodnje);
		add(lblRegistracija);
		add(txtRegistracija);
		add(lblVrstaAutomobila);
		add(cbVrstaAutomobila);
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
					String model = txtModel.getText().trim();
					String proizvodjac = txtProizvodjac.getText().trim();
					int godinaProizvodnje = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
					String registracija = txtRegistracija.getText().trim();
					String vrstaAutomobila = cbVrstaAutomobila.getSelectedItem().toString();
				
					if(automobil == null) { 
						Automobil novi = new Automobil(id, model, proizvodjac, godinaProizvodnje, registracija, VrstaAutomobila.valueOf(vrstaAutomobila), false);
						af.dodajAutomobil(novi);
					}else {
						automobil.setModel(model);
						automobil.setModel(proizvodjac);
						automobil.setGodinaProizvodnje(godinaProizvodnje);
						automobil.setRegistracija(registracija);
						automobil.setVrstaAutomobila(VrstaAutomobila.valueOf(vrstaAutomobila));
						
						
					}
					ps.snimiAutomobile(); 
					AutomobiliForma.this.dispose();
					AutomobiliForma.this.setVisible(false);
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AutomobiliForma.this.dispose();
				AutomobiliForma.this.setVisible(false);
			}
		});
	}
	
	private void popuniPolja() {
		txtID.setText(String.valueOf(automobil.getId()));
		txtModel.setText(automobil.getModel());
		txtProizvodjac.setText(automobil.getProizvodjac());
		txtGodinaProizvodnje.setText(String.valueOf(automobil.getGodinaProizvodnje()));
		txtRegistracija.setText(automobil.getRegistracija());
		cbVrstaAutomobila.setSelectedItem(automobil.getVrstaAutomobila());
		
		
		
	}
	
	private boolean validacija() {
		boolean ok = true;
		String poruka = "Molimo popravite sledece greske u unosu:\n";
		
		if(txtID.getText().trim().equals("")) {
			poruka += "- Morate unijeti ID\n";
			ok = false;
		}else if(automobil == null) {
			String id = txtID.getText().trim();
			Automobil pronadjen = ps.nadjiAutomobil(Integer.parseInt(id));
			if(pronadjen != null) {
				poruka += "- Automobil sa unijetim ID vec postoji\n";
				ok = false;
			}
		}
		
		if(txtModel.getText().trim().equals("")) {
			poruka += "- Morate unijeti model\n";
			ok = false;
		}else if(provjeraBroja(txtModel.getText())) {
			poruka += "- Model ne moze biti broj\n";
			ok = false;
		}
		
		if(txtProizvodjac.getText().trim().equals("")) {
			poruka += "- Morate unijeti proizvodjaca\n";
			ok = false;
		}else if(provjeraBroja(txtProizvodjac.getText())) {
			poruka += "- Proizvodjac ne moze biti broj\n";
			ok = false;
		}
		

		try {
			int godina = Integer.parseInt(txtGodinaProizvodnje.getText().trim());
			if(godina < 0) {
				poruka += "- Godina proizvodnje ne moze biti negativan broj\n";
				ok = false;
			}else if(godina > 2020) {
				poruka += "- Godina proizvodnje ne moze biti veca 2020\n";
				ok = false;
			}
		}catch (NumberFormatException e) {
			poruka += "- Godina proizvodnje mora biti broj\n";
			ok = false;
		}
		
		if(txtRegistracija.getText().trim().equals("")) {
			poruka += "- Morate unijeti registraciju\n";
			ok = false;
		}else if(provjeraBroja(txtRegistracija.getText())) {
			poruka += "- Registracija ne moze biti broj\n";
			ok = false;
		}
		
		
		
		
		if(cbVrstaAutomobila.getSelectedItem().equals("")) {
			poruka += "- Morate unijeti vrstu automobila\n";
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
