package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import guiPrikaz.DispeceriProzor;
import guiPrikaz.AutomobiliProzor;
import guiPrikaz.MusterijeProzor;
import guiPrikaz.VozaciProzor;
import guiPrikaz.VoznjeProzor;
import model.Korisnik;
import model.PoslovanjeSluzbe;

public class GlavniProzor extends JFrame {
	
	private JMenuBar mainMenu = new JMenuBar();
	private JMenu zaposleniMenu = new JMenu("Zaposleni");
	private JMenuItem dispeceriItem = new JMenuItem("Dispeceri");
	private JMenuItem vozaciItem = new JMenuItem("Vozaci");
	private JMenu sluzbaMenu = new JMenu("Sluzba");
	private JMenuItem musterijeItem = new JMenuItem("Musterije");
	private JMenuItem voznjeItem = new JMenuItem("Voznje");
	private JMenuItem automobiliItem = new JMenuItem("Automobili");
	
	
	private PoslovanjeSluzbe ps;
	private Korisnik prijavljeniKorisnik;
	
	public GlavniProzor(PoslovanjeSluzbe ps, Korisnik prijavljeniKorisnik) {
		this.ps = ps;
		this.prijavljeniKorisnik = prijavljeniKorisnik;
		setTitle("Admin: " + prijavljeniKorisnik.getKorisnickoIme());
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		initActions();
	}
	
	private void initMenu() {
		setJMenuBar(mainMenu);
		mainMenu.add(zaposleniMenu);
		zaposleniMenu.add(dispeceriItem);
		zaposleniMenu.add(vozaciItem);
		mainMenu.add(sluzbaMenu);
		sluzbaMenu.add(musterijeItem);
		sluzbaMenu.add(voznjeItem);
		sluzbaMenu.add(automobiliItem);
		
	}
	
	private void initActions() {
		
		dispeceriItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				DispeceriProzor dp = new DispeceriProzor(ps);
				dp.setVisible(true);
				
				
				
			}
		});
		
		vozaciItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VozaciProzor vp = new VozaciProzor(ps);
				vp.setVisible(true);
				
				
				
			}
		});
		
		musterijeItem.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				
				MusterijeProzor mp = new MusterijeProzor(ps);
				mp.setVisible(true);
			
			
			
			}
		});
		
		automobiliItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				AutomobiliProzor ap = new AutomobiliProzor(ps);
				ap.setVisible(true);
				
				
				
			}
		});
		
		voznjeItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				VoznjeProzor vp = new VoznjeProzor(ps, prijavljeniKorisnik);
				vp.setVisible(true);
					
				
				
			}
		});
		
		
	}

}
