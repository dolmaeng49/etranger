package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.CategoryInsertService;
import manager.svc.ProductInsertService;
import manager.vo.CategoryBean;
import manager.vo.ProductBean;

public class ProductInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;

		ProductBean pb = new ProductBean();
		pb.setCategoryCode(request.getParameter("package_category_code"));
		pb.setProductNum(request.getParameter("package_category_code") + request.getParameter("product_arrivdate"));
		pb.setProductDepartDate(request.getParameter("product_depardate"));
		pb.setProductArrivDate(request.getParameter("product_arrivdate"));
		pb.setProductPrice(Integer.parseInt(request.getParameter("product_price")));
		pb.setProductTotal(Integer.parseInt(request.getParameter("product_total")));


		ProductInsertService pis = new ProductInsertService();
		boolean isInsertSuccess = pis.InsertProduct(pb);

		if (!isInsertSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("false");
		} 
		return forward;
	}

}
