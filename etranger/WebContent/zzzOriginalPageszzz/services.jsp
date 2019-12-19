<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
	<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp"/>
  </head>
  <body>
    
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="../include/top_menu.jsp"/>
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('../images/bg_4.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="./index.jsp">Home</a></span> <span>Services</span></p>
              <h1 class="mb-3">Services</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->

    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-lg-3 promo ftco-animate">
            <a href="#" class="promo-img mb-4" style="background-image: url(../images/promo-1.jpg);"></a>
            <div class="text text-center">
              <h2>Group Cruises</h2>
              <h3 class="price"><span>from</span> $299</h3>
              <a href="#" class="read">Read more</a>
            </div>
          </div>
          <div class="col-lg-3 promo ftco-animate">
            <a href="#" class="promo-img mb-4" style="background-image: url(../images/promo-2.jpg);"></a>
            <div class="text text-center">
              <h2>Beach Tours</h2>
              <h3 class="price"><span>from</span> $199</h3>
              <a href="#" class="read">Read more</a>
            </div>
          </div>
          <div class="col-lg-3 promo ftco-animate">
            <a href="#" class="promo-img mb-4" style="background-image: url(../images/promo-3.jpg);"></a>
            <div class="text text-center">
              <h2>Mountain Tours</h2>
              <h3 class="price"><span>from</span> $179</h3>
              <a href="#" class="read">Read more</a>
            </div>
          </div>
          <div class="col-lg-3 promo ftco-animate">
            <a href="#" class="promo-img mb-4" style="background-image: url(../images/promo-3.jpg);"></a>
            <div class="text text-center">
              <h2>Family Tours</h2>
              <h3 class="price"><span>from</span> $599</h3>
              <a href="#" class="read">Read more</a>
            </div>
          </div>
        </div>
      </div>
    </section>

    <section class="ftco-section bg-light">
      <div class="container">
        <div class="row justify-content-center mb-5 pb-5">
          <div class="col-md-7 text-center heading-section ftco-animate">
            <h2>Our Services</h2>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6 col-lg-3 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center"><div class="icon d-flex justify-content-center mb-3"><span class="align-self-center flaticon-sailboat"></span></div></div>
              <div class="media-body p-2">
                <h3 class="heading">Special Activities</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>      
          </div>
          <div class="col-md-6 col-lg-3 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center"><div class="icon d-flex justify-content-center mb-3"><span class="align-self-center flaticon-around"></span></div></div>
              <div class="media-body p-2">
                <h3 class="heading">Travel Arrangements</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>      
          </div>
          <div class="col-md-6 col-lg-3 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center"><div class="icon d-flex justify-content-center mb-3"><span class="align-self-center flaticon-compass"></span></div></div>
              <div class="media-body p-2">
                <h3 class="heading">Private Guide</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>    
          </div>

          <div class="col-md-6 col-lg-3 d-flex align-self-stretch ftco-animate">
            <div class="media block-6 services d-block text-center">
              <div class="d-flex justify-content-center"><div class="icon d-flex justify-content-center mb-3"><span class="align-self-center flaticon-map-of-roads"></span></div></div>
              <div class="media-body p-2">
                <h3 class="heading">Location Manager</h3>
                <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
              </div>
            </div>      
          </div>
        </div>
      </div>
    </section>

	<!-- footer 인클루드 -->
<jsp:include page="../include/footer.jsp"/>
    
  <!-- loader 인클루드 -->
<jsp:include page="../include/loader.jsp"/>
    
  </body>
</html>