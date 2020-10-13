package _04_ShopCart;

import java.io.Serializable;
import java.util.List;

import _04_ST.ShowOj;

//本類別封裝單筆訂單資料
public class CartBean implements Serializable{
	private String title;
	private int halfnum;
	private int adultnum;
	private int total1;
	private int total2;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getHalfnum() {
		return halfnum;
	}
	public void setHalfnum(int halfnum) {
		this.halfnum = halfnum;
	}
	public int getAdultnum() {
		return adultnum;
	}
	public void setAdultnum(int adultnum) {
		this.adultnum = adultnum;
	}
	public int getTotal1() {
		return total1;
	}
	public void setTotal1(int total1) {
		this.total1 = total1;
	}
	public int getTotal2() {
		return total2;
	}
	public void setTotal2(int total2) {
		this.total2 = total2;
	}
	public int getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}
	private int totalprice;


		

	}
	
	
	
	
	
	
	
	

