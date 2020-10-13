package _14_shopAP.orderbean;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import _14_shopAP.Product;
import _14_shopAP.ProductItem;

public class ShoppingCartAP {
	private Map<String, ProductItem> cart =  new LinkedHashMap< >();

	public ShoppingCartAP() {
		
	}
	
	public Map<String, ProductItem> getCartAP() {
		return cart;
	}

	public void addToCarAP (String productID, ProductItem pd) {
		if(pd.getProductNumAP() <= 0) {
			return;
		}
		//判斷顧客是第一次購買此商品或加購商品
		if (cart.get(productID) == null) {
			cart.put(productID, pd);
		}else {
			ProductItem pdSet =cart.get(productID);
			pdSet.setProductNumAP(pd.getProductNumAP()+pdSet.getProductNumAP());
		}
	}
	

	public boolean modifyQty(String productID, int newQty) {
		System.out.println(productID);
		System.out.println(cart);
		System.out.println(cart.get(productID+" "));
		System.out.println(newQty);
		if ( cart.get(productID+" ") != null ) {
			ProductItem  pd = cart.get(productID+" ");
		   pd.setProductNumAP(newQty);

		   System.out.println("修改方法中的productID= "+productID);
		   System.out.println("修改方法中的newQty= "+newQty);

		   return true;
		} else {
			System.out.println("修改方法中的productID="+productID);
			System.out.println("修改方法錯誤，newQty="+newQty+"__ cart.get(productID)= "+cart.get(productID));
		   return false;
		   
		}
	}
	
	
	public int deleteBook(String productID) {
		if ( cart.get(productID+" ") != null ) {
			System.out.println("找到刪除物件");
	       cart.remove(productID+" ");  // Map介面的remove()方法
	       return 1;
		} else {
			System.out.println("找不到刪除物件");
		   return 0;
		}
	}
	
	
	public int getSubtotal(){
		int subTotal = 0 ;
		Set<String> set = cart.keySet();
		for(String n : set){
			int price = Integer.parseInt(cart.get(n).getProductPriceAP());
			int qty = cart.get(n).getProductNumAP();
			subTotal +=  price * qty;
		}
		return subTotal;
	}
	
	

}
