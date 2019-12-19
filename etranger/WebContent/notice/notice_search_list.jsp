<%@page import="notice.vo.NoticeBean"%>
<%@page import="common.vo.PageInfo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 전달받은 request 객체로부터 데이터 가져오기
	// "pageInfo" 객체와 "articleList" 객체를 request 객체로부터 꺼내서 저장
	// "pageInfo" 객체로부터 페이지 관련 값들을 꺼내서 변수에 저장
	ArrayList<NoticeBean> articleList = (ArrayList<NoticeBean>) request.getAttribute("articleList");
	PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
	int nowPage = pageInfo.getPage();
	int maxPage = pageInfo.getMaxPage();
	int startPage = pageInfo.getStartPage();
	int endPage = pageInfo.getEndPage();
	int listCount = pageInfo.getListCount();

	String search = request.getParameter("search");
	String sessionId = (String) session.getAttribute("member_id");
	NoticeBean article = (NoticeBean) request.getAttribute("article");
%>
<!DOCTYPE html>
<html>
<head>
<!-- 스타일 인클루드 -->
<jsp:include page="/include/style.jsp" />
<style type="text/css">
/* div{ */
/* 	border: 1px solid red; */
/* } */
</style>
</head>
<body>
	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="/include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item"
			style="background-image: url('images/bg_1.jpg');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-7 col-sm-12 ftco-animate">
						<p class="breadcrumbs">
							<span class="mr-2"><a href="./index.jsp">Home</a></span>
						</p>
						<h1 class="mb-3">공지사항</h1>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- END slider -->

	<!-- 추천여행지 -->
	<section class="ftco-section bg-light">
		<%
			if (articleList != null && listCount > 0) {
		%>
		<div class="container">
			<!--       list -->
			<div class="row d-flex">
				<div class="notice_list ftco-animate fadeInUp ftco-animated">
					<table class="notice_table">
						<tbody>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>날짜</th>
								<th>글쓴이</th>
								<!-- 								<th>조회수</th> -->
							</tr>
							<!--리스트에서 보여지는 게시물  한 덩이 시작 -->
							<%
								for (int i = 0; i < articleList.size(); i++) {
							%>

							<tr>
								<td align="center"><%=articleList.get(i).getNotice_num()%></td>

								<td align="center"><a data-toggle="collapse"
									data-parent="#accordian" href="#collapse<%=i%>"><%=articleList.get(i).getNotice_subject()%></a>
									<div id="collapse<%=i%>" class="panel-collapse collapse in">
										<div class="panel-body">
											<br><%=articleList.get(i).getNotice_content()%></div>
									</div></td>


								<%-- 								<td align="center"><a href="NoticeDetail.no?notice_num=<%=articleList.get(i).getNotice_num()%>&page=<%=nowPage %>"><%=articleList.get(i).getNotice_subject()%></a></td> --%>
								<td align="center"><%=articleList.get(i).getNotice_date()%></td>
								<%-- 								<td align="center"><%=articleList.get(i).getNotice_member_id() %></td> --%>
								<!-- 멤버 아이디 밑에 etranger 로 대체 -->
								<td align="center">etranger</td>
								<%-- 								<td align="center"><%=articleList.get(i).getNotice_readcount() %></td> --%>

								<%
									if (sessionId != null && sessionId.equals("admin")) {
								%>
								<td class="tagcloud"><a
									href="NoticeModifyForm.no?notice_num=<%=articleList.get(i).getNotice_num()%>&page=<%=nowPage%>"
									class="btncontrol btncontrol-sm btn-default"><i
										class="fa fa-pencil-alt"></i>수정</a> <a id="notice_del_button"
									href="NoticeDeletePro.no?notice_num=<%=articleList.get(i).getNotice_num()%>&notice_member_id=<%=sessionId%>&page=<%=nowPage%>"
									class="btncontrol btnforshare-rv btn-default"><i
										class="fa fa-trash"></i>삭제</a></td>
								<%
									}
								%>
							</tr>

							<%
								}
							%>


						</tbody>
					</table>
				</div>
			</div>
			<!--end 리스트에서 보여지는 게시물  한 덩이 시작 -->
			<!-- 			 end list -->

			<!-- 게시글 검색 & 태그-->
			<!-- 검색창 축소 및 태그 검색창 정렬 작업 -->
			<br>
			<form action="NoticeSearch.no" method="get" class="search-form-list"
				name="search">
				<fieldset id="search_fieldset">
					<input type="text" id="search_input" name="search"
						placeholder="Search" style="width: 100px;">
					<button type="submit" id="search_button">
						<i class="fa fa-search"></i>
					</button>
				</fieldset>
			</form>
			<!-- end 게시글 검색 -->

			<!-- 페이지 부분 -->
			<div class="row mt-5">
				<div class="col text-center">
					<div class="block-27">
						<ul>
							<%
								if (nowPage <= 1) {
							%>
							<li><a>&lt;</a></li>
							<%
								} else {
							%><li><a href="NoticeList.no?page=<%=nowPage - 1%>">&lt;</a></li>
							<%
								}
							%>
							<%
								for (int i = startPage; i <= endPage; i++) {
										if (i == nowPage) {
							%><li class="active"><span><%=i%></span></li>
							<%
								} else {
							%>
							<li><a href="NoticeList.no?page=<%=i%>"><%=i%></a></li>
							<%
								}
							%>
							<%
								}
							%>
							<%
								if (nowPage >= maxPage) {
							%><li><a>&gt;</a></li>
							<%
								} else {
							%>
							<li><a href="NoticeList.no?page=<%=nowPage + 1%>">&gt;</a></li>
							<%
								}
							%>
						</ul>
					</div>
				</div>
			</div>
			<!-- 페이지 부분 -->
			<%
				} else {
			%><div id="emptyArticle">검색 결과와 일치하는 공지사항이 없습니다.</div>
			<%
				}
			%>
		</div>
		<!-- <div class="container">의 끝 -->
		<%
			if (sessionId != null && sessionId.equals("admin")) {
		%>
		<div align="center">
			<input type="button" value="공지사항 등록"
				class="btn py-3 px-4 btn-primary"
				onclick="location.href='./NoticeWriteForm.no'">
		</div>
		<%
			}
		%>
	</section>
	<!--     <li class="active"><span>1</span></li> -->

	<!-- footer 인클루드 -->
	<jsp:include page="/include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="/include/loader.jsp" />
	<script type="text/javascript">
		$('h3.heading').each(function() {
			var length = 18; //표시할 글자수 정하기
			var iframe = "</iframe>"
			//전체 옵션을 자를 경우
			$(this).each(function() {
				if ($(this).text().length >= length) {
					$(this).text($(this).text().substr(0, length) + '...'); //지정한 글자수 이후 표시할 텍스트(...)
				}
			});

		});
	</script>
</body>
</html>