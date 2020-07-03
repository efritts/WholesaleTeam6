package TableModel;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import Wholesale.Product;

public class ProductTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PROD_ID = 0;
	private static final int NAME = 1;
	private static final int WHOLE_PRICE = 2;
	private static final int RETAIL_PRICE = 3;

	
	private String[] columnNames = {"Product ID", "Product Name", "Wholesale Price", "Retail Price"};
	
	private List<Product> products;
	
	public ProductTableModel(List<Product> theProducts) {
		products = theProducts;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return products.size();
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
		Product tempProduct = products.get(row);

		switch (col) {
		case PROD_ID:
			return tempProduct.getProdId();
		case NAME:
			return tempProduct.getProdName();
		case WHOLE_PRICE:
			return tempProduct.getWholePrice();
		case RETAIL_PRICE:
			return tempProduct.getRetailPrice();

		default:
			return tempProduct.getProdId();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}

}
