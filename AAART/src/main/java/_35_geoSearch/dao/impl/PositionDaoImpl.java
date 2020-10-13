package _35_geoSearch.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import _35_geoSearch.model.PositionBean;
import _35_init.util.DataSourceConn;
import _35_geoSearch.model.MainTableBean;

public class PositionDaoImpl {
	
	public List<PositionBean> readAllToPT() {
		List<PositionBean> list = new ArrayList<PositionBean>();//建立空arraylist放置增添的物件	
		try (	Connection connection = DataSourceConn.getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select * from POSITION");//執行sql查詢				
				){
				
			while( rs.next() ) {
				PositionBean pt = new PositionBean();
				pt.setNo(rs.getInt("NO"));
				pt.setUid(rs.getString("ID"));
				pt.setCity(rs.getString("CITY"));
				pt.setDistrict(rs.getString("DISTRICT"));
				pt.setVillage(rs.getString("VILLAGE"));
				pt.setAddress(rs.getString("ADDRESS"));
				pt.setLatitude(rs.getDouble("LATITUDE"));
				pt.setLongitude(rs.getDouble("LONGITUDE"));
				list.add(pt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("已讀取資料庫Position表格所有資料為PositionBean");
		return list;
	}
	
	public List<MainTableBean>  readNoTitleUnitToMT() {
		List<MainTableBean> list = new ArrayList<MainTableBean>();//建立空arraylist放置增添的物件	
		try (	Connection connection = DataSourceConn.getDataSource().getConnection();
				Statement stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery("select ACT_NO, ACT_TITLE, ACT_TIME from MAINTABLE");//執行sql查詢				
				){
				
			while( rs.next() ) {
				MainTableBean mt = new MainTableBean();
				mt.setNo(rs.getInt("ACT_NO"));
				mt.setTitle(rs.getString("ACT_TITLE"));
				mt.setTime(rs.getString("ACT_TIME"));
				list.add(mt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("已讀取資料庫ACT_NO,ACT_TITLE及ACT_TIME欄位為MainTableBean");
		return list;
	}
}
