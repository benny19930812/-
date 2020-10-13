package _04_Orderlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import _04_Orderlist.*;

@WebServlet("/_04_Orderlist/OrIdDeleteServlet")
public class OrIdDeleteServlet extends HttpServlet {

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

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		try {	
			String orderid = (String) request.getSession().getAttribute("orderid");
			
			OrderListDAO orderListDAO = new OrderListDAO();
			Orderlistbean orderlistbean =new Orderlistbean();
			orderlistbean.setORDERID(orderid);
			orderListDAO.deleteorderUSER(orderlistbean);
			orderListDAO.deleteorderlist(orderlistbean);
			System.out.println("訂單已刪除");
		
			request.getSession().invalidate();      
	    	System.out.println("清除session");
	        

		} catch (Exception e) {
			// TODO: handle exception
			

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/_04_Orderlist/SuccessDelete.jsp");
		dispatcher.forward(request, response);

	}
}
