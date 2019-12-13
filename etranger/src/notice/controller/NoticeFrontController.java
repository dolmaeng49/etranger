package notice.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import notice.action.NoticeDeleteProAction;
import notice.action.NoticeListAction;
import notice.action.NoticeModifyFormAction;
import notice.action.NoticeModifyProAction;
import notice.action.NoticeSearchListAction;
import notice.action.NoticeWriteProAction;



@WebServlet("*.no")
public class NoticeFrontController extends HttpServlet {

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/NoticeWriteForm.no")) {
			forward = new ActionForward();
			forward.setPath("/notice/notice_write.jsp");
		}else if(command.equals("/NoticeWritePro.no")) {
			action = new NoticeWriteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/NoticeList.no")){
			action = new NoticeListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/NoticeModifyForm.no")){
			action = new NoticeModifyFormAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/NoticeModifyPro.no")){
			action = new NoticeModifyProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			else if(command.equals("/NoticeDeletePro.no")){
			action = new NoticeDeleteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//			else if(command.equals("/ImageCallback.rv")){
//			action = new ImageCallbackAction();
//			try {
//				forward=action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}+
		else if(command.equals("/NoticeSearch.no")){
			action = new NoticeSearchListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
//			else if(command.equals("/ImageCallback.rv")){
//			action = new ImageCallbackAction();
//			try {
//				forward=action.execute(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		
		
		
		
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}
			else {
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
