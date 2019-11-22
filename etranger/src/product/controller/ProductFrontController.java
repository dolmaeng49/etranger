package product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import product.action.CategoryListAction;
import product.action.CategoryListSearchAction;

@WebServlet("*.pr") // 서블릿(Controller)이 매핑을 담당할 주소 설정
public class ProductFrontController extends HttpServlet {
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // Post 방식을 위한 한글처리
		String command = request.getServletPath(); // 서블릿 주소 추출
		
		// 공통으로 사용할 변수 선언
		Action action = null;
		ActionForward forward = null;
		
		// 추출한 서블릿 주소 매핑
		if(command.equals("/ProductList.pr")) {
			action = new CategoryListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/tours.pr")) {
			forward = new ActionForward();
			forward.setPath("product/tours.jsp");
		} else if(command.equals("/CategoryListSearch.pr")) {
			action = new CategoryListSearchAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//			else if (command.equals("/AddWish.pr")) {
//			action = new AddWishAction();
//			try {
//				forward = action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		// ActionForward 객체의 정보에 따른 포워딩
		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트 방식
				response.sendRedirect(forward.getPath());
			}
			else { // 디스패치 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
				
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}