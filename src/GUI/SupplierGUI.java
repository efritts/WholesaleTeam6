package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TableModel.SupplierTableModel;
import Wholesale.SupplierDAO;
import Wholesale.Supplier;
import Wholesale.SupplierDAO;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.LineBorder;
import javax.swing.table.TableCellRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SupplierGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	private SupplierDAO supplierDAO;
	private JTextField suppIdTxt;
	private JTextField nameTxt;
	private JTextField addressTxt;
	private JTextField phoneTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SupplierGUI frame = new SupplierGUI();
					frame.pack();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SupplierGUI() {
		
		try {
			supplierDAO = new SupplierDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setBounds(100, 100, 450, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Enter Search Term");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnSupplierSearch = new JButton("Search");
		btnSupplierSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = textField.getText();
				List<Supplier> suppliers = null;
				
				if(id != null && id.trim().length() > 0) {
					try {
						suppliers = supplierDAO.getSupplierById(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					suppliers = supplierDAO.getAllSuppliers();
				}
				
				SupplierTableModel model = new SupplierTableModel(suppliers);
				
			
				table.setModel(model);
				
//				for(Supplier supplier : suppliers) {
//					System.out.println(supplier);
//				}
				
			}
		});
		panel.add(btnSupplierSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable() {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int col) {
				Component comp = super.prepareRenderer(renderer, row, col);
				((JLabel) comp).setHorizontalAlignment(JLabel.LEFT);
				return comp;
			}

		};
		table.setBackground(Color.WHITE);

		table.setShowGrid(true);
		// Removing the grid line from the table
		// table.setShowGrid(false);
		// Show vertical grid lines
		table.setShowVerticalLines(true);
		table.setGridColor(Color.black);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String supId = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				String name = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
				String address = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
				String phone = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));

				suppIdTxt.setText(supId);
				nameTxt.setText(name);
				addressTxt.setText(address);
				phoneTxt.setText(phone);
			}
		});
		table.setBorder(new LineBorder(Color.BLACK));
		table.setFillsViewportHeight(true);
		table.setForeground(Color.BLACK);
		
		scrollPane.setViewportView(table);
		
		getContentPane().add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SupplierDAO supplier = new SupplierDAO();
				if (supplier.addRow(suppIdTxt.getText(), nameTxt.getText(), addressTxt.getText(), phoneTxt.getText())) {

					suppIdTxt.setText("");
					nameTxt.setText("");
					addressTxt.setText("");
					phoneTxt.setText("");
		

				} else {
					JOptionPane.showMessageDialog(null, "Not Saved");
				}
			}
		});
		panel_1.add(btnAdd);

		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());

				SupplierDAO supplierDAO = new SupplierDAO();

				if (supplierDAO.update(suppIdTxt.getText(), nameTxt.getText(), addressTxt.getText(), phoneTxt.getText())) {
					JOptionPane.showMessageDialog(null, "Successfully Updated");

					// CLEAR TXT
					suppIdTxt.setText("");
					nameTxt.setText("");
					addressTxt.setText("");
					phoneTxt.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Not Updated");
				}
			}
		});
		panel_1.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierDAO supplierDAO = new SupplierDAO();

				String[] options = { "Yes", "No" };
				int answ = JOptionPane.showOptionDialog(null, "Sure To Delete??", "Delete Confirm",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

				if (answ == 0) {
					int index = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(index, 0).toString());

					if (supplierDAO.delete(id)) {

						JOptionPane.showMessageDialog(null, "Deleted Updated");

						// CLEAR TXT
						suppIdTxt.setText("");
						nameTxt.setText("");
						addressTxt.setText("");
						phoneTxt.setText("");

					} else {
						JOptionPane.showMessageDialog(null, "Not Deleted");
					}

				}
			}
		});
		panel_1.add(btnDelete);
		
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.EAST);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier ID");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_2.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		suppIdTxt = new JTextField();
		GridBagConstraints gbc_suppIdTxt = new GridBagConstraints();
		gbc_suppIdTxt.insets = new Insets(0, 0, 5, 0);
		gbc_suppIdTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_suppIdTxt.gridx = 0;
		gbc_suppIdTxt.gridy = 1;
		panel_2.add(suppIdTxt, gbc_suppIdTxt);
		suppIdTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Supplier Name");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_2.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		nameTxt = new JTextField();
		GridBagConstraints gbc_nameTxt = new GridBagConstraints();
		gbc_nameTxt.insets = new Insets(0, 0, 5, 0);
		gbc_nameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTxt.gridx = 0;
		gbc_nameTxt.gridy = 3;
		panel_2.add(nameTxt, gbc_nameTxt);
		nameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Address");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		panel_2.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		addressTxt = new JTextField();
		GridBagConstraints gbc_addressTxt = new GridBagConstraints();
		gbc_addressTxt.insets = new Insets(0, 0, 5, 0);
		gbc_addressTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTxt.gridx = 0;
		gbc_addressTxt.gridy = 5;
		panel_2.add(addressTxt, gbc_addressTxt);
		addressTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Phone");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 6;
		panel_2.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		phoneTxt = new JTextField();
		GridBagConstraints gbc_phoneTxt = new GridBagConstraints();
		gbc_phoneTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneTxt.gridx = 0;
		gbc_phoneTxt.gridy = 7;
		panel_2.add(phoneTxt, gbc_phoneTxt);
		phoneTxt.setColumns(10);
		
	}
}
