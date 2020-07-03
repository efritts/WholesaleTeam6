package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Wholesale.PurchaseOrder;

public class PurchaseOrderTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PO_NUM = 0;
	private static final int DATE = 1;

	
	private String[] columnNames = {"PO Number", "Purchase Date"};
	
	private List<PurchaseOrder> purchaseOrders;
	
	public PurchaseOrderTableModel(List<PurchaseOrder> thePurchaseOrders) {
		purchaseOrders = thePurchaseOrders;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return purchaseOrders.size();
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
		PurchaseOrder tempPurchaseOrder = purchaseOrders.get(row);

		switch (col) {
		case PO_NUM:
			return tempPurchaseOrder.getPoNum();
		case DATE:
			return tempPurchaseOrder.getDate();

		default:
			return tempPurchaseOrder.getPoNum();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
