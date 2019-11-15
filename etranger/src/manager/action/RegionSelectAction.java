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
import manager.vo.CategoryBean;

public class RegionSelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RegionListService regionListService = new RegionListService();

		ArrayList<CategoryBean> articleList = new ArrayList<CategoryBean>();
		articleList = regionListService.getRegionList();

		JSONArray regionList = new JSONArray();
		for (int i = 0; i < articleList.size(); i++) {
			JSONObject rl = new JSONObject();
			rl.put("regionName", articleList.get(i).getRegionName());
			rl.put("regionCode", articleList.get(i).getRegionCode());
			regionList.add(rl);
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.print(regionList);

		ActionForward forward = null;

		return forward;
	}

}
