package _14_shopAP;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Complete
 */
@WebServlet("/Complete")
public class Complete extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String adltTickets ="0";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Complete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");	
		PrintWriter out = response.getWriter();
		String act_title = request.getParameter("param1");
		
			try
			{
			    if(request.getParameter("param2") != null) {
			    	 adltTickets = request.getParameter("param2");
			    }
			}
			catch (NumberFormatException e)
			{
				
				adltTickets = "0";
				
			}
		
//		String adltTickets = request.getParameter("param2");
		String halfTickets = request.getParameter("param3");
		int adltNum = Integer.parseInt(adltTickets);
		int halfNum = Integer.parseInt(halfTickets);
		
		
			out.print("您參加的活動為");
			out.print(act_title);
			out.println("全票: "+adltTickets+"張");
			out.println("半票: "+halfTickets+"張");
			out.println("共計: "+(halfNum*500+adltNum*1000)+"元");
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
