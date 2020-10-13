package _04_ST;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.*;
import javax.sql.*;



@WebServlet("/_04_ST/EndSearchServlet")
public class EnddateSearchServlet extends HttpServlet {

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
		List<Map> list = new ArrayList<Map>();
		// 結束日期查詢

		String queryVal = request.getParameter("queryVal");
		System.out.println(queryVal);
		//string再改為url編碼 提供頁數的網址使用
		String encodedQuery = URLEncoder.encode(queryVal, "UTF-8");
		System.out.println(encodedQuery);
		String querysite = request.getParameter("site");
		String queryCate = request.getParameter("category");		
		String querystartdate = request.getParameter("startdate");
		String queryenddate = request.getParameter("enddate");
	
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		try {

			// 將request日期字串轉為date
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");

			Date requestDate = sdf2.parse(queryenddate);
			System.out.println(requestDate);
			// 帶入list內日期字串 轉為date格式

			SelectDAO selectDAO = new SelectDAO();
			List<ShowOj> showList = selectDAO.Showlist();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-EE");
			for (ShowOj showOj : showList) {
				String dateString = showOj.getACT_START_DATE();
				int noint = showOj.getACT_NO();
				String titleString = showOj.getACT_TITLE();
				String siteString = showOj.getACT_LOCATION_NAME();
				String description = showOj.getACT_DESCRIPTION();
				Date date;
				date = sdf.parse(dateString);
				// System.out.println(date);
				request.getAttribute("");
				if (date.before(requestDate)) {
					Map map = new HashMap();
					map.put("no", noint);
					map.put("title", titleString);
					map.put("site", siteString);
					map.put("description", description);
					map.put("date", dateFormat.format(date));

					// 存入map集合中
					System.out.println(map);
					list.add(map);// 將map集合放入list集合
//					System.out.println("放入集合");
					for (Map map_1 : list) {
//						System.out.println(map_1);
					}
				}
			}

			String p = request.getParameter("page");
			int page;
			try {
			//當前頁數

			page = Integer.valueOf(p);
			} catch (NumberFormatException e) {
				page = 1;
			}
			//搜尋後總活動數
			int totalnum = list.size();
			
			System.out.println("共"+totalnum+"筆資料");
			//每頁顯示活動數
			int PerPage = 100;
			//總頁數
			int totalPages = totalnum % PerPage == 0 ? totalnum / PerPage : totalnum / PerPage ;
			//本頁起始使用者序號
			int beginIndex = (page - 1) * PerPage;
			//本頁末尾使用者序號的下一個
			int endIndex = beginIndex +PerPage;
			if (endIndex > totalnum)
				endIndex = totalnum;
			request.setAttribute("totalnum", totalnum);
			request.setAttribute("PerPage", PerPage);
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("beginIndex", beginIndex);
			request.setAttribute("endIndex", endIndex);
			request.setAttribute("page", page);
			
			request.setAttribute("queryVal", encodedQuery);
			request.setAttribute("category", queryCate);
			request.setAttribute("site", querysite);
			request.setAttribute("startdate", querystartdate);
			request.setAttribute("enddate", queryenddate);			
			
			
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("key_list", list);// 将list放入request中
		RequestDispatcher dispatcher = request.getRequestDispatcher("/_04_ST/04_select.jsp");
		dispatcher.forward(request, response);

	}
}
