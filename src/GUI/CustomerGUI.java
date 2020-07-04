package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TableModel.CustomerTableModel;
import Wholesale.Customer;
import Wholesale.CustomerDAO;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
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

public class CustomerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	private CustomerDAO customerDAO;
	private JTextField custIdTxt;
	private JTextField fNameTxt;
	private JTextField mNameTxt;
	private JTextField lNameTxt;
	private JTextField addressTxt;
	private JTextField phoneTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerGUI frame = new CustomerGUI();
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
	public CustomerGUI() {
		
		try {
			customerDAO = new CustomerDAO();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setBounds(100, 100, 501, 423);
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
		
		JButton btnCustomerSearch = new JButton("Search");
		btnCustomerSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = textField.getText();
				List<Customer> customers = null;
				
				if(id != null && id.trim().length() > 0) {
					try {
						customers = customerDAO.getCustomerById(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					customers = customerDAO.getAllCusomters();
				}
				
				CustomerTableModel model = new CustomerTableModel(customers);
				
			
				table.setModel(model);
				
//				for(Customer customer : customers) {
//					System.out.println(customer);
//				}
				
			}
		});
		panel.add(btnCustomerSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String custId = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
		        String fName = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
		        String mName = String.valueOf(table.getValueAt(table.getSelectedRow(),2));
		        String lName = String.valueOf(table.getValueAt(table.getSelectedRow(), 3));
		        String address = String.valueOf(table.getValueAt(table.getSelectedRow(), 4));
		        String phone = String.valueOf(table.getValueAt(table.getSelectedRow(), 5));

		        custIdTxt.setText(custId);
		        fNameTxt.setText(fName);
		        mNameTxt.setText(mName);
		        lNameTxt.setText(lName);
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
		contentPane.add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		custIdTxt = new JTextField();
		GridBagConstraints gbc_custIdTxt = new GridBagConstraints();
		gbc_custIdTxt.insets = new Insets(0, 0, 5, 0);
		gbc_custIdTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_custIdTxt.gridx = 0;
		gbc_custIdTxt.gridy = 1;
		panel_1.add(custIdTxt, gbc_custIdTxt);
		custIdTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("First Name");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		panel_1.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		fNameTxt = new JTextField();
		GridBagConstraints gbc_fNameTxt = new GridBagConstraints();
		gbc_fNameTxt.insets = new Insets(0, 0, 5, 0);
		gbc_fNameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_fNameTxt.gridx = 0;
		gbc_fNameTxt.gridy = 3;
		panel_1.add(fNameTxt, gbc_fNameTxt);
		fNameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Middle Name");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 4;
		panel_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		mNameTxt = new JTextField();
		GridBagConstraints gbc_mNameTxt = new GridBagConstraints();
		gbc_mNameTxt.insets = new Insets(0, 0, 5, 0);
		gbc_mNameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_mNameTxt.gridx = 0;
		gbc_mNameTxt.gridy = 5;
		panel_1.add(mNameTxt, gbc_mNameTxt);
		mNameTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Last Name");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 6;
		panel_1.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		lNameTxt = new JTextField();
		GridBagConstraints gbc_lNameTxt = new GridBagConstraints();
		gbc_lNameTxt.insets = new Insets(0, 0, 5, 0);
		gbc_lNameTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lNameTxt.gridx = 0;
		gbc_lNameTxt.gridy = 7;
		panel_1.add(lNameTxt, gbc_lNameTxt);
		lNameTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Address");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 8;
		panel_1.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		addressTxt = new JTextField();
		GridBagConstraints gbc_addressTxt = new GridBagConstraints();
		gbc_addressTxt.insets = new Insets(0, 0, 5, 0);
		gbc_addressTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_addressTxt.gridx = 0;
		gbc_addressTxt.gridy = 9;
		panel_1.add(addressTxt, gbc_addressTxt);
		addressTxt.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Phone");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 10;
		panel_1.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		phoneTxt = new JTextField();
		GridBagConstraints gbc_phoneTxt = new GridBagConstraints();
		gbc_phoneTxt.fill = GridBagConstraints.HORIZONTAL;
		gbc_phoneTxt.gridx = 0;
		gbc_phoneTxt.gridy = 11;
		panel_1.add(phoneTxt, gbc_phoneTxt);
		phoneTxt.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		
		JButton btnAdd = new JButton("Add");
		panel_2.add(btnAdd);
		
		JButton btnModify = new JButton("Modify");
		panel_2.add(btnModify);
		
		JButton btnDelete = new JButton("Delete");
		panel_2.add(btnDelete);
		
	}
}
