<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String delete_id = null; 
	if(session.getAttribute("member_id")!=null){
		delete_id = (String)session.getAttribute("member_id");
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<%	if(delete_id == null){%>
		<script type="text/javascript">
			alert('로그인이 필요한 기능입니다!');
			location.href="LoginForm.me";
		</script>
	<%}%>
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
								href="MemberLoginForm.me">회원 탈퇴</a></span>
						</p>
						<h1 class="mb-3">회원 탈퇴</h1>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- END slider -->
	
<form action="MemberDeletePro.me" method="post">
	<section class="ftco-section">
		<div class="container">
			<div class="row row-padding">
				<div class="col-md-8 ftco-animate">


					<div class="comment-form-wrap pt-5 form-wrap-login">
						<h3 class="mb-5">회원 탈퇴</h3>
						<div class="form-group">
							<label for="id">ID</label><br>
							<input type="text" class="form-control" required="required" name="member_id" value="<%=delete_id%>" readonly="readonly">
						</div>
						<div class="form-group">
							<label for="password">PASSWORD</label><br>
							<input type="password" class="form-control" required="required" name="member_passwd">
						</div>
			 			<div class="form-group form-group-btn">
							<input type="submit" value="탈퇴하기" class="btn py-3 px-4 btn-primary">&nbsp;&nbsp;
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