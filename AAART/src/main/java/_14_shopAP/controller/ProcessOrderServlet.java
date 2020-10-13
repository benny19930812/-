package _14_shopAP.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _14_shopAP.Product;
import _14_shopAP.ProductItem;
import _14_shopAP.impl.OrderServiceImpl;
import _14_shopAP.orderbean.OrderItemBeanAP;
import _14_shopAP.orderbean.OrderListBeamAP;
import _14_shopAP.orderbean.ShoppingCartAP;
import _35_register.model.MemberBean;


@WebServlet("/_14_shopAP/ProcessOrderServlet")
public class ProcessOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
		response.setContentType("text/html");   
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		String finalDecision = request.getParameter("finalDecision");
		ShoppingCartAP sc = (ShoppingCartAP) session.getAttribute("carList");
		if (sc == null) {
			// 處理訂單時如果找不到購物車(通常是Session逾時)，沒有必要往下執行
			// 導向首頁
			response.sendRedirect(getServletContext().getContextPath() + "/_14_shopAP/14_index.jsp"  );
			return;
		}
		
		if  (finalDecision.equals("CANCEL")){
			session.removeAttribute("ShoppingCart");
			response.sendRedirect(response.encodeRedirectURL (request.getContextPath()));
			return;  			// 一定要記得 return 
		}
		
		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		if (mb == null) {
			response.sendRedirect(getServletContext().getContextPath() + "/index.jsp"  );
			return;
		}
		
		
		String memberID = mb.getMemberId();
		String addAP = request.getParameter("Address");
		String bnoAP = request.getParameter("BNO");
		String orderTitleAP = request.getParameter("InvoiceTitle");
		int totalAmount = sc.getSubtotal();
		Date today = new Date();
		System.out.println("ShippingAddress= "+ addAP);
		System.out.println("進入 ProcessOrderServlet");
		System.out.println("memberID"+memberID);
		System.out.println("我要印出這一行");
		OrderListBeamAP olb = new OrderListBeamAP(null, memberID, addAP, bnoAP, orderTitleAP,totalAmount, today, null);
		
		Set<OrderItemBeanAP> items = new HashSet<OrderItemBeanAP>();
		
		Map<String, ProductItem> cartAP = sc.getCartAP();
		
		Set<String> keySet = cartAP.keySet();
		
		for (String i : keySet) {
			ProductItem pi = cartAP.get(i);
			OrderItemBeanAP oibAP = new OrderItemBeanAP(null, null, pi.getProductTitleAP(), pi.getProductNumAP(), pi.getProductPriceAP());
			
			items.add(oibAP);
			
		}
		
		olb.setItems(items);
		try {
			OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
			orderServiceImpl.persistOrder(olb);
			session.removeAttribute("carList");
			response.sendRedirect(response.encodeRedirectURL ("14_ThanksForOrdering.jsp"));
			return;
		} catch(RuntimeException ex){
			String message = ex.getMessage();
			String shortMsg = "" ;   
			shortMsg =  message.substring(message.indexOf(":") + 1);
			System.out.println(shortMsg);
			session.setAttribute("OrderErrorMessage", "處理訂單時發生異常: " + shortMsg  + "，請調正訂單內容" );
			//System.out.println("處理訂單時發生異常: " + message);
			response.sendRedirect(response.encodeRedirectURL ("/_14_shopAP/14_OrderConfirm.jsp"));
			return;
		}
		
	}

}
