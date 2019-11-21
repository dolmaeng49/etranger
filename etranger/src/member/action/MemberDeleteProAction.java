package member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberDeleteProService;

public class MemberDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward=null;
		
		int member_num=Integer.parseInt(request.getParameter("member_num"));
		String member_id=request.getParameter("member_id");
		
		MemberDeleteProService memberDeleteProService=new MemberDeleteProService();
		
		boolean isRightUser= memberDeleteProService.isArticleWriter(member_id, request.getParameter("member_passwd"));
		
		
		
		
		if(!isRightUser) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('삭제 권한이 없습니다!')");
			out.println("history.back()");
			out.println("</script>");
			}else {
				boolean isDeleteSuccess = MemberDeleteProService.removeArticle(member_id);
				
				if(!isRightUser) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('삭제 실패!')");
				out.println("history.back()");
				out.println("</script>");
				}else {
					
					forward=new ActionForward();
					forward.setRedirect(true);
					forward.setPath("index.jsp");
					
				}
			}
			return forward;
		}

	}