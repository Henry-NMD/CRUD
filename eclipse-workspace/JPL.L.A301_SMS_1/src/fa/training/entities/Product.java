package fa.training.entities;

public class Product {
	private int productid;
	private String productName;
	private double listPrice;
	
	
	public Product() {
		super();
	}
	
	public Product(int productid, String productName, double listPrice) {
		super();
		this.productid = productid;
		this.productName = productName;
		this.listPrice = listPrice;
	}
	
	public int getProductid() {
		return productid;
	}
	
	public void setProductid(int productid) {
		this.productid = productid;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getListPrice() {
		return listPrice;
	}
	
	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	@Override
	public String toString() {
		return "Product [productid=" + productid + ", productName=" + productName + ", listPrice=" + listPrice + "]";
	}
	
	
	
}
