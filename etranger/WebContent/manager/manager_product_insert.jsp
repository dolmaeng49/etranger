<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
							<h1 class="mb-3">Manager</h1>
						</div>
					</div>
				</div>
			</div>
		</section>
	</header>

	<!-- END slider -->

<div class="container">
	<form action="ProductInsertPro.ma" class="p-5 bg-light" method="post">
		<table class="pdList">
			<tr id="tr_top">
				<th>상품코드</th>
				<th>출발날짜</th>
				<th>도착날짜</th>
				<th>가격</th>
				<th>총인원수</th>
				<th>등록</th>
			</tr>
			<tr>
				<td><input type="text" class="form-control"
					name="package_category_code" id="package_category_code"
					value="<%=request.getParameter("package_category_code")%>" /></td>
				<td><input type="text"
					class="form-control form-control-shortshort pick_date"
					id="checkin_date" value="2019/12/1" placeholder="depardate"
					name="product_depardate" required="required"></td>
				<td><input type="text"
					class="form-control form-control-shortshort pick_date"
					id="checkin_date" value="2019/12/1" placeholder="arrivdate"
					name="product_arrivdate" required="required"></td>
				<td><input type="text" class="form-control"
					name="product_price" id="product_price" placeholder="price" /></td>
				<td><input type="text" class="form-control"
					name="product_total" id="product_total" placeholder="total" /></td>
				<td><input type="submit" value="등록"
					class="btn py-3 px-4 btn-primary"></td>
			</tr>
		</table>
	</form>
</div>
	<!-- .section -->

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />

</body>
</html>