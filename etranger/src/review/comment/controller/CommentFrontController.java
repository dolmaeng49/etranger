package review.comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.comment.action.CommentDeleteProAction;
import review.comment.action.CommentListAjax;
import review.comment.action.CommentModifyProAction;
import review.comment.action.CommentReplyProAction;
import review.comment.action.CommentWriteAction;
;

@WebServlet("*.cm")
public class CommentFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String command = request.getServletPath();
			
			Action action = null;
			ActionForward forward = null;
			
			if(command.equals("/CommentWrite.cm")) {
				action = new CommentWriteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/CommentModifyPro.cm")){
				action = new CommentModifyProAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/CommentDeletePro.cm")){
				action = new CommentDeleteProAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/CommentReplyPro.cm")){
				action = new CommentReplyProAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/CommentListAjax.cm")){
				action = new CommentListAjax();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			
			
			
			if (forward != null) {
				if (forward.isRedirect()) { 
					response.sendRedirect(forward.getPath());
				} else { 
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
