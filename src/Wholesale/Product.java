package Wholesale;

public class Product {
	
	private int prodId, wholePrice, retailPrice;
	private String prodName;
	
	
	
	public Product(int prodId, String prodName, int wholePrice, int retailPrice) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.wholePrice = wholePrice;
		this.retailPrice = retailPrice;
	}






	public int getProdId() {
		return prodId;
	}



	public String getProdName() {
		return prodName;
	}



	public int getWholePrice() {
		return wholePrice;
	}



	public int getRetailPrice() {
		return retailPrice;
	}




	
	
	

}
