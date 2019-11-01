package manager.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import manager.vo.CategoryBean;

public class ManagerInsertProAction implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = null;
		
		CategoryBean ccb = new CategoryBean();
		ccb.setCityName(request.getParameter(""));
		
		
		return null;
	}

}
