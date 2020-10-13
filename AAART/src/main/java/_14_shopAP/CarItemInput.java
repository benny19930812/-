package _14_shopAP;

import java.sql.Connection;
import java.sql.Statement;

public class CarItemInput {
	
	private Connection conn;

	   
	  public CarItemInput(Connection conn) {
			this.conn = conn;
	  }
	
	
//	public boolean insertOrderAP(ProductItem productItem) {
//	try {
//	      String sqlString = "insert into Apcart values('"
//		                  	   	+ productItem.getProductID()+"','"
//			                    + productItem.getProdutTitle()+"','"
//	                            + productItem.getProductNum()+"','"
//	                            + productItem.getProductPrice()+"')";
//	                           
//	      Statement stmt = conn.createStatement();
//	      System.out.println(sqlString);
//		    int updatecount = stmt.executeUpdate(sqlString);
//	      stmt.close();
//	      if (updatecount >= 1) return true;
//	      else                  return false;
//		  } catch (Exception e) {
//		    System.err.println("插入資料至訂單表格發生錯誤:" + e);
//			  return false;
//	    }
//
//	}
}
