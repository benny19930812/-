package _04_Orderlist;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class OrderlistUpdate
 */
@WebServlet("/_04_Orderlist/OrderlistUpdate")
public class OrderlistUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	DataSource ds = null;

	public void init() throws ServletException {
		try {
			InitialContext ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe"); // for Oracle DB
		} catch (NamingException ne) {
			throw new ServletException(ne);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
		
		String ORDERID = (String) request.getSession().getAttribute("orderid");
		String NAME = request.getParameter("name");		
		String EMAIL = request.getParameter("email");
		String TEL = request.getParameter("tel");
		String ADDRESS = request.getParameter("add");
		System.out.println(NAME);
		System.out.println(EMAIL);
		System.out.println(TEL);
		System.out.println(ADDRESS);
		System.out.println(ORDERID);
		
		
		OrderListDAO orderListDAO =new OrderListDAO();
		//建立orderListDAO物件	
		Orderlistbean orderlistbean = new Orderlistbean();
		//建立orderlist物件
		
		
		orderlistbean.setORDERID(ORDERID);
		orderlistbean.setNAME(NAME);
		orderlistbean.setEMAIL(EMAIL);
		orderlistbean.setTEL(TEL);
		orderlistbean.setADDRESS(ADDRESS);
		orderListDAO.updateOrderuser(orderlistbean);
		System.out.println("更新成功");
		request.getSession().invalidate();      
    	System.out.println("清除session");
		} catch (Exception e) {
			// TODO: handle exception
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/_04_Orderlist/SuccessUpdate.jsp");
		dispatcher.forward(request, response);
		
	}

}
