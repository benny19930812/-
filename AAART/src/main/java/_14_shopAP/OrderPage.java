package _14_shopAP;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class OrderPage
 */
@WebServlet("/OrderPage")
public class OrderPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public OrderPage() {
        super();
    }
    
    DataSource ds = null;

    public void init() throws ServletException
    {
      try 
      {
        InitialContext ctxt = new InitialContext();
        ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");  // for Oracle DB
      }
      catch (NamingException ne)
      {
        throw new ServletException(ne);
      }
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOPage14 daoPage = new DAOPage14();
		List<Shows> listShows = daoPage.listShows();
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		String checkoutURL =
		          response.encodeURL("check.jsp");
		
		out.println(
				"<!DOCTYPE html>\r\n" + 
				"<html lang=\"en\">\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"    <meta charset=\"UTF-8\">\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + 
				"<FORM ACTION=\"" + checkoutURL + "\">\n" +
				"    <title>OrderPage</title>\r\n" + 
				"\r\n" + 
				"    <style>\r\n" + 
				"        .tb{\r\n" + 
				"            border: darkolivegreen solid 2px;\r\n" + 
				"        }\r\n" + 
				"\r\n" + 
				"        th{\r\n" + 
				"\r\n" + 
				"            border: darkolivegreen solid 2px;\r\n" + 
				"        }\r\n" + 
				"    </style>\r\n" + 
				"</head>"+
				" <table class=\"tb\">\r\n" + 
				"        <thead>\r\n" + 
				"            <tr>\r\n" + 
				"                <th colspan=\"3\">活動一覽</th>\r\n" + 
				"                \r\n" + 
				"            </tr>\r\n" + 
				"\r\n" + 
				"            <tr>\r\n" + 
				"                <th>活動內容</th>\r\n" + 
				"                <th>票價</th>\r\n" + 
				"                <th>購買按鍵</th>\r\n" + 
				"            </tr>"
				
				);
		for (Shows show : listShows) {
			
			out.println(
					"<tr>\r\n" + 
					"<th>"+show.getTitle()+"</th>\r\n" + 
					"<th style=\"width: 150px;\">全票:1000 /<br>半票:500</th>\r\n" +           
					"<th><button type=\"submit\" name=\"param1\" value=\""+show.getTitle() +" \">前往購買 <th>"  +              
					"</tr> "  
			 
					
					);
			
			
			
			System.out.println(show.getTitle());
			
		}
		
	
//		out.println
//        ("</TABLE>\n" +
//         "<FORM ACTION=\"" + checkoutURL + "\">\n" +
//         "<BIG><CENTER>\n" +
//         "<INPUT TYPE=\"SUBMIT\"\n" +
//         "       VALUE=\"前往結帳\">\n" +
//         "</CENTER></BIG></FORM>");
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
