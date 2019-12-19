package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.ProductDetailService;
import manager.vo.CategoryBean;
import manager.vo.ProductBean;
import product.svc.ProductReviewListService;
import review.service.ReviewListService;
import review.vo.ReviewBean;

public class ProductDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 상품코드 저장
		String pcode = request.getParameter("package_category_code");

		// 상품리스트에서 선택된 상품에대한 상세리스트를 가져옴
		ProductDetailService prodectDetailService = new ProductDetailService();
		ArrayList<CategoryBean> pdetail = new ArrayList<CategoryBean>();

		pdetail = prodectDetailService.GetProductDetailList(pcode);
		request.setAttribute("pdetail", pdetail);

		ArrayList<ProductBean> pdList = null;
		pdList = prodectDetailService.GetProductList(pcode);
		request.setAttribute("pdList", pdList);

		ActionForward forward = new ActionForward();
		forward.setPath("/manager/manager_product_detail.jsp");

		return forward;
	}

}
