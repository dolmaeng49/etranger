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

		CategoryBean cb = new CategoryBean(); /*
												 * 상품리스트에서 넘어오는 값(지역코드,도시코드,테마,상품명,사진,상세내용) 을 저장하고 가져오기위해서 Category_Bean
												 * 객체 선언
												 */

		System.out.println("ProductDetailAction");

		// 저장

		cb.setPackage_category_code(request.getParameter("package_category_code"));
		cb.setPackage_category_theme(request.getParameter("package_category_theme"));

//		cb.setPackage_category_name(request.getParameter("package_category_name"));
//		cb.setPackage_category_theme(request.getParameter("package_category_theme"));
//		cb.setPackage_category_image(request.getParameter("package_category_image"));
//		cb.setPackage_category_content(request.getParameter("package_category_content"));
//		

		// 상품코드 저장

		String pcode = cb.getPackage_category_code();

		// 상품리스트에서 선택된 상품에대한 상세리스트를 가져옴
		ProductDetailService prodectDetailService = new ProductDetailService();
		ArrayList<CategoryBean> pdetail = new ArrayList<CategoryBean>();

		pdetail = prodectDetailService.GetProductDetailList(cb, pcode);
		request.setAttribute("pdetail", pdetail);

		ProductBean pb = new ProductBean();
		ArrayList<ProductBean> pdList = new ArrayList<ProductBean>();
		pdList = prodectDetailService.GetProductList(pb, pcode);
		request.setAttribute("pdList", pdList);

		ActionForward forward = new ActionForward();
		forward.setPath("/manager/manager_product_detail.jsp");

		return forward;
	}

}
