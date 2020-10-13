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
import _03_searchShowInfo.CategoryBarDAO;
import _03_searchShowInfo.CategoryTable;

/**
 * Servlet implementation class CultureAndCreativeShops_SearchShopsInfo
 */
@WebServlet("/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_SearchShopsInfo")
public class CultureAndCreativeShops_SearchShopsInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CultureAndCreativeShops_DAO shops_DAO = new CultureAndCreativeShops_DAO();

		// 設定request & response 編碼值，以及session

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		// 取得categorySearch 類別按鈕的值，回傳List型態，內含20個categoryTable物件
		try {
			String name = request.getParameter("Name");
			System.out.print(name);
			List<CultureAndCreativeShop> shopsList = shops_DAO.searchShops(name);

			request.setAttribute("ShopsList", shopsList); // 設定回傳屬性值

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("資料搜尋失敗");
			String serachErrorMsg = "資料搜尋失敗" ;
			request.setAttribute("SerachErrorMsg", serachErrorMsg); // 回傳錯誤訊息
		}

		RequestDispatcher rd = request
				.getRequestDispatcher("/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_ShopsInfo.jsp");
		rd.forward(request, response);
		return;

	}

}
