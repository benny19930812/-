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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;


@WebServlet("/_04_ST/SearchAllServlet")
public class SearchAllServlet extends HttpServlet {
	public List<ShowOj> Showlist() {
		//實作一個arrylist，list為介面
		List<ShowOj> lists = new ArrayList<ShowOj>();
		return lists;
	}

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

		List<Map> list =new ArrayList<Map>();
		// Searchall
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

		String query = "SELECT ACT_NO,ACT_TITLE,ACT_LOCATION_NAME,ACT_DESCRIPTION FROM MAINTABLE " + "WHERE ACT_TITLE LIKE \'%" + queryVal + "%\'";
	
		try {
			Connection conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
//			連接jdbc
			
			for (int count = 0;; count++) {
				if (rs.next()) {
					String no = rs.getString(1);
					String title = rs.getString(2);
					String site = rs.getString(3);
					String description = rs.getString(4);
					
	
				
					Map map = new HashMap(); 
					map.put("no", no);			
					map.put("title", title);		
					map.put("site", site);		
					map.put("description", description);		

					
//					用键值对存入到map集合中
//					System.out.println(map);
					list.add(map);//在将map集合对象存入list集合
//					System.out.println("放入集合");
					for (Map map_1 :list) {
//						System.out.println(map_1);
					}//在打印台遍历出数据查看是否有错误

				
				} else {
//             System.out.println(count);
           break;
				}
//				request.setAttribute("no", no);
//				request.setAttribute("title", title);
//				RequestDispatcher dispatcher = request.getRequestDispatcher("/04_select.jsp");
//				dispatcher.forward(request, response);
			}
			rs.close();
			stmt.close();
			conn.close();
			
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
			
			request.setAttribute("key_list",list);//将list集合数据放入到request中共享
			request.getRequestDispatcher("/_04_ST/04_select.jsp").forward(request, response);
			out.close();
		}
		catch (SQLException se) {
			se.printStackTrace(out);
		}
	}
}
