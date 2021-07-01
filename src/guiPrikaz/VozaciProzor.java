package guiPrikaz;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import guiDodavanjeIIzmjena.VozaciForma;
import model.PoslovanjeSluzbe;
import model.Vozac;

public class VozaciProzor extends JFrame{
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable vozaciTabela;
	
	private PoslovanjeSluzbe ps;
	
	public VozaciProzor(PoslovanjeSluzbe ps) {
		this.ps = ps;
		setTitle("Vozaci");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
		
	}
	

	private void initGUI() {
		
		ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
		btnAdd.setIcon(addIcon);
		ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
		btnEdit.setIcon(editIcon);
		ImageIcon deleteIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
		btnDelete.setIcon(deleteIcon);
		
		mainToolbar.add(btnAdd);
		mainToolbar.add(btnEdit);
		mainToolbar.add(btnDelete);
		add(mainToolbar, BorderLayout.NORTH);
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Broj telefona", "Korisnicko Ime", "Lozinka", "Obrisan", "Plata", "Broj clanske karte", "Automobil"};
		Object[][] sadrzaj = new Object[ps.sviNeobrisaniVozaci().size()][zaglavlja.length];
		
		for(int i=0; i<ps.sviNeobrisaniVozaci().size(); i++) {
			Vozac vozac = ps.sviNeobrisaniVozaci().get(i);
			sadrzaj[i][0] = vozac.getId();
			sadrzaj[i][1] = vozac.getIme();
			sadrzaj[i][2] = vozac.getPrezime();
			sadrzaj[i][3] = vozac.getJmbg();
			sadrzaj[i][4] = vozac.getPol();
			sadrzaj[i][5] = vozac.getAdresa();
			sadrzaj[i][6] = vozac.getBrTelefona();
			sadrzaj[i][7] = vozac.getKorisnickoIme();
			sadrzaj[i][8] = vozac.getLozinka();
			sadrzaj[i][9] = vozac.getObrisan();
			sadrzaj[i][10] = vozac.getPlata();
			sadrzaj[i][11] = vozac.getBrClanskeKarte();
			sadrzaj[i][12] = vozac.getAutomobil().getId();
			
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		vozaciTabela = new JTable(tableModel); 
		
		vozaciTabela.setRowSelectionAllowed(true);
		vozaciTabela.setColumnSelectionAllowed(false);
		vozaciTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		vozaciTabela.setDefaultEditor(Object.class, null);
		vozaciTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(vozaciTabela);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = vozaciTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String vozacID = tableModel.getValueAt(red, 0).toString();
					Vozac vozac = ps.nadjiVozaca(Integer.parseInt(vozacID));
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete servisera?", 
							vozacID + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						vozac.setObrisan(true);
						tableModel.removeRow(red);
						ps.snimiVozace();
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VozaciForma vf = new VozaciForma(ps, null);
				vf.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int red = vozaciTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String vozacID = tableModel.getValueAt(red, 0).toString();
					Vozac vozac = ps.nadjiVozaca(Integer.parseInt(vozacID));
					VozaciForma vf = new VozaciForma(ps, vozac);
					vf.setVisible(true);
				}
			}
		});
	}

}
