package _14_shopAP.impl;

import javax.sql.DataSource;

import _14_init.glob.GlobalService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _14_shopAP.dao.OrderDaoImpl;
import _14_shopAP.dao.OrderItemDao;
import _14_shopAP.dao.OrderItemDaoImpl;
import _14_shopAP.orderbean.OrderItemBeanAP;
import _14_shopAP.orderbean.OrderListBeamAP;
import _35_register.dao.impl.MemberDaoImpl_Jdbc;

public class OrderServiceImpl {
	
	private DataSource ds;
	private OrderItemDao oidao;
	private OrderDaoImpl odao;
	private MemberDaoImpl_Jdbc mdao;
	//缺一個
	
	
	public OrderServiceImpl() {
		try {
			Context ctx = new InitialContext();
			ds 	  = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
			oidao = new OrderItemDaoImpl();
			odao  = new OrderDaoImpl();
			mdao  = new MemberDaoImpl_Jdbc();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public void persistOrder(OrderListBeamAP olbAP) {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
		try {
			// 交易開始
			con.setAutoCommit(false);
			// 檢查未付款餘額是否超過限額，並更新未付款餘額
			mdao.setConnection(con);
			mdao.updateUnpaidOrderAmount(olbAP);
			
			// 檢查所有訂單明細所訂購之商品的庫存數量是否足夠
			System.out.println("進入 persistOrder 方法");
			checkStock(olbAP, con);
			System.out.println("檢查商品庫存");
			
			// 儲存訂單
			odao.setConnection(con);
			System.out.println("setConnection位置");
			odao.insertOrder(olbAP);
			System.out.println("儲存訂單");
			con.commit();
		} catch (Exception e) {
			try {
				con.rollback();
				System.out.println("OrderServiceImpl 發生異常，交易回滾.....,原因: " + e.getMessage());
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			// 前一次丟出異常是為了rollback，此處還要再丟處一次異常告知控制器交易失敗。
			 //控制器才會在螢幕顯示交易失敗
			throw new RuntimeException(e); 
		} finally {
			try {
				con.setAutoCommit(true);
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		
	}
	public void checkStock(OrderListBeamAP olbAP, Connection con) {
		Set<OrderItemBeanAP> items = olbAP.getItems();
		oidao.setConnection(con);
		for (OrderItemBeanAP oibAP : items) {
			oidao.updateProductStock(oibAP);
		}
		System.out.println("檢查訂單完成");
	}
	
	public List<OrderListBeamAP> getCustomerOrder(String memberID){
		return odao.getCustomerOrder(memberID);
	}
	
	public OrderListBeamAP getOrder(int orderNo) {
		return odao.getOrder(orderNo);
	}
	

}
