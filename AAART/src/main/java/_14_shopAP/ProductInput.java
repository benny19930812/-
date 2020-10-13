package _14_shopAP;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductInput {

	public static void main(String[] args) {
		DAOPage14 daoPage = new DAOPage14();
		

		File file = new File("C:\\Users\\Student\\Desktop\\pen.csv");
		int count = 0;

		try (Connection connection = daoPage.getDataSourcePJ().getConnection();
				InputStreamReader iReader = new InputStreamReader(new FileInputStream(file), "BIG5");
				BufferedReader lineReader = new BufferedReader(iReader);) {

			connection.setAutoCommit(false);
			String jdbc_insert_sql = "INSERT INTO PRODUCT" + "(PRODUCT_ID, PRODUCT_TITLE, PRODUCT_PRICE, PRODUCT_IMG, PRODUCT_NUM, PRODUCT_DES, PRODUCT_TYPE) VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?)";

			try (PreparedStatement pstmt = connection.prepareStatement(jdbc_insert_sql);) {

				String lineText = null;

				while ((lineText = lineReader.readLine()) != null) {
					count++;
					String[] data = lineText.split(",");
					String pId = data[0];
					String pTitle = data[1];
					String pRrice = data[2];
					String pImg = data[3];
					String pnum = "10";
					String pDes = data[4];
					String pType = "pen";
					


					pstmt.setString(1, pId);
					pstmt.setString(2, pTitle);
//					int pRrice2 = Integer.parseInt(pRrice);
					pstmt.setString(3, pRrice);
					pstmt.setString(4, pImg);
					pstmt.setString(5, pnum);
					pstmt.setString(6, pDes);
					pstmt.setString(7, pType);
					
					pstmt.addBatch();
					pstmt.executeBatch();
					pstmt.clearBatch();

					System.out.println(pId + " " + pTitle + " " + pRrice + " " + pImg + " " + pDes);
					System.out.println("-----");
				}
				
				System.out.println("--------------------------------");
				connection.commit();
				System.out.println("資料匯入完畢，共匯入 " + count + " 筆資料");

			} catch (SQLException e) {
//				connection.rollback();
				e.printStackTrace();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException | IOException e1) {
			e1.printStackTrace();
		}

	
	}

}
