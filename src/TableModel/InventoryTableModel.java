package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Wholesale.Inventory;

public class InventoryTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int SUP_ID = 0;
	private static final int PROD_ID = 1;
	private static final int QUANTITY = 2;

	
	private String[] columnNames = {"Supplier ID", "Product ID", "Quantity"};
	
	private List<Inventory> Inventories;
	
	public InventoryTableModel(List<Inventory> theInventorys) {
		Inventories = theInventorys;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return Inventories.size();
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
		Inventory tempInventory = Inventories.get(row);

		switch (col) {
		case SUP_ID:
			return tempInventory.getSupId();
		case PROD_ID:
			return tempInventory.getProdId();
		case QUANTITY:
			return tempInventory.getQuantity();

		default:
			return tempInventory.getSupId();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
