package _14_shopAP.orderbean;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

public class OrderListBeamAP {
	Integer orderNoAP;
	String memberID;
	String addAP;
	String bnoAP;
	String orderTitleAP;
	int totalAmountAP;
	Date date;
	Set<OrderItemBeanAP> items = new LinkedHashSet<>();
	
	public OrderListBeamAP() {
		
	}


	public OrderListBeamAP(Integer orderNoAP, String memberID, String addAP, String bnoAP, String orderTitleAP,
			int totalAmountAP, Date date, Set<OrderItemBeanAP> items) {
		super();
		this.orderNoAP = orderNoAP;
		this.memberID = memberID;
		this.addAP = addAP;
		this.bnoAP = bnoAP;
		this.orderTitleAP = orderTitleAP;
		this.totalAmountAP = totalAmountAP;
		this.date = date;
		this.items = items;
	}



	public int getTotalAmountAP() {
		return totalAmountAP;
	}




	public void setTotalAmountAP(int totalAmountAP) {
		this.totalAmountAP = totalAmountAP;
	}




	public Date getDate() {
		return date;
	}




	public void setDate(Date date) {
		this.date = date;
	}




	public int getOrderNoAP() {
		return orderNoAP;
	}
	public void setOrderNoAP(Integer orderNoAP) {
		this.orderNoAP = orderNoAP;
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public String getAddAP() {
		return addAP;
	}
	public void setAddAP(String addAP) {
		this.addAP = addAP;
	}
	public String getBnoAP() {
		return bnoAP;
	}
	public void setBnoAP(String bnoAP) {
		this.bnoAP = bnoAP;
	}
	public String getOrderTitleAP() {
		return orderTitleAP;
	}
	public void setOrderTitleAP(String orderTitleAP) {
		this.orderTitleAP = orderTitleAP;
	}
	public Set<OrderItemBeanAP> getItems() {
		return items;
	}
	public void setItems(Set<OrderItemBeanAP> items) {
		this.items = items;
	}
	
	
	

}
