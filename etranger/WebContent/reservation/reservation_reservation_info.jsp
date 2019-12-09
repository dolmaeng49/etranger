<%@page import="reservation.vo.ReservationBean"%>
<%@page import="manager.vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="manager.dao.ManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- 2019-12-08 -->
<!-- 임시예약조회 기능 실행방법 -->

<!-- 1. -->

<!-- reservation 테이블 category_code 컬럼 추가, 외래키 설정 -->

<!-- ////////////////////////////// -->
<!-- alter table reservation -->
<!-- add column reservation_category_code varchar(100); -->

<!-- alter table reservation -->
<!-- add constraint fk_rsv_ctg1 foreign key (reservation_category_code) references package_category(package_category_code) on delete no action on update cascade -->

<!-- cmd에 실행 해주세요 -->


<!-- 2. -->

<!-- insert 문으로 reservation 테이블에 데이터를 넣어주세요(예약하기 기능이만들어져 있는경우 제외) -->

<!-- ******참고******* -->

<!-- reservation_member_id      -->
<!-- reservation_product_num -->
<!-- reservation_category_code -->

<!-- 3개의 칼럼은 다른 테이블의 칼럼을 참조하고 있어서 자신의 DB에 실제 존재하는 값을 넣어주세요
(그렇지 않으면 외래키 제약조건에 걸려서 추가 불가합니다.) -->







<%
	ArrayList<ReservationBean> rb = new ArrayList<ReservationBean>();
	rb = (ArrayList<ReservationBean>) request.getAttribute("ReservationInfo");
%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp" />
<style type="text/css">
table.pdList {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 20px 10px;
	width: 115%;
}

table.pdList th {
	width: 155px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	color: #fff;
	background: #ff5f5f;
}

table.pdList td {
	width: 155px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #eee;
	font-size: smaller;
}

table.pdList td input {
	font-size: smaller;
}
</style>
</head>

<body>

	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="../include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item"
			style="background-image: url('images/bg_3.jpg');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-7 col-sm-12 ftco-animate">
						<p class="breadcrumbs">
							<span class="mr-2"><a href="../main/index.jsp"></a></span> <span><a
								href="blog.html"></a></span> <span></span>
						</p>
						<h1 class="mb-3">예약조회</h1>
						<br>

					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- END slider -->

	<section class="ftco-section">
		<div class="container">
			<div class="row">
				<div class="col-md-10 ftco-animate">

					<!--  글 작성 폼 시작 -->
					<div class="managerdetailform">
						<h3 class="mb-5"></h3>
					</div>

<%-- <%if(rb.getReservation_category_code()!=null){ %> --%>
					<div class="writeform-group">
					<div class ="col-md-12">
						<br>
						<h3>
							<label for="name">예약 상품 리스트</label>
						</h3>
						<table class="pdList" id="pdlist">
							<%
								if (rb != null) {
							%>
							<tr>
								<th>고객 아이디</th>
								<th>예약일</th>
								<th>예약번호</th>
								<th>출발날짜</th>
								<th>도착날짜</th>
								<th>예약상품</th>
								<th>예약인원</th>
								<th>금액</th>
								<th>결제방법</th>
								<th>진행상태</th>

							</tr>
							<%
								for (int i = 0; i < rb.size(); i++) {
							%>
							<tr>
								<td><%=rb.get(i).getReservation_member_id()%></td>
								<td><%=rb.get(i).getReservation_date()%></td>
								<td><%=rb.get(i).getReservation_num()%></td>
								<td><%=rb.get(i).getPackage_product_depart_date()%></td>
								<td><%=rb.get(i).getPackage_product_arriv_date()%></td>
								<td><%=rb.get(i).getPackage_category_name()%></td>
								<td><%=rb.get(i).getReservation_headcount()%></td>
								<td><%=rb.get(i).getReservation_price()%></td>
								<td><%=rb.get(i).getReservation_pay_way()%></td>
								<td><%=rb.get(i).getReservation_progress()%></td>
							</tr>
							<%
								}
								}
							%>
							<label id="newProduct"></label>
						</table>
						
					</div>
					</div>
				</div>
<%-- <%}%><% else { %> --%>
<!-- <div> 예약 상품이 없습니다</div> -->
<%-- <%} %> --%>
			</div>
		</div>
	</section>

	<!-- footer 인클루드 -->
	<jsp:include page="../include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="../include/loader.jsp" />
	<!-- js사용 -->

</body>



</html>
