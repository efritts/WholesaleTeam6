package Wholesale;

public class Transaction {
	
	private int custId, supId, poNum;

	public Transaction(int custId, int supId, int poNum) {
		super();
		this.custId = custId;
		this.supId = supId;
		this.poNum = poNum;
	}

	public int getCustId() {
		return custId;
	}

	public int getSupId() {
		return supId;
	}

	public int getPoNum() {
		return poNum;
	}
	
	

}
