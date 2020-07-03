package Wholesale;

import java.util.Date;

public class PurchaseOrder {
	
	private int poNum;
	private Date date;
	public PurchaseOrder(int poNum, Date date) {
		super();
		this.poNum = poNum;
		this.date = date;
	}
	public int getPoNum() {
		return poNum;
	}
	public Date getDate() {
		return date;
	}
	
	

}
