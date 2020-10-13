package _04_ST;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;



public class SelectDAO {
	private DataSource dataSource;
	private int pageNo = 0; // 存放目前顯示之頁面的編號
	private int recordsPerPage = 100; // 預設值：每頁三筆
	private int totalPages = -1;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	// 設定oracle連線方法
	public DataSource getDataSource() {
		if (dataSource == null) {
			System.out.println("進入連線");
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			//mac jdbcport
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("group4");
			ds.setPassword("oracle");
			ds.setMaxTotal(50); // 設定最多connection上線,超過使用量必須等待
			ds.setMaxIdle(50); // 設定最多idle的connection,超過的connection會被執行connection.close()
			dataSource = ds;
		}
		return dataSource;
	}

	//建一個showlist方法，類型為list
	public List<ShowOj> Showlist() {
		//實作一個arrylist，list為介面
		List<ShowOj> lists = new ArrayList<ShowOj>();
		try (
				Connection conn = getDataSource().getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("select * from maintable ")) {
				//取得所有table內資料 
				//用setxxx將值放入 方便之後用getxxx取值(之後就不用寫SQL語法)
			while (rs.next()) {
				ShowOj show = new ShowOj(); //建一個show物件，ShowOj只是藍圖沒有物件
				show.setACT_NO(rs.getInt("ACT_NO"));
				show.setACT_UID(rs.getString("ACT_UID"));				
				show.setACT_TITLE(rs.getString("ACT_TITLE"));
				show.setACT_CATEGORY(rs.getInt("ACT_CATEGORY"));
				show.setACT_LOCATION(rs.getString("ACT_LOCATION"));
				show.setACT_LOCATION_NAME(rs.getString("ACT_lOCATION_NAME"));
				show.setACT_ON_SALES(rs.getString("ACT_ON_SALES"));
				show.setACT_PRICE(rs.getString("ACT_PRICE"));
				show.setACT_TIME(rs.getString("ACT_TIME"));
				show.setACT_END_TIME(rs.getString("ACT_ENDTIME"));
				show.setACT_MAIN_UNIT(rs.getString("ACT_MAINUNIT"));
				show.setACT_SHOW_UNIT(rs.getString("ACT_SHOWUNIT"));
				show.setACT_COMMENT(rs.getString("ACT_COMMENT"));
				show.setACT_DESCRIPTION(rs.getString("ACT_DESCRIPTION"));
				show.setACT_IMAGE(rs.getString("ACT_IMAGE"));
				show.setACT_START_DATE(rs.getString("ACT_STARTDATE"));
				show.setACT_END_DATE(rs.getString("ACT_ENDDATE"));
				lists.add(show);
				}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return lists;
	}
////模糊查詢
//	//建一個showlist方法，類型為list
//		public List<ShowOj> getSearchlist() {
//			//實作一個arrylist，list為介面
//			List<ShowOj> lists = new ArrayList<ShowOj>();
//			String query = "SELECT ACT_NO,ACT_TITLE,ACT_LOCATION_NAME FROM MAINTABLE " + "WHERE ACT_TITLE LIKE \'%" + "?" + "%\'";
//			try (
//					Connection conn = ds.getConnection();
//					Statement stmt = conn.createStatement();
//					ResultSet rs = stmt.executeQuery(query);
//					) {
//					//取得所有table內資料 
//					//用setxxx將值放入 方便之後用getxxx取值(之後就不用寫SQL語法)
////				request.setAttribute("RS", rs);
////				RequestDispatcher dispatcher = request.getRequestDispatcher("/04_select.jsp");
////				dispatcher.forward(request, response);
//				
//				for (int count = 0;; count++) {
//					if (rs.next()) {
//						String no = rs.getString(1);
//						String title = rs.getString(2);
//						String site = rs.getString(3);
//						System.out.println(no);
//						System.out.println(title);		
//						System.out.println(site);		
//					
//					lists.add();
//					}
//			} catch (SQLException e) {
//				e.printStackTrace();
//				throw new RuntimeException(e);
//			}
//			return lists;
//		}	
//	

}