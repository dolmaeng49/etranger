package manager.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.svc.DataChartService;
import manager.vo.DatachartBean;

public class ChangeMostAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		
		DataChartService changeAgeService = new DataChartService();
		
		ArrayList<DatachartBean> ageList = new ArrayList<DatachartBean>();
		ageList = changeAgeService.getAgeList(age, gender);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String op = "";
		op+="<table class=\"table table-hover \" >";
		
		if (ageList != null && ageList.size() > 0) {
			op += "<tr>"
					+ "<th scope=\"col\" class=\"text-center\" style=\"width: 3%\">순위</th>"
					+ "<th scope=\"col\" class=\"text-center\" style=\"width: 20%\">상품이름</th>"
					+ "<th scope=\"col\" class=\"text-center\" style=\"width: 5%\">예약건수</th>"
					+ "</tr>";
					for(int i = 0; i < ageList.size(); i++) {
					op += "<tr>"
							+ "<td class=\"text-center\">"+(i+1)+"</td>"
							+ "<td>" + ageList.get(i).getAgeMostPick() +"</td>"
							+ "<td class=\"text-center\">"+ ageList.get(i).getAgeMostCount()+" 건 </td>"
						+ "</tr>";
					}
		}
		op+="</table>";
		out.print(op);
		return null;
	}

}
