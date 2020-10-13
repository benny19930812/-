package _35_geoSearch.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _35_geoSearch.model.PositionBean;
import _35_geoSearch.service.impl.PositionServiceImpl;

@WebServlet("/_35_geoSearch/RandomRecom")
public class RandomRecom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandomRecom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF8");
		response.setContentType("text/html;charset=UTF-8");
		
//		PositionServiceImpl service = new PositionServiceImpl();
//		List<PositionBean> list = service.getRandomAddress();
//		request.setAttribute("random3", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/_35_geoSearch/nearlestLocation.jsp");
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
