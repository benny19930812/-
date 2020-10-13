package _14_init;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductBean {
	private String idAP;
	private String titleAP;
	private String priceAP;
	private String imgAP;
	private int numAP;
	private String desAP;
	private String typeAP;
	
	
	public ProductBean() {
		
	}
	
	
	
	public ProductBean(String idAP, String titleAP, String priceAP, String imgAP, int numAP, String desAP, String typeAP) {
		super();
		this.idAP = idAP;
		this.titleAP = titleAP;
		this.priceAP = priceAP;
		this.imgAP = imgAP;
		this.numAP = numAP;
		this.desAP = desAP;
		this.typeAP = typeAP;
	}


	public String getIdAP() {
		return idAP;
	}

	public void setIdAP(String idAP) {
		this.idAP = idAP;
	}

	public String getTitleAP() {
		return titleAP;
	}

	public void setTitleAP(String titleAP) {
		this.titleAP = titleAP;
	}


	public String getPriceAP() {
		return priceAP;
	}

	public void setPriceAP(String priceAP) {
		this.priceAP = priceAP;
	}


	public String getImgAP() {
		return imgAP;
	}


	public void setImgAP(String imgAP) {
		this.imgAP = imgAP;
	}


	public int getNumAP() {
		return numAP;
	}

	public void setNumAP(int numAP) {
		this.numAP = numAP;
	}


	public String getDesAP() {
		return desAP;
	}

	public void setDesAP(String desAP) {
		this.desAP = desAP;
	}

	public String getTypeAP() {
		return typeAP;
	}


	public void setTypeAP(String typeAP) {
		this.typeAP = typeAP;
	}



	public Object getCoverImage() {
		// TODO Auto-generated method stub
		return null;
	}






//	public List<ProductBean> listProducts() {
//		List<ProductBean> productList = new ArrayList<ProductBean>();
//		
//		DAOPage daoPage = new DAOPage(); 
//        
//		try  
//			(Connection connection = daoPage.getDataSourcePJ().getConnection();
//			Statement stmt= connection.createStatement();
//			ResultSet rs= stmt.executeQuery("select PRODUCT_ID, PRODUCT_TITLE, PRODUCT_PRICE, PRODUCT_IMG, PRODUCT_NUM, PRODUCT_DES, PRODUCT_TYPE from "
//					+ "PRODUCT ORDER BY 2 ");){
//								
//				while (rs.next()) { 
//					ProductBean product = new ProductBean();
//					product.setIdAP(rs.getString("PRODUCT_ID"));
//					product.setTitleAP(rs.getString("PRODUCT_TITLE"));
//					product.setPriceAP(rs.getString("PRODUCT_PRICE"));
//					product.setImgAP(rs.getString("PRODUCT_IMG"));
//					product.setNumAP(rs.getInt("PRODUCT_NUM"));
////					product.setProductDes(rs.getClob("PRODUCT_DES"));
//					
//					Clob c = rs.getClob("PRODUCT_DES");
//					String clobString = c.getSubString(1, (int) c.length());
//					product.setDesAP(clobString);
//					
//					product.setTypeAP(rs.getString("PRODUCT_TYPE"));
//				
//					productList.add(product);
//					
//					}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return productList;
//		
//	}

}
