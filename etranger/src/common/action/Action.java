package common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.vo.ActionForward;

public interface Action {
	ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
