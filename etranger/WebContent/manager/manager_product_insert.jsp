<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp" />
</head>
<body>
	<header>
		<!-- 탑메뉴 인클루드 -->
		<jsp:include page="../include/top_menu.jsp" />
		<section class="home-slider owl-carousel">
			<div class="slider-item" style="background-image: url('./images/bg_4.jpg');" data-stellar-background-ratio="0.5">
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

	<section class="ftco-section">
		<div class="container">
			<form action="ProductInsertPro.ma" class="p-5 bg-light" method="post">
				<div class="col-md-8 ftco-animate">
					<div class="col-md-5">
						<input type="text" class="form-control" name="package_category_code" id="package_category_code" placeholder="total" value="<%=request.getParameter("package_category_code")%>" />
					</div>
					<div class="row">
						<div class="col-md-5">
							<input type="text" class="form-control" name="product_total" id="product_total" placeholder="total" />
						</div>
						<div class="col-md-5">
							<input type="text" class="form-control" name="product_price" id="product_price" placeholder="price" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-5">
							<label for="name">arrivdate</label><input type="text" class="form-control form-control-shortshort pick_date" id="checkin_date" value="1990/1/1" placeholder="arrivdate" name="product_arrivdate" required="required">
						</div>

						<div class="col-md-5">
							<label for="name">depardate</label><input type="text" class="form-control form-control-shortshort pick_date" id="checkin_date" value="1990/1/1" placeholder="depardate" name="product_depardate" required="required">
						</div>
					</div>
					<div class="form-group" style="display: block; clear: both;">
						<br>
						<input type="submit" value="등록하기" class="btn py-3 px-4 btn-primary">&nbsp;&nbsp; <input type="reset" value="다시쓰기" class="btn py-3 px-4 btn-primary">
					</div>
				</div>

			</form>
		</div>
	</section>
	<!-- .section -->

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />

</body>
</html>