package notice.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import notice.service.NoticeListService;
import notice.vo.NoticeBean;

public class NoticeListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("ReviewListAction");
		
		//페이징 처리
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		NoticeListService noticeListService = new NoticeListService();
		int listCount = noticeListService.getListCount();
		
		ArrayList<NoticeBean> articleList = new ArrayList<NoticeBean>();
		articleList = noticeListService.getArticleList(page, limit);
		
		//페이징
		int maxPage =(int)((double)listCount / limit +0.95);
		int startPage = ((int)((double)page / 10 +0.9)-1)*10 +1;
		int endPage = startPage +10 -1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("articleList", articleList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/notice/notice_list.jsp");
		
		
		return forward;
	}

}

