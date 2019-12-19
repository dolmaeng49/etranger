package product.action;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import manager.vo.CategoryBean;
import product.svc.CategoryListService;
import product.vo.SearchBean;

public class WishCategoryListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ActionForward forward = null;
		// 페이징 처리
		int page = 1;
		int limit = 8;
		// 페이지 정보 파라미터를 받은 경우
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		String member_id = (String)request.getSession().getAttribute("member_id");
		if(member_id == null) {
			return null;
		}
		
		CategoryListService categoryListService = new CategoryListService();
		int listCount = categoryListService.getWishListCount(member_id);
		CategoryBean[] categoryList = categoryListService.getWishCategoryList(page, limit, member_id);

		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("categoryList", categoryList);
		forward = new ActionForward();
		forward.setPath("/member/wish_category_list.jsp");

		return forward;
	}

}
