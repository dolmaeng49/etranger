<%@page import="notice.vo.NoticeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String nowPage = (String) request.getParameter("page");
	NoticeBean article = (NoticeBean) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<!-- 스타일 인클루드 -->
<jsp:include page="/include/style.jsp" />
</head>
<body>
	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="/include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item"
			style="background-image: url('images/bg_3.jpg');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-7 col-sm-12 ftco-animate">
						<p class="breadcrumbs">
							<span class="mr-2"><a href="./index.jsp">Home</a></span>
						</p>
						<h1 class="mb-3">공지사항 수정</h1>
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
					<h2 class="mb-5">공지사항 수정</h2>
					<!-- 볼드체 제목 -->
					<!-- 글 수정 폼 시작 -->
					<div style="height: 90px"></div>
					<form action="NoticeModifyPro.no" method="post"
						enctype="multipart/form-data" name="notice_write_form"
						onsubmit="return validCheck()">
						<input type="hidden" name="notice_num"
							value="<%=article.getNotice_num()%>"> <input
							type="hidden" name="page" value="<%=nowPage%>" />
						<div class="writeform-group">
							<input type="text" class="form-control-subject"
								name="notice_subject" id="notice_subject" maxlength="70"
								value="<%=article.getNotice_subject()%>" />
						</div>
						<div class="writeform-group">
							<div style="height: 50px"></div>
							<textarea id="summernote" name="notice_content" cols="30"
								rows="20" class="form-control"><%=article.getNotice_content()%></textarea>
						</div>
						<div id="test"></div>
						<div class="writeform-group">
							<label for="image">이미지 첨부</label> <input id="notice_image"
								name="notice_image" type="file" class="form-control"
								multiple="multiple" accept="image/*"
								value="<%=article.getNotice_image()%>" />
						</div>
						<div class="form-group">
							<input type="submit" value="수정" class="btn py-3 px-4 btn-primary">
							<input type="reset" value="글목록" class="btn py-3 px-4 btn-primary"
								onclick="location.href='NoticeList.no'">
						</div>

					</form>
				</div>
				<div class="col-md-4 sidebar">
					<!--검색창  -->
					<div class="sidebar-box">
						<form action="ReviewSearch.rv" method="get"
							class="search-form-detail" name="search">
							<fieldset id="search-fieldset-detail">
								<input type="text" id="search_input" class="search_border"
									name="search" placeholder="Search">
								<button type="submit" id="search_button">
									<i class="fa fa-search"></i>
								</button>
							</fieldset>
						</form>
					</div>

					<!--추천 지역 -->
					<div class="sidebar-box ftco-animate">
						<div class="categories">
							<h3>추천 지역</h3>
							<div id="side_region" class="side_region"></div>
						</div>
					</div>
					<!--  -->

					<!-- 추천 테마 -->
					<div class="sidebar-box ftco-animate">
						<h3>추천 테마</h3>
						<div id="side_theme" class="tagcloud"></div>
					</div>
					<!--  -->
				</div>

			</div>
			<!-- row 끝 -->
		</div>
		<!-- container 끝 -->
	</section>
	<!-- .section -->

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
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
							"<li><a href='CategoryListSearch.pr?region="
									+ value.regionCode + "'>"
									+ value.regionName + "</a></li>");
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
							"<a href='CategoryListSearch.pr?keyword="
									+ value.themeName
									+ "' class='tag-cloud-link'>"
									+ value.themeName + "</a>");
				});
			});
		}
	</script>



	<script type="text/javascript">
		function validCheck() {
			// 	const review_star = $('#review_star').val();
			const subject = $('#subject').val();
			const content = $('#content').val();
			const img = $('#image').val();

			if (subject.length<2 || subject.length>30) {
				alert('제목이 너무 짧거나 깁니다!');
				return false;
			}
			if (content.length < 10) {
				alert('내용은 10글자 이상 적어주세요 :)');
				return false;
			}
			if (img.length == 0) {
				alert('썸네일로 사용할 이미지를 업로드해주세요 :)');
				return false;
			}

		}

	</script>
</body>
</html>