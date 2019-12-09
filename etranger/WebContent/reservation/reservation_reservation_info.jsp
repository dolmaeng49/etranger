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
	ReservationBean rb = new ReservationBean();
	rb = (ReservationBean) request.getAttribute("ReservationInfo");
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
				<div class="col-md-8 ftco-animate">

					<!--  글 작성 폼 시작 -->
					<div class="managerdetailform">
						<h3 class="mb-5">예약조회</h3>
					</div>

					<%
						if (rb.getReservation_category_code() != null) {
					%>

					<div class="writeform-group">
						<label for="image">이미지</label><a
							href="CategoryDetail.pr?package_category_code=<%=rb.getReservation_category_code()%>"
							class="block-20"
							style="background-image: url('ManagerImgUpload/<%=rb.getPackage_category_image()%>'
										);">
						</a>
					</div>


					<div class="writeform-group">
						<br> <label for="code">아이디</label><input type="text"
							class="form-control" name="member_id" id="member_id"
							value="<%=rb.getReservation_member_id()%>" />
					</div>


					<div class="writeform-group">
						<br> <label for="code">상품코드</label><input type="text"
							class="form-control" name="category_code" id="category_code"
							value="<%=rb.getReservation_category_code()%>" />
					</div>

					<div class="writeform-group">
						<br> <label for="code">상품이름</label><input type="text"
							class="form-control" name="category_name()" id="category_name()"
							value="<%=rb.getPackage_category_name()%>" />
					</div>


					<div class="writeform-group">
						<br> <label for="code">출발날짜</label><input type="text"
							class="form-control" name="depart_date" id="depart_date"
							value="<%=rb.getPackage_product_depart_date()%>" />
					</div>


					<div class="writeform-group">
						<br> <label for="code">도착날짜</label><input type="text"
							class="form-control" name="arriv_date" id="arriv_date"
							value="<%=rb.getPackage_product_arriv_date()%>" />
					</div>

					<div class="writeform-group">
						<br> <label for="code">총 인원수</label><input type="text"
							class="form-control" name="headcount" id="headcount"
							value="<%=rb.getReservation_headcount()%>" />
					</div>
					<div class="writeform-group">


						<div class="writeform-group">
							<br> <label for="code">총 가격</label><input type="text"
								class="form-control" name="price" id="price"
								value="<%=rb.getReservation_price()%>" />
						</div>
						<div class="writeform-group"></div>
					</div>
					<%
						} else {
					%>


					<%
						}
					%>
				</div>
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
