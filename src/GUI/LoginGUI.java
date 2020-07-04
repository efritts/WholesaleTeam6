package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import java.awt.TextField;
import java.awt.Window;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.DropMode;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Wholesale.Login;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType; 

public class LoginGUI {

	private JFrame frame;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private JTextField txtDriver;
	private JTextField txtUrl;
	private JTextField txtDriverLocation;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private final Action action = new SwingAction();
	
	private Login login;

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
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
	public LoginGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setAlwaysOnTop(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 229, 0};
		gridBagLayout.rowHeights = new int[]{0, 22, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		txtUrl = new JTextField();
		txtUrl.setToolTipText("Address of your MySQL instance");
		
		//labels
		JLabel mysqlAddrLabel, userLabel, pwLabel, driverLabel, fileLocaltionLabel;
		mysqlAddrLabel = new JLabel("DB Address");
		GridBagConstraints gbc_addr = new GridBagConstraints();
		gbc_addr.gridx = 2;
		frame.getContentPane().add(mysqlAddrLabel, gbc_addr);

		txtUrl.setDropMode(DropMode.INSERT);
		txtUrl.setHorizontalAlignment(SwingConstants.CENTER);
//		txtUrl.setText("URL");
		txtUrl.setText("jdbc:mysql://localhost:3306/Wholesaler?autoReconnect=true&useSSL=false&serverTimezone=America/Chicago");

		GridBagConstraints gbc_txtUrl = new GridBagConstraints();
		gbc_txtUrl.insets = new Insets(0, 0, 5, 0);
		gbc_txtUrl.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUrl.gridx = 6;
		gbc_txtUrl.gridy = 0;
		frame.getContentPane().add(txtUrl, gbc_txtUrl);
		txtUrl.setColumns(10);

		userLabel = new JLabel("DB User");
		GridBagConstraints gbc_userlabel = new GridBagConstraints();
		gbc_userlabel.gridx = 2;
		gbc_userlabel.gridy = 1;
		frame.getContentPane().add(userLabel,gbc_userlabel);
		
		txtUsername = new JTextField();
		txtUsername.setToolTipText("MySQL Username");
//		txtUsername.setText("Username");
		txtUsername.setText("root");

		txtUsername.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUsername.gridx = 6;
		gbc_txtUsername.gridy = 1;
		frame.getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		


		btnNewButton = new JButton("Connect");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login(txtUrl.getText(), txtUsername.getText(), txtPassword.getText(), txtDriver.getText(), txtDriverLocation.getText());
				if(login.getStatus() == true) {
					frame.setVisible(false);

				}
			}
		});
		

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 5;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		pwLabel = new JLabel("DB PW");
		GridBagConstraints gbc_pwlabel = new GridBagConstraints();
		gbc_pwlabel.gridx = 2;
		gbc_pwlabel.gridy = 2;
		frame.getContentPane().add(pwLabel,gbc_pwlabel);

		txtPassword = new JTextField();
		txtPassword.setToolTipText("MySQL Password");
//		txtPassword.setText("Password");
		txtPassword.setText("root");

		txtPassword.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPassword.gridx = 6;
		gbc_txtPassword.gridy = 2;
		frame.getContentPane().add(txtPassword, gbc_txtPassword);
		txtPassword.setColumns(10);
		

		driverLabel = new JLabel("DB Driver Package");
		GridBagConstraints gbc_driverlabel = new GridBagConstraints();
		gbc_driverlabel.gridx = 2;
		gbc_driverlabel.gridy = 3;
		frame.getContentPane().add(driverLabel,gbc_driverlabel);

		txtDriver = new JTextField();
		txtDriver.setToolTipText("JDBC Driver");
//		txtDriver.setText("Driver");
		txtDriver.setText("com.mysql.jdbc.Driver");

		txtDriver.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtDriver = new GridBagConstraints();
		gbc_txtDriver.insets = new Insets(0, 0, 5, 0);
		gbc_txtDriver.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDriver.gridx = 6;
		gbc_txtDriver.gridy = 3;
		frame.getContentPane().add(txtDriver, gbc_txtDriver);
		txtDriver.setColumns(10);
		
		btnNewButton_1 = new JButton("Cancel");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 5;
		gbc_btnNewButton_1.gridy = 5;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		fileLocaltionLabel = new JLabel("Driver Package File Location");
		GridBagConstraints gbc_fileloclabel = new GridBagConstraints();
		gbc_fileloclabel.gridx = 2;
		gbc_fileloclabel.gridy = 4;
		frame.getContentPane().add(fileLocaltionLabel,gbc_fileloclabel);

		txtDriverLocation = new JTextField();
//		txtDriverLocation.setText("Driver Location");
		txtDriverLocation.setText("/Users/efritts/Eclipse-Workspaces/WholesaleTeam6/lib/mysql-connector-java-5.1.45-bin.jar");

		txtDriverLocation.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtDriverLocation = new GridBagConstraints();
		gbc_txtDriverLocation.insets = new Insets(0, 0, 5, 0);
		gbc_txtDriverLocation.fill = GridBagConstraints.BOTH;
		gbc_txtDriverLocation.gridx = 6;
		gbc_txtDriverLocation.gridy = 4;
		frame.getContentPane().add(txtDriverLocation, gbc_txtDriverLocation);
		txtDriverLocation.setColumns(10);
		
		frame.pack();
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}
