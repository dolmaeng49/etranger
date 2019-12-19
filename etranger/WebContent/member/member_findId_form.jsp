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
		width: 80%;
	}
	.wrap-links {
		text-align: right;
	}
	.form-control-short {
		display: inline-block;
		width: 60%;
	}
	.form-control-shortshort {
		display: inline-block;
		width: 30%;
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
								href="MemberFindIdForm.me">Find ID</a></span>
						</p>
						<h1 class="mb-3">Find ID</h1>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- END slider -->
	<section class="ftco-section">
		<div class="container">
			<div class="row row-padding">
				<div class="col-md-8 ftco-animate">


					<div class="comment-form-wrap pt-5 form-wrap-login">
						<h3 class="mb-5">Find ID</h3>
<form action="MemberFindIdPro.me" method="post" id="findIdForm">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Name" name="member_name"
								required="required">
						</div>
						<div class="form-group">
							<input type="text" class="form-control form-control-shortshort pick_date"
								id="checkin_date" value="1990/1/1" placeholder="Birth" readonly="readonly"
								name="member_birth" required="required">&nbsp;&nbsp;&nbsp;
							<span>생년월일을 선택해주세요</span>
						</div>
						<div class="form-group">
							<input type="radio" name="member_gender" value="m" id="gender_man">Man&nbsp;&nbsp;&nbsp;
							<input type="radio" name="member_gender" value="f" id="gender_woman">Woman
						</div>
						<div class="form-group form-group-btn">
							<input type="submit" value="ID 찾기" class="btn btn-primary py-3 px-5">
						</div>
</form>
						<p class="wrap-links"><a href="MemberFindPasswdForm.me">P/W 변경</a> |
							<a href="MemberJoinForm.me">회원가입</a> |</p>
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
	<script src="js/member.js"></script>

</body>
</html>