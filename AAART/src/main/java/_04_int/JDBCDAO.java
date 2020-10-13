package _04_int;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JDBCDAO {
private DataSource dataSource;
	
	//getDataSource連線的方法
	public DataSource getDataSource() {//只需get因為只要用不希望被改
		
		//lazy init，能有多晚用到就多晚產生，放constructor會先產出佔用記憶體
		if (dataSource == null) { //不希望重複產生，
			BasicDataSource ds = new BasicDataSource();
			ds.setDriverClassName("oracle.jdbc.OracleDriver");
			ds.setUrl("jdbc:oracle:thin:@//localhost:1521/xepdb1");
			ds.setUsername("group4");
			ds.setPassword("oracle");
			ds.setMaxTotal(50); 
			ds.setMaxIdle(50);  			

			dataSource=ds; //把BasicDataSource放在屬性上
		}
		return dataSource;
	}
	
	//創Orderlist表格
	public void createTableOL() {
		
		try (Connection connection = getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "CREATE TABLE ORDERLIST (ORDERID VARCHAR2(1000 BYTE), NAME VARCHAR2(1000 BYTE), EMAIL VARCHAR2(1000 BYTE), TEL VARCHAR2(1000 BYTE), ADDRESS VARCHAR2(1000 BYTE), TOTALPRICE NUMBER)";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("Orderlist表格已建立");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//刪Orderlist表格
	public void dropTableOL() {
		
		try (Connection connection = getDataSource().getConnection();) {
			Statement stmt = connection.createStatement();
			
		    String sql = "DROP TABLE ORDERLIST CASCADE CONSTRAINTS";
	    
		    stmt.executeUpdate(sql);
		    System.out.println("ORDERLIST表格已刪除");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//創OrderNUM表格
		public void createTableOU() {
			
			try (Connection connection = getDataSource().getConnection();) {
				Statement stmt = connection.createStatement();
				
			    String sql = "CREATE TABLE ORDERNUM( ORDERID VARCHAR2(1000 BYTE), TITLE VARCHAR2(1000 BYTE), ADULTNUM NUMBER, HALFNUM NUMBER)";
		    
			    stmt.executeUpdate(sql);
			    System.out.println("ORDERNUM表格已建立");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//刪ORDERNUM表格
		public void dropTableON() {
			
			try (Connection connection = getDataSource().getConnection();) {
				Statement stmt = connection.createStatement();
				
			    String sql = "DROP TABLE ORDERNUM CASCADE CONSTRAINTS";
		    
			    stmt.executeUpdate(sql);
			    System.out.println("ORDERNUM表格已刪除");

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	
	
	
}
