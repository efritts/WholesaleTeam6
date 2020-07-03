package Wholesale;

public class Inventory {
	
	private int supId, prodId, quantity;
	
	public Inventory(int supId, int prodId, int quantity) {
		super();
		this.supId = supId;
		this.prodId = prodId;
		this.quantity = quantity;
	}

	public int getSupId() {
		return supId;
	}

	public int getProdId() {
		return prodId;
	}

	public int getQuantity() {
		return quantity;
	}


	
	
	

}
