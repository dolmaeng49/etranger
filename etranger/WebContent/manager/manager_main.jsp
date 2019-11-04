<%@page import="manager.vo.PageInfo"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<%
ArrayList<CategoryBean> categoryList = (ArrayList<CategoryBean>)request.getAttribute("articleList");
PageInfo pageInfo = (PageInfo)request.getAttribute("pageInfo");
int nowPage = pageInfo.getPage();
int maxPage = pageInfo.getMaxPage();
int startPage = pageInfo.getStartPage();
int endPage = pageInfo.getEndPage();
int listCount = pageInfo.getListCount();
%>

<!-- 스타일 include -->
<jsp:include page="../include/style.jsp" />

</head>
<body>
	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="../include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item" style="background-image: url('../images/bg_4.jpg');" data-stellar-background-ratio="0.5">
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

	<h1>관리자 페이지 테스트</h1>
	<div class="comment-form-wrap pt-5">
		<input type="button" class="search-submit btn btn-primary" value="상품 분류 등록" onclick="location.href='ManagerInsert.ma'">
	</div>
		<div class="comment-form-wrap pt-5">
		<input type="button" class="search-submit btn btn-primary" value="상품 정보 조회" onclick="location.href='ManagerSELECT.ma'">
	</div>
	
	<h2>카테고리 목록</h2>
	<table>
			<%
			if(categoryList != null && listCount > 0) {
			%>
				<tr>
					<td width="100px">지역 번호</td>
					<td>지역 이름</td>
				</tr>
				<%
				for(int i = 0; i < categoryList.size(); i++) {
				%>
				<tr>
					<td align="center"><%=categoryList.get(i).getRegionCode() %></td>
					<td>
					
				
				<%}%>
		</table>
	<section id="pageList">
	<%if(nowPage <= 1) {%>
			<input type="button" value="이전">&nbsp;
	<%} else {%>
			<input type="button" value="이전" onclick="location.href='BoardList.bo?page=<%=nowPage - 1 %>'">&nbsp;
	<%} %>
	
	<%for(int i = startPage; i <= endPage; i++) { 
			if(i == nowPage) { %>
				[<%=i %>]
		<%} else { %>
				<a href="BoardList.bo?page=<%=i %>">[<%=i %>]</a>&nbsp;
		<%} %>
	<%} %>
	
	<%if(nowPage >= maxPage) { %>
			<input type="button" value="다음">
	<%} else { %>
			<input type="button" value="다음" onclick="location.href='BoardList.bo?page=<%=nowPage + 1 %>'">
	<%} %>
	</section>
	<%
	} else {
	%>
	<section id="emptyArea">등록된 글이 없습니다</section>
	<%
	}
	%>

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
</body>
</html>