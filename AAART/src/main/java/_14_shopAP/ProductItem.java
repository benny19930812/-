package _14_shopAP;

public class ProductItem {
	private String productIdAP;
	private String productTitleAP;
	private int productNumAP;
	private String productPriceAP;
	private String productImgAP;
	
	
	public ProductItem() {
		
	}


	public ProductItem(String productIdAP, String produtTitleAP, int productNumAP, String productPriceAP,
			String productImgAP) {
		super();
		this.productIdAP = productIdAP;
		this.productTitleAP = produtTitleAP;
		this.productNumAP = productNumAP;
		this.productPriceAP = productPriceAP;
		this.productImgAP = productImgAP;
	}


	public String getProductIdAP() {
		return productIdAP;
	}


	public void setProductIdAP(String productIdAP) {
		this.productIdAP = productIdAP;
	}


	public String getProductTitleAP() {
		return productTitleAP;
	}


	public void setProductTitleAP(String produtTitleAP) {
		this.productTitleAP = produtTitleAP;
	}


	public int getProductNumAP() {
		return productNumAP;
	}


	public void setProductNumAP(int productNumAP) {
		this.productNumAP = productNumAP;
	}


	public String getProductPriceAP() {
		return productPriceAP;
	}


	public void setProductPriceAP(String productPriceAP) {
		this.productPriceAP = productPriceAP;
	}


	public String getProductImgAP() {
		return productImgAP;
	}


	public void setProductImgAP(String productImgAP) {
		this.productImgAP = productImgAP;
	}
	
	

}
