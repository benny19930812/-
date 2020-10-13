package _14_shopAP.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _14_shopAP.orderbean.ShoppingCartAP;


@WebServlet("/_14_shopAP/ProductUpdateServlet")
public class ProductUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		
		ShoppingCartAP sCartAP = (ShoppingCartAP)session.getAttribute("carList");
		String cmd = request.getParameter("cmd");
		String productIdAP = request.getParameter("productIdAP");
		String productID = productIdAP;
		
		System.out.println("cmd："+cmd);
		if (cmd.equalsIgnoreCase("DEL")) {
			sCartAP.deleteBook(productID); // 刪除購物車內的某項商品
			System.out.println("回到44行");
	        RequestDispatcher rd = request.getRequestDispatcher("/_14_shopAP/14_OrderConfirm.jsp");
		    rd.forward(request, response);
		    return;
		} else if (cmd.equalsIgnoreCase("MOD")) {
			System.out.println("進入 MOD");
			String newQtyStr = request.getParameter("newQty");
			System.out.println("newQty= "+newQtyStr);
			System.out.println("productID："+productID);
			
			int newQty = Integer.parseInt(newQtyStr.trim());
			sCartAP.modifyQty(productID, newQty);   // 修改某項商品的數項
			System.out.println("第 55 行");
	        RequestDispatcher rd = request.getRequestDispatcher("/_14_shopAP/14_OrderConfirm.jsp");
		    rd.forward(request, response);
		    return;
		}
		
		
	}

}
