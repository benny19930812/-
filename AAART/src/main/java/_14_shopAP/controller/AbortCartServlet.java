package _14_shopAP.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _14_shopAP.orderbean.ShoppingCartAP;

/**
 * Servlet implementation class AbortCartServlet
 */
@WebServlet("/_14_shopAP/AbortCartServlet")
public class AbortCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        ShoppingCartAP cart = (ShoppingCartAP)session.getAttribute("carList");
		if (cart != null) {
			//由session物件中移除ShoppingCart物件
			session.removeAttribute("carList");
		}
		response.sendRedirect(response.encodeRedirectURL ("14_OrderConfirm.jsp"));
		return;
	}

}
