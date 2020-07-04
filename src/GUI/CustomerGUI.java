package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TableModel.CustomerTableModel;
import Wholesale.Customer;
import Wholesale.CustomerDAO;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.sql.SQLException;

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
					frame.addWindowListener(new WindowAdapter() {
						private CustomerDAO customerDAO;

						public void windowClosing() throws SQLException {
							// do something
							customerDAO = new CustomerDAO();
							customerDAO.closeConn();
						}
					});
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

				if (id != null && id.trim().length() > 0) {
					try {
						customers = customerDAO.getCustomerById(id);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
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

		JScrollPane scrollPane = new JScrollPane(table);
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

		table.setColumnSelectionAllowed(true);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String custId = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				String fName = String.valueOf(table.getValueAt(table.getSelectedRow(), 1));
				String mName = String.valueOf(table.getValueAt(table.getSelectedRow(), 2));
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
		gbl_panel_1.columnWidths = new int[] { 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
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
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CustomerDAO customer = new CustomerDAO();
				if (customer.addRow(Integer.parseInt(custIdTxt.getText()), fNameTxt.getText(), mNameTxt.getText(),
						lNameTxt.getText(), addressTxt.getText(), phoneTxt.getText())) {

					custIdTxt.setText("");
					fNameTxt.setText("");
					mNameTxt.setText("");
					lNameTxt.setText("");
					addressTxt.setText("");
					phoneTxt.setText("");

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

				CustomerDAO customerDAO = new CustomerDAO();

				if (customerDAO.update(Integer.parseInt(custIdTxt.getText()), fNameTxt.getText(), mNameTxt.getText(),
						lNameTxt.getText(), addressTxt.getText(), phoneTxt.getText())) {
					JOptionPane.showMessageDialog(null, "Successfully Updated");

					// CLEAR TXT
					custIdTxt.setText("");
					fNameTxt.setText("");
					mNameTxt.setText("");
					lNameTxt.setText("");
					addressTxt.setText("");
					phoneTxt.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Not Updated");
				}
			}
		});
		panel_2.add(btnModify);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerDAO customerDAO = new CustomerDAO();

				String[] options = { "Yes", "No" };
				int answ = JOptionPane.showOptionDialog(null, "Sure To Delete??", "Delete Confirm",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

				if (answ == 0) {
					int index = table.getSelectedRow();
					int id = Integer.parseInt(table.getValueAt(index, 0).toString());

					if (customerDAO.delete(id)) {

						JOptionPane.showMessageDialog(null, "Deleted Updated");

						// CLEAR TXT
						custIdTxt.setText("");
						fNameTxt.setText("");
						mNameTxt.setText("");
						lNameTxt.setText("");
						addressTxt.setText("");
						phoneTxt.setText("");

					} else {
						JOptionPane.showMessageDialog(null, "Not Deleted");
					}

				}
			}
		});
		panel_2.add(btnDelete);

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
