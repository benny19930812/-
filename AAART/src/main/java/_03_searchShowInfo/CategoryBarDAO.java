package _03_searchShowInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import _03_searchShowInfo.CategoryTable;

public class CategoryBarDAO {

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

	// 依活動類別搜尋找出以下欄位資料
	// Act_NO
	// Act_CATEGORY
	// Act_TITLE
	// Act_DESCRIPTION
	// Act_STARTDATE
	// Act_ENDDATE

	public List<CategoryTable> categoryBarSearch(int categoryId) {

		List<CategoryTable> categoryTable_20 = new ArrayList();

		// 搜尋同分類(categoryId)的20筆資料，並以時間排序(降冪)，最後儲存成List型態
		try {
			Connection connection = getDataSource().getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"SELECT * FROM (SELECT * FROM maintable where act_category = ? ORDER BY act_startdate DESC) WHERE ROWNUM <= 20");
			pstmt.setInt(1, categoryId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CategoryTable categoryBarSearch = new CategoryTable();
				categoryBarSearch.setACT_NO(Integer.toString(rs.getInt("ACT_NO")));
				categoryBarSearch.setACT_CATEGORY(Integer.toString(rs.getInt("ACT_CATEGORY")));
				categoryBarSearch.setACT_TITLE(rs.getString("ACT_TITLE"));
				categoryBarSearch.setACT_DESCRIPTION(rs.getString("ACT_DESCRIPTION"));
				categoryBarSearch.setACT_STARTDATE(rs.getString("ACT_STARTDATE"));
				categoryBarSearch.setACT_ENDDATE(rs.getString("ACT_ENDDATE"));
				categoryTable_20.add(categoryBarSearch);
			}
			
			rs.close();
			pstmt.clearParameters();
			pstmt.close();
			connection.close();

		} catch (Exception e) {
//			System.err.println("搜尋活動類別時，發生錯誤:" + e);
			e.printStackTrace();
		}
		return categoryTable_20;
	}

}
