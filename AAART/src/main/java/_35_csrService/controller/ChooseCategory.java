package _35_csrService.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import _35_csrService.model.MainTableBean;
import _35_csrService.service.impl.MainTableServiceImpl;

@WebServlet("/_35_csrService/ChooseCategory")
// 本控制器負責進行按類別取出活動
public class ChooseCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 先取出session物件
		//下方老師程式碼，login.filter過濾器已代勞
//		HttpSession session = request.getSession(false);
		
//		System.out.println(session == null);
//		// 如果session物件不存在
//		if (session == null) {
//			// 請使用者登入
//			response.sendRedirect(response.encodeRedirectURL(
//					request.getContextPath() + "/_35_login/login.jsp"));
//			return;
//		}
		//下方老師的程式碼是為了原先把pageNumber放進去cookie而寫的
		// 登入成功後，Session範圍內才會有LoginOK對應的MemberBean物件
//		MemberBean mb = (MemberBean) session.getAttribute("LoginOK");
		// 取出使用者的memberId，後面的Cookie會用到 
//		String memberId = mb.getMemberId();
		// BookService介面負責讀取資料庫內Book表格內某一頁的書籍資料，並能新增、修改、刪除
		// 書籍資料等。
		
		//取上一頁jsp選的"activity_category"
		request.setCharacterEncoding("UTF8");
		response.setContentType("text/html;charset=UTF-8");
		
		int i = Integer.parseInt(request.getParameter("activity_category"));
		//控制器先交棒給服務層去查詢（這邊還沒用介面，所以new實作本體
		MainTableServiceImpl service = new MainTableServiceImpl();
		//把活動代碼傳入service層，service層會再去設定dao層屬性活動代碼的值
		service.setActCategory(i);
		//此時原先空白的service物件，已設定好屬性活動代碼的值
		//服務層內再叫出資料存取層的底層方法去查詢對應值的資料
		List<MainTableBean> list = service.selectDBtoMTbyCat();
		//轉類別代號為中文字串
		String str="";
		switch(i) {
		case 1: str="音樂"; break;
		case 2: str="戲劇"; break;
		case 3: str="舞蹈"; break;
		case 4: str="親子"; break;
		case 5: str="獨立音樂"; break;
		case 6: str="展覽"; break;
		case 7: str="講座"; break;
		case 8: str="電影"; break;
		case 11: str="綜藝"; break;
		case 13: str="競賽"; break;
		case 14: str="徵選"; break;
		case 15: str="其他"; break;
		case 16: str="未知分類"; break;
		case 17: str="演唱會"; break;
		case 19: str="研習課程"; break;
		}
		
		request.setAttribute("categoryList", list);
		request.setAttribute("categoryName", str);
		RequestDispatcher rd = request.getRequestDispatcher("/_35_csrService/listActs.jsp");
		rd.forward(request, response);
		return;

	}
}