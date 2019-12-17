package member.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.action.Action;
import common.vo.ActionForward;
import member.service.MemberLoginProService;
import member.vo.MemberBean;
import product.svc.WishListService;

public class MemberLoginProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = null;
		
		String member_id = request.getParameter("member_id");
		String member_passwd = request.getParameter("member_passwd");
		
		MemberLoginProService memberLoginProService = new MemberLoginProService();
		int loginResult = memberLoginProService.memberLogin(member_id,member_passwd);
		
		if(loginResult==0) {
			// 아이디가 없을 경우
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('ID가 존재하지 않습니다')");
			out.println("history.back()");
			out.println("</script>");
		}else if(loginResult==-1) {
			// 아이디 일치, 비밀번호 불일치
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('패스워드가 일치하지 않습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}else if(loginResult==1) {
			// 아이디 비밀번호 일치, 로그인 과정 진행
			
			HttpSession session = request.getSession();
			// 아이디 세션에 저장
			session.setAttribute("member_id", member_id);
			
			// member_name, member_email, member_birth, member_gender, member_grade
			MemberBean memberInfo = memberLoginProService.getMemberInfo(member_id);
			// 5 개의 정보 저장되어 있음
			session.setAttribute("memberInfo", memberInfo);
			
			// 아이디에 해당하는 찜목록(ArrayList<String>) 조회, 세션에 저장
			// 찜목록이 없을 경우 ArrayList의 size = 0
			WishListService wishListService = new WishListService();
			ArrayList<String> member_wishList = wishListService.getMemberWishList(member_id);
			session.setAttribute("member_wishList", member_wishList);
			
			forward = new ActionForward();
			forward.setPath("index.jsp");
			forward.setRedirect(true);
			
		}
		return forward;
	}

}
