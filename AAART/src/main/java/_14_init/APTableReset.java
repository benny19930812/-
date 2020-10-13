package _14_init;

import java.io.*;
import java.sql.*;



public class APTableReset {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8??�BOM字�??

	public static void main(String[] args) {
		String line = "";
		int count = 0;
		try (Connection con = DriverManager.getConnection(OracleSQL.getDburlOracle(),
				OracleSQL.getUseridOracle(), OracleSQL.getPswdOracle());
				Statement stmt = con.createStatement();) {
			
			try {
				stmt.executeUpdate(OracleSQL.getDropTableApcart());
				System.out.println("Drop Apcart Table!");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			stmt.executeUpdate(OracleSQL.getCreateApcart());
			System.out.println("Apcart Table Creat <3");

		try {
			stmt.executeUpdate(OracleSQL.getDropTableArtproduct());
			System.out.println("Drop Artproduct Table!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		stmt.executeUpdate(OracleSQL.getCreateArtproduct());
		System.out.println("Artproduct Table Creat <3");
		
		try {
			stmt.executeUpdate(OracleSQL.getDropTableApcart());
			System.out.println("Drop Apcart Table!");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		stmt.executeUpdate(OracleSQL.getCreateApcart());
		System.out.println("Apcart Table Creat <3");
		
		try {
			stmt.executeUpdate(OracleSQL.getDropTableOrderitemsap());
			System.out.println("Drop OrderItemsAP Table!");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		stmt.executeUpdate(OracleSQL.getCreateOrderitemsap());
		System.out.println("OrderItemsAP Table Creat <3");
		
		
		try {
			stmt.executeUpdate(OracleSQL.getDropTableOrdersap());
			System.out.println("Drop Ordersap Table!");
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		stmt.executeUpdate(OracleSQL.getCreateOrdersOracle());
		System.out.println("Orders Table Creat <3");
		
		//注意檔案路徑可能須修改
		File file = new File("inputCSV/ink3.csv");
		
		try (FileInputStream fis = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(fis, "big5");
				BufferedReader br = new BufferedReader(isr);) {
				con.setAutoCommit(false);
				String jdbc_insert_sql = "INSERT INTO Artproduct " + 
				" (APId,  aptitle,  aptype, apprice, apimg, apdes, apnum) "
						+ " VALUES ( ?, ?, ?, ?, ?, ?, ?)";

				try (PreparedStatement pstmt = con.prepareStatement(jdbc_insert_sql);) {

					String lineText = null;
					while ((line = br.readLine()) != null) {
						System.out.println("line=" + line);
						// ?��?�� UTF8_BOM: \uFEFF
						if (line.startsWith(UTF8_BOM)) {
							line = line.substring(1);
						}
						String[] token = line.split(",");
						ProductBean pd = new ProductBean();
						String APId = token[0];
						String pTitle = (token[1]);
						String pType = (token[2]);
						String pRrice = (token[3]);
						String pImg = (token[4]);
						String pDes  = (token[5]);
						
						int pnum  = 10;
						count++;
				
						pstmt.setString(1, APId);
						pstmt.setString(2, pTitle);
//						int pRrice2 = Integer.parseInt(pRrice);
						pstmt.setString(3, pType);
						pstmt.setString(4, pRrice);
						pstmt.setString(5, pImg);
						pstmt.setString(6, pDes);
						pstmt.setInt(7, pnum);
						
						pstmt.addBatch();
						pstmt.executeBatch();
						pstmt.clearBatch();

						System.out.println(APId + " " + pTitle + " " + pRrice + " " + pImg + " " + pDes);
						System.out.println("-----");
					}
					
					System.out.println("--------------------------------");
					con.commit();
					System.out.println("匯入 " + count + " 筆");
			}
			
			System.out.println("APPRODUCT TABLE CREAT!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	}
		
		}
	
	
	
		

	

}
	
