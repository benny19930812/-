package _14_shopAP.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _14_shopAP.Product;

/**
 * Servlet implementation class ShowOneProductController
 */
@WebServlet("/_14_shopAP/ShowOneProductController")
public class ShowOneProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		String productID = request.getParameter("productID");
		String orderPrice = request.getParameter("orderPrice");
		String orderImg = request.getParameter("orderImg");
		String orderNum = request.getParameter("orderNum");
		String orderDes = request.getParameter("orderDes");
		String param1 = request.getParameter("param1");
		String query = request.getParameter("query");
		System.out.println("ShowOneProduct_query="+query);
		
		Product product = new Product();
		
		product.setProductId(productID);
		product.setProductTitle(param1);
		product.setProductNum(Integer.parseInt(orderNum));
		product.setProductPrice(orderPrice);
		product.setProductDes(orderDes);
		product.setProductImg(orderImg);
		
		//設定每個商品的庫存量
		List<Integer> stockNum = new ArrayList<Integer>();
		for (int i = 1; i <= Integer.parseInt(orderNum); i++) {
			stockNum.add(i);
		}
		request.setAttribute("stockNum", stockNum);
		request.setAttribute("oneProsuct", product);
		request.setAttribute("query", query);
		request.getRequestDispatcher("14_ShowOneProduct.jsp").forward(request, response);

	}

}
