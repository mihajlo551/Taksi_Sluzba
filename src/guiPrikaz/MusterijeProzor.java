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

import guiDodavanjeIIzmjena.MusterijeForma;
import model.Musterija;
import model.PoslovanjeSluzbe;

public class MusterijeProzor extends JFrame{
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable musterijeTabela;
	
	private PoslovanjeSluzbe ps;
	
	public MusterijeProzor(PoslovanjeSluzbe ps) {
		this.ps = ps;
		setTitle("Musterije");
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
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Broj telefona", "Korisnicko Ime", "Lozinka", "Obrisan"};
		Object[][] sadrzaj = new Object[ps.sveNeobrisaneMusterije().size()][zaglavlja.length];
		
		for(int i=0; i<ps.sveNeobrisaneMusterije().size(); i++) {
			Musterija musterija = ps.sveNeobrisaneMusterije().get(i);
			sadrzaj[i][0] = musterija.getId();
			sadrzaj[i][1] = musterija.getIme();
			sadrzaj[i][2] = musterija.getPrezime();
			sadrzaj[i][3] = musterija.getJmbg();
			sadrzaj[i][4] = musterija.getPol();
			sadrzaj[i][5] = musterija.getAdresa();
			sadrzaj[i][6] = musterija.getBrTelefona();
			sadrzaj[i][7] = musterija.getKorisnickoIme();
			sadrzaj[i][8] = musterija.getLozinka();
			sadrzaj[i][9] = musterija.getObrisan();
			
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		musterijeTabela = new JTable(tableModel);
		
		musterijeTabela.setRowSelectionAllowed(true);
		musterijeTabela.setColumnSelectionAllowed(false);
		musterijeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		musterijeTabela.setDefaultEditor(Object.class, null);
		musterijeTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(musterijeTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String musterijaID = tableModel.getValueAt(red, 0).toString();
					Musterija musterija = ps.nadjiMusteriju(Integer.parseInt(musterijaID));
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete admina?", 
							musterijaID + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						musterija.setObrisan(true);
						tableModel.removeRow(red);
						ps.snimiMusterije();
					}
				}
			}
		});
	
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MusterijeForma mf = new MusterijeForma(ps, null);
				mf.setVisible(true);
			}
		
		});
	
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = musterijeTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String musterijaID = tableModel.getValueAt(red, 0).toString();
					Musterija musterija = ps.nadjiMusteriju(Integer.parseInt(musterijaID));
					MusterijeForma mf = new MusterijeForma(ps, musterija);
					mf.setVisible(true);
				}
			}
		});
	}

}
