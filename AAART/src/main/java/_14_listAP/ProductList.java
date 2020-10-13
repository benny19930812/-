package _14_listAP;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _14_init.ProductBean;
import _14_listAP.service.ProductService;
import _14_listAP.service.ProductServiceInterface;

import javax.servlet.http.Cookie;

@WebServlet("/_14_listAP/ProductList")
public class ProductList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageNo = 1;
  
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ProductServiceInterface productService = new ProductService();
		//
		
		// 讀取瀏覽送來的 pageNo
				String pageNoStr = request.getParameter("pageNo");
				// 如果讀不到，直接點選主功能表的『購物』就不會送 pageNo給後端伺服器
				if (pageNoStr == null) {  
					pageNo = 1;
					
				} else {
					try {
						pageNo = Integer.parseInt(pageNoStr.trim());
					} catch (NumberFormatException e) {
						pageNo = 1;
					}
				}
		// 讀取一頁的書籍資料之前，告訴service，現在要讀哪一頁
		productService.setPageNo(pageNo);
		request.setAttribute("baBean", productService);
		// service.getPageBooks()方法開始讀取一頁的書籍資料
		Collection<ProductBean> coll = productService.getPageBooks();
		session.setAttribute("pageNo", String.valueOf(pageNo));
		request.setAttribute("totalPages", productService.getTotalPages());
		// 將讀到的一頁資料放入request物件內，成為它的屬性物件
		request.setAttribute("products_DPP", coll);
		RequestDispatcher rd = request.getRequestDispatcher("listBooks.jsp");
		rd.forward(request, response);
		return;

	}
	

}
