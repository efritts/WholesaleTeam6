package Wholesale;

public class Purchase {
	
	private int poNum, prodId, quantity;

	public Purchase(int poNum, int prodId, int quantity) {
		super();
		this.poNum = poNum;
		this.prodId = prodId;
		this.quantity = quantity;
	}

	public int getPoNum() {
		return poNum;
	}

	public int getProdId() {
		return prodId;
	}

	public int getQuantity() {
		return quantity;
	}
	
	
	
	

}
