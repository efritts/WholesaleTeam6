package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TableModel.PurchaseOrderTableModel;
import Wholesale.PurchaseOrderDAO;
import Wholesale.PurchaseOrder;
import Wholesale.PurchaseOrderDAO;
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
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;

public class PurchaseOrderGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	private PurchaseOrderDAO purchaseOrderDAO;
	private JTextField poTxt;
	private JTextField dateTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PurchaseOrderGUI frame = new PurchaseOrderGUI();
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
	public PurchaseOrderGUI() {
		
		try {
			purchaseOrderDAO = new PurchaseOrderDAO();
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
		
		JButton btnSupplierSearch = new JButton("Search");
		btnSupplierSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = textField.getText();
				List<PurchaseOrder> PurchaseOrders = null;
				
				if(id != null && id.trim().length() > 0) {
					try {
						PurchaseOrders = purchaseOrderDAO.getPurchaseOrderById(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					PurchaseOrders = purchaseOrderDAO.getAllPurchaseOrders();
				}
				
				PurchaseOrderTableModel model = new PurchaseOrderTableModel(PurchaseOrders);
				
			
				table.setModel(model);
				
//				for(Supplier inventory : PurchaseOrders) {
//					System.out.println(inventory);
//				}
				
			}
		});
		panel.add(btnSupplierSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				String poNum = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				String date = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
				
				poTxt.setText(poNum);
				dateTxt.setText(date);
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

				PurchaseOrderDAO purchaseOrder = new PurchaseOrderDAO();
				if (purchaseOrder.addRow(Integer.parseInt(poTxt.getText()), dateTxt.getText())) {

					poTxt.setText("");
					dateTxt.setText("");

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

				PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO();
				String string = dateTxt.getText();
				

				if (purchaseOrderDAO.update(Integer.parseInt(poTxt.getText()), dateTxt.getText())) {
					JOptionPane.showMessageDialog(null, "Successfully Updated");

					// CLEAR TXT
					poTxt.setText("");
					dateTxt.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Not Updated");
				}
			}
		});
		panel_2.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PurchaseOrderDAO purchaseOrderDAO = new PurchaseOrderDAO();

				String[] options = { "Yes", "No" };
				int answ = JOptionPane.showOptionDialog(null, "Sure To Delete??", "Delete Confirm",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

				if (answ == 0) {
					int index = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(index, 0).toString());

					if (purchaseOrderDAO.delete(id)) {

						JOptionPane.showMessageDialog(null, "Deleted Updated");

						// CLEAR TXT
						poTxt.setText("");
						dateTxt.setText("");

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
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("PO Number");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		poTxt = new JTextField();
		GridBagConstraints gbc_poTxt = new GridBagConstraints();
		gbc_poTxt.insets = new Insets(0, 0, 5, 0);
		gbc_poTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_poTxt.gridx = 0;
		gbc_poTxt.gridy = 1;
		panel_1.add(poTxt, gbc_poTxt);
		poTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Date");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		dateTxt = new JTextField();
		GridBagConstraints gbc_dateTxt = new GridBagConstraints();
		gbc_dateTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateTxt.gridx = 0;
		gbc_dateTxt.gridy = 3;
		panel_1.add(dateTxt, gbc_dateTxt);
		dateTxt.setColumns(10);
		
	}
}
