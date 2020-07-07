package GUI;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DBGui {
	

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DBGui window = new DBGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DBGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 485, 316);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(0, 0, 79, 29);
		frame.getContentPane().add(btnLogin);
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.DARK_GRAY);
		
		JButton btnSupplier = new JButton("Supplier");
		btnSupplier.setBounds(133, 84, 139, 29);
		btnSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SupplierGUI.main(null);
			}
		});
		frame.getContentPane().add(btnSupplier);
		
		JButton btnQuery = new JButton("Query Builder");
		btnQuery.setBackground(Color.GREEN);
		btnQuery.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e){
				QueryBuilderGUI.main(null);
			}
		});
		btnQuery.setBounds(6, 85, 118, 53);
		frame.getContentPane().add(btnQuery);
		
		JButton btnTransactions = new JButton("Transactions");
		btnTransactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransactionGUI.main(null);
			}
		});
		btnTransactions.setBounds(286, 125, 139, 29);
		frame.getContentPane().add(btnTransactions);
		
		JButton btnProduct = new JButton("Product");
		btnProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductGUI.main(null);
			}
		});
		btnProduct.setBounds(133, 125, 139, 29);
		frame.getContentPane().add(btnProduct);
		
		JButton btnPO = new JButton("Purchase Order");
		btnPO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PurchaseOrderGUI.main(null);
			}
		});
		btnPO.setBounds(286, 50, 139, 29);
		frame.getContentPane().add(btnPO);
		
		JButton btnInventory = new JButton("Inventory");
		btnInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryGUI.main(null);
			}
		});
		btnInventory.setBounds(133, 166, 139, 29);
		frame.getContentPane().add(btnInventory);
		
		JButton btnPurchase = new JButton("Purchase");
		btnPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PurchaseGUI.main(null);
			}
		});
		btnPurchase.setBounds(286, 84, 139, 29);
		frame.getContentPane().add(btnPurchase);
		
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerGUI.main(null);
				
			}
		});
		btnCustomer.setBounds(133, 50, 139, 29);
		frame.getContentPane().add(btnCustomer);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginGUI login = new LoginGUI();
				login.main(null);
			}
		});
	}
}
