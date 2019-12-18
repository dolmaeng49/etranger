package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.CategoryDeleteService;
import manager.svc.CategoryUpdateService;
import manager.vo.CategoryBean;

public class CategoryDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		String code = request.getParameter("package_category_code");
		
		CategoryDeleteService categoryDeleteService = new CategoryDeleteService();
		boolean isDeleteSuccess = categoryDeleteService.deleteCategory(code);
		
		if (!isDeleteSuccess) {
			response.setContentType("text/html; charser=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("카테고리 삭제가 실패했습니다");
		} else {
			forward = new ActionForward();
		}
		String path= "ManagerMain.ma";
		forward.setRedirect(true);
		forward.setPath(path);
		
		return forward;

	}
}
