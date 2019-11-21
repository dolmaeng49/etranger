package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import manager.svc.ProductListService;
import manager.vo.CategoryBean;
import review.service.ReviewListService;
import review.vo.ReviewBean;

public class ProductListAction implements Action {

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

		ProductListService productListService = new ProductListService();
		int listCount = productListService.getListCount();

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

		forward = new ActionForward();
		forward.setPath("/product/productList.jsp");

		return forward;
	}

}
