package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


import guiPrikaz.VoznjeProzor;
import model.Korisnik;
import model.PoslovanjeSluzbe;


public class VozacGlavniProzor extends JFrame{
	
	

	
	private JMenuBar mainMenu = new JMenuBar();

	private JMenu voznjeMenu = new JMenu("Voznja");
	private JMenuItem voznjeItem = new JMenuItem("Voznje");

	
	private PoslovanjeSluzbe ps;
	private Korisnik prijavljeniKorisnik;
	
	public VozacGlavniProzor(PoslovanjeSluzbe ps, Korisnik prijavljeniKorisnik) {
		this.ps = ps;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		setTitle("Vozac: " + prijavljeniKorisnik.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(voznjeMenu);
		voznjeMenu.add(voznjeItem);

	}
	
	private void initActions() {
					
		voznjeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VoznjeProzor vp = new VoznjeProzor(ps, prijavljeniKorisnik);
				vp.setVisible(true);
						

			}
		});
	}
	
	

}
