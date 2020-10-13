package _03_input;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CulturalAndCreativeShops_Input {

	private DataSource dataSource;

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
	
	public void createTableForDB(){
		try (Connection connection = getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
		     
		    String sql = "CREATE TABLE CS_CULTURE_AND_CREATIVE_SHOPS_TABLE ( GROUP_TYPE_NAME VARCHAR2(1000 BYTE), "
		    		+ "MAIN_TYPE_NAME VARCHAR2(1000 BYTE), NAME VARCHAR2(1000 BYTE), "
		    		+ "REPRESENT_IMAGE VARCHAR2(2000 BYTE), INTRO VARCHAR2(4000 BYTE), "
		    		+ "CITY_NAME VARCHAR2(4000 BYTE), ADDRESS VARCHAR2(2000 BYTE), LONGITUDE NUMBER(25,20), "
		    		+ "LATITUDE NUMBER(25,20), OPEN_TIME VARCHAR2(1000 BYTE), PHONE VARCHAR2(1000 BYTE), "
		    		+ "FAX VARCHAR2(1000 BYTE), EMAIL VARCHAR2(1000 BYTE), FACEBOOK VARCHAR2(1000 BYTE), "
		    		+ "WEBSITE VARCHAR2(1000 BYTE), MAIN_TYPE_PK NUMBER(8,0), CLICKS NUMBER(8,0) )";
		    
		    stmt.executeUpdate(sql);
		    System.out.println("商店表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<CultureAndCreativeShopsTable> readJsonToTable() {

		ArrayList<CultureAndCreativeShopsTable> list = new ArrayList<CultureAndCreativeShopsTable>();

		try (InputStream is = new FileInputStream("inputJSON/culture and creative shops.json");
				InputStreamReader isr = new InputStreamReader(is, "UTF8");
				BufferedReader br = new BufferedReader(isr);) {
			int c;
			StringBuilder strBuilder = new StringBuilder();
			while ((c = br.read()) != -1) {
				char d = (char) c;
				strBuilder.append(String.valueOf(d));
			}

			String tableStr = String.valueOf(strBuilder);
//			System.out.println(tableStr);

			Gson gson = new Gson();
			Type listType = new TypeToken<ArrayList<CultureAndCreativeShopsTable>>() {
			}.getType();

			ArrayList<CultureAndCreativeShopsTable> jsonArr = gson.fromJson(tableStr, listType);

			for (CultureAndCreativeShopsTable item : jsonArr) {
				CultureAndCreativeShopsTable shops = new CultureAndCreativeShopsTable();

				shops.setGroupTypeName(item.getGroupTypeName());
				shops.setMainTypeName(item.getMainTypeName());
				shops.setName(item.getName());
				shops.setRepresentImage(item.getRepresentImage());
				shops.setIntro(item.getIntro());

				shops.setCityName(item.getCityName());
				shops.setAddress(item.getAddress());
				shops.setLongitude(item.getLongitude());
				shops.setLatitude(item.getLatitude());
				shops.setOpenTime(item.getOpenTime());

				shops.setPhone(item.getPhone());
				shops.setFax(item.getFax());
				shops.setEmail(item.getEmail());
				shops.setFacebook(item.getFacebook());
				shops.setWebsite(item.getWebsite());

				shops.setMainTypePk(item.getMainTypePk());
				shops.setClicks(item.getClicks());

				list.add(shops);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("已成功轉換json");
		return list;

	}

	double ParseDouble(String strNumber) {
		if (strNumber != null && strNumber.length() > 0) {
			try {
				return Double.parseDouble(strNumber);
			} catch (Exception e) {
				return -1; // or some value to mark this field is wrong. or make a function validates field
							// first ...
			}
		} else
			return 0;
	}

	public void creatShopsInfoTable(ArrayList<CultureAndCreativeShopsTable> list) {

		try (Connection connection = getDataSource().getConnection();
				PreparedStatement pstmt = connection
						.prepareStatement("INSERT INTO CS_CULTURE_AND_CREATIVE_SHOPS_TABLE (" + "group_type_name, "
								+ "main_type_name, " + "name, " + "represent_image, " + "intro, " + "city_name, "
								+ "address, " + "longitude, " + "latitude, " + "open_time, " + "phone, " + "fax, "
								+ "email, " + "facebook, " + "website, " + "main_type_pk, " + "clicks)"
								+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");) {

			for (CultureAndCreativeShopsTable shop : list) {
				pstmt.setString(1, shop.getGroupTypeName());
				pstmt.setString(2, shop.getMainTypeName());
				pstmt.setString(3, shop.getName());
				pstmt.setString(4, shop.getRepresentImage());
				pstmt.setString(5, shop.getIntro());

				pstmt.setInt(16, Integer.parseInt(shop.getMainTypePk()));
				pstmt.setInt(17, Integer.parseInt(shop.getClicks()));

				pstmt.setString(6, shop.getCityName());
				pstmt.setString(7, shop.getAddress());
				pstmt.setDouble(8, ParseDouble(shop.getLongitude()));
				pstmt.setDouble(9, ParseDouble(shop.getLatitude()));
				pstmt.setString(10, shop.getOpenTime());

				pstmt.setString(11, shop.getPhone());
				pstmt.setString(12, shop.getFax());
				pstmt.setString(13, shop.getEmail());
				pstmt.setString(14, shop.getFacebook());
				pstmt.setString(15, shop.getWebsite());
				pstmt.executeUpdate();
				pstmt.clearParameters();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("商家資料已新增");
	}
}
