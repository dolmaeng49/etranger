package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.ProductInsertService;
import manager.vo.CategoryBean;
import manager.vo.ProductBean;
//
//public class ProductInsertAction implements Action {
//
//	@Override
//	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		ActionForward forward = null;
//		
//		ProductBean pb = new ProductBean();
//		
//		pb.setProductWishCount(0);
//		pb.setProductCurrent(0);
//		pb.setProductTotal(Integer.parseInt(request.getParameter("productTotal")));
//		pb.setProductPrice(Integer.parseInt(request.getParameter("productPrice")));
//		pb.setProductArrivDate(request.getParameter("arrivDate"));
//		pb.setProductDepartDate(request.getParameter("departData"));
//		pb.setCategoryCode(request.getParameter("categoryCode"));
//		
//		ProductInsertService cis = new ProductInsertService();
//		boolean isInsertSuccess = cis.InsertProduct(pb); 
//
//		if (!isInsertSuccess) {
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = response.getWriter(); 
//			out.println("<script>");
//			out.println("alert('상품 등록 실패!')");
//			out.println("history.back()");
//			out.println("</script>");
//		}else {
//			forward = new ActionForward();
//			forward.setPath("ManagerMain.ma");
//			forward.setRedirect(true);
//		}
//
//		return forward;
//	}
//
//}
