<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    //현재 세션 객체에 "sid"세션값이 존재할 경우 String 타입 변수 sid에 저장
    String member_id = null;

    if (session.getAttribute("member_id") != null) {
        member_id = (String) session.getAttribute("member_id");
    }
    String member_name = null;
    if (session.getAttribute("member_name") != null) {
        member_name = (String) session.getAttribute("member_name");
    }
%>

<!-- login-info  class="container"-->
<div class="container">
    <p id="login-info-p">
        <%
            if (member_id == null) {
        %>
        <a href="LoginForm.me">Log In</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="MemberJoinForm.me">Create an Account</a>
        <%
            }
            else {
        %>
        <%=member_name%>님 | <a href="LogoutPro.me">Log Out</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="MemberModifyForm.me">회원정보수정</a>
        |<a href="MemberReservationInfo.me?member_id=<%=member_id%>">예약 조회</a>
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
                <li class="nav-item"><a href="about.jsp" class="nav-link">About</a></li>
                <li class="nav-item"><a href="../zzzOriginalPageszzz/contact.jsp" class="nav-link">Contact</a></li>

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