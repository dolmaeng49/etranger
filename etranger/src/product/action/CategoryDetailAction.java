package product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import common.vo.PageInfo;
import manager.svc.ProductDetailService;
import manager.vo.CategoryBean;
import manager.vo.ProductBean;
import product.svc.ProductReviewListService;
import review.vo.ReviewBean;

public class CategoryDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//		int page = 1;
//		int limit = 2;

		CategoryBean cb = new CategoryBean();
		cb.setPackage_category_code(request.getParameter("package_category_code"));
		cb.setPackage_category_theme(request.getParameter("package_category_theme"));
		
		// 상품코드 저장

		String pcode = cb.getPackage_category_code();

		// 상품리스트에서 선택된 상품에대한 상세리스트를 가져옴
		ProductDetailService productDetailService = new ProductDetailService();
		ArrayList<CategoryBean> pdetail = null;

		pdetail = productDetailService.GetProductDetailList(pcode);
		request.setAttribute("pdetail", pdetail);

		ProductBean pb = new ProductBean();
		ArrayList<ProductBean> pdList = new ArrayList<ProductBean>();
		pdList = productDetailService.GetProductList(pb, pcode);
		request.setAttribute("pdList", pdList);
		
//		ProductReviewListService productReviewListService = new ProductReviewListService();
//		int listCount = productReviewListService.getListCount(pcode);
//		ArrayList<ReviewBean> reviewList = new ArrayList<ReviewBean>();
//		reviewList = productReviewListService.getReviewList(pcode, page, limit);
//		
//
//		if(request.getParameter("page")!=null) {
//			page=Integer.parseInt(request.getParameter("page"));
//		}
//
//		//페이징
//		int maxPage =(int)((double)listCount / limit +0.95);
//		int startPage = ((int)((double)page / 10 +0.9)-1)*10 +1;
//		int endPage = startPage +10 -1;
//		if(endPage > maxPage) {
//			endPage = maxPage;
//		}
//		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
//		request.setAttribute("pageInfo", pageInfo);
//		request.setAttribute("reviewList", reviewList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/product/category_detail.jsp");

		return forward;
	}

}
