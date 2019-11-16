package manager.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.ProductListService;
import manager.svc.RegionListService;
import manager.vo.CategoryBean;

public class ProductListSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ProductListService productListService = new ProductListService();

		ArrayList<CategoryBean> productList = new ArrayList<CategoryBean>();
		productList = productListService.getProductList();
		
		request.setAttribute("regionList", productList);
		
		
		return null;
	}

}
