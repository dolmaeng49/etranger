<%@page import="java.util.Collection"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page import="java.util.Collections"%>
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
	String product_num = "";
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
	for (int i = 0; i < pdList.size(); i++) {
		product_num = pdList.get(i).getProductNum();
	}
	
	String member_id = (String)session.getAttribute("member_id");
	
	// 세션에서 로그인 회원의 찜목록 받아오기
	Object memberWishObject = session.getAttribute("member_wishList");
	ArrayList<String> member_wishList = null;
	if(session.getAttribute("member_wishList") != null){
		member_wishList = (ArrayList<String>)memberWishObject;
	}
%>




<!DOCTYPE html>
<html lang="en">

<head>
<script type="text/javascript">
	function wishFunction(domain,code) {
		// isOHeart = '0' : 하트 속이 비어있는 상태 , '1' : 속이 꽉 찬 하트
		var isOHeart = $(domain).attr('id');
		
		if(isOHeart=='0') {
			// ajax로 insertWish DB 작업
			
			$(domain).removeClass('fa-heart-o');
			$(domain).addClass('fa-heart');
			$(domain).attr('id',"1");
		} else if(isOHeart=='1') {
			// ajax로 deleteWish DB 작업
			
			$(domain).removeClass('fa-heart');
			$(domain).addClass('fa-heart-o');
			$(domain).attr('id',"0");
		}
	}
</script>
<script type="text/javascript">
	// 하트를 누르면 호출되는 함수
	// 파라미터로 해당 패키지카테고리의 package_category_code 를 전달 받음
// 	function wishFunction(this, code) {
// 		alert('hihi');
	// 하트 태그의 이름이 1 이면 속이 찬 하트, 0 이면 속이 빈하트
// 		var isOHeart = $(this).attr('name');
// 		alert(isOHeart);
</script>
	<!-- 스타일 인클루드 -->
	<jsp:include page="../include/style.jsp" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<style type="text/css">
		hr {
			border-top: 5px solid rgba(0, 0, 0, 0.1);
		}
		h2 {
			color: white;
		}
		h4 {
			color: white;
		}
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
		table.pdList th {
			width: 155px;
			font-weight: bold;
			vertical-align: top;
			color: #fff;
			background: #ff5f5f;
			font-size: 0.9em;
		}
	</style>

</head>
<%-- <body onload="checkSession(<%=sid%>)"> --%>

<body>

	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="../include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item" style="background-image: url('ManagerImgUpload/<%=image%>');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-12 col-sm-12 ftco-animate">
						<h2 class="mb-12"><%=name%></h2>
						<h4 class="mb-2"><%=theme%>
						</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="slider-item" style="background-image: url('ManagerImgUpload/<%=image%>');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-12 col-sm-12 ftco-animate">
						<h2 class="mb-12"><%=name%></h2>
						<h4 class="mb-2"><%=theme%>
						</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="slider-item" style="background-image: url('ManagerImgUpload/<%=image%>');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-12 col-sm-12 ftco-animate">
						<h2 class="mb-12"><%=name%></h2>
						<h4 class="mb-2"><%=theme%>
						</h4>
					</div>
				</div>
			</div>
		</div>
		<div class="slider-item" style="background-image: url('ManagerImgUpload/<%=image%>');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-12 col-sm-12 ftco-animate">
						<h2 class="mb-12"><%=name%></h2>
						<h4 class="mb-2"><%=theme%>
						</h4>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- END slider -->

	<div class="container">
		<div class="row">
			<div class="col-md-8 ftco-animate">
				<hr>
				<!--  글 작성 폼 시작 -->
				<div class="writeform-group">
					<table width="730">
					<tr>
							<td><%=name%></td>
							<td><%=theme%></td>
							<!-- member_wishList 가 null이 아니고 해당 상품번호를 포함하면
								 => 속이 꽉 찬 하트 -->
							<td style="text-align: right;">좋아요숫자 <span id="wish_icon">1<i onclick="wishFunction(this,'<%=code%>')"
								<%if(member_wishList != null && member_wishList.contains("상품번호")) {%>
									class="fa fa-heart" id="1" 
								<%} else {%>
									class="fa fa-heart-o" id="0"
								<%} %>
								style="font-size:24px;color:red;"></i></span></td>
							<!-- 빈하트는 class="fa fa-heart-o" name="0" -->
							<!-- 꽉찬하트는 class="fa fa-heart" name="1" -->
						</tr>
						<tr>
							<td><label for="image">이미지1</label> <a href="#" class="block-20"
									style="background-image: url('ManagerImgUpload/<%=image%>');"> </a></td>
							<td><label for="image">이미지2</label> <a href="#" class="block-20"
									style="background-image: url('ManagerImgUpload/<%=image%>');"> </a></td>
							<td><label for="image">이미지3</label> <a href="#" class="block-20"
									style="background-image: url('ManagerImgUpload/<%=image%>');"> </a></td>
						</tr>
						<tr>
							<td colspan="3"><label for="image"></label> <a href="#" class="block-20"
									style="background-image: url('ManagerImgUpload/<%=image%>');"> </a></td>
						</tr>


					</table>
				</div>
				<div class="writeform-group">
					<hr>
					<label for="name">상세내용</label>
					<br>
					<textarea name="Product_Detail_content" cols="75" rows="20" readonly="readonly"
						style="resize: none;"><%=content%></textarea>
				</div>

				<div class="writeform-group" id="review">
					<hr>
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
						<input type="text" class="actual_range"> <input type="text" class="actual_range">
					</div>
					<!-- 					날짜 두개를 선택할 수 있는 달력 -->
					<!-- 					범위 표시 안됨, Action에서 날짜 두개 쪼개서
