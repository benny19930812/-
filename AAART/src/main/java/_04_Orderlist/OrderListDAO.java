package _04_Orderlist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import _04_Orderlist.*;
import _04_ST.ShowOj;


public class OrderListDAO {
	
 	private DataSource dataSource;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 設定oracle連線方法
	public DataSource getDataSource() {
		if (dataSource == null) {
			System.out.println("進入連線");
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("group4");
			ds.setPassword("oracle");
			ds.setMaxTotal(50); // 設定最多connection上線,超過使用量必須等待
			ds.setMaxIdle(50); // 設定最多idle的connection,超過的connection會被執行connection.close()
			dataSource = ds;
		}
		return dataSource;
	}
	//建立訂單
	//void 代表什麼都不返回，即無return
	//建立一個addlist
	//Orderlistbean類別的物件-orderlist ex int 5
	public Orderlistbean addUser(Orderlistbean orderlist) {
		
		System.out.println("進入方法");
		String liString = "INSERT INTO ORDERLIST (ORDERID,NAME,EMAIL,TEL,ADDRESS,TOTALPRICE) VALUES (?,?,?,?,?,?)";
		try (
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(liString);
				) {
				pstmt.setString(1, orderlist.getORDERID());
				pstmt.setString(2, orderlist.getNAME());
				pstmt.setString(3, orderlist.getEMAIL());
				pstmt.setString(4, orderlist.getTEL());
				pstmt.setString(5, orderlist.getADDRESS());
				pstmt.setInt(6, orderlist.getTOTALPRICE());
				pstmt.executeUpdate();
				System.out.println("已插入");
				return orderlist;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	
	//void 代表什麼都不返回，即無return
	//建立一個addlist
	//Orderlistbean類別的物件-orderlist ex int 5
	public Orderlistbean addlist(Orderlistbean orderlistbean) {
		
		System.out.println("進入方法");
		String liString = "INSERT INTO ORDERNUM (ORDERID,TITLE,HALFNUM,ADULTNUM) VALUES (?,?,?,?)";
		try (
				Connection conn = getDataSource().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(liString);
				) {
				pstmt.setString(1, orderlistbean.getORDERID());
				pstmt.setString(2, orderlistbean.getTITLE());
				pstmt.setInt(3, orderlistbean.getHALF_NUM());
				pstmt.setInt(4, orderlistbean.getADULT_NUM());
				pstmt.executeUpdate();
				System.out.println("已插入2");
				return orderlistbean;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	//查詢訂單資訊
	//建一個showlist方法，類型為list
		public List<Orderlistbean> GetOrderUser() {
			//實作一個arrylist，list為介面
			List<Orderlistbean> orderlists = new ArrayList<Orderlistbean>();
			try (
					Connection conn = getDataSource().getConnection();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * FROM ORDERLIST ")) {
					//取得所有table內資料 
					//用setxxx將值放入 方便之後用getxxx取值(之後就不用寫SQL語法)
				while (rs.next()) {
					Orderlistbean orderlistbean = new Orderlistbean(); //建一個show物件，ShowOj只是藍圖沒有物件
					orderlistbean.setORDERID(rs.getString("ORDERID"));
					orderlistbean.setNAME(rs.getString("NAME"));
					orderlistbean.setEMAIL(rs.getString("EMAIL"));
					orderlistbean.setTEL(rs.getString("TEL"));
					orderlistbean.setADDRESS(rs.getString("ADDRESS"));
					orderlistbean.setTOTALPRICE(rs.getInt("TOTALPRICE"));
					orderlists.add(orderlistbean);
					}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return orderlists;
		}
		
		//查詢訂單數量
		//建一個showlist方法，類型為list
				public List<Orderlistbean> GetOrderlist() {
					//實作一個arrylist，list為介面
					List<Orderlistbean> orderlists = new ArrayList<Orderlistbean>();
					try (
							Connection conn = getDataSource().getConnection();
							Statement stmt = conn.createStatement();
							ResultSet rs = stmt.executeQuery("select * FROM ORDERNUM ")) {
							//取得所有table內資料 
							//用setxxx將值放入 方便之後用getxxx取值(之後就不用寫SQL語法)
						while (rs.next()) {
							Orderlistbean orderlistbean = new Orderlistbean(); //建一個show物件，ShowOj只是藍圖沒有物件
							orderlistbean.setORDERID(rs.getString("ORDERID"));
							orderlistbean.setTITLE(rs.getString("TITLE"));
							orderlistbean.setHALF_NUM(rs.getInt("HALFNUM"));
							orderlistbean.setADULT_NUM(rs.getInt("ADULTNUM"));
							orderlists.add(orderlistbean);
							}
						
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					return orderlists;
				}
		
		
		
		//訂單編號生成
		public  String getOrderIdByTime() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String newDate = sdf.format(new Date());
			String result = "";
			Random random = new Random();
			for (int i = 0; i < 3; i++) {
				result += random.nextInt(10);
			}
			return newDate + result;
	}
		
	//刪除商品訂單	
		public Orderlistbean deleteorderUSER(Orderlistbean orderlistbean) {
			
			System.out.println("進入刪除訂單方法");
			String liString = "DELETE FROM ORDERLIST WHERE ORDERID=?";
			try (
					Connection conn = getDataSource().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(liString);
					) {
					pstmt.setString(1, orderlistbean.getORDERID());
					pstmt.executeUpdate();
					System.out.println("已刪除");
					return orderlistbean;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}	
		
		
				//刪除商品訂單	
				public Orderlistbean deleteorderlist(Orderlistbean orderlistbean) {
					
					System.out.println("進入刪除訂單方法");
					String liString = "DELETE FROM ORDERNUM WHERE ORDERID=?";
					try (
							Connection conn = getDataSource().getConnection();
							PreparedStatement pstmt = conn.prepareStatement(liString);
							) {
							pstmt.setString(1, orderlistbean.getORDERID());
							pstmt.executeUpdate();
							System.out.println("已刪除");
							return orderlistbean;
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}	
				
				//修改訂單資訊	
				public int updateOrderuser(Orderlistbean orderlistbean) {
					int n =0;
					System.out.println("進入修改方法");
					String liString = "UPDATE ORDERLIST SET NAME=? ,EMAIL=? ,TEL=? ,ADDRESS=? WHERE ORDERID=?";
					try (
							Connection conn = getDataSource().getConnection();
							PreparedStatement pstmt = conn.prepareStatement(liString);
							) {
						pstmt.setString(1, orderlistbean.getNAME());
						pstmt.setString(2, orderlistbean.getEMAIL());
						pstmt.setString(3, orderlistbean.getTEL());
						pstmt.setString(4, orderlistbean.getADDRESS());
						pstmt.setString(5, orderlistbean.getORDERID());
						System.out.println(orderlistbean.getNAME());
						System.out.println(orderlistbean.getEMAIL());
						System.out.println(orderlistbean.getNAME());
						System.out.println(orderlistbean.getNAME());
						System.out.println(orderlistbean.getNAME());
						
						n= pstmt.executeUpdate();
						
						System.out.println("已更新訂單");
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
					return n;
				}	
		
		
}
