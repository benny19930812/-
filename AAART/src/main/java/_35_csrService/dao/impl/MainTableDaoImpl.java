package _35_csrService.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import _35_csrService.model.MainTableBean;
import _35_init.util.DataSourceConn;

public class MainTableDaoImpl {
	
	private int actCategory;
	
	public int getActCategory() {
		return actCategory;
	}

	public void setActCategory(int actCategory) {
		this.actCategory = actCategory;
	}

	//按藝文類別選取所有資訊
	public List<MainTableBean> selectDBtoMTbyCat() {
		 
		List<MainTableBean> list = new ArrayList<MainTableBean>();//建立空arraylist放置增添的物件	
		try (	Connection connection = DataSourceConn.getDataSource().getConnection();
				){
			PreparedStatement pstmt = connection.prepareStatement("select * from MAINTABLE where ACT_CATEGORY=?");
			pstmt.setInt(1, actCategory);
			ResultSet rs = pstmt.executeQuery();				
				
			while( rs.next() ) {
				MainTableBean mt = new MainTableBean();
				mt.setNo(rs.getInt("ACT_NO"));
				mt.setTitle(rs.getString("ACT_TITLE"));
				mt.setCategory(rs.getInt("ACT_CATEGORY"));
				mt.setLocation(rs.getString("ACT_LOCATION"));
				mt.setLocationName(rs.getString("ACT_LOCATION_NAME"));
				mt.setOnSales(rs.getString("ACT_ON_SALES"));
				mt.setPrice(rs.getString("ACT_PRICE"));
				mt.setTime(rs.getString("ACT_Time"));
				mt.setEndTime(rs.getString("ACT_EndTime"));
				mt.setMainUnit(rs.getString("ACT_MAINUNIT"));
				mt.setShowUnit(rs.getString("ACT_SHOWUNIT"));
				mt.setComment(rs.getString("ACT_COMMENT"));
				mt.setDescriptionFilterHtml(rs.getString("ACT_DESCRIPTION"));
				mt.setImageUrl(rs.getString("ACT_IMAGE"));
				mt.setStartDate(rs.getString("ACT_STARTDATE"));
				mt.setEndDate(rs.getString("ACT_ENDDATE"));
				list.add(mt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("已按活動類別讀取資料庫為MainTable物件");
		return list;
	}
	
	//選取所有資訊
	public List<MainTableBean> selectDBtoMT() {
		 
		List<MainTableBean> list = new ArrayList<MainTableBean>();//建立空arraylist放置增添的物件	
		try (	Connection connection = DataSourceConn.getDataSource().getConnection();
				){
			PreparedStatement pstmt = connection.prepareStatement("select * from MAINTABLE");
			ResultSet rs = pstmt.executeQuery();				
				
			while( rs.next() ) {
				MainTableBean mt = new MainTableBean();
				mt.setNo(rs.getInt("ACT_NO"));
				mt.setTitle(rs.getString("ACT_TITLE"));
				mt.setCategory(rs.getInt("ACT_CATEGORY"));
				mt.setLocation(rs.getString("ACT_LOCATION"));
				mt.setLocationName(rs.getString("ACT_LOCATION_NAME"));
				mt.setOnSales(rs.getString("ACT_ON_SALES"));
				mt.setPrice(rs.getString("ACT_PRICE"));
				mt.setTime(rs.getString("ACT_Time"));
				mt.setEndTime(rs.getString("ACT_EndTime"));
				mt.setMainUnit(rs.getString("ACT_MAINUNIT"));
				mt.setShowUnit(rs.getString("ACT_SHOWUNIT"));
				mt.setComment(rs.getString("ACT_COMMENT"));
				mt.setDescriptionFilterHtml(rs.getString("ACT_DESCRIPTION"));
				mt.setImageUrl(rs.getString("ACT_IMAGE"));
				mt.setStartDate(rs.getString("ACT_STARTDATE"));
				mt.setEndDate(rs.getString("ACT_ENDDATE"));
				list.add(mt);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("已讀取所有資料庫內容為MainTable物件");
		return list;
	}
	
}
