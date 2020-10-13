package _03_searchShowInfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_searchShowInfo.SingleShowInfoDAO;
import _03_searchShowInfo.SingleShowInfoTable;

/**
 * Servlet implementation class SingleShowInfoServlet
 */
@WebServlet("/_03_searchShowInfo/SingleShowInfoServlet")
public class SingleShowInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 建立Database Access Object,負責Table的Access
		SingleShowInfoDAO singleShowInfoDAO = new SingleShowInfoDAO();

		// 設定request & response 編碼值，以及session
		
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		String showNO = request.getParameter("ShowNO");			
	
		SingleShowInfoTable singleShowInfo = singleShowInfoDAO.singleShowInfoSearch(Integer.parseInt(showNO));
		
		request.setAttribute("SingleShowInfo", singleShowInfo); // 設定回傳屬性值

		RequestDispatcher rd = request.getRequestDispatcher("/_03_searchShowInfo/03_SingleShowInfo.jsp");
		rd.forward(request, response);
		return;
		
		
	}

}
