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

	<h1>관리자 페이지 테스트</h1>
	<div class="comment-form-wrap pt-5">
		<input type="button" class="search-submit btn btn-primary" value="상품 분류 등록" onclick="location.href='ManagerInsert.ma'">
		
	</div>

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
</body>
</html>