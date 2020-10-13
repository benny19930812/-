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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import _14_shopAP.impl.ImportProductDaoImpl;
import _14_shopAP.orderbean.ShoppingCartAP;

/**
 * Servlet implementation class ProductArray
 */
@WebServlet("/_14_shopAP/ProductArray")
public class ProductArray extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo =0;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductArray() {
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
		String orderImg = request.getParameter("orderImg");
		String query = request.getParameter("query");
//		String CarSize = request.getParameter("carSize");
		System.out.println("method"+method);
//		System.out.println(sCurrePage);
//		System.out.println(request.getAttribute("currentPage"));
		
		if ("selectItem".equals(method)) {
//			request.setAttribute("currentPage", Integer.parseInt(sCurrePage));
			gotoSubmitProcess(query, orderImg ,productID, orderPrice, orderNum, param1, request, response);
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
		ShoppingCartAP carList = (ShoppingCartAP)session.getAttribute("carList");
		if (carList == null) {
	    	  carList = new ShoppingCartAP();
	     
	        session.setAttribute("carList", carList);

	      }else {
	    	  Map<String, ProductItem> cartAP = carList.getCartAP();
	    	  request.setAttribute("carSize", cartAP.size());
	      }
		request.getRequestDispatcher("14_index.jsp").forward(request, response);
	}
	
	public void gotoSubmitProcess(String query, String orderImg, String productID, String orderPrice, String orderNum, String param1,  HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
//		request.getRequestDispatcher("/orderPage.jsp").forward(request,response);
		System.out.println("進入 SubmitProcess");
		ImportProductDaoImpl service = new ImportProductDaoImpl();
		Product pdProduct = new Product();
		
		
		HttpSession session = request.getSession(true);
		synchronized(session) {

//			List<ProductItem> carList = (List<ProductItem>)session.getAttribute("carList");
			ShoppingCartAP carList = (ShoppingCartAP)session.getAttribute("carList");
			
		      if (carList == null) {
		    	  carList = new ShoppingCartAP();
		        session.setAttribute("carList", carList);
		      }
			
			
		    String orderproductID = productID;
			String act_nameString = param1;
			String ordString = orderNum;
			int orderNum_int = Integer.parseInt(ordString);
			String orderPrice_String = orderPrice;
			String orderproductImg = orderImg;
				
			ProductItem pd = new ProductItem();

			pd.setProductIdAP(orderproductID);
			pd.setProductTitleAP(act_nameString);
			pd.setProductNumAP(orderNum_int);
			pd.setProductPriceAP(orderPrice_String);
			pd.setProductImgAP(orderproductImg);
			
			
			
			synchronized(this) {
				
				carList.addToCarAP(productID, pd);

			}
			
			Map<String, ProductItem> cartAP = carList.getCartAP();
			Set set = cartAP.keySet();
			for(Iterator iter = set.iterator(); iter.hasNext();)
			  {
			   String key = (String)iter.next();
			   ProductItem value = (ProductItem)cartAP.get(key);
			   System.out.println(key+"===="+value);
			  }
			
			request.setAttribute("carSize", cartAP.size());
		
		System.out.println("orderNum_int"+orderNum_int);
		
//		request.getRequestDispatcher("14_serach.jsp").forward(request, response);
		
		}
		
		//這是分隔線
		
		try {
//			HttpSession session = request.getSession(false);
			String pageNoStr = request.getParameter("pageNo");
			if (pageNoStr == null) {
				pageNo = 1;
			} else {
				try {
					pageNo = Integer.parseInt(pageNoStr.trim());
				} catch (NumberFormatException e) {
					pageNo = 1;
				}
			}
			System.out.println("設定完頁數");
			// 先設定起始PAGENO
			service.setPageNo(pageNo);
			// query是查詢INPUT的參數
//			String query = request.getParameter("query");
			
			//totalPage 是拿來接動態產生 1,2,3,4頁等等的超連結按鈕
			List<Integer> totalPages = new ArrayList<Integer>();
			System.out.println("一開始的參數是="+query);
			
			// 這邊做if else 判斷查詢欄位有無值
			if (query != "" && query != null) {
				System.out.println("進入有參數方法");
				//有值用定義有query參數的List 
				//這裡的setQuery 是為了讓DAO裡的全域變數有值
//				service.setQuery(query);
				System.out.println("有參數query="+query);
				ArrayList<Product> entList = service.getQueryPdList(query);
				request.setAttribute("baBean", pdProduct);
				request.setAttribute("queryList", entList);
				session.setAttribute("pageNo", String.valueOf(pageNo));
				
				// 這裡的QueryTotalPages也是特別為query設計的總頁數
				request.setAttribute("totalPages", service.getQueryTotalPages());
				//為了保險起見我又多set query回去jsp用 
				request.setAttribute("query", query);
				// for 迴圈 把1,2,3,4....動態頁數加到List內給jsp用
				for (int i = 1; i <= service.getQueryTotalPages(); i++) {
					totalPages.add(i);
				}
				request.setAttribute("pages", totalPages);
				request.getRequestDispatcher("14_index.jsp").forward(request, response);
				//Else 這裡就跟老師的沒兩樣 沒有查詢就會執行這裡的頁數
				//需要特別注意的是我這支寫的沒有很嚴謹，所以如果jsp 上下頁那邊的C:URL沒有設定?後面的query=xxx的話
				//剛進去主查詢畫面會沒有查詢結果，因為完全沒有query這個參數連if else都不會判斷
				//如果是分開寫的可以再自己設計不同的判斷方式
			}else {
				ArrayList<Product> entList = service.getPdPages();
				System.out.println("進入無參數方法");
				for (int i = 1; i <= service.getTotalPages(); i++) {
					totalPages.add(i);
//					System.out.println(i);
				}
				
				request.setAttribute("baBean", pdProduct);
				session.setAttribute("pageNo", String.valueOf(pageNo));
				request.setAttribute("totalPages", service.getTotalPages());
				request.setAttribute("pages", totalPages);
				request.setAttribute("events_ent", entList);
				request.getRequestDispatcher("14_index.jsp").forward(request, response);
			}
			
			
			
			return;

		} catch (Exception e) {
			
			
		}
		
		
		
		
		
		
		
		
		
	  }
	
	
	public void gotoCart( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(false);
		ShoppingCartAP carList = (ShoppingCartAP)session.getAttribute("carList");
		
		//判斷購物車不存在時的導向
		if (carList == null) {
			request.getRequestDispatcher("14_index.jsp").forward(request, response);

			return;
		}
		
		
		request.getRequestDispatcher("14_OrderConfirm.jsp").forward(request, response);
		return;
		
	  }
	
	
	public void OrderSubmit(String orderNum, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		HttpSession session = request.getSession(true);
	    DataSource ds = null;
	    InitialContext ctxt = null;
	    Connection conn = null;
	    
	
	    }
	           
	  
	  
	
	
	
}
