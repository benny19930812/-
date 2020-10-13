package _04_Orderlist;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import org.apache.commons.pool2.impl.AbandonedConfig;
import _04_Orderlist.*;
import _04_ShopCart.CartMap;


@WebServlet("/_04_Orderlist/OrderlistServlet")
public class OrderlistServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;    
	private static final String CONTENT_TYPE = "text/html; charset=utf-8";
	DataSource ds = null;
	
	public void init() throws ServletException {
		try {
			//在InitialContext取得JDBC連線資料
			InitialContext ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe"); // for Oracle DB
			System.out.println("DB連接成功");
		} catch (NamingException ne) {
			System.out.println("NamingException");
			throw new ServletException(ne);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);

		try {
			String NAME = (String) request.getSession().getAttribute("name");
			String EMAIL = (String) request.getSession().getAttribute("email");
			String TEL = (String) request.getSession().getAttribute("tel");
			String ADDRESS = (String) request.getSession().getAttribute("add");
			int totalprice = Integer.parseInt( (String) request.getSession().getAttribute("totalprice"));
			System.out.println(totalprice );
			
			
			OrderListDAO orderListDAO =new OrderListDAO();
			//建立orderListDAO物件
			
			Orderlistbean orderlist = new Orderlistbean();
			//建立orderlist物件
			
			String ORDERID =orderListDAO.getOrderIdByTime();
			//建立物件 . 來調用方法
			
			orderlist.setORDERID(ORDERID);
			orderlist.setNAME(NAME);			
			orderlist.setEMAIL(EMAIL);
			orderlist.setTEL(TEL);
			orderlist.setADDRESS(ADDRESS);
			orderlist.setTOTALPRICE(totalprice);
			orderListDAO.addUser(orderlist);

			

			
			HashSet<HashMap>  cartlist =(HashSet<HashMap>) request.getSession().getAttribute("cartlist");
			for (HashMap carmap : cartlist) {		
				String title = (String) carmap.get("title");	
				int halfnum =Integer.parseInt( (String) carmap.get("halfnum"));					
				System.out.println(" halfnum");
				int adultnum =Integer.parseInt( (String)carmap.get("adultnum"));	
//				int total1 =  (int)carmap.get("total1");	
//				int total2 =  (int)carmap.get("total2");	
	
				Orderlistbean orderlist2 = new Orderlistbean();
				orderlist2.setORDERID(ORDERID);
				orderlist2.setTITLE(title);
				orderlist2.setHALF_NUM(halfnum);
				orderlist2.setADULT_NUM(adultnum);
				orderListDAO.addlist(orderlist2);
						
			}
			
			PrintWriter out = response.getWriter();
	        System.out.println("訂單已成立");
	        
	    	
	    	request.getSession().invalidate();
	        
	    	System.out.println("清除session");
	        
	        
	        request.setAttribute("orderid", ORDERID);

			
		} catch (Exception e) {
			// TODO: handle exception
		}	
		RequestDispatcher dispatcher = request.getRequestDispatcher("/_04_Orderlist/ThxOrder.jsp");
		dispatcher.forward(request, response);
//		
	}
//	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType(CONTENT_TYPE);
//		doPost(request, response);
//	}

}
