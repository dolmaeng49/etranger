<%@page import="reservation.vo.ReservationBean"%>
<%@page import="common.vo.PageInfo"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

ArrayList<ReservationBean> reservList = (ArrayList<ReservationBean>)request.getAttribute("reservList");




%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<!-- 스타일 include -->
<jsp:include page="../include/style.jsp" />

<style type="text/css">

/* 사이드바 화면고정 */
.sticky {
	position: sticky;
	top: 100px;
}

.li_hover:hover {
	border: 2px solid #ff5f5f;
	background: transparent;
	color: #ff5f5f;
}

.member {
	font-size: smaller;
}

table.reservList {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 50px 0px;
	width: 100%;
}

table.reservList th {
	width: 155px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: #fff;
	background: #ff5f5f;
}

table.reservList td {
	width: 155px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #eee;
	font-size: smaller;
}

table.reservList td input {
	font-size: smaller;
}
</style>
</head>

<body>
	<header>
		<!-- 탑메뉴 인클루드 -->
		<jsp:include page="../include/top_menu.jsp" />
		<section class="home-slider owl-carousel">
			<div class="slider-item"
				style="background-image: url('./images/bg_4.jpg');"
				data-stellar-background-ratio="0.5">
				<div class="overlay"></div>
				<div class="container">
					<div class="row slider-text align-items-center">
						<div class="col-md-7 col-sm-12 ftco-animate">
							<p class="breadcrumbs">
								<span class="mr-2"><a href="../main/index.jsp">Home</a></span> <span>Manager</span>
							</p>
							<h1 class="mb-2">Management</h1>
						</div>
					</div>
				</div>
			</div>
		</section>
	</header>

	<div class="container-fluid">
		<div class="row">
			<!-- 3단길이의 첫번째 열 -->
			<div class="col-md-2">
				<div class="panel panel-info sticky" id="leftside">
					<div class="panel-heading">

						<h3 class="panel-title">Manager Page</h3>
					</div>
					<ul class="list-group">
						<li class="list-group-item active li_hover"
							onclick="location.href='./index.jsp'"><span
							class="icon icon-home"></span> 홈</li>
						<li class="list-group-item li_hover dataChart"
							onclick="location.href='ManagerMain.ma'"><span
							class="icon icon-line-chart"></span> 관리자 메인</li>
						<li class="list-group-item li_hover"
							onclick="location.href='ManagerMain.ma'"><span
							class="icon icon-pencil"></span> 상품 등록으로 돌아가기</li>
						<li class="list-group-item li_hover" id="member"><span
							class="icon icon-users"></span>회원관리</li>
					</ul>
					<ul class="list-group" id="memberManagement">
						<li class="list-group-item li_hover member"
							onclick="location.href='MemberManagement.ma'">&nbsp;­회원예약</li>
						<li class="list-group-item li_hover member">&nbsp;­회원등급</li>
					</ul>
				</div>
			</div>
			<div class="col-md-9">
<!-- 고객아이디 예약일(결제시한) 예약번호 예약상품(이름) 예약인원 출발날짜/도착날짜 금액 진행상태 -->
				<table class="reservList">
					<tr><td>고객아이디</td>
					<td>예약일(결제시한)</td>
					<td>예약번호</td>
					<td>예약상품</td>
					<td>예약인원</td>
					<td>출발날짜/도착날짜</td>
					<td>금액</td>
					<td>진행상태</td>
					</tr>
					<%
					if(reservList !=null){
						for(int i=0; i<reservList.size(); i++){
					%>
					<tr><td>고객아이디</td>
					<td>예약일(결제시한)</td>
					<td>예약번호</td>
					<td>예약상품</td>
					<td>예약인원</td>
					<td>출발날짜/도착날짜</td>
					<td>금액</td>
					<td>진행상태</td>
					</tr>
					<%
						}
					}
					%>
					
					
					
				</table>

			</div>



		</div>
	</div>
	<!-- END slider -->




	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
	<!-- JavaScript 가져오기 -->
	<script src="js/manager.js"></script>

</body>

</html>