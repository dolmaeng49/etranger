<%@page import="review.vo.ReviewBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String nowPage = (String)request.getParameter("page");
	ReviewBean article = (ReviewBean) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
	<!-- 스타일 인클루드 -->
<jsp:include page="/include/style.jsp"/>
</head>
  <body>
	<!-- 탑메뉴 인클루드 -->    
<jsp:include page="/include/top_menu.jsp"/>
    
    <section class="home-slider owl-carousel">
      <div class="slider-item" style="background-image: url('images/bg_3.jpg');" data-stellar-background-ratio="0.5">
        <div class="overlay"></div>
        <div class="container">
          <div class="row slider-text align-items-center">
            <div class="col-md-7 col-sm-12 ftco-animate">
              <p class="breadcrumbs"><span class="mr-2"><a href="./index.jsp">Home</a></span> <span><a href="blog.html">Blog</a></span> <span>Single Blog</span></p>
              <h1 class="mb-3">여행후기 수정</h1>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!-- END slider -->

    <section class="ftco-section">
      <div class="container">
        <div class="row">
          <div class="col-md-8 ftco-animate">
          
          
            <!--  글 작성 폼 시작 -->
              <div class="comment-form-wrap">
                <h3 class="mb-5">여행후기 수정</h3>
                <div style="height:90px"></div>
                <form action="ReviewModifyPro.rv" method="post" name="review_write_form" onsubmit="return validCheck()">
                <input type="hidden" name="review_num" value=<%=article.getReview_num() %>>
				<input type="hidden" name="review_package_category_code" value="<%=article.getReview_package_catagory_code() %>">
				<input type="hidden" name="review_readcount" value=0>
				<input type="hidden" id="review_star" name="review_star" value="<%=article.getReview_star() %>">
				<input type="hidden" name="review_member_id" value=<%=article.getReview_member_id() %>>
				<input type="hidden" name="review_member_name">
				<input type="hidden" name="review_comment_count" value=0>
                  <div class="writeform-group">
                    <input type="text" class="form-control-subject" name="review_subject" id="review_subject" maxlength="40" value="<%=article.getReview_subject() %>"/>
                  </div>
                  <div class="writeform-group">
                  <div style="height:20px"></div>
                  <div class="starRev" id="starRev">
  					<span class="starR1 on">1</span>
  					<span class="starR2">2</span>
  					<span class="starR1">3</span>
  					<span class="starR2">4</span>
  					<span class="starR1">5</span>
 					<span class="starR2">6</span>
 					<span class="starR1">7</span>
  					<span class="starR2">8</span>
 					<span class="starR1">9</span>
  					<span class="starR2">10</span>
				   </div>
                    <div style="height:50px"></div>
                    <textarea id="summernote" name="review_content" cols="30" rows="20" class="form-control" <%=article.getReview_content() %>></textarea>
                  </div>
                  <div class="writeform-group">
                    <input id="review_image" name="review_image" type="hidden" class="form-control" multiple="multiple" accept="image/*" value="<%=article.getReview_image() %>" />
                  </div>
                  <div class="form-group"> 
                    <input type="submit" value="등록" class="btn py-3 px-4 btn-primary">
                    <input type="button" value="취소" class="btn py-3 px-4 btn-primary" onclick="history.back()"> <!-- 나중에 팝업띄워줄 것 -->
                  </div>

                </form>
              </div>  <!--  end 글 작성 폼 -->
			</div>
			
			<div class="col-md-4 sidebar">
					<!--검색창  -->
            <div class="sidebar-box">
          <form action="ReviewSearch.rv" method="get" class="search-form-detail" name="search">
                <fieldset id="search-fieldset-detail" >
                <input type="text"  id="search_input" class="search_border" name="search" placeholder="Search">
                <button type="submit" id="search_button" ><i class="fa fa-search"></i></button>
                </fieldset>
                </form>
            </div>
            
            <!--추천 지역 -->
            <div class="sidebar-box ftco-animate">
              <div class="categories">
                <h3>추천 지역</h3>
                <div id="side_region" class="side_region">
                
                </div>
              </div>
            </div>
            <!--  -->
            
			<!-- 추천 테마 -->
            <div class="sidebar-box ftco-animate">
              <h3>추천 테마</h3>
              <div id="side_theme" class="tagcloud">
              </div>
            </div>
            <!--  -->
				</div>
        </div> <!-- row 끝 -->
      </div><!-- container 끝 -->
    </section> <!-- .section -->

	<!-- footer 인클루드 -->
<jsp:include page="/include/footer.jsp"/>

	<!-- loader 인클루드 -->
<jsp:include page="/include/loader.jsp"/>
	<script type="text/javascript">
displaySideRegion();
displaySideTheme();

// 사이드바 추천 지역 
function displaySideRegion() {
	
	$('#side_region').empty();
	// JSON으로 가져온 데이터 #side_region에 뿌려주기
	$.getJSON('RegionSelect.ma', function(data) {

		$.each(data, function(index, value) {
			$('#side_region').append(
					"<li><a href='CategoryListSearch.pr?region="+value.regionCode+"'>" + value.regionName + "</a></li>");
		});
	});
}

//사이드바 추천 테마

function displaySideTheme() {
	
$('#side_theme').empty();
	// JSON으로 가져온 데이터 #side_theme에 뿌려주기
	$.getJSON('GetThemeListAjax.ma', function(data) {

		$.each(data, function(index, value) {
			$('#side_theme').append(
					"<a href='CategoryListSearch.pr?keyword="+ value.themeName +"' class='tag-cloud-link'>" +value.themeName + "</a>");
		});
	});
}


</script>


<script type="text/javascript">

function validCheck() {
	const subject = $('#subject').val();
	const content = $('#content').val();
	const img = $('#image').val();
	
	if(subject.length<2 || subject.length>30){
		alert('제목이 너무 짧거나 깁니다!');
		return false;
	}if(content.length<10){
		alert('내용은 10글자 이상 적어주세요 :)');
		return false;
	}if(img.length==0){
		alert('썸네일로 사용할 이미지를 업로드해주세요 :)');
		return false;
	}
	
}

// 글 수정시 기존 별점 불러오기
$('#review_star').ready(function() {

var star = $('#review_star').val() -1;
$(".starRev span:eq(" + star + ")").trigger("click");
});

</script>    
  </body>
</html>