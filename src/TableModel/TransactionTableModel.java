package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Wholesale.Transaction;

public class TransactionTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int CUST_ID = 0;
	private static final int SUP_ID = 1;
	private static final int PO_NUM = 2;

	
	private String[] columnNames = {"Customer ID", "Supplier ID", "PO Number"};
	
	private List<Transaction> transactions;
	
	public TransactionTableModel(List<Transaction> theTransactions) {
		transactions = theTransactions;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return transactions.size();
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
		Transaction tempTransaction = transactions.get(row);

		switch (col) {
		case CUST_ID:
			return tempTransaction.getCustId();
		case SUP_ID:
			return tempTransaction.getSupId();
		case PO_NUM:
			return tempTransaction.getPoNum();

		default:
			return tempTransaction.getCustId();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
