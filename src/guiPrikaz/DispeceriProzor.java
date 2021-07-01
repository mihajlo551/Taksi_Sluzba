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


import guiDodavanjeIIzmjena.DispeceriForma;
import model.Dispeceri;
import model.PoslovanjeSluzbe;

public class DispeceriProzor extends JFrame {
	

	private JToolBar mainToolbar = new JToolBar();
	private JButton btnAdd = new JButton();
	private JButton btnEdit = new JButton();
	private JButton btnDelete = new JButton();
	
	private DefaultTableModel tableModel;
	private JTable adminiTabela;
	
	private PoslovanjeSluzbe ps;
	
	public DispeceriProzor(PoslovanjeSluzbe ps) {
		this.ps = ps;
		setTitle("Dispeceri");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initActions();
	}
	
	
	public void initGUI() {
		
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
		
		String[] zaglavlja = new String[] {"ID", "Ime", "Prezime", "JMBG", "Pol", "Adresa", "Broj telefona", "Korisnicko Ime", "Lozinka", "Obrisan", "Plata", "Broj Linije", "Odjeljenje"};
		Object[][] sadrzaj = new Object[ps.sviNeobrisaniAdmini().size()][zaglavlja.length];
		
		for(int i=0; i<ps.sviNeobrisaniAdmini().size(); i++) {
			Dispeceri admin = ps.sviNeobrisaniAdmini().get(i);
			sadrzaj[i][0] = admin.getId();
			sadrzaj[i][1] = admin.getIme();
			sadrzaj[i][2] = admin.getPrezime();
			sadrzaj[i][3] = admin.getJmbg();
			sadrzaj[i][4] = admin.getPol();
			sadrzaj[i][5] = admin.getAdresa();
			sadrzaj[i][6] = admin.getBrTelefona();
			sadrzaj[i][7] = admin.getKorisnickoIme();
			sadrzaj[i][8] = admin.getLozinka();
			sadrzaj[i][9] = admin.getObrisan();
			sadrzaj[i][10] = admin.getPlata();
			sadrzaj[i][11] = admin.getBrLinije();
			sadrzaj[i][12] = admin.getOdjeljenje();
		}
		
		tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
		adminiTabela = new JTable(tableModel);
		
		adminiTabela.setRowSelectionAllowed(true);
		adminiTabela.setColumnSelectionAllowed(false);
		adminiTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adminiTabela.setDefaultEditor(Object.class, null);
		adminiTabela.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane scrollPane = new JScrollPane(adminiTabela);
		add(scrollPane, BorderLayout.CENTER);
		
	}
	
	
	private void initActions() {
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String adminID = tableModel.getValueAt(red, 0).toString();
					Dispeceri admin = ps.nadjiAdmina(Integer.parseInt(adminID));
					int izbor = JOptionPane.showConfirmDialog(null, 
							"Da li ste sigurni da zelite da obrisete admina?", 
							adminID + " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
					if(izbor == JOptionPane.YES_OPTION) {
						admin.setObrisan(true);
						tableModel.removeRow(red);
						ps.snimiDispecere();
					}
				}
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DispeceriForma df = new DispeceriForma(ps, null);
				df.setVisible(true);
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = adminiTabela.getSelectedRow();
				if(red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska", JOptionPane.WARNING_MESSAGE);
				}else {
					String adminID = tableModel.getValueAt(red, 0).toString();
					Dispeceri admin = ps.nadjiAdmina(Integer.parseInt(adminID));
					DispeceriForma af = new DispeceriForma(ps, admin);
					af.setVisible(true);
				}
			}
		});
	}

}
