package _04_ShopCart;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/_04_ShopCart/ClearCart")
public class ClearCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

	
	System.out.println("清除session");
	
	request.getSession().invalidate();
	
	

	RequestDispatcher dispatcher = request.getRequestDispatcher("/_04_ShopCart/_04_ShoppingCart.jsp");
	dispatcher.forward(request, response);
		
		
		
		
		
		
	}

}
