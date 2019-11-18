package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.ProductListService;
import manager.vo.CategoryBean;
import manager.vo.PageInfo;

public class ProductListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int page = 1;
		int limit = 12;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		ProductListService productListService = new ProductListService();
		int listCount = productListService.getListCount();
		System.out.println("총 게시물 수(Action) " + listCount);

		ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();
		productList = productListService.getProductList(page, limit);

		int maxPage = (int) ((double) listCount / limit + 0.95);
		int startPage = ((int) ((double) page / 10 + 0.9) - 1) * 10 + 1;
		int endPage = startPage + 10 - 1;
		if (endPage > maxPage) {
			endPage = maxPage;
		}

		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);

		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("productList", productList);

		ActionForward forward = new ActionForward();
		forward.setPath("/review/review_list.jsp");

		return forward;
	}

}
