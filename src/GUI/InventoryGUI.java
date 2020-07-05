package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TableModel.InventoryTableModel;
import Wholesale.InventoryDAO;
import Wholesale.Inventory;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
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

import GUI.CustomerGUI.SimpleHeaderRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoryGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	private InventoryDAO inventoryDAO;
	private JTextField supIdTxt;
	private JTextField prodIdTxt;
	private JTextField quantityTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryGUI frame = new InventoryGUI();
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
	public InventoryGUI() {
		
		try {
			inventoryDAO = new InventoryDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setBounds(100, 100, 450, 300);
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
		
		JButton btnInventorySearch = new JButton("Search");
		btnInventorySearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = textField.getText();
				List<Inventory> inventories = null;
				
				if(id != null && id.trim().length() > 0) {
					try {
						inventories = inventoryDAO.getInventoryById(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					inventories = inventoryDAO.getAllInventorys();
				}
				
				InventoryTableModel model = new InventoryTableModel(inventories);
				
			
				table.setModel(model);
				
//				for(Inventory inventory : inventories) {
//					System.out.println(inventory);
//				}
				
			}
		});
		panel.add(btnInventorySearch);
		
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
				String prodId = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
				String quantity = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));

				supIdTxt.setText(supId);
				prodIdTxt.setText(prodId);
				quantityTxt.setText(quantity);
		
			}
		});
		table.setBorder(new LineBorder(Color.BLACK));
		table.setFillsViewportHeight(true);
		table.setForeground(Color.BLACK);
		
		scrollPane.setViewportView(table);
		
		getContentPane().add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				InventoryDAO inventory = new InventoryDAO();
				if (inventory.addRow(Integer.parseInt(supIdTxt.getText()), 
						Integer.parseInt(prodIdTxt.getText()), 
						Integer.parseInt(quantityTxt.getText()))) {

					supIdTxt.setText("");
					prodIdTxt.setText("");
					quantityTxt.setText("");
		

				} else {
					JOptionPane.showMessageDialog(null, "Not Saved");
				}
			}
		});
		panel_2.add(btnAdd);

		JButton btnModify = new JButton("Modify");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());

				InventoryDAO inventoryDAO = new InventoryDAO();

				if (inventoryDAO.update(Integer.parseInt(supIdTxt.getText()), 
						Integer.parseInt(prodIdTxt.getText()), 
						Integer.parseInt(quantityTxt.getText()))){
					JOptionPane.showMessageDialog(null, "Successfully Updated");

					// CLEAR TXT
					supIdTxt.setText("");
					prodIdTxt.setText("");
					quantityTxt.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Not Updated");
				}
			}
		});
		panel_2.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryDAO inventoryDAO = new InventoryDAO();

				String[] options = { "Yes", "No" };
				int answ = JOptionPane.showOptionDialog(null, "Sure To Delete??", "Delete Confirm",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

				if (answ == 0) {
					int index = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(index, 0).toString());

					if (inventoryDAO.delete(id)) {

						JOptionPane.showMessageDialog(null, "Deleted Updated");

						// CLEAR TXT
						supIdTxt.setText("");
						prodIdTxt.setText("");
						quantityTxt.setText("");

					} else {
						JOptionPane.showMessageDialog(null, "Not Deleted");
					}

				}
			}
		});
		panel_2.add(btnDelete);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Supplier ID");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		supIdTxt = new JTextField();
		GridBagConstraints gbc_supIdTxt = new GridBagConstraints();
		gbc_supIdTxt.insets = new Insets(0, 0, 5, 0);
		gbc_supIdTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_supIdTxt.gridx = 0;
		gbc_supIdTxt.gridy = 1;
		panel_1.add(supIdTxt, gbc_supIdTxt);
		supIdTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Product ID");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		prodIdTxt = new JTextField();
		GridBagConstraints gbc_prodIdTxt = new GridBagConstraints();
		gbc_prodIdTxt.insets = new Insets(0, 0, 5, 0);
		gbc_prodIdTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_prodIdTxt.gridx = 0;
		gbc_prodIdTxt.gridy = 3;
		panel_1.add(prodIdTxt, gbc_prodIdTxt);
		prodIdTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		quantityTxt = new JTextField();
		GridBagConstraints gbc_quantityTxt = new GridBagConstraints();
		gbc_quantityTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_quantityTxt.gridx = 0;
		gbc_quantityTxt.gridy = 5;
		panel_1.add(quantityTxt, gbc_quantityTxt);
		quantityTxt.setColumns(10);
		
		table.getTableHeader().setDefaultRenderer(new SimpleHeaderRenderer());

	}

	public class SimpleHeaderRenderer extends JLabel implements TableCellRenderer {

		public SimpleHeaderRenderer() {
			setFont(new Font("Consolas", Font.BOLD, 12));
			setForeground(Color.BLUE);
			setBorder(BorderFactory.createEtchedBorder());
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value.toString());
			return this;
		}
	}

}
