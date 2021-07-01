package guiPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;


import guiDodavanjeIIzmjena.VoznjaForma;
import model.Dispeceri;
import model.Korisnik;
import model.Musterija;
import model.Voznja;
import model.PoslovanjeSluzbe;
import model.Vozac;
import model.Status;

public class VoznjeProzor extends JFrame{
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable voznjeTabela;
	
	private PoslovanjeSluzbe ps;
	private boolean adminLogovan, vozacLogovan, musterijaLogovana = false;
	
	public VoznjeProzor(PoslovanjeSluzbe ps,Korisnik prijavljeniKorisnik) {
		this.ps = ps;
		setTitle("Voznje");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		provjeraLogovanogKorisnika(prijavljeniKorisnik);
		initGUI(prijavljeniKorisnik);
		initActions(prijavljeniKorisnik);
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
		}
	}
	
	private void initGUI(Korisnik korisnik) {
		
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		
		if(!vozacLogovan) {
			mainToolbar.add(btnAdd);
			mainToolbar.add(btnEdit);
			mainToolbar.add(btnDelete);
		}else if(vozacLogovan) {
			mainToolbar.add(btnAdd);
			mainToolbar.add(btnEdit);
		}

		add(mainToolbar, BorderLayout.NORTH);
		String[] zaglavlja = null;
		Object[][] sadrzaj = null;
		
		if(adminLogovan) {
		
			zaglavlja = new String[] {"ID", "Termin", "Adresa Polaska", "Adresa Destinacije", "Musterija","Vozac","Broj Predjenih Km", "Trajanje Voznje","Status","Obrisan"};
			sadrzaj = new Object[ps.sveNeobrisaneVoznje().size()][zaglavlja.length];
			
	
			for(int i=0; i<ps.sveNeobrisaneVoznje().size(); i++) {
				Voznja voznja = ps.sveNeobrisaneVoznje().get(i);
				
				sadrzaj[i][0] = voznja.getId();
				sadrzaj[i][1] = new SimpleDateFormat("dd/MM/yyyy").format(voznja.getTermin());
				sadrzaj[i][2] = voznja.getAdresaPolaska();
				sadrzaj[i][3] = voznja.getAdresaDestinacije();
				if(voznja.getMusterija()!=null)
					sadrzaj[i][4] = voznja.getMusterija().getId();
				else
					sadrzaj[i][4] = "null";
				
				if(voznja.getVozac()!=null)
					sadrzaj[i][5] = voznja.getVozac().getId();
				else
					sadrzaj[i][5] = "null";
				
				//sadrzaj[i][3] = servis.getTermin();
				sadrzaj[i][6] = voznja.getBrPredjenihKm();
				sadrzaj[i][7] = voznja.getTrajanjeVoznje();
				sadrzaj[i][8] = voznja.getStatus();
				sadrzaj[i][9] = voznja.getObrisan();
				
			}
		}else if(vozacLogovan) {
			zaglavlja = new String[] {"ID", "Termin", "Adresa Polaska", "Adresa Destinacije", "Musterija","Vozac","Broj Predjenih Km", "Trajanje Voznje","Status","Obrisan"};
			sadrzaj = new Object[ps.sveNeobrisaneVoznjeVozaca(korisnik.getId()).size()][zaglavlja.length];
			
	
			for(int i=0; i<ps.sveNeobrisaneVoznjeVozaca(korisnik.getId()).size(); i++) {
				Voznja voznja = ps.sveNeobrisaneVoznjeVozaca(korisnik.getId()).get(i);
				
				
				
				sadrzaj[i][0] = voznja.getId();
				sadrzaj[i][1] = new SimpleDateFormat("dd/MM/yyyy").format(voznja.getTermin());
				sadrzaj[i][2] = voznja.getAdresaPolaska();
				sadrzaj[i][3] = voznja.getAdresaDestinacije();
				if(voznja.getMusterija()!=null)
					sadrzaj[i][4] = voznja.getMusterija().getId();
				else
					sadrzaj[i][4] = -1;
				
				if(voznja.getVozac()!=null)
					sadrzaj[i][5] = voznja.getVozac().getId();
				else
					sadrzaj[i][5] = -1;
				//sadrzaj[i][3] = servis.getTermin();
				sadrzaj[i][6] = voznja.getBrPredjenihKm();
				sadrzaj[i][7] = voznja.getTrajanjeVoznje();
				sadrzaj[i][8] = voznja.getStatus();
				sadrzaj[i][9] = voznja.getObrisan();
				
			}
		}



		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		voznjeTabela = new JTable(tableModel);
		
		voznjeTabela.setRowSelectionAllowed(true);
		voznjeTabela.setColumnSelectionAllowed(false);
		voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		voznjeTabela.setDefaultEditor(Object.class, null);
		voznjeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(voznjeTabela);
		add(scrollPane, BorderLayout.CENTER);

	}

	private void initActions(final Korisnik korisnik) {
		
		btnDelete.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				int red = voznjeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String voznjaID = tableModel.getValueAt(red, 0).toString();
					Voznja voznja = ps.nadjiVoznje(Integer.parseInt(voznjaID));
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete servis?", 
							voznjaID + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						voznja.setObrisan(true);
						tableModel.removeRow(red);
						ps.snimiVoznje();
					}
				}
			}
		});
	
		btnAdd.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				VoznjaForma vf = new VoznjaForma(ps, null, korisnik);
				vf.setVisible(true);
			}
		});
	
		btnEdit.addActionListener(new ActionListener() {
			//@Override
			public void actionPerformed(ActionEvent e) {
				int red = voznjeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String voznjaID = tableModel.getValueAt(red, 0).toString();
					Voznja voznja = ps.nadjiVoznje(Integer.parseInt(voznjaID));
					
					if(vozacLogovan) {
						if(voznja.getStatus() != Status.Dodijeljena) {
							JOptionPane.showMessageDialog(null, "Mozete mijenjati samo servise sa statusom zakazan", "Greska", JOptionPane.WARNING_MESSAGE);
						}else {
							VoznjaForma vf = new VoznjaForma(ps, voznja,korisnik);
							vf.setVisible(true);
						}
					}else {
						VoznjaForma vf = new VoznjaForma(ps, voznja, korisnik);
						vf.setVisible(true);
					}
				}
			}
		});
	}

}
