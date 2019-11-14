package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.RegionInsertService;
import manager.vo.CategoryBean;

public class RegionInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		CategoryBean cb = new CategoryBean();
		cb.setRegionName(request.getParameter("region_name"));
		
		RegionInsertService mips = new RegionInsertService();
		boolean isInsertSuccess = mips.InsertCategory(cb);
		
		if(!isInsertSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("false");
		} 
		
		return forward;
	}

}
