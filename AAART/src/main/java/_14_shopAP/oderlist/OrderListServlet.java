package _14_shopAP.oderlist;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _14_shopAP.impl.OrderServiceImpl;
import _14_shopAP.orderbean.OrderListBeamAP;
import _35_register.model.MemberBean;


@WebServlet("/_14_shopAP/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session == null) {      // 使用逾時
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp");
			return;
		}
		OrderServiceImpl os = new OrderServiceImpl();
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		String memberID= mb.getMemberId();
		List<OrderListBeamAP> customerOrder = os.getCustomerOrder(memberID);
		System.out.println("customerOrder"+customerOrder);
		request.setAttribute("customerOrder", customerOrder);
		RequestDispatcher rd = request.getRequestDispatcher("/_14_shopAP/14_OrderList.jsp");
		rd.forward(request, response);
		return;
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
