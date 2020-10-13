package _14_shopAP.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import _14_init.ProductBean;
import _14_init.glob.GlobalService;
import _14_shopAP.Product;




public class ImportProductDaoImpl {
	
	private static final long serialVersionUID = 1L;
	private int pageNo = 0; // 存放目前顯示之頁面的編號
	private int recordsPerPage = GlobalService.RECORDS_PER_PAGE;
	private int totalPages = -1;
	private String query ;
	DataSource ds = null;
	String selected = "";
	
	public ImportProductDaoImpl() {
		try {
			Context ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup(GlobalService.JNDI_DB_NAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public long getRecordCounts() {
		long count = 0;
		String sql = "SELECT count(1) FROM ARTPRODUCT";
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				count = rs.getLong(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ImportProductDaoImpl()#getRecordCounts()發生例外: " + ex.getMessage());
		}
		return count;
	}
	
	
	public int getRecordCounts(String query) {
		this.query = query;
		int count = 0;
		String sql = "SELECT count(1) FROM ARTPRODUCT WHERE APTYPE LIKE '%"+query+"%'";
		try (Connection connection = ds.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("ImportProductDaoImpl()#getRecordCounts(query)發生例外: " + ex.getMessage());
		}
		return count;
	}
	
	public int getTotalPages() {
		totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));

		return totalPages;
	}
	
	
	public int getQueryTotalPages() {
		totalPages = (int) (Math.ceil(getRecordCounts(query) / (double) recordsPerPage));
		if (totalPages <= 0) {
			totalPages = 1;
		}
		return totalPages;
	}
	
	//取得所有表格內容
	public ArrayList<Product> getPdPages() {
		ArrayList<Product> entList = new ArrayList<Product>();
		String sql0 = "SELECT * FROM (SELECT ROWNUM as rn , APID,APTITLE,APTYPE,APPRICE,APIMG,APDES,APNUM FROM ARTPRODUCT ORDER BY 3) WHERE rn >= ? AND rn <=?";
		String sql = sql0;
		int startRecordNo = (pageNo - 1) * recordsPerPage + 1;
		int endRecordNo = (pageNo) * recordsPerPage;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setBigDecimal(1, new BigDecimal(startRecordNo));
			pstmt.setBigDecimal(2, new BigDecimal(endRecordNo));
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Product pd = new Product();
					pd.setProductId(rs.getString("APID"));
					pd.setProductTitle(rs.getString("APTITLE"));
					pd.setProductType(rs.getString("APTYPE"));
					pd.setProductPrice(rs.getString("APPRICE"));
					pd.setProductImg(rs.getString("APIMG"));
					pd.setProductDes(rs.getString("APDES"));
					pd.setProductNum(rs.getInt("APNUM"));

					entList.add(pd);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entList;
	}
	
	
//取得指定類別的商品
	public ArrayList<Product> getQueryPdList(String query) {
		ArrayList<Product> entList = new ArrayList<Product>();
		System.out.println("進入getQueryPdList");
		String sql0 = "SELECT * FROM (SELECT ROWNUM as rn , APID,APTITLE,APTYPE,APPRICE,APIMG,APDES,APNUM FROM ARTPRODUCT "
				+ "WHERE APTYPE LIKE '%"+ query + "%'  ORDER BY 2 desc) WHERE rn >= ? AND rn <=?";
		String sql = sql0;
		int startRecordNo = (pageNo - 1) * recordsPerPage + 1;
		int endRecordNo = (pageNo) * recordsPerPage;

		try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setBigDecimal(1, new BigDecimal(startRecordNo));
			pstmt.setBigDecimal(2, new BigDecimal(endRecordNo));
			try (ResultSet rs = pstmt.executeQuery();) {
				while (rs.next()) {
					Product pd = new Product();
					pd.setProductId(rs.getString("APID"));
					pd.setProductTitle(rs.getString("APTITLE"));
					pd.setProductType(rs.getString("APTYPE"));
					pd.setProductPrice(rs.getString("APPRICE"));
					pd.setProductImg(rs.getString("APIMG"));
					pd.setProductDes(rs.getString("APDES"));
					pd.setProductNum(rs.getInt("APNUM"));
					
					entList.add(pd);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entList;
	}
	
	
	//取得指定類別的商品
		public ArrayList<Product> getPdListByName(String query) {
			ArrayList<Product> ByNameList = new ArrayList<Product>();
			System.out.println("進入PdListByName");
			String sql0 = "SELECT * FROM (SELECT ROWNUM as rn , APID,APTITLE,APTYPE,APPRICE,APIMG,APDES,APNUM FROM ARTPRODUCT "
					+ "WHERE APTITLE LIKE '%"+ query + "%'  ORDER BY 2 desc) WHERE rn >= ? AND rn <=?";
			String sql = sql0;
			int startRecordNo = (pageNo - 1) * recordsPerPage + 1;
			int endRecordNo = (pageNo) * recordsPerPage;

			try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setBigDecimal(1, new BigDecimal(startRecordNo));
				pstmt.setBigDecimal(2, new BigDecimal(endRecordNo));
				try (ResultSet rs = pstmt.executeQuery();) {
					while (rs.next()) {
						Product pd = new Product();
						pd.setProductId(rs.getString("APID"));
						pd.setProductTitle(rs.getString("APTITLE"));
						pd.setProductType(rs.getString("APTYPE"));
						pd.setProductPrice(rs.getString("APPRICE"));
						pd.setProductImg(rs.getString("APIMG"));
						pd.setProductDes(rs.getString("APDES"));
						pd.setProductNum(rs.getInt("APNUM"));
						
						ByNameList.add(pd);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ByNameList;
		}
	
	
	
	//取得所有東西
	public ArrayList<Product> getPdList() {
		ArrayList<Product> entList = new ArrayList<Product>();
		String sql= "SELECT * FROM ARTPRODUCT ";
		
		try (Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement()
				;) {
			
			try (ResultSet rs = stmt.executeQuery(sql);) {
				while (rs.next()) {
					Product pd = new Product();
					pd.setProductId(rs.getString("APID"));
					pd.setProductTitle(rs.getString("APTITLE"));
					pd.setProductType(rs.getString("APTYPE"));
					pd.setProductPrice(rs.getString("APPRICE"));
					pd.setProductImg(rs.getString("APIMG"));
					pd.setProductDes(rs.getString("APDES"));
					pd.setProductNum(rs.getInt("APNUM"));

					entList.add(pd);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return entList;
	}
	
	
	//模糊地尋找某 id 商品
	public ArrayList<Product> getProductAPID(String APID) {
		ArrayList<Product> apidtList = new ArrayList<Product>();
		System.out.println("進入getQueryPdList");
		String sql0 = "SELECT * FROM ARTPRODUCT WHERE APID LIKE '%"+ APID + "%'  ORDER BY 2 desc";
		String sql = sql0;
		

		try (Connection conn = ds.getConnection();
				Statement stmt = conn.createStatement()
				;) {
			
			try (ResultSet rs = stmt.executeQuery(sql);) {
				while (rs.next()) {
					Product pd = new Product();
					pd.setProductId(rs.getString("APID"));
					pd.setProductTitle(rs.getString("APTITLE"));
					pd.setProductType(rs.getString("APTYPE"));
					pd.setProductPrice(rs.getString("APPRICE"));
					pd.setProductImg(rs.getString("APIMG"));
					pd.setProductDes(rs.getString("APDES"));
					pd.setProductNum(rs.getInt("APNUM"));
					
					apidtList.add(pd);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apidtList;
	}
	
	
	
	//認真的尋找某 id 商品
		public ArrayList<Product> getPreciseAPID(String APID) {
			ArrayList<Product> preciseList = new ArrayList<Product>();
			System.out.println("進入getQueryPdList");
			String idString = APID +" ";
			String sql0 = "SELECT * FROM ARTPRODUCT WHERE APID ='"+ idString + "'";

			try (Connection conn = ds.getConnection();
					Statement stmt = conn.createStatement()
					;) {
				
				try (ResultSet rs = stmt.executeQuery(sql0);) {
					while (rs.next()) {
						Product pd = new Product();
						pd.setProductId(rs.getString("APID"));
						pd.setProductTitle(rs.getString("APTITLE"));
						pd.setProductType(rs.getString("APTYPE"));
						pd.setProductPrice(rs.getString("APPRICE"));
						pd.setProductImg(rs.getString("APIMG"));
						pd.setProductDes(rs.getString("APDES"));
						pd.setProductNum(rs.getInt("APNUM"));
						
						preciseList.add(pd);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return preciseList;
		}
	
	
	
	
	public int DeletProduct(String APid) {
		
		
		System.out.println("進入DeletProduct");
		System.out.println("APid="+APid);
		String sql0 = "DELETE FROM ARTPRODUCT WHERE APID =?";
		String id = APid+" ";
		int n = 0;

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql0);) {
			
					pstmt.setString(1, id);
					n = pstmt.executeUpdate();
				} catch (SQLException ex) {
					ex.printStackTrace();
					throw new RuntimeException("MemberDaoImpl_Jdbc()#deleteBook()發生例外: " 
												+ ex.getMessage());
				}
				return n;
		
		
	}
				
	public int saveAPPdroduct(Product pd) {
		int n = 0;
		String sql = "INSERT INTO ARTPRODUCT " + " (APId,  aptitle,  aptype, apprice, "
			+ " apimg, apdes, apnum) "
			+ " VALUES ( ?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				) {
			pstmt.setString(1, pd.getProductId()+" ");
			pstmt.setString(2, pd.getProductTitle());
			pstmt.setString(3, pd.getProductType());
			pstmt.setString(4, pd.getProductPrice());
			pstmt.setString(5, pd.getProductImg());
			pstmt.setString(6, pd.getProductDes());
			pstmt.setInt(7, pd.getProductNum());
//		
			n = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return n;
	}
			
			
		
	public int modifyAP(Product pd) {
		System.out.println("進入modifyAP");
		int n = 0;
		String sql = "UPDATE ARTPRODUCT SET " 
				+ " aptitle=?,  aptype=?, apprice=?,  apimg=?, "
				+ " apdes=?,  apnum=?  WHERE APId = ?";
		try (
			Connection connection = ds.getConnection(); 
			PreparedStatement ps = connection.prepareStatement(sql);
		) {
			ps.clearParameters();
			
			System.out.println("執行修改");
			
			ps.setString(1, pd.getProductTitle());
			ps.setString(2, pd.getProductType());
			ps.setString(3, pd.getProductPrice());
			ps.setString(4, pd.getProductImg());
			ps.setString(5, pd.getProductDes());
			ps.setInt(6, pd.getProductNum());
			ps.setString(7, pd.getProductId());
	

			n = ps.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc()#updateBook(BookBean)發生例外: " 
										+ ex.getMessage());
		}
		return n;
	}
	
	
	
	

	
	public String getQuery(String query) {
		this.query=query;
		return query;
	}
	
	
	public void setQuery(String query) {
		this.query=query;
	}
	
	
	public void setSelected(String selected) {
		this.selected = selected;
	}

	
	public int getPageNo() {
		return pageNo;
	}

	
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	
	public int getRecordsPerPage() {
		return recordsPerPage;
	}

	
	public void setRecordsPerPage(int recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}

}
