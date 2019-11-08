<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp" />
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
							<span class="mr-2"><a href="../main/index.jsp">Home</a></span><span><a 
								href="blog.html">Blog</a></span> <span>Single Blog</span>
						</p>
						<h1 class="mb-3">Login</h1>
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


					<div class="comment-form-wrap pt-5">
						<h3 class="mb-5">Login</h3>
						<form action="#" class="p-5 bg-light">
							<div class="form-group">
								<label for="id">ID *</label> <input type="text"
									class="form-control" id="id">
							</div>
							<div class="form-group">
								<label for="password">PASSWORD *</label> <input type="password"
									class="form-control" id="passwd">
							</div>
							<div class="form-group">
								<input type="submit" value="Login"
									class="btn py-3 px-4 btn-primary">

			
								<input type="button" value="Sign up"
									class="btn py-3 px-4 btn-primary"
									onclick="location.href='MemberJoinForm.me'">
							</div>

						</form>
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

</body>
</html>

</body>
</html>