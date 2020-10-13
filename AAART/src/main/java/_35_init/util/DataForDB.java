package _35_init.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import _35_geoSearch.model.PositionBean;
import _35_geoSearch.model.MainTableBean;

public class DataForDB {
	
	//讀取網路上的json檔為MainTableBean型態
	public List<MainTableBean> readJsonToMT() {
		
		List<MainTableBean> list = new ArrayList<MainTableBean>();
//		try (InputStream is = new URL("https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&category=all").openStream();
		try (FileInputStream fis = new FileInputStream("inputJSON/1.json");
				InputStreamReader isr = new InputStreamReader(fis, "UTF8");
				BufferedReader br = new BufferedReader(isr);	
				) {
			int c;
			StringBuilder strBuilder = new StringBuilder();
			while ((c = br.read())!=-1) {
				char d =(char)c;
				strBuilder.append(String.valueOf(d));
			}
			
			String tableStr= String.valueOf(strBuilder);
//			若資料不乾淨要用下面方法篩選符號
//			System.out.println(tableStr);
//			String result1 = tableStr.replaceAll("\\\\", "");
//			String result2 = result1.replaceAll(Matcher.quoteReplacement("$"), "");
//			String result3 = result2.substring(1, result2.length()-1);
//			System.out.println(result3);
			
			Gson gson = new Gson();
			Type listType = new TypeToken<List<MainTableBean>>() {}.getType();
			List<MainTableBean> jsonArr = gson.fromJson(tableStr,listType);
			
			int counter = 0;
			for (MainTableBean item: jsonArr) {
				MainTableBean mt = new MainTableBean();
				counter++;
				mt.setNo(counter);
				mt.setUid(item.getUid());
				mt.setTitle(item.getTitle());
				mt.setCategory(item.getCategory());
//				System.out.println("UID:"+ item.getUid());
//				System.out.println("title:"+ item.getTitle());
//				System.out.println("category:"+ item.getCategory());
				
//				"showInfo"
//				System.out.println("List<HashMap> showInfo:"+ item.getShowInfo());
				HashMap hashMap1= new HashMap();
				for (int i=0 ; i < item.getShowInfo().size(); i++) {
					hashMap1 = item.getShowInfo().get(i);										
				}
				mt.setLocation(String.valueOf(hashMap1.get("location")));
				mt.setLocationName(String.valueOf(hashMap1.get("locationName")));
				mt.setOnSales(String.valueOf(hashMap1.get("onSales")));
				mt.setPrice(String.valueOf(hashMap1.get("price")));
				mt.setTime(String.valueOf(hashMap1.get("time")));
				mt.setEndTime(String.valueOf(hashMap1.get("endTime")));
//				System.out.println("location:"+hashMap1.get("location"));
//				System.out.println("locationName:"+hashMap1.get("locationName"));
//				System.out.println("onSales:"+hashMap1.get("onSales"));
//				System.out.println("price:"+hashMap1.get("price"));
//				System.out.println("time:"+hashMap1.get("time"));
//				System.out.println("endTime:"+hashMap1.get("endTime"));

//				"masterUnit"
//				System.out.println("List<String> masterUnit:"+ item.getMasterUnit());
				String str1= "";
				for (int i=0 ; i < item.getMasterUnit().size(); i++) {
					str1 = item.getMasterUnit().get(i);										
				}
				mt.setMainUnit(str1);
//				System.out.println("masterUnit:"+str1);
				
				mt.setShowUnit(item.getShowUnit());
				mt.setComment(item.getComment());
				mt.setDescriptionFilterHtml(item.getDescriptionFilterHtml());
				mt.setImageUrl(item.getImageUrl());				
//				System.out.println("showUnit:"+ item.getShowUnit());
//				System.out.println("comment:"+ item.getComment());
//				System.out.println("descriptionFilterHtml:"+ item.getDescriptionFilterHtml());
//				System.out.println("imageUrl:"+ item.getImageUrl());
				
				mt.setStartDate(item.getStartDate());
				mt.setEndDate(item.getEndDate());
//				System.out.println("startDate:"+ item.getStartDate());
//				System.out.println("endDate:"+ item.getEndDate());
				list.add(mt);
			}
			
//			int counter=0;
//			for (MainTable item: list) {
//				counter++;
//				System.out.println(item.getLocation());
//			}
//			System.out.println( counter);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("已讀取文化部json檔為MainTableBean");
		return list;
	}
	
	//MainTableBean塞進資料庫
	public void mtWriteDB(List<MainTableBean> list) {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement("insert into MainTable (ACT_NO, ACT_UID, ACT_TITLE, ACT_CATEGORY,  ACT_LOCATION, ACT_LOCATION_NAME, ACT_ON_SALES, ACT_PRICE, ACT_TIME, ACT_ENDTIME, ACT_MAINUNIT, ACT_SHOWUNIT, ACT_COMMENT, ACT_DESCRIPTION, ACT_IMAGE, ACT_STARTDATE, ACT_ENDDATE) values (?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?,?)");
				){
		
			for (MainTableBean item: list) {
				pstmt.setInt(1, item.getNo());
				pstmt.setString(2, item.getUid());
				pstmt.setString(3, item.getTitle());
				pstmt.setInt(4, item.getCategory());
				pstmt.setString(5, item.getLocation());
				pstmt.setString(6, item.getLocationName());
				pstmt.setString(7, item.getOnSales());
				pstmt.setString(8, item.getPrice());
				pstmt.setString(9, item.getTime());
				pstmt.setString(10, item.getEndTime());
				pstmt.setString(11, item.getMainUnit());
				pstmt.setString(12, item.getShowUnit());
				pstmt.setString(13, item.getComment());
				if(item.getDescriptionFilterHtml().length()<1500) {
					pstmt.setString(14, item.getDescriptionFilterHtml());
				}else {
					pstmt.setString(14, "");
				}
				pstmt.setString(15, item.getImageUrl());
				pstmt.setString(16, item.getStartDate());
				pstmt.setString(17, item.getEndDate());
				pstmt.addBatch();//加入批次
				pstmt.clearParameters();//清除參數再加入下一物件的值
			}
			pstmt.executeBatch();
			pstmt.clearBatch();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("MainTableBean已寫入資料庫");
		
	}
	
	//取得MainTable的編號與地址
	public List<MainTableBean> readLocToMT() {
		 
		List<MainTableBean> list = new ArrayList<MainTableBean>();//建立空arraylist放置增添的物件	
		try (	Connection connection = DataSourceConn.getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select ACT_NO, ACT_LOCATION from MAINTABLE");//執行sql查詢				
				){
				
			while( rs.next() ) {
				MainTableBean mt = new MainTableBean();
				mt.setNo(rs.getInt("ACT_NO"));
				mt.setLocation(rs.getString("ACT_LOCATION"));
				list.add(mt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("已讀取資料庫ACT_NO及ACT_LOCATION欄位為MainTableBean");
		return list;
	}
	
	//取得MainTable的編號與UID
	public List<MainTableBean> readUIDtoMT() {
		 
		List<MainTableBean> list = new ArrayList<MainTableBean>();//建立空arraylist放置增添的物件	
		try (	Connection connection = DataSourceConn.getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select ACT_NO, ACT_UID from MAINTABLE");//執行sql查詢				
				){
				
			while( rs.next() ) {
				MainTableBean mt = new MainTableBean();
				mt.setNo(rs.getInt("ACT_NO"));
				mt.setUid(rs.getString("ACT_UID"));
				list.add(mt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("已讀取資料庫ACT_NO及ACT_UID欄位為MainTableBean");
		return list;
	}
	
	//寫入資料到Position表格
	//除了傳入從csv檔轉來的PositionBean
	//因為同時要寫入MainTable的編號所以傳入MainTableBean
	public void ptmtWriteDB(List<PositionBean> listPT, List<MainTableBean> listMT) {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();
				PreparedStatement pstmt = connection.prepareStatement("insert into Position (NO, ID, CITY, DISTRICT, VILLAGE, ADDRESS, LATITUDE, LONGITUDE) values (?, ?, ?, ?, ?, ?, ?, ?)");
				){
			
			for (int i=0; i<listPT.size();i++) {
		
				pstmt.setInt(1, listPT.get(i).getNo());
				for (MainTableBean item: listMT) {	//每次參數2前，都要遍歷找尋MT內符合相對應ACT_NO的ACT_UID
					if (listPT.get(i).getNo()==item.getNo()) {
						pstmt.setString(2, item.getUid());
						break;
					}
				}
				
				pstmt.setString(3, listPT.get(i).getCity());
				pstmt.setString(4, listPT.get(i).getDistrict());
				pstmt.setString(5, listPT.get(i).getVillage());
				pstmt.setString(6, listPT.get(i).getAddress());
				pstmt.setDouble(7, listPT.get(i).getLatitude());
				pstmt.setDouble(8, listPT.get(i).getLongitude());
				pstmt.addBatch();//加入批次
				pstmt.clearParameters();//清除參數再加入下一物件的值
			}
			pstmt.executeBatch();
			pstmt.clearBatch();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("PositionBean已寫入資料庫");
		
	}
	
	//MT轉字串，為了輸出要上傳內政部做經緯度表格的csv檔
	public String mtToString() {
		
		List<MainTableBean> list = this.readLocToMT();
		
		StringBuilder sb = new StringBuilder();
		sb.append("ID(非必填),縣市(必填),鄉鎮(必填),村里(可不填),鄰(可不填),地址(必填)\n");
		String addressStr="";
		for(MainTableBean item: list) {
			addressStr = item.getLocation();
			if (addressStr!=null) {
				if (addressStr.contains(",")){
					addressStr  = item.getLocation().replace(",", "、");
				}
				if (addressStr.regionMatches(0, "202", 0, 3)){
					sb.append(item.getNo()+","+addressStr.substring(4,7)+","+addressStr.substring(7,10)+","+","+","+addressStr.substring(10)+"\n");
				}else if (addressStr.regionMatches(0, "74彰", 0, 3)) {
					sb.append(item.getNo()+","+addressStr.substring(2,5)+","+addressStr.substring(5,8)+","+","+","+addressStr.substring(8)+"\n");
				}else if (addressStr.charAt(0) >= 48 && addressStr.charAt(0) <= 57 && addressStr.indexOf("區") == 9 && addressStr.length() > 10) {
					sb.append(item.getNo()+","+addressStr.substring(5,8)+","+addressStr.substring(8,10)+","+","+","+addressStr.substring(10)+"\n");
				}else if (addressStr.charAt(0) >= 48 && addressStr.charAt(0) <= 57 && addressStr.length() > 11) {
					sb.append(item.getNo()+","+addressStr.substring(5,8)+","+addressStr.substring(8,11)+","+","+","+addressStr.substring(11)+"\n");
				}else if (addressStr.length() > 6 && addressStr.indexOf("區") == 4){
					sb.append(item.getNo()+","+addressStr.substring(0,3)+","+addressStr.substring(3,5)+","+","+","+addressStr.substring(5)+"\n");
				}else if (addressStr.length() > 6){
					sb.append(item.getNo()+","+addressStr.substring(0,3)+","+addressStr.substring(3,6)+","+","+","+addressStr.substring(6)+"\n");
				}else if (addressStr.length() > 5){
					sb.append(item.getNo()+","+addressStr.substring(0,3)+","+addressStr.substring(3,6)+","+","+","+"\n");
				}else if (addressStr.length() > 4){
					sb.append(item.getNo()+","+addressStr.substring(0,3)+","+addressStr.substring(3,5)+","+","+","+"\n");
				}else {
					sb.append(item.getNo()+","+","+","+","+","+"\n");
				}
			}else {
				sb.append(item.getNo()+","+","+","+","+","+"\n");
			}
			
		}
		String str = String.valueOf(sb);
		System.out.println("MainTableBean已轉換為字串物件");
		return str;
		
	}
	
	//字串寫入c要上傳內政部做經緯度表格的csv檔
	public void stringWriteCSV() {

//		FileOutputStream某參數設定true，資料會從覆蓋寫入變成插入
		try( FileOutputStream fos = new FileOutputStream("outputCSV/1.csv");
				OutputStreamWriter osw = new OutputStreamWriter(fos, "BIG5");
				BufferedWriter bw = new BufferedWriter(osw);
				){
			
			String string = mtToString();
			bw.write(string);
			
		} catch (FileNotFoundException e) { //file not found時的exception			
			e.printStackTrace();
		} catch (IOException e) { //close時會丟出的exception
			e.printStackTrace();
		}
		System.out.println("字串物件已寫入至1.csv檔");

	}
	
	//讀取內政部轉換下來的csv檔，轉成List<PositionBean>資料型態
	public List<PositionBean> readCSVtoPT() {

		List<PositionBean> list = new ArrayList<PositionBean>();
//		FileOutputStream某參數設定true，資料會從覆蓋寫入變成插入
		try( FileInputStream fis = new FileInputStream("inputCSV/2.csv");
				InputStreamReader isr = new InputStreamReader(fis, "BIG5");
				BufferedReader br = new BufferedReader(isr);
				){
			
			StringBuilder sb = new StringBuilder();
			int c; //BufferedReader.read()回傳的是int型態，先建變數接
			while ((c=br.read())!=-1) {	
				char d=(char)c;  //ASCII型態的數字轉成位元代碼
				sb.append(String.valueOf(d)); //位元代碼轉人類看的字串
			}
			
			String str =String.valueOf(sb);
			String[] strArr = str.split("[,\n\r]");
//			for (String item: strArr) {
//				System.out.println(item);
//			}
			
//			for (int i=13; i<strArr.length; i+=13) { //Win
			for (int i=12; i<strArr.length; i+=12) { //Mac
				PositionBean pt = new PositionBean();
				if(strArr[i+8].equals("門牌比對失敗或格式有誤")) {
					pt.setNo(Integer.valueOf(strArr[i]));
					pt.setCity(null);
					pt.setDistrict(null);
					pt.setVillage(null);
					pt.setAddress(null);
					pt.setLatitude(Double.valueOf(0));
					pt.setLongitude(Double.valueOf(0));	
				}else {
					pt.setNo(Integer.valueOf(strArr[i]));
					pt.setCity(strArr[i+1]);
					pt.setDistrict(strArr[i+2]);
					pt.setVillage(strArr[i+3]);
					pt.setAddress(strArr[i+4]);
					pt.setLatitude(Double.valueOf(strArr[i+10]));
					pt.setLongitude(Double.valueOf(strArr[i+9]));	
				}
				list.add(pt);
			}
			
		} catch (FileNotFoundException e) { //file not found時的exception			
			e.printStackTrace();
		} catch (IOException e) { //close時會丟出的exception
			e.printStackTrace();
		}
		System.out.println("已讀取滿滿經緯度資訊的2.csv檔為PositionBean");
		return list;
		
	}

}
