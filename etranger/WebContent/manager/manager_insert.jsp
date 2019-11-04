<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<!-- 스타일 include -->
<jsp:include page="../include/style.jsp" />

</head>
<body>
	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="../include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item" style="background-image: url('../images/bg_4.jpg');" data-stellar-background-ratio="0.5">
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
	<!-- END slider -->

	<div class="comment-form-wrap pt-5">
		<h3 class="mb-5">상품 분류 등록</h3>
		<!-- 상품코드 이름 지역 도시 테마 -->
		<form action="ManagerProInsert.ma" class="p-5 bg-light">
			<div class="form-group">
				<label for="region">지역이름 *</label> <input type="text" class="form-control" name="region_name">
			</div>
			<div class="form-group">
				<label for="code">도시이름 *</label> <input type="text" class="form-control" name="city_name">
			</div>

			<div class="form-group">
				<input type="submit" value="등록하기" class="btn py-3 px-4 btn-primary">
			</div>
		</form>
	</div>

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
</body>
</html>