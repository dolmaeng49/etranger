package manager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.CityListService;
import manager.vo.CategoryBean;

public class CitySelectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CityListService cityListService = new CityListService();
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		ArrayList<CategoryBean> articleList = new ArrayList<CategoryBean>();
		articleList = cityListService.getCityList(code);


		JSONArray cityList = new JSONArray();
		for (int i = 0; i < articleList.size(); i++) {
			JSONObject cl = new JSONObject();
			cl.put("cityName", articleList.get(i).getCityName());
			cl.put("cityCode", articleList.get(i).getCityCode());
			cityList.add(cl);
		}
		
//		System.out.println(cityList);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print(cityList);
		
		ActionForward forward = null;

		return forward;
	}

}
