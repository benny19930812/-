package _03_searchShowInfo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import _03_CultureAndCreativeShops_CMS.CultureAndCreativeShops_DAO;
import _03_searchShowInfo.CategoryBarDAO;
import _03_searchShowInfo.CategoryTable;

/**
 * Servlet implementation class CategoryBarServlet
 */
@WebServlet("/_03_searchShowInfo/CategoryBarServlet")
public class CategoryBarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DataSource ds = null;
		InitialContext ctxt = null;
		Connection connection = null;

		try {

			// 建立Context Object,連到JNDI Server
			ctxt = new InitialContext();

			// 使用JNDI API找到DataSource
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			// 向DataSource要Connection
			connection = ds.getConnection();

			// 建立Database Access Object,負責Table的Access
			CategoryBarDAO categoryBarDAO = new CategoryBarDAO();

			// 設定request & response 編碼值，以及session
			
			request.setCharacterEncoding("UTF-8");	
			response.setContentType("text/html; charset=UTF-8");
			HttpSession session = request.getSession();
			
			// 取得categorySearch 類別按鈕的值，回傳List型態，內含20個categoryTable物件
			String categoryId = request.getParameter("CategoryId");			
			List<CategoryTable> categoryTable_20 = categoryBarDAO.categoryBarSearch(Integer.parseInt(categoryId));
			CultureAndCreativeShops_DAO  shops_DAO = new CultureAndCreativeShops_DAO();
			
			
			
			request.setAttribute("CategoryTable", categoryTable_20); // 設定回傳屬性值

			RequestDispatcher rd = request.getRequestDispatcher("/_03_searchShowInfo/03_CategorySearch.jsp");
			rd.forward(request, response);
			return;
			
			// 期末
			// 之後session attribute，加上分類偏好(category preference)
			// 已登入帳號，回傳分類偏好(category preference)點擊率
			
		} 
		catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

}
