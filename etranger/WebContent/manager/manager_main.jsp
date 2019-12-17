<%@page import="common.vo.PageInfo"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<CategoryBean> regionList = (ArrayList<CategoryBean>) request.getAttribute("regionList");
	ArrayList<CategoryBean> themeList = (ArrayList<CategoryBean>) request.getAttribute("themeList");
	ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>) request.getAttribute("categoryList");
%>
<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="UTF-8">
	<!-- 스타일 include -->
	<jsp:include page="../include/style.jsp" />

	<style type="text/css">
		/* 사이드바 화면고정 */
		.sticky {
			position: sticky;
			top: 100px;
		}

		.li_hover:hover {
			border: 2px solid #68c8f2;
			background: transparent;
			color: #68c8f2;
		}

		#memberManagement {
			font-size: smaller;
 			display: none;  
		}
	</style>
</head>

<body>
	<header>
		<!-- 탑메뉴 인클루드 -->
		<jsp:include page="../include/top_menu.jsp" />
		<section class="home-slider owl-carousel">
			<div class="slider-item" style="background-image: url('./images/bg_4.jpg');"
				data-stellar-background-ratio="0.5">
				<div class="overlay"></div>
				<div class="container">
					<div class="row slider-text align-items-center">
						<div class="col-md-7 col-sm-12 ftco-animate">
							<p class="breadcrumbs">
								<span class="mr-2"><a href="../main/index.jsp">Home</a></span> <span>Manager</span>
							</p>
							<h1 class="mb-3">Manager</h1>
						</div>
					</div>
				</div>
			</div>
		</section>
	</header>

	<div class="container-fluid">
		<div class="row">
			<!-- 3단길이의 첫번째 열 -->
			<div class="col-md-2">
				<div class="panel panel-info sticky" id="leftside">
					<div class="panel-heading">

						<h3 class="panel-title">Manager Page</h3>
					</div>
					<ul class="list-group">
						<li class="list-group-item active li_hover" onclick="location.href='./index.jsp'"><span
								class="icon icon-home"></span> 홈</li>
						<li class="list-group-item li_hover dataChart"><span class="icon icon-line-chart"></span> 관리자 메인
						</li>
						<li class="list-group-item li_hover" onclick="showCategoryInsert()"><span
								class="icon icon-pencil"></span> 상품 분류 등록</li>
						<li class="list-group-item li_hover pList"><span class="icon icon-add_shopping_cart"></span> 상품
							상세 등록</li>

						<li class="list-group-item li_hover" id="member">
							<span class="icon icon-users"></span>회원관리</li>
					</ul>
					<ul class="list-group" id="memberManagement">
						<li class="list-group-item li_hover member" onclick="location.href='ReservManagement.ma'">
							&nbsp;­회원예약</li>
						<li class="list-group-item li_hover member">&nbsp;­회원등급</li>
					</ul>
				</div>
			</div>

			<!-- 9단길이의 첫번째 열 -->
			<div class="col-md-9">
				<section id="showDataChart">
					<jsp:include page="../manager/datachart.jsp" />
				</section>

				<section id="categoryInsert" style="display: none;">
					<!-- 지역,도시,테마 선택결과 가지고 ManagerProInsert.ma 이동 -->
					<form action="CategoryInsert.ma" class="p-5 bg-light" method="post" enctype="multipart/form-data" onkeydown="return captureReturnKey(event)">
						<!-- 지역,도시,테마 구역 패딩줘서 안삐져나가게. -->
						<div class="row block-9 mb-4" style="padding-left: 20px;">
							<div class="col-md-6 pr-md-5 flex-column">
								<div class="row d-block flex-row">
									<!-- 지역선택 -->
									<div class="select-wrap one-third">
										<h3 class="h4 mb-4">Region</h3>
										<select id="selectRegion" class="form-control" onChange="showRegionAdd()"
											name="category_regioncode" required="required">
											<!-- 옵션 클릭시 select()동작 -->
											<option>지역선택</option>
											<option value="add">지역추가</option>
											<%
												for (int i = 0; i < regionList.size(); i++) {
											%>
											<option value="<%=regionList.get(i).getRegionCode()%>">
												<%="지역코드 : " + regionList.get(i).getRegionCode() + ", 지역이름 : " + regionList.get(i).getRegionName()%>
											</option>
											<%
												}
											%>
										</select>
										<div class="form-group" id="addRegion" style="display: none;">
											<input type="text" size="20" id="region_addbox" onkeydown="enterKey(this);">
											<input type="button" id="region_addbtn" value="지역추가"
												class="btn btn-primary">
										</div>
									</div>
								</div>
							</div>

							<!-- 도시선택 -->
							<div class="col-md-6">
								<div class="select-wrap one-third">
									<h3 class="h4 mb-4">City</h3>
									<select id="selectCity" class="form-control" onChange="showCityAdd()"
										name="category_citycode" required="required">
										<option>도시선택</option>
										<option value="add">도시추가</option>
									</select>
									<div class="form-group" id="addCity" style="display: none;">
										<input type="text" size="20" id="city_addbox" onkeydown="enterKey(this);">
										<input type="button" id="city_addbtn" value="도시추가" class="btn btn-primary">
									</div>
								</div>
							</div>

							<!-- 테마선택 -->
							<div class="col-md-12">
								<div class="select-wrap one-third theme">
									<br>
									<h3 class="h4 mb-4">Theme</h3>
									<div class="btn-group-toggle pb-1" data-toggle="buttons" id="ThemeBox">
										<%
											for (int i = 0; i < themeList.size(); i++) {
										%>
									
										<label class="btn btn-outline-info btn-sm" id="newTheme">
										<input type="checkbox" name="theme" class="custom-control-input"
												value="<%=themeList.get(i).getThemeName()%>"><%=themeList.get(i).getThemeName()%>
										</label>
											
										<%
											}
										%>
									</div>
									
									<div class="col-md-12">
										<input type="button" id="theme_addbtn" value="테마추가" class="btn btn-info"
											onclick="check()" data-toggle="tooltip" data-placement="right" title="엔터키로 추가할 수 있습니다."
											>
										<div class="form-group" id="addTheme" style="display: none;">

											<input type="text" size="20" id="theme_addbox" onkeydown="enterKey(this);">
											<input type="button" id="theme_addbtn2" class="btn btn-primary" value="추가">
										</div>
									</div>
								</div>
							</div>
							
							<script>
							$(function () {
								$('[data-toggle="tooltip"]').tooltip()
								})
							</script>
							<!-- package_category_name -->
							<div class="col-md-6">
								<div class="select-wrap one-third theme">
									<h3 class="h4 mb-12 mt-3">Name</h3>
									<input type="text" id="category_name" name="category_name" required="required"
										size="80">

								</div>
							</div>
							<!-- package_category_content -->
							<div class="col-md-12">
								<div class="select-wrap one-third theme">
									<h3 class="h4 mb-4 mt-3">Content</h3>
									<textarea id="summernoteManager"  name="category_content" rows="20" cols="80"
										required="required"></textarea>

								</div>
							</div>
							<!-- package_category_image -->
							<div class="col-md-12">
								<div class="select-wrap one-third theme">
								
									<h3 class="h4 mb-4 mt-3" >Img</h3>
									<div class="custom-file" style="width: 60%">
									<input multiple="multiple" type="file" name="category_image" required="required" class="custom-file-input" id="customFile"/>
									 <label class="custom-file-label" for="customFile">Choose image file</label>
									</div>
								
								</div>
							</div>
							<input style="VISIBILITY: hidden; WIDTH: 0px">
							<!-- 지역,도시,테마 선택결과 가지고 ManagerProInsert.ma 이동 -->
							<div class="col-md-12">
								<div class="form-group" style="display: block; clear: both;">
									<br> &nbsp;&nbsp; <input type="submit" value="등록하기"
										class="btn py-3 px-4 btn-primary">&nbsp;&nbsp; <input type="reset" value="다시쓰기"
										class="btn py-3 px-4 btn-primary">
								</div>
							</div>
						</div>
					</form>
				</section>

				<!-- 상품리스트 출력 -->
				<section id="productInsert" style="display: none;">
					<div id="productList" class="col-md-12"></div>
				</section>

			</div>
		</div>
	</div>
	<!-- END slider -->

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
	<!-- JavaScript 가져오기 -->
	<script src="js/manager.js"></script>

</body>

</html>