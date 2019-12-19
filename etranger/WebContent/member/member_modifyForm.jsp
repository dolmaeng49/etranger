<%@page import="member.vo.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
 	String member_id = request.getParameter("member_id");
    MemberBean memberBean=(MemberBean)request.getAttribute("memberBean");
    
    
    String delete_id = null; 
	if(session.getAttribute("member_id")!=null){
		delete_id = (String)session.getAttribute("member_id");
	}
    
%>
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
			width: 130px;
			height: 50px;
			padding: 0.7rem 0.5rem !important;
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
							<span class="mr-2"><a href="./index.jsp">Home</a></span> <span>Create an Account</span>
						</p>
						<h1 class="mb-3">Create an Account</h1>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- END slider -->

	<form action="MemberModifyPro.me" method="post" name="modifyForm" id="modifyForm">
		<section class="ftco-section contact-section">
			<div class="container">
				<div class="row block-9 mb-4">
					<div class="col-md-6 pr-md-5 flex-column">
						<div class="row d-block flex-row">
							<h2 class="h4 mb-4">Account Information</h2>
							<!-- 아이디 -->
							<div class="form-group">
								<input type="text" class="form-control form-control-short" placeholder="ID" name="member_id"
									required="required" value="<%=memberBean.getMember_id()%>" readonly="readonly"/>
							</div>
							<!-- 아이디 유효성 검사 결과 표시 -->
							<div class="form-group form-group-message">
<!-- 								<input type="hidden" id="member_id_DupCheck" placeholder="hidden으로 바꿀 예정"> -->
<!-- 								<span id="checkIdResult"></span> -->
							</div>

							<div class="form-group">
								<input type="password" class="form-control"
									placeholder="Password" name="member_passwd" required="required">
							</div>
							<div class="form-group form-group-message" style="text-align: right;">
								<a href="MemberFindPasswdForm.me">P/W 변경하러 가기</a>
							</div>
							<div class="form-group form-group-message"></div>
							<!-- <div class="form-group">
								<input type="password" class="form-control" id="member_passwd_retype" placeholder="Retype Password"
									name="member_passwd2" required="required">
							</div>
							패스워드 유효성 검사 결과, 패스워드 재입력 일치 여부 표시
							<div class="form-group form-group-message">
								<span id="checkPasswdResult"></span><br>
							</div>
							<div class="form-group form-group-message">
								<span id="checkPasswdRetype"></span>
							</div> -->
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Name" name="member_name" required="required"
								value="<%=memberBean.getMember_name()%>"/>
							</div>
						</div>
					</div>
					<div class="col-md-6">
						<div class="form-group">
							<input type="number" class="form-control" placeholder="Phone Number" name="member_phone" required="required"
							value="<%=memberBean.getMember_phone()%>" maxlength="14">
						</div>
						
						<!-- EMail -->
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Email" name="member_email" required="required" onkeyup="checkEmail(this)"
							 id="member_email" value="<%=memberBean.getMember_email()%>" readonly="readonly"/>
							
							<span id="old_email_check">
								<input type="button" value="기존 Email 사용" class="btn btn-primary py-3 px-5 btn-small" id="select_old_email">
								<input type="button" value="새 Email 인증" class="btn btn-primary py-3 px-5 btn-small" id="select_new_email">
							</span>
							
							<span id="new_email_check" style="display: none;">
								<input type="text" class="form-control form-control-shortshort" 
									placeholder="Email 인증 코드" name="member_email_code" id="member_email_code">
								<input type="button" value="인증" class="btn btn-primary py-3 px-5 btn-small" id="btn_email_check">
								<input type="button" value="인증코드받기" class="btn btn-primary py-3 px-5 btn-small" id="btn_email_code">
							</span>
							
							<input type="hidden" id="email_check" placeholder="hidden으로 바꿀 예정">
							<br><span id="checkEmailResult"></span>
						</div>

							<div class="form-group">
								<input type="text" class="form-control" placeholder="Address"
									name="member_addr" required="required" id="sample6_address"
									readonly="readonly" onclick="sample6_execDaumPostcode()" value="<%=memberBean.getMember_addr()%>"/>
								<input type="text" id="sample6_detailAddress"
									placeholder="Detail Address" class="form-control" name="member_addr2" value="<%=memberBean.getMember_addr2()%>">
								<!-- 우편번호, 참고항목은 type="hidden"으로 처리 -->
								<input type="hidden" id="sample6_postcode"
									class="form-control form-control-short" name="member_addr3" value="<%=memberBean.getMember_addr3()%>">
								<input type="hidden" id="sample6_extraAddress"
									class="form-control form-control-short" name="member_addr4" value="<%=memberBean.getMember_addr4()%>">
							</div>
							<div class="form-group">
								<input type="text" class="form-control form-control-shortshort pick_date"
									id="checkin_date" value="<%=memberBean.getMember_birth() %>" placeholder="Birth"
									name="member_birth" required="required" readonly="readonly">&nbsp;&nbsp;&nbsp;
								<span>생년월일을 선택해주세요</span>
							</div>
							<div class="form-group">
								<input type="radio" name="member_gender" value="m" id="gender_man"
									<%if(memberBean.getMember_gender().equals("m")){%>
										checked="checked"
									<%} %>
								>Man&nbsp;&nbsp;&nbsp;
								<input type="radio" name="member_gender" value="f" id="gender_woman"
									<%if(memberBean.getMember_gender().equals("f")){%>
										checked="checked"
									<%} %>
								>Woman
							</div>
							<!-- 버튼 -->
							<div class="form-group">
								<input type="submit" value="수정완료" class="btn btn-primary py-3 px-5">
<!-- 								<input type="button" value="탈퇴하기" class="btn btn-primary py-3 px-5" id="withdrawal" onclick="location.href='layer2Withdrawal'"> -->
							<a href="#layer2Withdrawal" id="withdrawal" class="btn btn-primary py-3 px-5">탈퇴하기</a>
							
							</div>
					</div>
				</div>

			</div>
		</section>
	</form>
	<!-- 딤 시작 -->
	<div class="dim-layerWithdrawal">
							<div class="dimBgWithdrawal"></div>
							<div id="layer2Withdrawal" class="pop-layerWithdrawal">
								<div class="pop-containerWithdrawal">
									<div class="pop-contsWithdrawal">
										<!--content //-->
										<form action="MemberDeletePro.me" method="post">
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
						<div class="btn-rWithdrawal">
												<a href="#" id="btnPopUpCloseWithdrawal"
													class="btn py-1 px-2 btn-primary">창 닫기</a>
											</div>
					</div>
	</form>
										<!--// content-->
									</div>
								</div>
							</div>
						</div>
	<!-- 딤 끝 -->

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