package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import model.Dispeceri;
import model.Musterija;
import model.PoslovanjeSluzbe;

import model.Vozac;

public class LoginProzor extends JFrame {
	

	private JLabel lblPoruka = new JLabel("Unesite korisnicko ime i lozinku.");
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(20);
	private JLabel lblSifra = new JLabel("Sifra");
	private JPasswordField pfSifra = new JPasswordField(20);
	private JButton btnOk = new JButton("OK");
	private JButton btnCancel = new JButton("Cancel");
	
	private PoslovanjeSluzbe ps = null;
	
	
	public LoginProzor(PoslovanjeSluzbe ps) {
		this.ps = ps;
		setTitle("Prijava");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		pack();
	}
	
	public void initGUI() {
		
		MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
		setLayout(mig);
		
		add(lblPoruka, "span 2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(pfSifra);
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
		
		getRootPane().setDefaultButton(btnOk);
	}
	
	public void initActions() {
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.dispose();
				LoginProzor.this.setVisible(false);
			}
		});
		
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String korisnikoIme = txtKorisnickoIme.getText().trim();
				String sifra = new String(pfSifra.getPassword()).trim();
				
				if(korisnikoIme.equals("") || sifra.equals("")) {
					JOptionPane.showMessageDialog(null, "Niste unijeli sve podatke za prijavu.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					Dispeceri prijavljeniDispecer = ps.loginDispecera(korisnikoIme, sifra);
					/*if(prijavljeniDispecer == null) {
						JOptionPane.showMessageDialog(null, "Pogrešni login podaci.", "Greška", JOptionPane.WARNING_MESSAGE);
					}else {
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false);
						GlavniProzor gp = new GlavniProzor(ps, prijavljeniDispecer);
						gp.setVisible(true);
					}*/
					if(prijavljeniDispecer != null) {
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false);
						GlavniProzor gp = new GlavniProzor(ps, prijavljeniDispecer);
						gp.setVisible(true);
					
					}
					
					Musterija prijavljenaMusterija = ps.loginMusterije(korisnikoIme, sifra);
					if(prijavljenaMusterija != null) {
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false);
						MusterijaGlavniProzor mgp = new MusterijaGlavniProzor(ps, prijavljenaMusterija);
						mgp.setVisible(true);
					
					}
					Vozac prijavljeniVozac = ps.loginVozac(korisnikoIme, sifra);
					if(prijavljeniVozac != null) {
						LoginProzor.this.dispose();
						LoginProzor.this.setVisible(false);
						VozacGlavniProzor vgp = new VozacGlavniProzor(ps, prijavljeniVozac);
						vgp.setVisible(true);
					
					}
					
					if(prijavljeniDispecer == null && prijavljeniVozac == null && prijavljenaMusterija == null ) {
						JOptionPane.showMessageDialog(null, "Pogrešni login podaci.", "Greška", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
	}
	
	
	
	
	
	

}
