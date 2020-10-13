package _14_shopAP;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Check_new
 */
@WebServlet("/Check_new")
public class Check_new extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<CartItem> CartItem;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check_new() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession(true);
	synchronized(session) {
	
		
//		Vector carList = (Vector)session.getAttribute("carList");
		
		List<CartItem> carList = (List<CartItem>)session.getAttribute("carList");
		
	      
	      // New visitors get a fresh shopping cart.
	      // Previous visitors keep using their existing cart.
	      if (carList == null) {
	    	  carList = new ArrayList<CartItem>();
	     
	       
	        session.setAttribute("carList", carList);
	        
	        	
//	        CartItem = new ArrayList<CartItem>();
	      }
		
		
//		int count = 0;
		String act_nameString = request.getParameter("param1");
		String adltNum = request.getParameter("param2");
		String halfNum = request.getParameter("param3");
			
		CartItem ca = new CartItem();
		ca.setCar_no(Math.random());
		ca.setCar_title(act_nameString);
		ca.setCar_adltNum(adltNum);
		ca.setCar_halfNum(halfNum);
		
		for(int i=0; i < carList.size(); i++) {
			CartItem order;
			order = (CartItem) carList.get(i);
			
			if (ca.getCar_no()==(order.getCar_no())) {
//				System.out.println(ca.getCar_title());
//				System.out.println(order.getCar_title());
//				System.out.println(carList);
//				carList.removeElement(order);
				System.out.println(carList.remove(i));
				
//				System.out.println(carList);
//				System.out.println(i);
			}
			
			if (ca.getCar_title().equals(order.getCar_title())) {
				ca.setCar_adltNum(Integer.toString(Integer.parseInt(ca.getCar_adltNum())+Integer.parseInt(order.getCar_adltNum())));
				ca.setCar_halfNum(Integer.toString(Integer.parseInt(ca.getCar_halfNum())+Integer.parseInt(order.getCar_halfNum())));
				System.out.println(carList.remove(i));
			}
		
		}
		synchronized(this) {
			
			carList.add(ca);
//			carList.addAll(CartItem);
		}
		
//		carList.addElement(act_nameString);
//		carList.addElement(adltNum);
//		carList.addElement(halfNum);
		
		out.print("成功加入購物車");
		out.print("目前物品: <br>");
		out.print(session);
		for(int i=0; i<carList.size(); i++) {
			CartItem order;
			order = carList.get(i);
//			order = (CartItem)carList.elementAt(i);
			
//			order = carList.elementAt(i);
	        out.println
	            ("vector size: "+carList.size()+
	             "  <p>" + order.getCar_title() + "</p>" +
	             "  <p> 全票: " + order.getCar_adltNum() + " 張</p>\n" +
	             "  <p>半票: " +order.getCar_halfNum() +" 張</p>");
	        }
		}

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
