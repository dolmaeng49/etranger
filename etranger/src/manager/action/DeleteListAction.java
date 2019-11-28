package manager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.DeleteListService;
import manager.svc.ProductDetailService;
import manager.vo.CategoryBean;
import manager.vo.ProductBean;

public class DeleteListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("DeleteListAction");
		String dpcode = request.getParameter("dpcode");
		System.out.println(dpcode);
//		System.out.println(dpcode);
		DeleteListService dls = new DeleteListService();
		ArrayList<ProductBean> deletelist = new ArrayList<ProductBean>();
		deletelist = dls.DeleteList(dpcode);
		
		System.out.println(deletelist.size());
	
		
	
		
		JSONArray Jdeletelist = new JSONArray();
		for (int i = 0; i < deletelist.size(); i++) {
		JSONObject dl = new JSONObject();
	
		dl.put("product_num", deletelist.get(i).getProductNum());
		dl.put("productArrivDate", deletelist.get(i).getProductArrivDate());
		dl.put("productDepartDate", deletelist.get(i).getProductDepartDate());
		dl.put("productPrice", deletelist.get(i).getProductPrice());
		dl.put("productTotal", deletelist.get(i).getProductTotal());
		dl.put("productCode", deletelist.get(i).getCategoryCode());
		
		Jdeletelist.add(dl);
		}
		
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//out.println(JSONArray.toJSONString(articleList));
	//JSONArray.writeJSONString(articleList, out);
	out.print(Jdeletelist);
		System.out.println(Jdeletelist);
		ActionForward forward = null;

		return forward;
		
		

		
		
		
	}

}
