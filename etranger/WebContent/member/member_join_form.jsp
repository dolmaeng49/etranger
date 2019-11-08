<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>

<script type="text/javascript">
	function checkId(member_id) {
		var regex = /^[A-Za-z0-9_]{4,16}$/g;
		var element = document.getElementById('checkIdResult');
		if (regex.exec(member_id.value)) {
			element.innerHTML = "사용 가능한 아이디입니다.";
		} else {
			element.innerHTML = "사용 불가능한 아이디입니다.";
		}
	}
	
	function checkPasswd(member_passwd) {
		var lengthCaseRegex = /[A-Za-z0-9!@#$%^&*()_+]{8,20}/;
		var upperCaseRegex = /[A-z]/;
		var lowerCaseRegex = /[a-z]/;
		var digitCaseRegex = /[0-9]/;
		var specialCharRegex = /[!@#$%^&*()_+]/
		var element = document.getElementById('checkPasswdResult');
		if(lengthCaseRegex.exec(member_passwd.value) && upperCaseRegex.exec(member_passwd.value)
				&& lowerCaseRegex.exec(member_passwd.value) && digitCaseRegex.exec(member_passwd.value)
				&& specialCharRegex.exec(member_passwd.value)){
			element.innerHTML = "사용 가능한 패스워드 입니다.";
		}else{
			element.innerHTML = "사용 불가능한 패스워드 입니다.";
		}
		}
	function checkEmail(member_email) {

		var element = document.getElementById('checkEmailResult');

		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

		if(exptext.exec(member_email.value)){

			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우

			element.innerHTML="올바른 이메일 형식입니다."

		}else{

			element.innerHTML="올바른 이메일 형식이 아닙니다."

		}

	}

	
	
</script>
<!-- 스타일 인클루드 -->
<jsp:include page="/include/style.jsp" />
<style type="text/css">
.form-control-short {
	display: inline-block;
	width: 60%;
}

.form-group-message {
	height: 1em;
}

.container, .navbar-brand, .navbar-collapse {
	border: 1px solid red;
}

#login-info-div {
	border: 1px solid blue;
}

#ftco-navbar {
	margin-top: 20px;
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
							<span class="mr-2"><a href="../main/index.jsp">Home</a></span> <span>Create
								an Account</span>
						</p>
						<h1 class="mb-3">Create an Account</h1>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- END slider -->
<form action="MemberJoinPro.me" method="post" name="joinForm">
	<section class="ftco-section contact-section">
		<div class="container">
			<div class="row block-9 mb-4">
				<div class="col-md-6 pr-md-5 flex-column">
					<div class="row d-block flex-row">
						<h2 class="h4 mb-4">Account Information</h2>
						<div class="form-group">
							<input type="text" class="form-control form-control-short"
								placeholder="ID" name="member_id" required="required"
								onkeyup="checkId(this)"> 
							 <span id="checkIDresult"></span>
							 <input type="button" value="Dup.Check" class="btn btn-primary py-3 px-5">
						</div>
						<div class="form-group form-group-message"></div>
						<div class="form-group">
							<input type="password" class="form-control"
								placeholder="Password" name="member_passwd" required="required"
								onkeyup="checkPasswd(this)">
							<span id="checkPasswdResult"></span>
						</div>
						<div class="form-group">
							<input type="password" class="form-control"
								placeholder="Retype Password" name="member_passwd2"
								required="required">
						</div>
						<div class="form-group form-group-message"></div>
					</div>
				</div>
				<div class="col-md-6">
					<form action="#">
					<div class="form-group">
							<input type="text" class="form-control"
								placeholder="Name" name="member_name"
								required="required">
						</div>
					
						<div class="form-group">
							<input type="text" class="form-control"
								placeholder="Phone Number" name="member_phone"
								required="required">
						</div>
						<div class="form-group">
							<input type="email" class="form-control" placeholder="Email"
								name="member_email" required="required"onkeyup="checkEmail(this)">

                <span id ="checkEmailResult"></span>

						</div>
						<div class="form-group">

							<input type="text" class="form-control form-control-short" placeholder="Address"
								name="member_addr" required="required" id="sample6_postcode"> 
							<input type="button" onclick="sample6_execDaumPostcode()"
								value="Zip code" class="btn btn-primary py-3 px-5" ><br>
								<input type="text" id="sample6_address" placeholder="주소" class="form-control form-control-short" name="member_addr2"><br>
							<input type="text" id="sample6_detailAddress" placeholder="상세주소" class="form-control form-control-short" name="member_addr3">
							<input type="text" id="sample6_extraAddress" placeholder="참고항목" class="form-control form-control-short" name="member_addr4">

							<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
							<script>
    						function sample6_execDaumPostcode() {
       						 new daum.Postcode({
         					   oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample6_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>
						</div>
						<div class="form-group">
							<input type="text" class="form-control pick_date"
								id="checkin_date" value="1990/1/1" placeholder="Birth"
								name="member_birth" required="required">
						</div>
						<div class="form-group">
							<input type="radio" name="member_gender" value="1">Man&nbsp;&nbsp;&nbsp;
							<input type="radio" name="member_gender" value="2">Woman
						</div>
						<!--               <div class="form-group"> -->
						<!--                 <textarea name="" id="" cols="30" rows="7" class="form-control" placeholder="Message"></textarea> -->
						<!--               </div> -->
						<div class="form-group">
							<input type="submit" value="Join"
								class="btn btn-primary py-3 px-5">
						</div>
					</form>
				</div>
			</div>
			
			<!--         <div class="row mt-5"> -->
			<!--           <div class="col-md-12" id="map"></div> -->
			<!--         </div> -->
		</div>
	</section>

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
</form>
</body>
</html>