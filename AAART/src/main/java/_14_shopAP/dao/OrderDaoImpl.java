package _14_shopAP.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _14_init.OracleSQL;
import _14_init.glob.GlobalService;
import _14_shopAP.orderbean.OrderItemBeanAP;
import _14_shopAP.orderbean.OrderListBeamAP;

public class OrderDaoImpl {
	private String memberId = null;
	private Connection con;
	int orderNo = 0;
	
	public void insertOrder(OrderListBeamAP olbAP) {
		
		
		//資料庫欄位待改
		String sqlOrder = "Insert Into ORDERSAP "
				+ " ( memberId, shippingAddress,"
				+ " BNO, InvoiceTitle, orderDate) "
				+ " values(?, ?, ?, ?, ?) ";

		String sqlItem = "Insert Into ORDERITEMSAP (ordersAPNo,productTitleAP,"
				+ " productNumAP, productPriceAP) "
				+ " values(?, ?, ?, ?) ";

		ResultSet generatedKeys = null;

		String generatedColumns[] = {"ORDERNOAP"};
		try (
			PreparedStatement ps = con.prepareStatement(sqlOrder,
					generatedColumns);
		) {
			ps.setString(1, olbAP.getMemberID());
			ps.setString(2, olbAP.getAddAP());
			ps.setString(3, olbAP.getBnoAP());
			ps.setString(4, olbAP.getOrderTitleAP());
			Timestamp ts = new Timestamp(olbAP.getDate().getTime());
			ps.setTimestamp(5, ts);
			
			ps.executeUpdate();
			System.out.println("OrderDaoImpl的executeUpdate");
			int id = 0;
			// 取回剛才新增之訂單的主鍵值
			generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next()) {
				id = generatedKeys.getInt(1);
			} else {
				throw new RuntimeException("OrderDaoImpl類別#insertOrder()無法取得新增之orders表格的主鍵");
			}
            int n = 0 ; 
            System.out.println("olb.getItems()"+olbAP.getItems());
            System.out.println("con.prepareStatement(sqlItem)"+con.prepareStatement(sqlItem));
			Set<OrderItemBeanAP> items = olbAP.getItems();
			try (PreparedStatement ps2 = con.prepareStatement(sqlItem);) {
				for (OrderItemBeanAP oib : items) {
					 //下列四個敘述為交易測試而編寫
					 n++;
//					 if (n > 1) {
//					 	  System.out.println("發生例外 n>2");
//					 	  throw new RuntimeException("JDBC交易測試用");
//					 }
					 System.out.println("OrderItemBeanAP="+oib.getOrderNameAP());
					 System.out.println("主鍵值= "+generatedKeys.getInt(1));
					ps2.setInt(1, id);
//					ps2.setInt(2, oib.getOrderIdAP());
					ps2.setString(2, oib.getOrderNameAP());
					ps2.setDouble(3, oib.getProductNum());
					ps2.setString(4, oib.getProductPrice());
					
					ps2.executeUpdate();
					System.out.println("ps2.setString");
					ps2.clearParameters();
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#insertOrder()發生SQL例外: " + ex.getMessage());
		}
	
	}
	
	
	public void setConnection(Connection con) {
		this.con = con;
	}
	
	
	public List<OrderListBeamAP> getCustomerOrder(String memberID){
		System.out.println("memberID= "+memberID);
		DataSource ds = null;
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}
		List<OrderListBeamAP> list = new ArrayList<OrderListBeamAP>();
		String sql = "SELECT ORDERNOAP FROM ORDERSAP where memberId = ? Order by orderDate desc ";
		try (
				Connection con = ds.getConnection();
				PreparedStatement ps = con.prepareStatement(sql);
			) {
				ps.setString(1, memberID);
				try (
					ResultSet rs = ps.executeQuery();
				) {
					while (rs.next()) {
						Integer no = rs.getInt(1);
						
						list.add(getOrder(no));
					}
				}
		} catch(SQLException ex){
			throw new RuntimeException(ex);
		}
		return list;
		
	}
	
	
	
	public OrderListBeamAP getOrder(int orderNoAP) {
		OrderListBeamAP ob = null;
		DataSource ds = null;
		Set<OrderItemBeanAP> set = null;
		System.out.println("方法內的orderNo="+orderNoAP);
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-1發生例外: " + ex.getMessage());
		}

		String sql = "SELECT * FROM ORDERSAP WHERE ORDERNOAP = ? ";
		String sql1 = "SELECT * FROM ORDERITEMSAP WHERE ORDERSAPNO = ? ";
		try (
			Connection con = ds.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			PreparedStatement ps1 = con.prepareStatement(sql1);
		) {
			ps.setInt(1, orderNoAP);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				if (rs.next()) {
					Integer no = rs.getInt("ORDERNOAP");
					//String cancel = rs.getString("cancelTag");
					String id = rs.getString("MEMBERID");
					String bno = rs.getString("BNO");
					String title = rs.getString("INVOICETITLE");
					int totalAmount =rs.getInt("TOTAL");
					Timestamp orderDate = rs.getTimestamp("ORDERDATE");
					String shipAddr = rs.getString("SHIPPINGADDRESS");
					ob = new OrderListBeamAP(no, id, shipAddr, bno, title,totalAmount, orderDate, null);
				}
			}
			ps1.setInt(1, orderNoAP);
			try (
				ResultSet rs = ps1.executeQuery();
			) {
				set = new HashSet<>();
				while (rs.next()) {
					int orderIdAP = rs.getInt("ORDERIDAP");
					int ordersAPNo = rs.getInt("ORDERSAPNO");
					String orderNameAP = rs.getString("PRODUCTTITLEAP");
					int productNum = rs.getInt("PRODUCTNUMAP");
					String productPrice = rs.getString("PRODUCTPRICEAP");
					
					OrderItemBeanAP oi = new OrderItemBeanAP(orderIdAP, ordersAPNo, orderNameAP, productNum, productPrice);
					set.add(oi);
				}
				ob.setItems(set);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("OrderDaoImpl類別#getOrder()-2發生例外: " + ex.getMessage());
		}
		return ob;
	}
	

}
