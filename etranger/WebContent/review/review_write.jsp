<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
// //현재 세션 객체에 "sId" 세션값이 존재하지 않을 경우
// 	// alert 창에 "로그인이 필요합니다" 출력 후 LoginForm.me 로 이동
// 	String sid= null; 
// 	if(session.getAttribute("sid") !=null) {
// 		sid=(String)session.getAttribute("sid");
// 	}
%>	

<!----------------------------------------------임시 입출력페이지(미완성)------------------------------------------->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기 임시</title>
<style type="text/css">
#registForm {
	width: 500px;
	height: 610px;
	border: 1px solid red;
	margin: auto;
}

h2 {
	text-align: center;
}

table {
	margin: auto;
	width: 450px;
}

.td_left {
	width: 150px;
	background: orange;
}

.td_right {
	width: 300px;
	background: skyblue;
}

#commandCell {
	text-align: center;
}
</style>
<script type="text/javascript">

// 	function checkSession(sid){
		
// 		if(sid==null){
// 			alert("로그인이 필요합니다.");
// 			location.href="LoginForm.me";
// 		}
// 	}
</script>
</head>
<%-- <body onload="checkSession(<%=sid%>)"> --%>
<body>
	<h1>/etranger/WebContent/review/review_write.jsp</h1>
	<section id="writeForm">
		<h2>임시 글쓰기 출력</h2>
		<form action="ReviewWritePro.rv" method="post" enctype="multipart/form-data" name="review_write_form">
		<input type="hidden" id="review_num" value=0>
		<input type="hidden" id="review_package_category_code" name="review_package_category_code" value="1">
		<input type="hidden" id="review_readcount" value=0>
			<table>
				<tr>
					<td class="td_left">작성자</td>
					<td class="td_right"><input type="text" name="member_id"id="member_id"/></td>
				</tr>
				<tr>
					<td class="td_left">제 목</td>
					<td class="td_right"><input name="subject" type="text"id="subject"/></td>
				</tr>
				<tr>
					<td class="td_left">내 용</td>
					<td><textarea id="content" name="content" id="content"cols="40" rows="15"></textarea></td>
				</tr>
				<tr>
					<td class="td_left">파일 첨부</td>
					<td class="td_right"><input name="image" type="file" multiple="multiple" accept="image/*" id="image"/></td>
				</tr>
			</table>
			<section id="commandCell">
				<input type="submit" value="등록">&nbsp;&nbsp; <input
					type="reset" value="다시쓰기" />
			</section>
		</form>

	</section>
</body>
</html>