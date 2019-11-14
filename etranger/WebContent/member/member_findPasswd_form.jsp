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
	.btn-small {
		width: 110px;
		height: 50px;
		padding: 0.7rem 0.3rem !important;
		text-align: center;
	}
	.form-group-message {
		height: 0.8em;
		margin-bottom: 1.3em;
		font-size: 0.8em;
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
							<span class="mr-2"><a href="../main/index.jsp">Home</a></span><span><a 
								href="MemberFindIdForm.me">Find Password</a></span>
						</p>
						<h1 class="mb-3">Find Password</h1>
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
						<h3 class="mb-5">Find Password</h3>
<form action="MemberResetPasswdPro.me" method="post" id="findPasswdForm">
						<div class="form-group">
							<input type="text" class="form-control" placeholder="비밀번호를 찾을 ID를 입력해주세요" name="member_id"
								required="required" id="member_id_findPasswd">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="가입시 입력한 E-mail을 입력해주세요" name="member_email" id="member_email" required="required">
							<input type="text" class="form-control form-control-shortshort"
								placeholder="인증 코드" name="member_email_code" id="member_email_code" required="required">
							<input type="button" value="인증" class="btn btn-primary py-3 px-5 btn-small" id="btn_email_check">
							<input type="button" value="인증코드받기" class="btn btn-primary py-3 px-5 btn-small" id="btn_email_code_findPasswd">
							<input type="hidden" id="email_check" placeholder="hidden으로 바꿀 예정">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="member_passwd"
								placeholder="Password" name="member_passwd" required="required">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" id="member_passwd_retype" placeholder="Retype Password"
								name="member_passwd2" id="member_passwd2" required="required">
						</div>
						<!-- 패스워드 유효성 검사 결과, 패스워드 재입력 일치 여부 표시 -->
						<div class="form-group form-group-message">
							<p id="checkPasswdResult"></p><br>
						</div>
						<div class="form-group form-group-message">
							<span id="checkPasswdRetype"></span>
						</div>
						<div class="form-group form-group-btn">
							<input type="submit" value="비밀번호 변경 " class="btn btn-primary py-3 px-5">
						</div>
</form>
						<p class="wrap-links"><a href="MemberFindIdForm.me">ID 찾기</a> |
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