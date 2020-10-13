package _14_shopAP.oderlist;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _14_shopAP.impl.OrderServiceImpl;
import _14_shopAP.orderbean.OrderListBeamAP;

/**
 * Servlet implementation class OrderDetailServlet
 */
@WebServlet("/OrderDetailServlet")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String orderNo = request.getParameter("orderNo");
		System.out.println("orderNo= "+ orderNo);
		int no = Integer.parseInt(orderNo.trim());

		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		OrderListBeamAP ob = orderServiceImpl.getOrder(no);
		System.out.println("ob.getItems()"+ob.getItems());
		request.setAttribute("OrderBean", ob);   // 將OrderBean物件暫存到請求物件內
		RequestDispatcher rd = request.getRequestDispatcher("/_14_shopAP/14_OrderDetail.jsp");
		rd.forward(request, response);
		return;
	}
		
}


