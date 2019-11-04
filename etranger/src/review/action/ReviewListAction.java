package review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import review.service.ReviewListService;
import review.vo.PageInfo;
import review.vo.ReviewBean;


public class ReviewListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("ReviewListAction");
		
		//페이징 처리
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		ReviewListService reviewListService = new ReviewListService();
		int listCount = reviewListService.getListCount();
		System.out.println("총 게시물 수(Action) " +listCount);
		
		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
		articleList = reviewListService.getArticleList(page, limit);
		
//		int commentNumber = reviewListService.getCommentNumber();
		
		
		//페이징
		int maxPage =(int)((double)listCount / limit +0.95);
		int startPage = ((int)((double)page / 10 +0.9)-1)*10 +1;
		int endPage = startPage +10 -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo",pageInfo);
		request.setAttribute("articleList", articleList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/review/review_list.jsp");
		
		
		return forward;
	}

}
