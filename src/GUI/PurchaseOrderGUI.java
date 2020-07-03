package GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import TableModel.PurchaseOrderTableModel;
import Wholesale.PurchaseOrder;
import Wholesale.PurchaseOrderDAO;
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

public class PurchaseOrderGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	
	private PurchaseOrderDAO purchaseOrderDAO;

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
		table.setRowSelectionAllowed(false);
		table.setBorder(new LineBorder(Color.BLACK));
		table.setFillsViewportHeight(true);
		table.setForeground(Color.BLACK);
		
		scrollPane.setViewportView(table);
		
		getContentPane().add(scrollPane);
		
	}
}
