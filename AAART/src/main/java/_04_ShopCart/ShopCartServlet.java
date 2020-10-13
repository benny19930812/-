package _04_ShopCart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _04_ShopCart.*;


@WebServlet("/_04_ShopCart/ShopCartServlet")
public class ShopCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	
	
	String title = (String) request.getSession().getAttribute("title");
	String  halfnum =  request.getParameter("halfnum");
	String  adultnum =  request.getParameter("adultnum");
	int  total1 =    Integer.parseInt(request.getParameter("total1"));
	//String轉型成 int以利計算
	int  total2 =    Integer.parseInt(request.getParameter("total2"));
	int  totalprice =   Integer.parseInt( request.getParameter("total3"));

System.out.println(total1);
System.out.println(total2);
	
	
	HttpSession session = request.getSession();	

	Set<Map> cartlist = (Set<Map>) session.getAttribute("cartlist");
	if (cartlist == null) {// 若session中並無cart,則創建cart
		cartlist = new HashSet<Map>();
		System.out.println("new新session");
		Map cartmap =new HashMap();
		cartmap.put("title", title);
		cartmap.put("halfnum", halfnum);
		cartmap.put("adultnum", adultnum);
		cartmap.put("total1", total1);
		cartmap.put("total2", total2);
		cartmap.put("totalprice", totalprice);
		// 存入map集合中
		System.out.println(cartmap);
		cartlist.add(cartmap);// 將map集合放入list集合
		System.out.println("放入集合");
		for (Map map_1 : cartlist) {
			System.out.println(map_1);
			
		}
	}
	else {
	Map cartmap =new HashMap();
	cartmap.put("title", title);
	cartmap.put("halfnum", halfnum);
	cartmap.put("adultnum", adultnum);
	cartmap.put("total1", total1);
	cartmap.put("total2", total2);
	cartmap.put("totalprice", totalprice);
	// 存入map集合中
	System.out.println(cartmap);
	cartlist.add(cartmap);// 將map集合放入list集合
	System.out.println("放入集合");
	for (Map map_1 : cartlist) {
		System.out.println(map_1);
		
	}
	}
	session.setAttribute("cartlist", cartlist);// 将list放入request中
	RequestDispatcher dispatcher = request.getRequestDispatcher("/_04_ST/04_Booking2.jsp");
	dispatcher.forward(request, response);
				
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
		
	}
}
