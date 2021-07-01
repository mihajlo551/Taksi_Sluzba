package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;


import guiDodavanjeIIzmjena.VoznjaForma;
import model.Korisnik;
import model.PoslovanjeSluzbe;


public class MusterijaGlavniProzor extends JFrame {
	

	private PoslovanjeSluzbe ps;
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JLabel label = new JLabel();
	private Korisnik prijavljeniKorisnik;
	
	
	public MusterijaGlavniProzor(PoslovanjeSluzbe ps, Korisnik prijavljeniKorisnik) {
		this.ps = ps;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		setTitle("Musterija: " + prijavljeniKorisnik.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGui();
		initActions(prijavljeniKorisnik);
	}
	
	

	private void initGui() {
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		label.setText("Zakazi voznju: ");
		mainToolbar.add(label);
		mainToolbar.add(btnAdd);
		add(mainToolbar, BorderLayout.NORTH);
		
	}
	
	private void initActions(final Korisnik korisnik) {
		btnAdd.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				VoznjaForma vf = new VoznjaForma(ps, null, korisnik);
				vf.setVisible(true);
			}
		});
		
		
	}
}
