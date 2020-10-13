package _14_shopAP.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import _14_shopAP.Product;
import _14_shopAP.impl.ImportProductDaoImpl;

/**
 * Servlet implementation class CRUDServlet
 */
@WebServlet("/_14_shopAP/CRUDServlet")
public class CRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		
		String method = request.getParameter("method");
		String apidString =request.getParameter("apid");
		String APTITLE = request.getParameter("APTITLE");
		String APTYPE = request.getParameter("APTYPE");
		String APPRICE = request.getParameter("APPRICE");
		String APIMG = request.getParameter("APIMG");
		String APDES = request.getParameter("APDES");
		String APNUM = request.getParameter("APNUM");
		
		
		if ("search".equals(method)) {
			searchAPId(apidString, request, response);
		}else if ("delete".equals(method)) {
			deleteAPId(apidString, request, response);
		}else if("insert".equals(method)){
			insertAP(apidString, APTITLE,APTYPE,APPRICE,APIMG,APDES, APNUM, request, response);
		}else if("modify".equals(method)){
			modifyAP(apidString,request, response);
		}else if("modifComplete".equals(method)){
			modifComplete(apidString, APTITLE,APTYPE,APPRICE,APIMG,APDES, APNUM, request, response);
		}
		
	}
	
	
	
	
	private void modifComplete(String apidString, String APTITLE, String APTYPE, String APPRICE, String APIMG,
			String APDES, String APNUM, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ImportProductDaoImpl service = new ImportProductDaoImpl();
		String apidString2 = apidString+" ";
		System.out.println("apidString2"+apidString);
		 Product pd = new  Product();
		 pd.setProductId(apidString2);
//		 pd.setProductId(apidString);
		 pd.setProductTitle(APTITLE);
		 pd.setProductType(APTYPE);
		 pd.setProductPrice(APPRICE);
		 pd.setProductImg(APIMG);
		 pd.setProductDes(APDES);
		 int productNum = Integer.parseInt(APNUM);
		 pd.setProductNum(productNum);
//		 System.out.println("進入modifComplete");
		 service.modifyAP(pd);
		
		 request.getRequestDispatcher("14_CRUDPage.jsp").forward(request, response);
		
	}

	private void modifyAP(String apidString, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ImportProductDaoImpl service = new ImportProductDaoImpl();
		ArrayList<Product> preciseAPID = service.getPreciseAPID(apidString);

		if (preciseAPID.size()==0) {
			session.setAttribute("OrderErrorMessage", "查無商品編號"+ apidString +"商品");	
			request.getRequestDispatcher("14_CRUDPage.jsp").forward(request, response);

		}
		

//		session.setAttribute("OrderErrorMessage", "真的有"+ apidString +"商品喔");
		request.setAttribute("preciseAP", preciseAPID);
		request.getRequestDispatcher("14_ModifyPd.jsp").forward(request, response);
		
		
	}

	private void insertAP(String apidString, String APTITLE, String APTYPE, String APPRICE, String APIMG,
			String APDES, String APNUM, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		ImportProductDaoImpl service = new ImportProductDaoImpl();
		ArrayList<Product> preciseAPID = service.getPreciseAPID(apidString);
		if (preciseAPID.size()!=0) {
			session.setAttribute("OrderErrorMessage", "商品貨號 "+ apidString +" 已被使用，您可能會想嘗試將貨號改為 "+apidString+(int)(Math.random()*100));	
			request.getRequestDispatcher("14_CRUDPage.jsp").forward(request, response);

		}
		 Product pd = new  Product();
		 pd.setProductId(apidString);
		 pd.setProductTitle(APTITLE);
		 pd.setProductType(APTYPE);
		 pd.setProductPrice(APPRICE);
		 pd.setProductImg(APIMG);
		 pd.setProductDes(APDES);
		 int productNum = Integer.parseInt(APNUM);
		 pd.setProductNum(productNum);
		 
		service.saveAPPdroduct(pd);
		session.setAttribute("SuccessMessage", "商品貨號 "+ apidString +" 新增成功!!");	
		request.getRequestDispatcher("14_CRUDPage.jsp").forward(request, response);
	}

	public void searchAPId(String apidString, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		
		ImportProductDaoImpl service = new ImportProductDaoImpl();
		ArrayList<Product> productAPID = service.getProductAPID(apidString);
		
		request.setAttribute("searchList", productAPID);
		
		request.getRequestDispatcher("14_CRUDPage.jsp").forward(request, response);
		
	  }
	
	public void deleteAPId(String apidString, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		
		HttpSession session = request.getSession(false);
		ImportProductDaoImpl service = new ImportProductDaoImpl();
		ArrayList<Product> preciseAPID = service.getPreciseAPID(apidString);

		if (preciseAPID.size()==0) {
			session.setAttribute("OrderErrorMessage", "查無商品編號"+ apidString +"商品");	
			request.getRequestDispatcher("14_CRUDPage.jsp").forward(request, response);

		}
		service.DeletProduct(apidString);
//		ArrayList<Product> productAPID = service.getProductAPID(apidString);
//		request.setAttribute("searchList", productAPID);
		session.setAttribute("SuccessMessage", "商品貨號 "+ apidString +" 刪除成功!!");	
		request.getRequestDispatcher("14_CRUDPage.jsp").forward(request, response);
	  }
	
	
	

}
