package _04_ST;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.*;
import javax.sql.*;

@WebServlet("/_04_ST/SearchServlet")
public class SearchServlet extends HttpServlet {

	private static final String CONTENT_TYPE = "text/html; charset=utf-8";

	DataSource ds = null;

	public void init() throws ServletException {
		try {
			InitialContext ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe"); // for Oracle DB
		} catch (NamingException ne) {
			throw new ServletException(ne);
		}
	}

	public void doPOST(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String queryVal = request.getParameter("queryVal");
		String querysite = request.getParameter("site");
		String queryCate = request.getParameter("category");		
		String querystartdate = request.getParameter("startdate");
		String queryenddate = request.getParameter("enddate");
		
		//使用.equals("")來判定字串是否相同 ==判定的是物件位址 

		if (!queryVal.equals("")) {	
			request.getRequestDispatcher("/_04_ST/SearchAllServlet").forward(request, response);				
		}
		else if (!querysite.equals("")) {
			request.getRequestDispatcher("/_04_ST/SiteSearchServlet").forward(request, response);	
		}
		else if (!queryCate.equals("")) {
			request.getRequestDispatcher("/_04_ST/CategorySearchServlet").forward(request, response);			
		}
		else if (!querystartdate.equals("")) {
			request.getRequestDispatcher("/_04_ST/StartdateSearchServlet").forward(request, response);			
		}
		else if (!queryenddate.equals("")) {
			request.getRequestDispatcher("/_04_ST/EndSearchServlet").forward(request, response);			
		}
		else {
			request.getRequestDispatcher("/_04_ST/SearchAllServlet").forward(request, response);	
		}
	}
}

