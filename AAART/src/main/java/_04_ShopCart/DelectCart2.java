package _04_ShopCart;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/_04_ShopCart/DelectCart2")
public class DelectCart2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	protected void doPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("here");
		HashSet<HashMap>  cartlist =(HashSet<HashMap>) request.getSession().getAttribute("cartlist");
		for (HashMap carmap : cartlist) {	
			carmap.remove("title");
			carmap.remove("halfnum");
			carmap.remove("adultnum");
			carmap.remove("total1");
			carmap.remove("total2");
			for (Map map_1 : cartlist) {
				System.out.println(map_1);
				
			}
		}

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/_04_ShopCart/_04_ShoppingCart.jsp");
		dispatcher.forward(request, response);		
		

}

}