package _03_searchShowInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import _03_searchShowInfo.SingleShowInfoTable;

public class SingleShowInfoDAO {

	private DataSource dataSource;

	// getDataSource連線的方法
	public DataSource getDataSource() {// 只需get因為只要用不希望被改

		// lazy init，能有多晚用到就多晚產生，放constructor會先產出佔用記憶體
		if (dataSource == null) { // 不希望重複產生，
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("group4");
			ds.setPassword("oracle");
			ds.setMaxTotal(50);
			ds.setMaxIdle(50);

			dataSource = ds; // 把BasicDataSource放在屬性上
		}
		return dataSource;
	}

// 使用以下欄位資料
//	ACT_NO;
//	ACT_TITLE;
//	ACT_CATEGORY;
//	ACT_LOCATION_NAME;
//	ACT_PRICE;
//	ACT_MAINUNIT;
//	ACT_SHOWUNIT;
//	ACT_DESCRIPTION;
//	ACT_IMAGE;
//	ACT_STARTDATE;
//	ACT_ENDDATE;

	public SingleShowInfoTable singleShowInfoSearch(int showNO) {

		SingleShowInfoTable singleShowInfo = new SingleShowInfoTable();

		// 以ACT_NO搜尋該筆表演資料
		try {
			Connection connection = getDataSource().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM maintable where act_no = ?");
			pstmt.setInt(1, showNO);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				singleShowInfo.setACT_NO(Integer.toString(rs.getInt("ACT_NO")));
				singleShowInfo.setACT_TITLE(rs.getString("ACT_TITLE"));
				singleShowInfo.setACT_CATEGORY(Integer.toString(rs.getInt("ACT_CATEGORY")));	
				singleShowInfo.setACT_LOCATION_NAME(rs.getString("ACT_LOCATION_NAME"));
				singleShowInfo.setACT_PRICE(rs.getString("ACT_PRICE"));
				singleShowInfo.setACT_MAINUNIT(rs.getString("ACT_MAINUNIT"));
				singleShowInfo.setACT_SHOWUNIT(rs.getString("ACT_SHOWUNIT"));
				singleShowInfo.setACT_DESCRIPTION(rs.getString("ACT_DESCRIPTION"));
				singleShowInfo.setACT_IMAGE(rs.getString("ACT_IMAGE"));
				singleShowInfo.setACT_STARTDATE(rs.getString("ACT_STARTDATE"));
				singleShowInfo.setACT_ENDDATE(rs.getString("ACT_ENDDATE"));
			}
			
			rs.close();
			pstmt.clearParameters();
			pstmt.close();
			connection.close();

		} catch (Exception e) {
//			System.err.println("搜尋活動類別時，發生錯誤:" + e);
			e.printStackTrace();
		}
		return singleShowInfo;
	}

}
