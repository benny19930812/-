package _03_CultureAndCreativeShops_CMS;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import _03_input.CultureAndCreativeShopsTable;

/**
 * Servlet implementation class CultureAndCreativeShops_UpdateShopInfo
 */
@WebServlet("/_03_CultureAndCreativeShops_CMS/CultureAndCreativeShops_UpdateShopInfo")
public class CultureAndCreativeShops_UpdateShopInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 建立Database Access Object,負責Table的Access
		CultureAndCreativeShops_DAO shops_DAO = new CultureAndCreativeShops_DAO();

		// 設定request & response 編碼值，以及session

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		CultureAndCreativeShop shop = new CultureAndCreativeShop();

		shop.setGroupTypeName("文創商店");
		shop.setMainTypeName("文創商店");

		String name = request.getParameter("Name");
		shop.setName(name);

		String representImage = request.getParameter("RepresentImage");
		shop.setRepresentImage(representImage);

		String intro = request.getParameter("Intro");
		shop.setIntro(intro);

		String cityName = request.getParameter("CityName");
		shop.setCityName(cityName);

		String address = request.getParameter("Address");
		shop.setAddress(address);

		String longitude = request.getParameter("Longitude");
		shop.setLongitude(longitude);

		String latitude = request.getParameter("Latitude");
		shop.setLatitude(latitude);

		String openTime = request.getParameter("OpenTime");
		shop.setOpenTime(openTime);

		String phone = request.getParameter("Phone");
		shop.setPhone(phone);

		String fax = request.getParameter("Fax");
		shop.setFax(fax);

		String email = request.getParameter("Email");
		shop.setEmail(email);

		String facebook = request.getParameter("Facebook");
		shop.setFacebook(facebook);

		String website = request.getParameter("Website");
		shop.setWebsite(website);

		String mainTypePk = request.getParameter("MainTypePk");
		shop.setMainTypePk(mainTypePk);

		String clicks = request.getParameter("Clicks");
		shop.setClicks(clicks);

		if (shops_DAO.updateShopInfo(shop) == true) {
			request.setAttribute("shopUpdateMsg", "商家資料更新成功");
		} else {
			request.setAttribute("shopUpdateMsg", "商家資料更新失敗");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/_03_CultureAndCreativeShops_CMS/Return.jsp");
		rd.forward(request, response);
		return;

		
		
		
		
	}

}
