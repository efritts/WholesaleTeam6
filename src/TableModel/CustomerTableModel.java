package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Wholesale.Customer;

public class CustomerTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ID_COL = 0;
	private static final int FNAME_COL = 1;
	private static final int MNAME_COL = 2;
	private static final int LNAME_COL = 3;
	private static final int ADDRESS_COL = 4;
	private static final int PHONE_COL = 5;
	
	private String[] columnNames = {"Cust_id", " f_name", "m_name", "l_name", "Cust_address", "Cust_phone"};
	
	private List<Customer> customers;
	
	public CustomerTableModel(List<Customer> theCustomers) {
		customers = theCustomers;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return customers.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		Customer tempCustomer = customers.get(row);

		switch (col) {
		case ID_COL:
			return tempCustomer.getId();
		case FNAME_COL:
			return tempCustomer.getfName();
		case MNAME_COL:
			return tempCustomer.getmName();
		case LNAME_COL:
			return tempCustomer.getlName();
		case ADDRESS_COL:
			return tempCustomer.getAddress();
		case PHONE_COL:
			return tempCustomer.getPhone();
		default:
			return tempCustomer.getfName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
