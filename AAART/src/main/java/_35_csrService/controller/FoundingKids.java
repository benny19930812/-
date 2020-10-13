package _35_csrService.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _35_csrService.service.impl.FoundingServiceImpl;

@WebServlet("/_35_csrService/FoundingKids")
public class FoundingKids extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FoundingKids() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF8");
		response.setContentType("text/html;charset=UTF-8");
		
		String kidsLocation = String.valueOf(request.getParameter("kidsLocation"));
		FoundingServiceImpl fs = new FoundingServiceImpl();
		int kidsAmount = fs.getKidAmount(kidsLocation);
		request.setAttribute("kidsAmount", kidsAmount);
		RequestDispatcher rd = request.getRequestDispatcher("/_35_csrService/showKids.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
