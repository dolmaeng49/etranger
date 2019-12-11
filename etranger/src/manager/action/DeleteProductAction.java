package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.DeleteProductService;

public class DeleteProductAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String deletepcode = request.getParameter("package_product_num");
//		System.out.println(deletepcode);
		DeleteProductService deleteservice = new DeleteProductService();
		 boolean isDeleteSuccess = deleteservice.DeleteProduct(deletepcode);
		

			if (!isDeleteSuccess) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.print("false");
			}
		
		
		
		return forward;
	}

}
