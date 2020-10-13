package _03_CultureAndCreativeShops_CMS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import _03_searchShowInfo.CategoryTable;

public class CultureAndCreativeShops_DAO {

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

//處理 double 轉換 字串空值問題

//	double ParseDouble(String strNumber) {
//		if (strNumber != null && strNumber.length() > 0) {
//			try {
//				return Double.parseDouble(strNumber);
//			} catch (Exception e) {
//				return -1; 
//			}
//		} else
//			return 0;
//	}

//	###create new shop
	public boolean creatNewShop(CultureAndCreativeShop shop) {
		
		try (Connection connection = getDataSource().getConnection();
				PreparedStatement pstmt = connection
						.prepareStatement("INSERT INTO CS_CULTURE_AND_CREATIVE_SHOPS_TABLE (" + "group_type_name, "
								+ "main_type_name, " + "name, " + "represent_image, " + "intro, " + "city_name, "
								+ "address, " + "longitude, " + "latitude, " + "open_time, " + "phone, " + "fax, "
								+ "email, " + "facebook, " + "website, " + "main_type_pk, " + "clicks)"
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");) {

			pstmt.setString(1, shop.getGroupTypeName());
			pstmt.setString(2, shop.getMainTypeName());
			pstmt.setString(3, shop.getName());
			pstmt.setString(4, shop.getRepresentImage());
			pstmt.setString(5, shop.getIntro());

			pstmt.setString(6, shop.getCityName());
			pstmt.setString(7, shop.getAddress());
			pstmt.setDouble(8, Double.parseDouble(shop.getLongitude()));
			pstmt.setDouble(9, Double.parseDouble(shop.getLatitude()));
			pstmt.setString(10, shop.getOpenTime());

			pstmt.setString(11, shop.getPhone());
			pstmt.setString(12, shop.getFax());
			pstmt.setString(13, shop.getEmail());
			pstmt.setString(14, shop.getFacebook());
			pstmt.setString(15, shop.getWebsite());

			pstmt.setInt(16, Integer.parseInt(shop.getMainTypePk()));
			pstmt.setInt(17, Integer.parseInt(shop.getClicks()));

			pstmt.executeUpdate();
			pstmt.clearParameters();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("資料新增失敗");
		}
		System.out.println("商家資料已新增");
		return true;
	}

//	###read shops info 
	public List<CultureAndCreativeShop> searchShops(String str) {

		List<CultureAndCreativeShop> shopsList = new ArrayList();

		// 使用商家名稱，進行模糊搜尋，最後儲存成List型態
		try {
			Connection connection = getDataSource().getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM CS_CULTURE_AND_CREATIVE_SHOPS_TABLE where name like ? ");

			System.out.println(str);
			pstmt.setString(1, "%"+str+"%");
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CultureAndCreativeShop cc_Shop = new CultureAndCreativeShop();

				cc_Shop.setGroupTypeName((rs.getString("Group_Type_Name")));
				cc_Shop.setMainTypeName((rs.getString("Main_Type_Name")));
				cc_Shop.setName((rs.getString("Name")));
				cc_Shop.setRepresentImage((rs.getString("Represent_Image")));
				cc_Shop.setIntro((rs.getString("Intro")));

				cc_Shop.setCityName((rs.getString("City_Name")));
				cc_Shop.setAddress((rs.getString("Address")));
				cc_Shop.setLongitude(Double.toString(rs.getDouble("Longitude")));
				cc_Shop.setLatitude(Double.toString(rs.getDouble("Latitude")));
				cc_Shop.setOpenTime((rs.getString("Open_Time")));

				cc_Shop.setPhone((rs.getString("Phone")));
				cc_Shop.setFax((rs.getString("Fax")));
				cc_Shop.setEmail((rs.getString("Email")));
				cc_Shop.setFacebook((rs.getString("Facebook")));
				cc_Shop.setWebsite((rs.getString("Website")));

				cc_Shop.setMainTypePk(Integer.toString(rs.getInt("Main_Type_Pk")));
				cc_Shop.setClicks(Integer.toString(rs.getInt("Clicks")));

				shopsList.add(cc_Shop);
			}

			rs.close();
			pstmt.clearParameters();
			pstmt.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("資料讀取失敗");
		}
		System.out.println("商家資料已讀取");
		return shopsList;	
	}

	// ###update shop info

	public boolean updateShopInfo(CultureAndCreativeShop shop) {

		try (Connection connection = getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement("UPDATE CS_CULTURE_AND_CREATIVE_SHOPS_TABLE SET "
						+ "name = ? , represent_image = ? , intro = ? ,  city_name = ?, address = ? ,"
						+ "longitude = ? , latitude  = ? , open_time = ? , phone = ? , fax = ? , email = ? ,"
						+ "facebook = ? , website = ? , clicks = ? WHERE main_type_pk = ? ");) {

			pstmt.setString(1, shop.getName());
			pstmt.setString(2, shop.getRepresentImage());
			pstmt.setString(3, shop.getIntro());
			pstmt.setString(4, shop.getCityName());
			pstmt.setString(5, shop.getAddress());
			
			pstmt.setDouble(6, Double.parseDouble(shop.getLongitude()));
			pstmt.setDouble(7, Double.parseDouble(shop.getLatitude()));
			pstmt.setString(8, shop.getOpenTime());
			pstmt.setString(9, shop.getPhone());
			pstmt.setString(10, shop.getFax());
			
			pstmt.setString(11, shop.getEmail());
			pstmt.setString(12, shop.getFacebook());
			pstmt.setString(13, shop.getWebsite());
			pstmt.setInt(14, Integer.parseInt(shop.getClicks()));
			pstmt.setInt(15, Integer.parseInt(shop.getMainTypePk()));

			pstmt.executeUpdate();
			pstmt.clearParameters();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("資料更新失敗");
			return false;
		}
		System.out.println("商家資料已更新");
		return true;
	}

	// ###delete shops info by Name

//	public void deleteShopsInfo(String str) {
//
//		String shopsName = str;
//		CultureAndCreativeShopsTable shop= new CultureAndCreativeShopsTable();
//
//		// 使用商家名稱，進行模糊搜尋，最後儲存成List型態
//		try {
//			Connection connection = getDataSource().getConnection();
//			PreparedStatement pstmt = connection
//					.prepareStatement("DELETE FROM CS_CULTURE_AND_CREATIVE_SHOPS_TABLE where name = ? ");
//
//			pstmt.setString(1, shopsName);
//			pstmt.executeQuery();
//			pstmt.clearParameters();
//			pstmt.close();
//			connection.close();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("資料刪除失敗");
//		}
//		System.out.println("商家資料已刪除失敗");
//	}
	
	
	// ###delete shops info by MainTypePk

	public void deleteShopInfo(int i) {
		
		//依照辨識碼刪除商家資料
		CultureAndCreativeShop shop= new CultureAndCreativeShop();

		try {
			Connection connection = getDataSource().getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("DELETE FROM CS_CULTURE_AND_CREATIVE_SHOPS_TABLE where Main_Type_Pk = ? ");
			
			pstmt.setInt(1, i);
			pstmt.executeQuery();
			pstmt.clearParameters();
			pstmt.close();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("商家資料刪除失敗");
		}
		System.out.println("商家資料刪除成功");
	}
	
	
	
}
