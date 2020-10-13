package _35_init.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTableForDB {
	
	//創MainTable表格
	public void createTableMT() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "CREATE TABLE MAINTABLE (ACT_NO NUMBER(8,2), ACT_UID VARCHAR2(1000), ACT_TITLE VARCHAR2(1000), ACT_CATEGORY NUMBER(8,2),  ACT_LOCATION VARCHAR2(1000), ACT_LOCATION_NAME VARCHAR2(1000), ACT_ON_SALES VARCHAR2(1000), ACT_PRICE VARCHAR2(4000), ACT_TIME VARCHAR2(1000), ACT_ENDTIME VARCHAR2(1000), ACT_MAINUNIT VARCHAR2(1000), ACT_SHOWUNIT VARCHAR2(1000), ACT_COMMENT VARCHAR2(4000), ACT_DESCRIPTION VARCHAR2(4000), ACT_IMAGE VARCHAR2(1000), ACT_STARTDATE VARCHAR2(1000), ACT_ENDDATE VARCHAR2(1000), PRIMARY KEY(ACT_NO))";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("主表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//刪MainTable表格
	public void dropTableMT() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "DROP TABLE MAINTABLE CASCADE CONSTRAINTS";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("主表格已刪除");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//創Position表格
	public void createTablePT() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "CREATE TABLE POSITION (NO Number(8,2), ID VARCHAR(1000), CITY VARCHAR(1000), DISTRICT VARCHAR(1000),  VILLAGE VARCHAR2(1000), ADDRESS VARCHAR2(1000),  LATITUDE NUMBER(25, 20), LONGITUDE NUMBER(25, 20), PRIMARY KEY(NO))";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("POSITION表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//刪Position表格
	public void dropTablePT() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "DROP TABLE POSITION CASCADE CONSTRAINTS";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("POSITION表格已刪除");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//創MEMBER表格
	public void createTableMB() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "CREATE TABLE MEMBER"
					   + "(seqNo INT GENERATED as IDENTITY constraint MEMBER_PK primary key, "
					   + " memberID			varchar2(20), " 
					   + " name    			varchar2(32), "
					   + " password			varchar2(32), " 
					   + " address 			varchar2(64), "
					   + " email 			varchar2(64), " 
					   + " tel  			varchar2(15), "
					   + " userType			varchar2(10), " 
					   + " registerTime    	Date, "
					   + " total_amount     	number(12,2),  "
					   + " ticket_amount        number(12,2), "
					   + " product_amount       number(12,2), "
					   + " class_amount       number(12,2), "
					   + " reservation_quantity   number(12,2), "
					   + " artShop_quantity       number(12,2), "
					   + " class_quantity         number(12,2), "
					   + " preference           number(6,2) "
					   + " )";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("MEMBER表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//刪MEMBER表格
	public void dropTableMB() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "DROP TABLE MEMBER CASCADE CONSTRAINTS";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("MEMBER表格已刪除");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//創BU_RESERVATIONITEM表格
	public void createTableBURI() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "CREATE TABLE BU_RESERVATIONITEM"
					   + "(seqno number generated as identity constraint ORDERITEMS_PK primary key, "
					   + " orderNo          int, "
					   + " actNo            int, "
					   + " actTitle      	VARCHAR2(1000), "
					   + " actLocation      VARCHAR2(1000), "
					   + " actLocationName  VARCHAR2(1000), "
					   + " actTime  		VARCHAR2(1000), "
					   + " amount           int, "
					   + " unitPrice        number(18,2), "
					   + " Discount         number "
					   + " ) ";
		    
		    stmt.executeUpdate(sql);
		    System.out.println("BU_RESERVATIONITEM表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//刪BU_RESERVATIONITEM表格
	public void dropTableBURI() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "DROP TABLE BU_RESERVATIONITEM CASCADE CONSTRAINTS";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("BU_RESERVATIONITEM表格已刪除");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//創BU_RESERVATION表格
	public void createTableBUR() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "CREATE TABLE BU_RESERVATION"
					   + "(orderNo  INT GENERATED as IDENTITY constraint ORDERS_PK primary key, "
					+ " memberId          varchar2(20), "
					+ " totalAmount       number(11,1), "
					+ " BNO               varchar2(8), " 
					+ " invoiceTitle      varchar2(72), " 
					+ " orderDate         Date, "
					+ " CancelTag         varchar2(1 char) "
					+ " )";
		    
		    stmt.executeUpdate(sql);
		    System.out.println("BU_RESERVATION表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//刪BU_RESERVATION表格
	public void dropTableBUR() {
		
		try (Connection connection = DataSourceConn.getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "DROP TABLE BU_RESERVATION CASCADE CONSTRAINTS";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("BU_RESERVATION表格已刪除");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
