package _14_shopAP.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _14_shopAP.Product;
import _14_shopAP.ProductItem;
import _14_shopAP.impl.ImportProductDaoImpl;
import _14_shopAP.orderbean.ShoppingCartAP;



@WebServlet("/_14_shopAP/ShopListController")
public class ShopListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;
	int carSize=0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ImportProductDaoImpl service = new ImportProductDaoImpl();
		Product pdProduct = new Product();
		System.out.println("進入ShopListController");
		HttpSession session = request.getSession(true);
		

		ShoppingCartAP carList = (ShoppingCartAP)session.getAttribute("carList");
		
		if (carList == null) {
			System.out.println("生成一個新的車方法");
	    	  carList = new ShoppingCartAP();
	        session.setAttribute("carList", carList);
	        session.setAttribute("carSize", carSize);

	      }else {
	    	  Map<String, ProductItem> cartAP = carList.getCartAP();
	    	
	    	  request.setAttribute("carSize", cartAP.size());
	      }
		
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
			// 先設定起始PAGENO
			service.setPageNo(pageNo);
			// query是查詢INPUT的參數
			String query = request.getParameter("query");
			String searchName = request.getParameter("searchName");
			//totalPage 是拿來接動態產生 1,2,3,4頁等等的超連結按鈕
			List<Integer> totalPages = new ArrayList<Integer>();
			
			// 這邊做if else 判斷查詢欄位有無值
			if (query != "" && query != null) {
				//有值用定義有query參數的List 
				//這裡的setQuery 是為了讓DAO裡的全域變數有值
				service.setQuery(query);
				System.out.println("query="+query);
				ArrayList<Product> entList = service.getQueryPdList(query);
				request.setAttribute("baBean", pdProduct);
				request.setAttribute("queryList", entList);
				session.setAttribute("pageNo", String.valueOf(pageNo));
//				System.out.println("queryList= " +entList.size());
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
			}else if((searchName != "" && searchName != null)){
				ArrayList<Product> pdListByName = service.getPdListByName(searchName);
				request.setAttribute("queryList", pdListByName);
				session.setAttribute("pageNo", String.valueOf(pageNo));
				request.setAttribute("totalPages", service.getQueryTotalPages());
				for (int i = 1; i <= service.getQueryTotalPages(); i++) {
					totalPages.add(i);
				}
				request.setAttribute("pages", totalPages);
				request.getRequestDispatcher("14_index.jsp").forward(request, response);
				
				
			}else{
				ArrayList<Product> entList = service.getPdPages();
				System.out.println("進入無參數方法");
				for (int i = 1; i <= service.getTotalPages(); i++) {
					totalPages.add(i);
					System.out.println(i);
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

}
