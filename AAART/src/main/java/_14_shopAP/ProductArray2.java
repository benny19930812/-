package _14_shopAP;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class ProductArray
 */
@WebServlet("/_14_shopAP/ProductArray2")
public class ProductArray2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductArray2() {
        super();
    }
    DataSource ds = null;
    public void init() throws ServletException
    {
      try 
      {
        InitialContext ctxt = new InitialContext();
        ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");  // for Oracle DB
      }
      catch (NamingException ne)
      {
        throw new ServletException(ne);
      }
    }
   
    List<Product> productList = new ArrayList<Product>();
	
	DAOPage14 daoPage = new DAOPage14(); 

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String method = request.getParameter("method");
		String sCurrePage = request.getParameter("currentPage");
		String param1 = request.getParameter("param1");
		String check = request.getParameter("check");
		String orderNum = request.getParameter("orderNum");
		String orderPrice = request.getParameter("orderPrice");
		String productID = request.getParameter("productID");
//		String CarSize = request.getParameter("carSize");
		System.out.println(method);
		System.out.println(sCurrePage);
		
//		System.out.println(request.getAttribute("currentPage"));
		
		if ("selectItem".equals(method)) {
			request.setAttribute("currentPage", Integer.parseInt(sCurrePage));
			gotoSubmitProcess( productID, orderPrice, orderNum, param1, request, response);
		}else if("changePage".equals(method)) {
			changePage(sCurrePage, request, response);
		}else if("order".equals(method)) {
			gotoCart( request, response);
		}else if("submit".equals(method)) {
			OrderSubmit( orderNum,request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
		
	}
	public void changePage(String sCurrePage, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("currentPage", Integer.parseInt(sCurrePage));
		HttpSession session = request.getSession(true);
		List<ProductItem> carList = (List<ProductItem>)session.getAttribute("carList");
		 if (carList == null) {
	    	  carList = new ArrayList<ProductItem>();
	       
	        session.setAttribute("carList", carList);

	      }else {
	    	  
	    	  request.setAttribute("carSize", carList.size());
	      }
		request.getRequestDispatcher("14_serach.jsp").forward(request, response);
	}
	
	public void gotoSubmitProcess(String productID, String orderPrice, String orderNum, String param1, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		request.getRequestDispatcher("/orderPage.jsp").forward(request,response);
		System.out.println("進入方法");
		
		HttpSession session = request.getSession(true);
		synchronized(session) {

			List<ProductItem> carList = (List<ProductItem>)session.getAttribute("carList");
			
		      if (carList == null) {
		    	  carList = new ArrayList<ProductItem>();
		     
		       
		        session.setAttribute("carList", carList);

		      }
			
			String act_nameString = param1;
			String orderNum_int = orderNum;
			String orderPrice_String = orderPrice;
			String orderproductID = productID;
				
			ProductItem ca = new ProductItem();
//			ca.setProdutTitle(act_nameString);
//			ca.setProductNum(orderNum_int);
//			ca.setProductPrice(orderPrice_String);
//			ca.setProductID(orderproductID);
			
			
			for(int i=0; i < carList.size(); i++) {
				ProductItem order;
				order = (ProductItem) carList.get(i);
//				
//				if (ca.getProdutTitle()==(order.getProdutTitle())) {
//					System.out.println(carList.remove(i));
//					
//				}
				
//				if (ca.getProdutTitle().equals(order.getProdutTitle())) {
//					ca.setProductNum(Integer.toString(Integer.parseInt(ca.getProductNum())+Integer.parseInt(order.getProductNum())));
					
					System.out.println(carList.remove(i));
				}
			
			}
			synchronized(this) {
				
//				carList.add(ca);

			}
		
//		request.setAttribute("carSize", carList.size());
//		System.out.println("orderNum_int"+orderNum_int);
		
		request.getRequestDispatcher("14_serach.jsp").forward(request, response);
//		response.sendRedirect("orderPage.jsp");
		}
//	  }
	
	public void gotoCart( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		
		
		
		request.getRequestDispatcher("NewFile.jsp").forward(request, response);

		
	  }
	
	
	public void OrderSubmit(String orderNum, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		HttpSession session = request.getSession(true);
	    DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
	    try {
	     

	      ctxt = new InitialContext();

	      ds = ( DataSource ) ctxt.lookup("java:comp/env/jdbc/xe");

	      conn = ds.getConnection();


	      CarItemInput carItemInput = new CarItemInput(conn);
//	      ProductItem productItem = (ProductItem)request.getSession(true).getAttribute("carList");
	      List<ProductItem> carList = (List<ProductItem>)session.getAttribute("carList");
	      
	      for(int i=0; i < carList.size(); i++) {
	    	  ProductItem order;
				order = (ProductItem) carList.get(i);
	    	  
//	    	  if (carItemInput.insertOrderAP(order))
		        {
		          System.out.println("Get some SQL commands done!");
		          request.getSession(true).invalidate();
		          request.getRequestDispatcher("14_Done.jsp").forward(request,response);
		        }
	    	  
	      }
	      
	      
	    } catch (NamingException ne) {
	      System.out.println("Naming Service Lookup Exception");  
	    } catch (SQLException e) {
	      System.out.println("Database Connection Error"); 
	    } finally {
	      try {
	        if (conn != null) conn.close();
	      } catch (Exception e) {
	        System.out.println("Connection Pool Error!");
	      }
	    }
	           
	  }
	  
	
	
	
}
