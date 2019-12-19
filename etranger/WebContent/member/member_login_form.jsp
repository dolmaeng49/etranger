<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp" />
<style type="text/css">
	.form-group-btn {
		text-align: right;
	}
	.row-padding {
		padding-left: 20%
	}
	.form-wrap-login {
		display: inline-block;
		width: 70%;
	}
	.wrap-links {
		text-align: right;
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
							<span class="mr-2"><a href="./index.jsp">Home</a></span><span><a 
								href="MemberLoginForm.me">Login</a></span>
						</p>
						<h1 class="mb-3">Login</h1>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- END slider -->

	
<form action="LoginPro.me" method="post">
	<section class="ftco-section">
		<div class="container">
			<div class="row row-padding">
				<div class="col-md-8 ftco-animate">


					<div class="comment-form-wrap pt-5 form-wrap-login">
						<h3 class="mb-5">Login</h3>
						<div class="form-group">
							<label for="id">ID</label><br>
							<input type="text" class="form-control" id="id" required="required" name="member_id">
						</div>
						<div class="form-group">
							<label for="password">PASSWORD</label><br>
							<input type="password" class="form-control" id="passwd" required="required" name="member_passwd">
						</div>
						<p class="wrap-links"><a href="MemberFindIdForm.me">ID 찾기</a> |
							<a href="MemberFindPasswdForm.me">P/W 변경</a> |
							<a href="MemberJoinForm.me">회원가입</a> |</p>
			 			<div class="form-group form-group-btn">
							<input type="submit" value="Login" class="btn py-3 px-4 btn-primary">&nbsp;&nbsp;
						</div>
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


</form>
</body>
</html>