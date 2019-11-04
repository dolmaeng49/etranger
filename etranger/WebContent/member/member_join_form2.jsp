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
	.login-info {
		text-align: right;
	}
	.container, .navbar-brand, .navbar-collapse {
	border: 1px solid red;}
</style>
  </head>
  <body>
    
<!-- 탑메뉴 인클루드 -->    
<jsp:include page="../include/top_menu.jsp"/>
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('../images/bg_5.jpg');" data-stellar-background-ratio="0.5">
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
                <input type="text" class="form-control form-control-short" placeholder="ID" name="member_id" required="required">
                <input type="button" value="Dup.Check" class="btn btn-primary py-3 px-5">
              </div>
              <div class="form-group form-group-message"></div>
              <div class="form-group">
                <input type="text" class="form-control" placeholder="Password" name="member_passwd" required="required">
              </div>
              <div class="form-group">
                <input type="text" class="form-control" placeholder="Retype Password" name="member_passwd2" required="required">
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
              </div>
              <div class="form-group">
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
<jsp:include page="../include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="../include/loader.jsp"/>
    
  </body>
</html>