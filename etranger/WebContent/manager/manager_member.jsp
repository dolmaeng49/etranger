<%@page import="reservation.vo.ReservationBean"%>
<%@page import="common.vo.PageInfo"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<ReservationBean> reservList = (ArrayList<ReservationBean>) request.getAttribute("reservList");
	String code = "";
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<!-- 스타일 include -->
<jsp:include page="../include/style.jsp" />
<link rel="stylesheet" href="./css/style_management.css" type="text/css">
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
	<!--side bar -->

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
							onclick="location.href='ReservManagement.ma'">&nbsp;­회원예약</li>
						<li class="list-group-item li_hover member">&nbsp;­회원등급</li>
					</ul>
				</div>
			</div>

			<input type="hidden" id="deletenum" name="<%=code%>">
			<div class="col-md-10">
				<!-- 고객아이디 예약일(결제시한) 예약번호 예약상품(이름) 예약인원 출발날짜/도착날짜 금액 진행상태 -->
				<table class="reservList">
					<tr>
						<th>예약번호</th>
						<th>고객아이디</th>
						<th>예약일</th>
						<th>예약상품</th>
						<th>예약인원</th>
						<th>출발날짜/도착날짜</th>
						<th>금액</th>
						<th>결제유무</th>
						<th>진행상태</th>
						<th>제어</th>
					</tr>
					<%
						if (reservList != null && listCount > 0) {
							for (int i = 0; i < reservList.size(); i++) {
					%>

					<tr>

						<td><%=reservList.get(i).getReservation_num()%></td>
						<td><%=reservList.get(i).getReservation_member_id()%></td>
						<td><%=reservList.get(i).getReservation_date()%></td>
						<td class="left"><%=reservList.get(i).getPackage_category_name()%><br>
							<%=reservList.get(i).getReservation_category_code()%></td>
						<td><%=reservList.get(i).getReservation_headcount()%></td>
						<td>출발&nbsp;<%=reservList.get(i).getPackage_product_depart_date()%><br>도착&nbsp;<%=reservList.get(i).getPackage_product_arriv_date()%></td>
						<td class="right price"><%=reservList.get(i).getReservation_price()%></td>
						<td><%=reservList.get(i).getReservation_ispayment()%></td>
						<td><select name="progress"
							onChange="updateYesOrNo(this,'<%=reservList.get(i).getReservation_num()%>')">
								<option value="<%=reservList.get(i).getReservation_progress()%>"
									selected><%=reservList.get(i).getReservation_progress()%></option>
								<option value="none" disabled>--변경 시 선택--</option>
								<option value="예약완료">예약완료</option>
								<option value="예약취소">예약취소</option>
								<option value="결제완료">결제완료</option>
								<option value="결제취소">결제취소</option>
						</select></td>
						<td><input type="button" value="삭제" class="btn py-1 px-2 btn-primary deletecode"
							onclick="deleteYesOrNo('<%=reservList.get(i).getReservation_num()%>')"
							></td>

					</tr>
					<%
						}
						}
					%>

				</table>

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
								%><li><a href="ReservManagement.ma?page=<%=nowPage - 1%>">&lt;</a></li>
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
								<li><a href="ReservManagement.ma?page=<%=i%>"><%=i%></a></li>
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
								<li><a href="ReservManagement.ma?page=<%=nowPage + 1%>">&gt;</a></li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
				</div>

			</div>

		</div>
	</div>





	<script>
		//예약 삭제
		function deleteYesOrNo(rnum) {
			if (confirm("정말 삭제하시겠습니까?")) {
				$.ajax('ReservDelete.ma', {
					data : {
						reservation_num : rnum
					},
					success : function(sdata) {
						if (sdata == 'false') {
							alert('예약 삭제가 실패했습니다.');
						} else {
							alert('삭제가 완료되었습니다.');
							location.reload();
						}

					}
				});

			} else {
			}

		}

		// 예약 진행상태 변경
		function updateYesOrNo(rprogress, rnum) {
			if (confirm("변경하시겠습니까?")) {
				$.ajax('ReservUpdate.ma', {
					data : {
						reservation_progress : rprogress.value,
						reservation_num : rnum
					},
					success : function(sdata) {
						if (sdata == 'false') {
							alert('진행상태 수정이 실패했습니다.');
						} else {
							alert('수정이 완료되었습니다.');
						}
					}
				});

			} else {
				// 이건 머지?
			}
		}
	</script>


	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
	<!-- JavaScript 가져오기 -->
	<script src="js/manager.js"></script>

</body>

</html>