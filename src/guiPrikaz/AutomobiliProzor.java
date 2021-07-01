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

import guiDodavanjeIIzmjena.AutomobiliForma;
import model.Automobil;
import model.PoslovanjeSluzbe;

public class AutomobiliProzor extends JFrame{
	
	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable automobiliTabela;
	
	private PoslovanjeSluzbe ps;
	
	
	public AutomobiliProzor(PoslovanjeSluzbe ps) {
		this.ps = ps;
		setTitle("Automobili");
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
		
		String[] zaglavlja = new String[] {"ID", "Model", "Proizvodjac","Godina proizvodnje", "Registracija", "Vrsta Automobila",  "Obrisan"};
		Object[][] sadrzaj = new Object[ps.sviNeobrisaniAutomobili().size()][zaglavlja.length];

		for(int i=0; i<ps.sviNeobrisaniAutomobili().size(); i++) {
			Automobil automobil = ps.sviNeobrisaniAutomobili().get(i);
			sadrzaj[i][0] = automobil.getId();
			sadrzaj[i][1] = automobil.getModel();
			sadrzaj[i][2] = automobil.getProizvodjac();
			sadrzaj[i][3] = automobil.getGodinaProizvodnje();
			sadrzaj[i][4] = automobil.getRegistracija();
			sadrzaj[i][5] = automobil.getVrstaAutomobila();
			sadrzaj[i][6] = automobil.getObrisan();
			
		}
		

		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		automobiliTabela = new JTable(tableModel);
		
		automobiliTabela.setRowSelectionAllowed(true);
		automobiliTabela.setColumnSelectionAllowed(false);
		automobiliTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		automobiliTabela.setDefaultEditor(Object.class, null);
		automobiliTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(automobiliTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}

	private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = automobiliTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String automobilID = tableModel.getValueAt(red, 0).toString();
					Automobil automobil = ps.nadjiAutomobil(Integer.parseInt(automobilID));
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete automobil?", 
							automobilID + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						automobil.setObrisan(true);
						tableModel.removeRow(red);
						ps.snimiAutomobile();
					}
				}
			}
		});
		

		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AutomobiliForma af = new AutomobiliForma(ps, null);
				af.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = automobiliTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String automobilID = tableModel.getValueAt(red, 0).toString();
					Automobil automobil = ps.nadjiAutomobil(Integer.parseInt(automobilID));
					AutomobiliForma af = new AutomobiliForma(ps, automobil);
					af.setVisible(true);
				}
			}
		});
		
		
	}

}
