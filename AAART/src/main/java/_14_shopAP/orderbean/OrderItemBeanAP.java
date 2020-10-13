package _14_shopAP.orderbean;



public class OrderItemBeanAP {
	
	
	Integer orderIdAP;
	Integer ordersAPNo;
	String orderNameAP;
	int productNum;
	String productPrice;
	
	public OrderItemBeanAP() {
		
	}
	
	
	public OrderItemBeanAP(Integer orderIdAP, Integer ordersAPNo, String orderNameAP, int productNum,
			String productPrice) {
		super();
		this.orderIdAP = orderIdAP;
		this.ordersAPNo = ordersAPNo;
		this.orderNameAP = orderNameAP;
		this.productNum = productNum;
		this.productPrice = productPrice;
	}


	public Integer getOrdersAPNo() {
		return ordersAPNo;
	}


	public void setOrdersAPNo(Integer ordersAPNo) {
		this.ordersAPNo = ordersAPNo;
	}


	public Integer getOrderIdAP() {
		return orderIdAP;
	}
	public void setOrderIdAP(Integer orderIdAP) {
		this.orderIdAP = orderIdAP;
	}
	public String getOrderNameAP() {
		return orderNameAP;
	}
	public void setOrderNameAP(String orderNameAP) {
		this.orderNameAP = orderNameAP;
	}
	public int getProductNum() {
		return productNum;
	}
	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
	
	

}
