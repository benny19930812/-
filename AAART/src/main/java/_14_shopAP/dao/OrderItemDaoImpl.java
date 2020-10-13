package _14_shopAP.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import _14_shopAP.orderbean.OrderItemBeanAP;
import _14_shopAP.ude.ProductException;

public class OrderItemDaoImpl implements OrderItemDao {
	Connection conn;
	
	public OrderItemDaoImpl() {
		
	}
	
	
	@Override
	public int updateProductStock(OrderItemBeanAP oibAP) {
		
		int n = 0;
		int stock = 0 ;
		String sql10 = "SELECT APNUM FROM ARTPRODUCT WHERE APTITLE = ? " ;
		String sql11 = "UPDATE ARTPRODUCT SET APNUM = APNUM - ? WHERE APTITLE = ?" ;
		try (PreparedStatement ps = conn.prepareStatement(sql10);) {
			ps.setString(1, oibAP.getOrderNameAP());
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					stock = rs.getInt(1);
					if (stock < oibAP.getProductNum()) {
						throw new ProductException("庫存數量不足: BookId: " 
								+ oibAP.getOrderNameAP() + ", 在庫量: " + stock+ ", 訂購量: " + oibAP.getProductNum());
					} else {
						;
					}
					try (PreparedStatement ps1 = conn.prepareStatement(sql11);) {
						ps1.setInt(1, oibAP.getProductNum());
						ps1.setString(2, oibAP.getOrderNameAP());
						n = ps1.executeUpdate();
					}
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderItemDaoImpl類別#updateProductStock()發生SQL例外: " + ex.getMessage());
		}
		return n;
	}
		
	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
		
	}

}
