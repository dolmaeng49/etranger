package review.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import review.service.ReviewSearchListService;
import review.vo.ReviewBean;

public class ReviewSearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String search = request.getParameter("search");

		int page = 1;
		int limit = 12;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		ReviewSearchListService reviewSearchListService = new ReviewSearchListService();
		int listCount = reviewSearchListService.getListCount(search);
//		System.out.println("검색한 게시물 수(Action) " +listCount);

		ArrayList<ReviewBean> articleList = new ArrayList<ReviewBean>();
		articleList = reviewSearchListService.getArticleList(page, limit, search);

		// 페이징
		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);

		ActionForward forward = new ActionForward();
		forward.setPath("/review/review_search_list.jsp");

		return forward;
	}

}
