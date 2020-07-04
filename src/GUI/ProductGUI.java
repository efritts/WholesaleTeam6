package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TableModel.ProductTableModel;
import Wholesale.ProductDAO;
import Wholesale.Product;
import Wholesale.ProductDAO;
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

public class ProductGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	private ProductDAO productDAO;
	private JTextField prodIdTxt;
	private JTextField nameTxt;
	private JTextField wholePriceTxt;
	private JTextField retailPriceTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductGUI frame = new ProductGUI();
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
	public ProductGUI() {
		
		try {
			productDAO = new ProductDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setBounds(100, 100, 450, 343);
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
				List<Product> inventories = null;
				
				if(id != null && id.trim().length() > 0) {
					try {
						inventories = productDAO.getProductById(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					inventories = productDAO.getAllProducts();
				}
				
				ProductTableModel model = new ProductTableModel(inventories);
				
			
				table.setModel(model);
				
//				for(Supplier product : inventories) {
//					System.out.println(product);
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
				String prodId = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				String name = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
				String wholePrice = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
				String retailPrice = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));

				prodIdTxt.setText(prodId);
				nameTxt.setText(name);
				wholePriceTxt.setText(wholePrice);
				retailPriceTxt.setText(retailPrice);
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

				ProductDAO product = new ProductDAO();
				if (product.addRow(Integer.parseInt(prodIdTxt.getText()), nameTxt.getText(), Integer.parseInt(wholePriceTxt.getText()),
						Integer.parseInt(retailPriceTxt.getText()))) {

					prodIdTxt.setText("");
					nameTxt.setText("");
					wholePriceTxt.setText("");
					retailPriceTxt.setText("");

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

				ProductDAO productDAO = new ProductDAO();

				if (productDAO.update(Integer.parseInt(prodIdTxt.getText()), nameTxt.getText(), Integer.parseInt(wholePriceTxt.getText()),
						Integer.parseInt(retailPriceTxt.getText()))) {
					JOptionPane.showMessageDialog(null, "Successfully Updated");

					// CLEAR TXT
					prodIdTxt.setText("");
					nameTxt.setText("");
					wholePriceTxt.setText("");
					retailPriceTxt.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Not Updated");
				}
			}
		});
		panel_2.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductDAO productDAO = new ProductDAO();

				String[] options = { "Yes", "No" };
				int answ = JOptionPane.showOptionDialog(null, "Sure To Delete??", "Delete Confirm",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

				if (answ == 0) {
					int index = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(index, 0).toString());

					if (productDAO.delete(id)) {

						JOptionPane.showMessageDialog(null, "Deleted Updated");

						// CLEAR TXT
						prodIdTxt.setText("");
						nameTxt.setText("");
						wholePriceTxt.setText("");
						retailPriceTxt.setText("");

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
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Product ID");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		prodIdTxt = new JTextField();
		GridBagConstraints gbc_prodIdTxt = new GridBagConstraints();
		gbc_prodIdTxt.insets = new Insets(0, 0, 5, 0);
		gbc_prodIdTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_prodIdTxt.gridx = 0;
		gbc_prodIdTxt.gridy = 1;
		panel_1.add(prodIdTxt, gbc_prodIdTxt);
		prodIdTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Product Name");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		nameTxt = new JTextField();
		GridBagConstraints gbc_nameTxt = new GridBagConstraints();
		gbc_nameTxt.insets = new Insets(0, 0, 5, 0);
		gbc_nameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTxt.gridx = 0;
		gbc_nameTxt.gridy = 3;
		panel_1.add(nameTxt, gbc_nameTxt);
		nameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Wholesale Price");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		wholePriceTxt = new JTextField();
		GridBagConstraints gbc_wholePriceTxt = new GridBagConstraints();
		gbc_wholePriceTxt.insets = new Insets(0, 0, 5, 0);
		gbc_wholePriceTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_wholePriceTxt.gridx = 0;
		gbc_wholePriceTxt.gridy = 5;
		panel_1.add(wholePriceTxt, gbc_wholePriceTxt);
		wholePriceTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Retail Price");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 6;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		retailPriceTxt = new JTextField();
		GridBagConstraints gbc_retailPriceTxt = new GridBagConstraints();
		gbc_retailPriceTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_retailPriceTxt.gridx = 0;
		gbc_retailPriceTxt.gridy = 7;
		panel_1.add(retailPriceTxt, gbc_retailPriceTxt);
		retailPriceTxt.setColumns(10);
		
	}
}
