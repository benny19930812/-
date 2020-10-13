package _03_CultureAndCreativeShops_CMS;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_input.CultureAndCreativeShopsTable;
import _03_searchShowInfo.CategoryTable;
import _03_searchShowInfo.SingleShowInfoDAO;
import _03_searchShowInfo.SingleShowInfoTable;

/**
 * Servlet implementation class CultureAndCreativeShops_DeleteShopInfo
 */
@WebServlet("/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_DeleteShopInfo")
public class CultureAndCreativeShops_DeleteShopInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 建立Database Access Object,負責Table的Access
		CultureAndCreativeShops_DAO shops_DAO = new CultureAndCreativeShops_DAO();

		// 設定request & response 編碼值，以及session

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		try {
			CultureAndCreativeShops_DAO Shops_DAO = new CultureAndCreativeShops_DAO();
			String id = request.getParameter("MainTypePk");
			int intID = Integer.parseInt(id);
			Shops_DAO.deleteShopInfo(intID);
			System.out.println("商家資料刪除成功");
			request.setAttribute("shopDeleteMsg", "商家刪除成功");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("商家資料刪除失敗");
			request.setAttribute("shopDeleteMsg", "商家刪除失敗");
		}

		RequestDispatcher rd = request.getRequestDispatcher("/_03_CultureAndCreativeShops_CMS/Return.jsp");
		rd.forward(request, response);
		return;

	}

}
