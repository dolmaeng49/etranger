package comment.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.service.CommentListService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;

public class CommentListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니?-CommentListAction");
		int page = 1;
		int limit = 10;
		
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
				CommentListService commentListService = new CommentListService();
				int listCount = commentListService.getCommentCount();
				
				ArrayList<CommentBean> commentList = new ArrayList<CommentBean>();
				commentList =commentListService.getCommentList();
				
				
				//페이징
				int maxPage = (int) ((double) listCount / limit + 0.95);
				int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
				int endPage = startPage + 10 - 1;
				if (endPage > maxPage) {
					endPage = maxPage;
				}
				
				PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
				int nowPage = pageInfo.getPage();
				maxPage = pageInfo.getMaxPage();
				startPage = pageInfo.getStartPage();
				endPage = pageInfo.getEndPage();
				listCount = pageInfo.getListCount();
				
				
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				
				request.setAttribute("commentList", commentList);
				
				ActionForward forward = new ActionForward();
				forward.setPath("/review/commentlist.jsp");
				forward=null;
				
				return forward;
	}

}
