package _14_listAP;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import _14_init.ProductBean;

// 本類別使用純JDBC的技術來存取資料庫。
// 所有SQLException都以catch區塊捕捉，然後一律再次丟出RuntimeException。
// 對SQLException而言，即使catch下來，程式依然無法正常執行，所以捕捉SQLException，再次丟出
// RuntimeException。
public class ProductDaoImpl_Jdbc implements Serializable, ProductDAO {

	private static final long serialVersionUID = 1L;
	private int bookId = 0; 	// 查詢單筆商品會用到此代號
	private int pageNo = 0;		// 存放目前顯示之頁面的編號
	private int recordsPerPage = _14_init.glob.GlobalService.RECORDS_PER_PAGE; // 預設值：每頁三筆
	private int totalPages = -1;
	DataSource ds = null;
	
//	private String tagName = "";
	String selected = "";

	public ProductDaoImpl_Jdbc() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup(_14_init.glob.GlobalService.JNDI_DB_NAME);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc#建構子發生例外: " 
										+ ex.getMessage());
		}
	}
	
	// 計算販售的商品總共有幾頁
	
	@Override
	public int getTotalPages() {
		// 注意下一列敘述的每一個型態轉換
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));

		return totalPages;
	}
	
	// 查詢某一頁的商品(書籍)資料，執行本方法前，一定要先設定實例變數pageNo的初值
	
	@Override
	public List<ProductBean> getPageBooks() {
		List<ProductBean> list = new ArrayList<ProductBean>();
		System.out.println("進入 List<ProductBean>");
		String sql0 = "SELECT *" + 
				"FROM ( SELECT ROWNUM as rn, a.APID, a.APTITLE, a.APTYPE, a.APPRICE, a.APIMG, a.APDES, a.APNUM"
				+" from artproduct a ORDER BY 1) WHERE rn >= ? and rn <= ? ";

//		String sql0 = "SELECT  * FROM (SELECT  ROW_NUMBER() OVER (ORDER BY BOOKID)"
//				+ " AS RowNum, b.BookId, b.author, b.bookNo, b.category, b.TITLE, b.PRICE, "
//				+ " b.discount, b.companyID, b.fileName, b.coverImage, bc.name "
//				+ " FROM Book b JOIN BookCompany bc ON  b.companyID = bc.id )"
//				+ " AS NewTable WHERE RowNum >= ? AND RowNum <= ?";
		
//		String sql1 = "SELECT b.BookId, b.author, b.bookNo, b.category, b.TITLE, "
//				+ "b.PRICE, b.discount, b.companyID, b.fileName, b.coverImage, "
//				+ "bc.name FROM Book b JOIN BookCompany bc ON  b.companyID = bc.id "
//						+ " LIMIT ?, ?";
		String sql = sql0;
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (pageNo - 1) * recordsPerPage + 1;
		int endRecordNo = (pageNo) * recordsPerPage;
//		// 由頁碼推算出該頁是由哪一筆紀錄開始(0 based)		
//		int startRecordNo = (pageNo - 1) * recordsPerPage;
//		int endRecordNo = recordsPerPage;
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);)
		 { 
			ps.setBigDecimal(1, new BigDecimal(startRecordNo));
			ps.setBigDecimal(2, new BigDecimal(endRecordNo));
			System.out.println("設置 BigDecimal");
			System.out.println("startRecordNo="+ startRecordNo);
			System.out.println("endRecordNo="+ endRecordNo);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				// 只要還有紀錄未取出，rs.next()會傳回true
				// 迴圈內將逐筆取出ResultSet內的紀錄
				while (rs.next()) {
					// 準備一個新的BookBean，將ResultSet內的一筆紀錄移植到BookBean內
					System.out.println("準備一個新的BookBean");
					ProductBean bean = new ProductBean();    	
					bean.setIdAP(rs.getString("APId"));		
					bean.setTitleAP(rs.getString("aptitle"));
					bean.setTypeAP(rs.getString("aptype"));
					bean.setPriceAP(rs.getString("apprice"));
					bean.setImgAP(rs.getString("apimg"));
					bean.setDesAP(rs.getString("apdes"));
					bean.setNumAP(rs.getInt("apnum"));

					// 最後將BookBean物件放入大的容器內
					list.add(bean);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#getPageBooks()發生例外: " 
										+ ex.getMessage());
		}
		return list;
	}

	
	@Override
	public long getRecordCounts() {
		long count = 0; // 必須使用 long 型態
		String sql = "SELECT count(1) FROM Artproduct";
		try (
			Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#getRecordCounts()發生例外: " 
										+ ex.getMessage());
		}
		return count;
	}
	
	
	@Override
	public List<String> getCategory() {
		String sql = "SELECT DISTINCT aptype FROM Artproduct";
		List<String> list = new ArrayList<>();
		try (
			Connection connection = ds.getConnection();
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
		) {
			while (rs.next()) {
				String cate = rs.getString(1);
				if (cate != null) {
					list.add(cate);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#getCategory()發生例外: " 
										+ ex.getMessage());
		}
		return list;
	}
	
	
	@Override
	public String getCategoryTag() {
		String ans = "";
		List<String> list = getCategory();
		ans += "<SELECT name='aptype'>";
		for (String cate : list) {
			if (cate.equals(selected)) {
				ans += "<option value='" + cate + "' selected>" + cate + "</option>";
			} else {
				ans += "<option value='" + cate + "'>" + cate + "</option>";
			}
		}
		ans += "</SELECT>";
		return ans;
	}
	
	// 修改一筆書籍資料
	
//	public int updateBook(ProductBean bean, long sizeInBytes) {
//		int n = 0;
//		String sql = "UPDATE Book SET " 
//				+ " title=?,  author=?,  price=?, discount = ?, coverImage = ?, "
//				+ " fileName=?, bookNo=?, stock=?, companyID=? , category = ? WHERE bookID = ?";
//		if (sizeInBytes == -1) { // 不修改圖片
//			n = updateBook(bean);
//			return n;
//		}
//		InputStream blobStream = null;
//		try (
//			Connection connection = ds.getConnection(); 
//			PreparedStatement ps = connection.prepareStatement(sql);
//		) {
//			ps.setString(1, bean.getTitle());
//			ps.setString(2, bean.getAuthor());
//			ps.setDouble(3, bean.getPrice());
//			ps.setDouble(4, bean.getDiscount());
//
//			if(bean.getCoverImage() != null) blobStream = bean.getCoverImage().getBinaryStream();
//			ps.setBlob(5, blobStream);
//			ps.setString(6, bean.getFileName());
//			ps.setString(7, bean.getBookNo());
//			ps.setInt(8, bean.getStock());
//			ps.setInt(9, bean.getCompanyId());
//			ps.setString(10, bean.getCategory());
//			ps.setInt(11, bean.getBookId());
//			n = ps.executeUpdate();
//		} catch (SQLException ex) {
//			ex.printStackTrace();
//			throw new RuntimeException("MemberDaoImpl_Jdbc()#updateBook(BookBean, long)發生例外: " 
//										+ ex.getMessage());
//		}finally {
//			if(blobStream!=null) {
//				try {
//					blobStream.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return n;
//	}

	// 修改一筆商品資料，不改圖片
	@Override
	public int updateProduct(ProductBean bean) {
		int n = 0;
		String sql = "UPDATE Artproduct SET " 
				+ "  aptitle=?,  aptype=?, apprice=?,  apimg=?, "
				+ " apdes=?,  apnum=?  WHERE APId = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.clearParameters();
			
			ps.setString(1, bean.getTitleAP());
			ps.setString(2, bean.getTypeAP());
			ps.setString(3, bean.getPriceAP());
			ps.setString(4, bean.getImgAP());
			ps.setString(5, bean.getDesAP());
			ps.setInt(6, bean.getNumAP());
			ps.setString(7, bean.getIdAP());


			n = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#updateProduct(ProductBean bean)發生例外: " 
										+ ex.getMessage());
		}
		return n;
	}

	// 依bookID來刪除單筆記錄
	
	@Override
	public int deleteProduct(int no) {
		int n = 0;
		String sql = "DELETE FROM Artproduct WHERE APId = ?";

		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement pStmt = connection.prepareStatement(sql);
		) {
			pStmt.setInt(1, no);
			n = pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#deleteProduct(int no)發生例外: " 
										+ ex.getMessage());
		}
		return n;
	}

	// 新增一筆記錄---
	
	@Override
	public int saveBook(ProductBean bean) {
		int n = 0;

		String sql = "INSERT INTO Artproduct " 
				+ " (APId,  aptitle,  aptype, apprice, "
				+ " apimg, apdes, apnum) " 
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";

		InputStream blobStream = null;
		try (
			Connection connection = ds.getConnection();
			PreparedStatement pStmt = connection.prepareStatement(sql);
		) {
			pStmt.setString(1, bean.getIdAP());
			pStmt.setString(2, bean.getTitleAP());
			pStmt.setString(3, bean.getTypeAP());
			pStmt.setString(4, bean.getPriceAP());
			pStmt.setString(5, bean.getImgAP());
			pStmt.setString(6, bean.getDesAP());
			pStmt.setInt(7, bean.getNumAP());

//			if(bean.getCoverImage() != null) blobStream = bean.getCoverImage().getBinaryStream();
//			pStmt.setBlob(8, blobStream);
//
//			pStmt.setInt(9, bean.getStock());
//			pStmt.setString(10, bean.getCategory());
			n = pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#saveBook()發生例外: " 
										+ ex.getMessage());
		}finally {
			if(blobStream != null) {
				try {
					blobStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return n;
	}

	
	

	
	@Override
	public void setSelected(String selected) {
		this.selected = selected;
	}

	
	@Override
	public int getPageNo() {
		return pageNo;
	}

	
	@Override
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	
	@Override
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	
	@Override
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

	
	@Override
	public ProductBean getProduc(int bookId) {
		ProductBean bean = null;
		String sql = "SELECT * FROM Book WHERE APId = ?";

		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.setInt(1, bookId);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					bean = new ProductBean();
					bean.setTitleAP(rs.getString(2));
					bean.setTypeAP(rs.getString(3));
					bean.setPriceAP(rs.getString(4));
					bean.setImgAP(rs.getString(5));
					bean.setDesAP(rs.getString(6));
					bean.setNumAP(rs.getInt(7));
					
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc()#queryBook()發生例外: " 
										+ ex.getMessage());
		}
		return bean;
	}
	
	@Override
	public List<ProductBean> getSerachAP(String serachInput) {
		List<ProductBean> list = new ArrayList<ProductBean>();
		System.out.println("serachInput");
		String sql0 = "SELECT" + 
				"FROM ( SELECT ROWNUM as rn, a.apid, a.APTITLE from artproduct a ORDER BY 1)\r\n" + 
				"WHERE rn >= ? and rn<= ? and  APTITLE LIKE '%y%'";
//				"WHERE rn >= ? and rn<= ? and  APTITLE LIKE '%"+ y +"%'";

//		String sql0 = "SELECT  * FROM (SELECT  ROW_NUMBER() OVER (ORDER BY BOOKID)"
//				+ " AS RowNum, b.BookId, b.author, b.bookNo, b.category, b.TITLE, b.PRICE, "
//				+ " b.discount, b.companyID, b.fileName, b.coverImage, bc.name "
//				+ " FROM Book b JOIN BookCompany bc ON  b.companyID = bc.id )"
//				+ " AS NewTable WHERE RowNum >= ? AND RowNum <= ?";
		
//		String sql1 = "SELECT b.BookId, b.author, b.bookNo, b.category, b.TITLE, "
//				+ "b.PRICE, b.discount, b.companyID, b.fileName, b.coverImage, "
//				+ "bc.name FROM Book b JOIN BookCompany bc ON  b.companyID = bc.id "
//						+ " LIMIT ?, ?";
		String sql = sql0;
		// 由頁碼推算出該頁是由哪一筆紀錄開始(1 based)
		int startRecordNo = (pageNo - 1) * recordsPerPage + 1;
		int endRecordNo = (pageNo) * recordsPerPage;
//		// 由頁碼推算出該頁是由哪一筆紀錄開始(0 based)		
//		int startRecordNo = (pageNo - 1) * recordsPerPage;
//		int endRecordNo = recordsPerPage;
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);)
		 { 
			ps.setBigDecimal(1, new BigDecimal(startRecordNo));
			ps.setBigDecimal(2, new BigDecimal(endRecordNo));
			System.out.println("設置 BigDecimal");
			System.out.println("startRecordNo="+ startRecordNo);
			System.out.println("endRecordNo="+ endRecordNo);
			try (
				ResultSet rs = ps.executeQuery();
			) {
				// 只要還有紀錄未取出，rs.next()會傳回true
				// 迴圈內將逐筆取出ResultSet內的紀錄
				while (rs.next()) {
					// 準備一個新的BookBean，將ResultSet內的一筆紀錄移植到BookBean內
					System.out.println("準備一個新的BookBean");
					ProductBean bean = new ProductBean();    	
					bean.setIdAP(rs.getString("APId"));		
					bean.setTitleAP(rs.getString("aptitle"));
					bean.setTypeAP(rs.getString("aptype"));
					bean.setPriceAP(rs.getString("apprice"));
					bean.setImgAP(rs.getString("apimg"));
					bean.setDesAP(rs.getString("apdes"));
					bean.setNumAP(rs.getInt("apnum"));

					// 最後將BookBean物件放入大的容器內
					list.add(bean);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ProductDaoImpl_Jdbc()#getPageBooks()發生例外: " 
										+ ex.getMessage());
		}
		return list;
	}
	
	

}