<%@page import="member.vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    String member_id = null;
    if (session.getAttribute("member_id") != null) {
        member_id = (String) session.getAttribute("member_id");
    }
    String member_name = null;
    if (session.getAttribute("member_name") != null) {
        member_name = (String)session.getAttribute("member_name");
    }
%>

<!-- login-info  class="container"-->
<div class="container">
    <p id="login-info-p">
        <%
            if (member_id == null) {
        %>
        <a href="#layer2" class="btn-example">Log In</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="MemberJoinForm.me">Create an Account</a>
        <!--로그인 딤처리 레이어  -->
   <div class="dim-layer">
    <div class="dimBg"></div>
    <div id="layer2" class="pop-layer">
        <div class="pop-container">
            <div class="pop-conts">
                <!--content //-->
                <form action="LoginPro.me" method="post">
                <div class="comment-form-wrap pt-5 form-wrap-login">
						<h3 class="mb-5">Login</h3>
						<div class="form-group">
							<label for="id">ID</label><br>
							<input type="text" class="form-control" id="id" required="required" name="member_id">
						</div>
						<div class="form-group">
							<label for="password">PASSWORD</label><br>
							<input type="password" class="form-control" id="passwd" required="required" name="member_passwd">
						</div>
						<p class="wrap-links"><a href="MemberFindIdForm.me">ID 찾기</a> |
							<a href="MemberFindPasswdForm.me">P/W 변경</a> |
							<a href="MemberJoinForm.me">회원가입</a> |</p>
			 			<div class="form-group form-group-btn">
							<input type="submit" value="Login" class="btn py-3 px-4 btn-primary">&nbsp;&nbsp;
						</div>
					</div>
                <div class="btn-r">
                    <a href="#"  id="btnPopUpClose" class="btn py-1 px-2 btn-primary">Close</a>
                </div>
			</form>
                <!--// content-->
            </div>
        </div>
    </div>
</div>

        <%
            }
            else {
        %>
        <%=member_name%>님 | <a href="LogoutPro.me">Log Out</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="MemberModifyForm.me">회원정보수정</a>
		&nbsp;&nbsp;|&nbsp;&nbsp;<a href="ReservationInfo.rs?member_id=<%=member_id%>">예약 조회</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="WishCategoryList.me">찜목록 조회</a>
        <%
            }
        %>
    </p>
</div>
<!-- login-info -->
<nav class="navbar navbar-expand-lg navbar-dark ftco_navbar bg-dark ftco-navbar-light" id="ftco-navbar">
    <div class="container">
        <a class="navbar-brand" href="index.jsp">étranger</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#ftco-nav" aria-controls="ftco-nav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="oi oi-menu"></span> Menu
        </button>

        <div class="collapse navbar-collapse" id="ftco-nav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a href="index.jsp" class="nav-link">Home</a></li>
                <li class="nav-item"><a href="./ProductList.pr" class="nav-link">Packages</a></li>
                <li class="nav-item"><a href="./ReviewList.rv" class="nav-link">Review</a></li>
                <li class="nav-item"><a href="./NoticeList.no" class="nav-link">Notice</a></li>
                <li class="nav-item"><a href="./contact.jsp" class="nav-link">Contact</a></li>

                <%
                    if (member_id != null && member_id.equals("admin")) {
                %>
                <li class="nav-item"><a href="ManagerMain.ma" class="nav-link">Manager</a></li>
                <%
                    }
                %>

            </ul>
        </div>
    </div>
</nav>