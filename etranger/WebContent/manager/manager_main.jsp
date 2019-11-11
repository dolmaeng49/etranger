<%@page import="manager.vo.PageInfo"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	ArrayList<CategoryBean> articleList = (ArrayList<CategoryBean>) request.getAttribute("articleList");
    ArrayList<CategoryBean> themeList = (ArrayList<CategoryBean>) request.getAttribute("themeList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<!-- 스타일 include -->
<jsp:include page="../include/style.jsp" />

<script src="../js/jquery-3.4.1.js"></script>
<script type="text/javascript">
	function dis() {
		if ($('#dis').css('display') == 'none') {
			$('#dis').show();
		}
		else {
			$('#dis').hide();
		}
	}

	function select() {
		// selectVal : selectRegion 에서 선택한 값 저장
		var selectVal = document.getElementById("selectRegion").value;

		// 만약 selectVal이 add 일경우 addRegion에 text 박스 추가
		if (selectVal == "add") {
			document.getElementById('addRegion').innerHTML = '<input type="text" name="val1" size="20" id="region_add">';

		}
		// add를 제외한 다른 옵션 선택시 Region에 선택한 옵션의 지역코드 출력
		else {
			document.getElementById("addRegion").removeChild(
					document.getElementById("region_add"));
			document.getElementById("Region").innerHTML = "선택한 지역 코드: "
					+ selectVal;

		}
	}
</script>
</head>
<body>
	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="../include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item" style="background-image: url('./images/bg_4.jpg');" data-stellar-background-ratio="0.5">
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
	<!-- END slider -->
	<div class="container">
		<h1>관리자 페이지 테스트</h1>
		<input type="button" id='show' class="search-submit btn btn-primary" value="상품 분류 등록" onclick="dis()">
		<section id="dis" style="display: none;">


			<!-- 지역선택 -->
			<div class="select-wrap one-third">
				<select id="selectRegion" class="form-control" onChange="select()">
					<!-- 옵션 클릭시 select()동작 -->
					<option>지역선택</option>
					<option value="add">지역추가</option>
					<%
						for (int i = 0; i < articleList.size(); i++) {
					%>
					<option value="<%=articleList.get(i).getRegionCode()%>"><%="지역코드 : " + articleList.get(i).getRegionCode() + ", 지역이름 : "
						+ articleList.get(i).getRegionName()%></option>
					<%
						}
					%>
				</select>
				<p id="Region"></p>
				<div class="form-group" id="addRegion"></div>
			</div>

			<!-- 도시선택 클릭하면 해당 지역의 도시를 옵션으로 출력.. 해야하는데 select 방식, 어떻게 작동시킬지 고민.. -->
			<div class="select-wrap one-third">
				<select id="selectRegion" class="form-control" onChange="select()">
					<option>도시선택</option>
					<option value="add">도시추가</option>
					<%
						for (int i = 0; i < articleList.size(); i++) {
					%>
					<option value="<%=articleList.get(i).getCityCode()%>"><%="도시코드 : " + articleList.get(i).getCityCode() + ", 도시이름 : " + articleList.get(i).getCityName()%></option>
					<%
						}
					%>
				</select>
				<p id="Region"></p>
			</div>

			<div class="select-wrap one-third">
				<!-- 		예시임. 원래 db로 가져와야함      -->
				테마<br>
				<%
			       for(int i = 0; i< themeList.size(); i++){
			    	   %>
			    	  <input type="checkbox" name="theme" value="<%=themeList.get(i).getThemeName()%>"><%=themeList.get(i).getThemeName() %>
			    	  
			    	   <%
			      }
				%>
				<br>
			     <input type="button" value="테마추가하기" onclick="check()">
				<p id="Theme"></p>
				
			</div>

			<form action="ManagerProInsert.ma" class="p-5 bg-light">

				<div class="form-group">
					<input type="submit" value="등록하기" class="btn py-3 px-4 btn-primary">
				</div>
			</form>
		</section>
	</div>
	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
</body>
</html>