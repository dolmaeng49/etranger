<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
 	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
<style type="text/css">
	.form-control-short {
		display: inline-block;
		width: 60%;
	}
	.form-group-message {
		height: 1em;
	}
	.container, .navbar-brand, .navbar-collapse {
		border: 1px solid red;}
	#login-info-div {
		border: 1px solid blue;
	}
	#ftco-navbar {
		margin-top: 20px;
	}
	
	
</style>
<script type="text/javascript">
	function checkId(member_id){
		var regex = /^[A-Za-z0-9_]{4,16}$/g;
		var element = document.getElementById('checkIdResult');
		if(regex.exec(member_id.balue)){
			element.innerHTML="사용 가능한 아이디입니다.";
		}else{
			element.innerHTML="사용 불가능한 아이디입니다.";		
	}
}
	function checkPasswd(member_passwd) {
		var lengthCaseRegex = /[A-Za-z0-9!@#$%^&*()_+]{8,20}/;
		var upperCaseRegex = /[A-z]/;
		var loverCaseRegex = /[a-z]/;
		var digitCaseRegex = /[0-9]/;
		var specialCharRegex = /[!@#$%^&*()_+]/
		
		var element = document.getElementById('checkPasswdResult');
		if(lengthCaseRegex.exec(member_passwd.value) && upperCaseRegex.exec(passwd.value)
				&& lowerCaseRegex.exec(passwd.value) && digitCaseRegex.exec(passwd.value)
				&& specialCharRegex.exec(passwd.value)) {
			element.innerHTML = "사용 가능한 패스워드 입니다.";
		} else {
			element.innerHTML = "사용 불가능한 패스워드 입니다.";
		}
	}
	function checkEmail(member_email) {
		var email = document.getElementById('checkEmailResult');
		var exptext = /^[A-Za-z0-9_\.\]+@[A-Za-z0-09\-]+\.[A-Za-z0-9\-]+/;
		if(exptext.test(member_email)==false){
			//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
			element.innerHTML="올바른 이메일 형식이 아닙니다."
		}else{
			element.innerHTML="올바른 이메일 형식 입니다."
		}
	}
</script>
  </head>
  <body>
    
<!-- 탑메뉴 인클루드 -->    
<jsp:include page="/include/top_menu.jsp"/>
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('images/bg_5.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="../main/index.jsp">Home</a></span> <span>Create an Account</span></p>
              <h1 class="mb-3">Create an Account</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->

    <section class="ftco-section contact-section">
      <div class="container">
        <div class="row block-9 mb-4">
          <div class="col-md-6 pr-md-5 flex-column">
            <div class="row d-block flex-row">
              <h2 class="h4 mb-4">Account Information</h2>
              <div class="form-group">
                <input type="text" class="form-control form-control-short" placeholder="ID" name="member_id" required="required"
                placeholder="4-16자리 영문자,숫자조합" onkeyup="checkId(this)">
                <span id="checkIDresult"></span>
                
                <input type="button" value="Dup.Check" class="btn btn-primary py-3 px-5">
              </div>
              <div class="form-group form-group-message"></div>
              <div class="form-group">
                <input type="password" class="form-control" placeholder="Password" name="member_passwd" required="required" placeholder="8-20자리 영문자,숫자,특수문자 조합"
                 onkeyup="checkPasswd(this)">
                 <span id="checkPasswdResult"></span>
              </div>
              <div class="form-group">
                <input type="password" class="form-control" placeholder="Retype Password" name="member_passwd2" required="required">
              </div>
              <div class="form-group form-group-message"></div>
            </div>
          </div>
          <div class="col-md-6">
            <form action="#">
              <div class="form-group">
                <input type="text" class="form-control" placeholder="Phone Number" name="member_phone" required="required">
              </div>
              <div class="form-group">
                <input type="email" class="form-control" placeholder="Email" name="member_email" required="required">
                <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                
              </div>
              <div class="form-group">
                v
                <input type="text" class="form-control" placeholder="Address" name="member_addr" required="required">
              </div>
              <div class="form-group">
                <input type="text" class="form-control pick_date" id="checkin_date" value="1990/1/1" placeholder="Birth" name="member_birth" required="required">
              </div>
              <div class="form-group">
              	<input type="radio"	name="gender" value="1">Man&nbsp;&nbsp;&nbsp;
              	<input type="radio"	name="gender" value="2">Woman
              </div>
<!--               <div class="form-group"> -->
<!--                 <textarea name="" id="" cols="30" rows="7" class="form-control" placeholder="Message"></textarea> -->
<!--               </div> -->
              <div class="form-group">
                <input type="submit" value="Join" class="btn btn-primary py-3 px-5">
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
<jsp:include page="/include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="/include/loader.jsp"/>
    
  </body>
</html>