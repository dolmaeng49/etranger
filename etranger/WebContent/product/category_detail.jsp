<%@page import="manager.vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="manager.dao.ManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<CategoryBean> pdetail = (ArrayList<CategoryBean>) request.getAttribute("pdetail");
	ArrayList<ProductBean> pdList = (ArrayList<ProductBean>) request.getAttribute("pdList");
	String code = "";
	String image = "";
	String name = "";
	String content = "";
	String theme = "";
	int region_code = 0;
	int city_code = 0;
	for (int i = 0; i < pdetail.size(); i++) {

		code = pdetail.get(i).getPackage_category_code();
		image = pdetail.get(i).getPackage_category_image();
		name = pdetail.get(i).getPackage_category_name();
		content = pdetail.get(i).getPackage_category_content();
		theme = pdetail.get(i).getPackage_category_theme();
		region_code = pdetail.get(i).getPackage_category_region();
		city_code = pdetail.get(i).getPackage_category_city();
	}
%>




<!DOCTYPE html>
<html lang="en">

<head>
<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp" />

<style type="text/css">
.sticky {
	position: sticky;
	top: 100px;
}

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
	font-weight: bold;
	vertical-align: top;
	color: #fff;
	background: #ff5f5f;
}

table.pdList td {
	width: 155px;
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
<%-- <body onload="checkSession(<%=sid%>)"> --%>

<body>

	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="../include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item" style="background-image: url('images/bg_3.jpg');" data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-7 col-sm-12 ftco-animate">
						<p class="breadcrumbs">
							<span class="mr-2"><a href="../main/index.jsp"></a></span> <span><a href="blog.html"></a></span> <span></span>
						</p>
						<h1 class="mb-3">상품 상세페이지</h1>
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
						<h3 class="mb-5">상품 상세 페이지</h3>
					</div>

					<div class="writeform-group">
						<label for="image">이미지</label> <a href="#" class="block-20" style="background-image: url('ManagerImgUpload/<%=image%>');"> </a>
					</div>
					<br>
					<div class="writeform-group">
						<label for="name">상품명</label> <input type="text" class="form-control" name="product_name" id="product_name_id" value="<%=name%>" />
					</div>

					<br>
					<div class="writeform-group">
						<label for="name">상품테마</label> <input type="text" class="form-control" name="product_theme" id="product_theme_id" value="<%=theme%>" />
					</div>

					<div class="writeform-group">
						<br>
						<label for="name">상세내용</label>
						<br>
						<textarea name="Product_Detail_content" cols="72" rows="20"><%=content%></textarea>
					</div>

					<div class="writeform-group" id="review">
						<br>
						<label for="name">리뷰</label>
						<br>
					</div>

					<div class="form-group">
						<!-- 						<input type="button" value="등록" class="btn py-3 px-4 btn-primary" -->
						<%-- 							onclick="location.href='ProductInsert.ma?package_category_code=<%=code%>'"> --%>
						<input type="button" value="뒤로가기" class="btn py-3 px-4 btn-primary" onclick="history.back()">
					</div>
				</div>

				<div class="col-md-4 ftco-animate">
					<div class="writeform-group sticky">
						<br>
						
<!-- 인풋태그 두개로 날짜 범위 선택 하는 달력 -->
<!-- 달력이 항상 표시되지가 않음
	input 태그를 div로 바꾸면 달력이 항상 표시되지만 2개가 표시됨 -->
						<div id="event_period">
						    <input type="text" class="actual_range">
						    <input type="text" class="actual_range">
						</div>
<!-- 날짜 두개를 선택할 수 있는 달력 -->
<!-- 범위 표시 안됨, Action에서 날짜 두개 쪼개서
	빠른 날짜를 출발날짜, 늦은 날짜를 도착날짜로 구분하는 과정 필요 -->
						<div class="pick_start_date"></div>
<!-- 						<div class="pick_end_date"></div> -->
							<input type="text" class="pick_start_date_input">
<!-- 						    <div class="input-group-addon">to</div> -->
<!-- 						    <input type="text" class="pick_end_date_input"> -->
						<input type="button" class="btn py-1 px-2 btn-primary" id="moveReview" value="리뷰보기">
						<table class="pdList">
							<%
								for (int i = 0; i < pdList.size(); i++) {
									if (pdList != null) {
							%>
							<tr id="tr_top">
								<th>출발날짜</th>
								<th>도착날짜</th>
								<th>가격</th>
								<th>총인원수</th>
							</tr>
							<tr>
								<td><%=pdList.get(i).getProductDepartDate()%></td>
								<td><%=pdList.get(i).getProductArrivDate()%></td>
								<td><%=pdList.get(i).getProductPrice()%></td>
								<td><%=pdList.get(i).getProductTotal()%></td>
							</tr>
							<tr>
								<td><select>
										<option>인원수</option>
										<%
										int total = 10;
										int max = pdList.get(i).getProductTotal()<total ? pdList.get(i).getProductTotal() : total;
										for(int j = 1; j <= max;j++){
											%>
											<option><%=j%></option>
											<%
										}
										%>
								</select></td>
								<td colspan="2">총합계<br></td>
								<td><input type="button" class="btn py-1 px-1 btn-primary" value="예약하기" onclick="reservation('<%=pdList.get(i).getProductNum()%>')"></td>
							</tr>
							<%
								}
								}
							%>
						</table>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- .section -->

	<!-- footer 인클루드 -->
	<jsp:include page="../include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="../include/loader.jsp" />
	<script>
		$(document).ready(function() {
			$('#moveReview').click(function() {
				var offset = $('#review').offset(); // 이동할 태그 위치 저장
				// animate() 메서드를 이용해서 지정된 태그위치로 이동 0.3초
				$('html').animate({
					scrollTop : offset.top
				}, 300);
			});
		});
	</script>
</body>

</html>