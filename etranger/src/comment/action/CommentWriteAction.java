package comment.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import comment.service.CommentListService;
import comment.service.CommentWriteProService;
import comment.vo.CommentBean;
import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;

public class CommentWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("들어왔니? -CommentWriteAction");
		
		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ActionForward forward = null;
		CommentBean cb = null;
		
		
		request.setCharacterEncoding("UTF-8");
		cb= new CommentBean();
		cb.setReview_comment_member_id(request.getParameter("review_comment_member_id"));
		cb.setReview_comment_member_name(request.getParameter("review_comment_member_name"));
		cb.setReview_comment_review_num(Integer.parseInt(request.getParameter("review_comment_review_num")));
		cb.setReview_comment_content(request.getParameter("review_comment_content"));
		CommentWriteProService commentWriteProService = new CommentWriteProService();
		
		boolean isAddSuccess = commentWriteProService.AddComment(cb);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		
		if(!isAddSuccess){ //실패시
			out.print("false");
			
		}else { //성공시
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
			
			System.out.println("commentList:"+commentList);
			
			
			JSONArray commentListJson = new JSONArray();
			for(int i =0; i < commentList.size(); i++) {
				JSONObject commentInfo = new JSONObject();
				commentInfo.put("member_id",commentList.get(i).getReview_comment_member_id());
				commentInfo.put("member_name",commentList.get(i).getReview_comment_member_name());
				commentInfo.put("review_num", commentList.get(i).getReview_comment_review_num());
				commentInfo.put("content", commentList.get(i).getReview_comment_content());
				commentInfo.put("time", commentList.get(i).getReview_comment_date());
				commentInfo.put("nowPage", nowPage);
				commentListJson.add(commentInfo);
			}
			
			out.print(commentListJson);
			
			
		}

		
		forward = null;
		return forward;
		
	}

}
