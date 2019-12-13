package notice.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import notice.service.NoticeSearchListService;
import notice.vo.NoticeBean;
import review.service.ReviewSearchListService;
import review.vo.ReviewBean;

public class NoticeSearchListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String search = request.getParameter("search");
		
		//페이징 처리
		int page=1;
		int limit=12;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		
		NoticeSearchListService noticeSearchListService = new NoticeSearchListService();
		int listCount = noticeSearchListService.getListCount(search);
		System.out.println("검색한 공지글 수(Action) " +listCount);
		
		ArrayList<NoticeBean> articleList = new ArrayList<NoticeBean>();
		articleList = noticeSearchListService.getArticleList(page, limit, search);
		System.out.println(articleList.size());
		
		
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
		forward.setPath("/notice/notice_search_list.jsp");
		
		
		return forward;
	}

}
