package manager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.RegionListService;
import manager.svc.ThemeCheckBoxService;
import manager.vo.CategoryBean;

public class GetThemeListAjaxAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ThemeCheckBoxService themeCheckBoxService = new ThemeCheckBoxService();
		
		ArrayList<CategoryBean> articleList = new ArrayList<CategoryBean>();
		articleList = themeCheckBoxService.getThemeListAjax();

		JSONArray themeList = new JSONArray();
		for (int i = 0; i < articleList.size(); i++) {
			JSONObject rl = new JSONObject();
			rl.put("themeName", articleList.get(i).getThemeName());
			rl.put("themenCode", articleList.get(i).getThemeCode());
			themeList.add(rl);
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(themeList);
		
		ActionForward forward = null;

		return forward;
	}

}
