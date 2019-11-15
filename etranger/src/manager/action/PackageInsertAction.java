package manager.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspApplicationContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.PackageInsertService;
import manager.svc.RegionInsertService;
import manager.vo.CategoryBean;

public class PackageInsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	ActionForward forward = null;
			

	
	
		CategoryBean cb = new CategoryBean();
		String theme = "";
		String s = "#";
		for(int i=0; i<request.getParameterValues("theme").length;i++) {
				
		theme += (s+(request.getParameterValues("theme")[i]));
		
			
		}
		
		
		
		 int addRegioncode = Integer.parseInt(request.getParameter("addRegioncode"));
		 int addCitycode = Integer.parseInt(request.getParameter("addCitycode"));
		

		 cb.setPackage_category_region(addRegioncode);
		 cb.setPackage_category_city(addCitycode);
		
		

		
		PackageInsertService mips = new PackageInsertService();
		boolean isInsertSuccess = mips.InsertPackage(cb,theme);
		
		
		
		
		
		if(!isInsertSuccess) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("false");
		} 
		
		return forward;
	}
		
		
		
		

}
