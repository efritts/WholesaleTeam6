package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Wholesale.Purchase;

public class PurchaseTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PO_NUM = 0;
	private static final int PROD_ID = 1;
	private static final int QUANTITY = 2;

	
	private String[] columnNames = {"PO Number", "Product ID", "Quantity"};
	
	private List<Purchase> purchases;
	
	public PurchaseTableModel(List<Purchase> thePurchases) {
		purchases = thePurchases;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return purchases.size();
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
		Purchase tempPurchase = purchases.get(row);

		switch (col) {
		case PO_NUM:
			return tempPurchase.getPoNum();
		case PROD_ID:
			return tempPurchase.getProdId();
		case QUANTITY:
			return tempPurchase.getQuantity();

		default:
			return tempPurchase.getPoNum();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
