<%@page import="common.db.InsertProductData"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<!-- 스타일 인클루드 -->
	<jsp:include page="../include/style.jsp" />
	<style type="text/css">
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
			height: 1em;
			margin-bottom: 1.5em;
		}
	</style>
</head>
<body>

	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="/include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item"
			style="background-image: url('images/bg_5.jpg');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-7 col-sm-12 ftco-animate">
						<p class="breadcrumbs">
							<span class="mr-2"><a href="../main/index.jsp">Home</a></span> <span>Create an Account</span>
						</p>
						<h1 class="mb-3">Create an Account</h1>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- END slider -->

	<form action="MemberJoinPro.me" method="post" name="joinForm" id="joinForm">
		<section class="ftco-section contact-section">
			<div class="container">
				<div class="row block-9 mb-4">
					<div class="col-md-6 pr-md-5 flex-column">
						<div class="row d-block flex-row">
							<h2 class="h4 mb-4">Account Information</h2>
							<!-- 아이디 -->
							<div class="form-group">
								<input type="text" class="form-control form-control-short" placeholder="ID" name="member_id"
									required="required" id="member_id">
								<input type="button" value="Dup.Check" class="btn btn-primary py-3 px-5 btn-small" id="btn_dup">
							</div>
							<!-- 아이디 유효성 검사 결과 표시 -->
							<div class="form-group form-group-message">
								<input type="hidden" id="member_id_DupCheck" placeholder="hidden으로 바꿀 예정">
								<span id="checkIdResult"></span>
							</div>

							<div class="form-group">
								<input type="password" class="form-control" id="member_passwd"
									placeholder="Password" name="member_passwd" required="required">
							</div>
							<div class="form-group">
								<input type="password" class="form-control" id="member_passwd_retype" placeholder="Retype Password"
									name="member_passwd2" required="required">
							</div>
							<!-- 패스워드 유효성 검사 결과, 패스워드 재입력 일치 여부 표시 -->
							<div class="form-group form-group-message">
								<span id="checkPasswdResult"></span><br>
							</div>
							<div class="form-group form-group-message">
								<span id="checkPasswdRetype"></span>
							</div>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Name" name="member_name" required="required">
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<input type="number" class="form-control" placeholder="Phone Number" name="member_phone" required="required" maxlength="14">
						</div>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Email" name="member_email" id="member_email" required="required" onkeyup="checkEmail(this)">
							<input type="text" class="form-control form-control-shortshort"
								placeholder="인증 코드" name="member_email_code" id="member_email_code" required="required">
							<input type="button" value="인증" class="btn btn-primary py-3 px-5 btn-small" id="btn_email_check">
							<input type="button" value="인증코드받기" class="btn btn-primary py-3 px-5 btn-small" id="btn_email_code">
							<input type="hidden" id="email_check" placeholder="hidden으로 바꿀 예정">
							<br><span id="checkEmailResult"></span>
						</div>

							<div class="form-group">
								<input type="text" class="form-control" placeholder="Address"
									name="member_addr" required="required" id="sample6_address"
									readonly="readonly" onclick="sample6_execDaumPostcode()">
								<input type="text" id="sample6_detailAddress"
									placeholder="Detail Address" class="form-control" name="member_addr2">
								<!-- 우편번호, 참고항목은 type="hidden"으로 처리 -->
								<input type="hidden" id="sample6_postcode"
									class="form-control form-control-short" name="member_addr3">
								<input type="hidden" id="sample6_extraAddress"
									class="form-control form-control-short" name="member_addr4">
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
							<div class="form-group">
								<input type="submit" value="Join"
									class="btn btn-primary py-3 px-5">
							</div>
					</div>
				</div>

			</div>
		</section>
	</form>

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
	<script src="js/member.js"></script>
	<!-- kakao 우편번호 -->
	<script
		src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script src="js/member.kakao.js"></script>
</body>
</html>