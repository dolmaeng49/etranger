package review.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.action.ReviewDeleteProAction;
import review.action.ReviewDetailAction;
import review.action.ReviewListAction;
import review.action.ReviewModifyFormAction;
import review.action.ReviewModifyProAction;
import review.action.ReviewReplyFormAction;
import review.action.ReviewReplyProAction;
import review.action.ReviewWriteProAction;


@WebServlet("*.rv")
public class ReviewFrontController extends HttpServlet {

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String command = request.getServletPath();
		
		Action action = null;
		ActionForward forward = null;
		
		
		if(command.equals("/R/ReviewWriteForm.rv")) {
			forward = new ActionForward();
			forward.setPath("/review/review_write.jsp");
		}else if(command.equals("/R/ReviewWritePro.rv")) {
			action = new ReviewWriteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/R/ReviewList.rv")){
			action = new ReviewListAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/R/ReviewDetail.rv")){
			action = new ReviewDetailAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/R/ReviewModifyForm.rv")){
			action = new ReviewModifyFormAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/R/ReviewModifyPro.rv")){
			action = new ReviewModifyProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/R/ReviewDeleteForm.rv")) {
			forward = new ActionForward();
			forward.setPath("/review/review_delete.jsp");
		}else if(command.equals("/R/ReviewDeletePro.rv")){
			action = new ReviewDeleteProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/R/ReviewReplyForm.rv")){
			action = new ReviewReplyFormAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/R/ReviewReplyPro.rv")){
			action = new ReviewReplyProAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
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