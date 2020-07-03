package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Wholesale.Supplier;

public class SupplierTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;

	private static final int ADDRESS_COL = 2;
	private static final int PHONE_COL = 3;
	
	private String[] columnNames = {"ID", " Name", "Address", "Phone"};
	
	private List<Supplier> suppliers;
	
	public SupplierTableModel(List<Supplier> theSuppliers) {
		suppliers = theSuppliers;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return suppliers.size();
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
		Supplier tempSupplier = suppliers.get(row);

		switch (col) {
		case ID_COL:
			return tempSupplier.getId();
		case NAME_COL:
			return tempSupplier.getName();

		case ADDRESS_COL:
			return tempSupplier.getAddress();
		case PHONE_COL:
			return tempSupplier.getPhone();
		default:
			return tempSupplier.getName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
