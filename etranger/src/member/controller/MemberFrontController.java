package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.action.MemberLoginProAction;
import member.action.MemberLogoutProAction;
import member.action.MemberModifyFormAction;
import member.action.MemberModifyProAction;
import member.action.MemberReservationInfoAction;
import member.action.MemberResetPasswdFormAction;
import member.action.MemberResetPasswdProAction;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.action.Action;
import common.vo.ActionForward;
import member.action.MemberDeleteProAction;
import member.action.MemberEmailCheckAction;
import member.action.MemberFindIdProAction;
import member.action.MemberIdDupCheckAction;
import member.action.MemberJoinProAction;

@WebServlet("*.me") // 서블릿(Controller)이 매핑을 담당할 주소 설정
public class MemberFrontController extends HttpServlet {

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // Post 방식을 위한 한글처리
		String command = request.getServletPath(); // 서블릿 주소 추출

		// 공통으로 사용할 변수 선언
		Action action = null;
		ActionForward forward = null;

		// 추출한 서블릿 주소 매핑
		// 회원가입
		if (command.equals("/MemberJoinForm.me")) {
			forward = new ActionForward();
			forward.setPath("member/member_join_form.jsp");
		} else if (command.equals("/MemberIdDupCheck.me")) {
			action = new MemberIdDupCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberJoinPro.me")) {
			action = new MemberJoinProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberSendEmailCode.me")) {
			action = new MemberEmailCheckAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 로그인, 로그아웃
		} else if (command.equals("/LoginForm.me")) {
			forward = new ActionForward();
			forward.setPath("member/member_login_form.jsp");
		} else if (command.equals("/LoginPro.me")) {
			action = new MemberLoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/LoginPro.me")) {
			action = new MemberLoginProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/LogoutPro.me")) {
			action = new MemberLogoutProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 아이디 찾기, 비밀번호 찾기
		} else if (command.equals("/MemberFindIdForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/member_findId_form.jsp");
		} else if (command.equals("/MemberFindIdPro.me")) {
			action = new MemberFindIdProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberFindPasswdForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/member_findPasswd_form.jsp");
		} else if (command.equals("/MemberResetPasswdForm.me")) {
			action = new MemberResetPasswdFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberResetPasswdPro.me")) {
			action = new MemberResetPasswdProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 회원정보 수정, 회원 탈퇴
		} else if (command.equals("/MemberDeleteForm.me")) {
			forward = new ActionForward();
			forward.setPath("/member/member_delete_form.jsp");
		} else if (command.equals("/MemberDeletePro.me")) {

			action = new MemberDeleteProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberModifyForm.me")) {
			// MemberModifyFormAction 클래스로 이동
			action = new MemberModifyFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/MemberModifyPro.me")) {
			action = new MemberModifyProAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
					
		}else if (command.equals("/MemberReservationInfo.me")) {
			//MemberReservationInfoAction 클래스로 이동 
			System.out.println("/MemberReservation.me");
			action = new MemberReservationInfoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

		// ActionForward 객체의 정보에 따른 포워딩
		if (forward != null) {
			if (forward.isRedirect()) { // 리다이렉트 방식
				response.sendRedirect(forward.getPath());
			} else { // 디스패치 방식
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