<!-- 	빠른 날짜를 출발날짜, 늦은 날짜를 도착날짜로 구분하는 과정 필요 -->
					<!-- 					<div class="pick_start_date"></div> -->
					<!-- 											<div class="pick_end_date"></div> -->
					<!-- 					<input type="text" class="pick_start_date_input"> -->
					<!-- 											    <div class="input-group-addon">to</div> -->
					<!-- 											    <input type="text" class="pick_end_date_input"> -->
					<input type="button" class="btn py-1 px-2 btn-primary" id="moveReview" value="리뷰보기">
					<%
						for (int i = 0; i < pdList.size(); i++) {
							if (pdList != null) {
					%>
					<form action="ReservationInsert.rs" method="get">
						<table class="pdList">
							<tr id="tr_top">
								<th>출발날짜</th>
								<th>도착날짜</th>
								<th>가격</th>
								<th>현재/남은인원</th>
							</tr>
							<tr>
								<td><%=pdList.get(i).getProductDepartDate()%></td>
								<td><%=pdList.get(i).getProductArrivDate()%></td>
								<td><%=pdList.get(i).getProductPrice()%></td>
								<%
									if (pdList.get(i).getProductTotal() == pdList.get(i).getProductCurrent()) {
								%>
								<td><%=pdList.get(i).getProductCurrent()%> / 정원초과</td>
								<%
									} else {
								%>
								<td><%=pdList.get(i).getProductCurrent()%> / <%=pdList.get(i).getProductTotal()%></td>
								<%
									}
								%>
							</tr>
							<tr>
								<td><select onchange="setPeopleCount(<%=pdList.get(i).getProductPrice()%>, <%=i%>)"
										id="count<%=i%>" name="headCount">
										<option value="0">인원수</option>
										<%
											int total = 10;
													int max = pdList.get(i).getProductTotal()< total ? pdList.get(i).getProductTotal() - pdList.get(i).getProductCurrent() : total;
													if (max > pdList.get(i).getProductTotal() - pdList.get(i).getProductCurrent()){
														max = pdList.get(i).getProductTotal() - pdList.get(i).getProductCurrent();
													}
													for (int j = 1; j <= max; j++) {
										%>
										<option><%=j%></option>
										<%
											}
										%>
									</select></td>
								<td colspan="2">총합계<br> <span id="result<%=i%>"></span></td>
								<td><input type="submit" class="btn py-1 px-1 btn-primary" id="submitBtn<%=i%>" value="예약하기" disabled="disabled"></td>
							</tr>
						</table>
						<!-- 						<input type="hidden" name="headCount" id="headCount"> -->
						<input type="hidden" name="member_id" value="<%=member_id%>"> 
						<input type="hidden" name="category_code" value="<%=code%>"> 
						<input type="hidden" name="price" id="price<%=i%>" value="0">
						<input type="hidden" name="product_num" id="product_num" value="<%=pdList.get(i).getProductNum()%>">
						<input type="hidden" name="totalCount" value="<%=pdList.get(i).getProductTotal()%>">
					</form>
					<%
						}
						}
					%>
				</div>
			</div>
		</div>
	</div>
	<!-- .section -->

	<!-- footer 인클루드 -->
	<jsp:include page="../include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="../include/loader.jsp" />
	<script>
		// 리뷰보기 버튼 클릭시 리뷰 구간으로 이동
		$(document).ready(function () {
			$('#moveReview').click(function () {
				var offset = $('#review').offset(); // 이동할 태그 위치 저장
				// animate() 메서드를 이용해서 지정된 태그위치로 이동 0.3초
				$('html').animate({ scrollTop: offset.top }, 300);
			});
		});
		// 인원수 설정시 총합계 계산, 인원수 미입력, 정원초과시 예약버튼 비활성화
		function setPeopleCount(pay, count) {
			var peopleCount = $('#count' + count + ' option:selected').val(); // 선택한 인원수 불러오기
			if (peopleCount == '0') { // 인원수가 0일경우
				$('#submitBtn' + count).attr('disabled',true); // 예약버튼 비활성화
				$('#result' + count).html('');
				$('#price' + count).val('');
			}
			else {
				$('#result' + count).html(peopleCount * pay); // 총합계 계산
				$('#price' + count).val(peopleCount * pay); // DB로 넘길값 생성
				$('#submitBtn' + count).attr('disabled', false); // 예약버튼 활성화
			}
		}
	</script>
</body>

</html>