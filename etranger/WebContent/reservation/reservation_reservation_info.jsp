<%@page import="member.vo.MemberBean"%>
<%@page import="common.vo.PageInfo"%>
<%@page import="reservation.vo.ReservationBean"%>
<%@page import="manager.vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="manager.dao.ManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
	ArrayList<ReservationBean> rb = (ArrayList<ReservationBean>) request.getAttribute("ReservationInfo");
	MemberBean memberList = (MemberBean) request.getAttribute("MemberInfo");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	String member_id = (String) request.getAttribute("member_id");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
%>
<!DOCTYPE html>
<html lang="en">

<head>

	<!-- 결제 API연결 -->
	<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
	<script type="text/javascript">
		function requestPay(count) {
			var IMP = window.IMP;
			IMP.init("imp57599045"); // 가맹정 식별코드 입력

			IMP.request_pay({
				pg: 'inicis', // version 1.1.0부터 지원.
				pay_method: 'card',
				merchant_uid: 'merchant_' + new Date().getTime(),
				name: $('#productName'+count).val(),
				amount: $('#price'+count).val(),
				buyer_email: $('#memberEmail'+count).val(),
				buyer_name: $('#memberName'+count).val(),
				buyer_tel: $('#memberTel'+count).val(),
				buyer_addr: $('#memberAddr'+count).val(),
				buyer_postcode: '123-456',
				m_redirect_url: 'https://www.yourdomain.com/payments/complete'
			}, function (rsp) {
				if (rsp.success) {
					var msg = '결제가 완료되었습니다.';
					msg += '고유ID : ' + rsp.imp_uid;
					msg += '상점 거래ID : ' + rsp.merchant_uid;
					msg += '결제 금액 : ' + rsp.paid_amount;
					msg += '카드 승인번호 : ' + rsp.apply_num;
				} else {
					var msg = '결제에 실패하였습니다.';
					msg += '에러내용 : ' + rsp.error_msg;
				}
				alert(msg);
			});
		}
	</script>

	<!-- 스타일 인클루드 -->
	<jsp:include page="../include/style.jsp" />
	<style type="text/css">
		table.pdList {
			border-collapse: separate;
			border-spacing: 1px;
			text-align: center;
			line-height: 1.5;
			margin: 50px 10px;
			width: 115%;
		}

		table.pdList th {
			width: 0px;
			padding: 10px;
			font-weight: bold;
			vertical-align: top;
			color: #fff;
			background: #ff5f5f;
		}

		table.pdList td {
			width: 0px;
			padding: 10px;
			vertical-align: top;
			border-bottom: 1px solid #ccc;
			background: #eee;
			font-size: smaller;
		}
		
		table.pdList .left {
	text-align: left !important;
}
table.pdList .right {
	text-align: right !important;
}

		table.pdList td input {
			font-size: smaller;
		}
		table.pdList .price {
	color: #f47422 !important;
	font-weight: 700 !important;
}
	</style>
</head>

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
					<div class="managerdetailform">
						<h3 class="mb-5"></h3>
					</div>
							<h3>
								<label for="name">예약 상품 리스트</label>
							</h3>
							<%
								if (rb != null && listCount > 0) {
							%>
							<table class="pdList" id="pdlist">
								<tr>
									<th>예약번호</th>
									<th>고객아이디</th>
									<th>예약일</th>
									<th>출발날짜/도착날짜</th>
									<th>예약상품</th>
									<th>예약인원</th>
									<th>금액</th>
									<th>결제방법</th>
									<th>진행상태</th>
									<th>리뷰쓰기</th>
								</tr>
								<%
									for (int i = 0; i < rb.size(); i++) {
								%>
								<tr>
									<td><%=rb.get(i).getReservation_num()%></td>
									<td><input type="hidden" id="memberName<%=i %>" value="<%=memberList.getMember_name()%>"><%=rb.get(i).getReservation_member_id()%>
									<input type="hidden" id="memberEmail<%=i %>" value="<%=memberList.getMember_email()%>">
									<input type="hidden" id="memberTel<%=i %>" value="<%=memberList.getMember_num()%>">
									<input type="hidden" id="memberAddr<%=i %>" value="<%=memberList.getMember_addr()%>">
									</td>
									<td><%=rb.get(i).getReservation_date()%></td>
									<td>출발&nbsp;<%=rb.get(i).getPackage_product_depart_date()%><br>도착&nbsp;<%=rb.get(i).getPackage_product_arriv_date()%></td>
									<td class="left"><input type="hidden" id="productName<%=i %>" value="<%=rb.get(i).getPackage_category_name()%>"><%=rb.get(i).getPackage_category_name()%></td>
									<td><%=rb.get(i).getReservation_headcount()%></td>
									<td class="right price"><input type="hidden" id="price<%=i %>" value="<%=rb.get(i).getReservation_price()%>"><%=rb.get(i).getReservation_price()%></td>
									<td><%=rb.get(i).getReservation_pay_way()%></td>
									<%
										if (rb.get(i).getReservation_progress().equals("예약완료")) {
									%>
									<td><input type="button" class="btn py-1 px-2 btn-primary" value="결제하기"
											onclick="requestPay(<%=i %>)"></td>
									<%
										} else {
									%>
									<td><%=rb.get(i).getReservation_progress()%></td>
									<%
										}
									%>
									<!--작업 중  -->
									<td><input type="button" value="리뷰작성" class="btn py-3 px-4 btn-primary" onclick="location.href='./ReviewWriteForm.rv?<%=rb.get(i).getReservation_category_code()%>'"></td>
								</tr>
								<%
									}
								%>
							</table>
							<!-- 페이지 부분 -->
							<div class="row mt-5">
								<div class="col text-center">
									<div class="block-27">
										<ul>
											<%
												if (nowPage <= 1) {
											%>
											<li><a>&lt;</a></li>
											<%
												} else {
											%><li><a href="ReservationInfo.rs?page=<%=nowPage - 1%>&member_id=<%=member_id%>">&lt;</a></li>
											<%
												}
											%>
											<%
												for (int i = startPage; i <= endPage; i++) {
														if (i == nowPage) {
											%><li class="active"><span><%=i%></span></li>
											<%
												} else {
											%>
											<li><a
													href="ReservationInfo.rs?page=<%=i%>&member_id=<%=member_id%>"><%=i%></a>
											</li>
											<%
												}
											%>
											<%
												}
											%>
											<%
												if (nowPage >= maxPage) {
											%><li><a>&gt;</a></li>
											<%
												} else {
											%>
											<li><a
													href="ReservationInfo.rs?page=<%=nowPage + 1%>&member_id=<%=member_id%>">&gt;</a>
											</li>
											<%
												}
											%>
										</ul>
									</div>
								</div>
							</div>
							<!-- 페이지 부분 -->
							<%
								} else {
							%>
							<div>예약상품이 없습니다.</div>
							<div  style="text-align: center;"><input type="button" class="btn py-2 px-2 btn-primary" value="홈으로" onclick="location.href='./index.jsp'" style="margin-right: 30px;"> <input type="button" class="btn py-2 px-2 btn-primary" value="상품 페이지" onclick="location.href='ProductList.pr'" style="margin-left: 30px;"></div>
							<%
								}
							%>
		</div>
	</section>
	<!-- footer 인클루드 -->
	<jsp:include page="../include/footer.jsp" />
	<!-- loader 인클루드 -->
	<jsp:include page="../include/loader.jsp" />
</body>

</html>