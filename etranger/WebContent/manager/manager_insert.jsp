<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<!-- 스타일 include -->
<jsp:include page="../include/style.jsp" />


</head>
<body>
	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="../include/top_menu.jsp" />

	<div class="comment-form-wrap pt-5">
		<h3 class="mb-5">상품 분류 등록</h3>
		<!-- 상품코드 이름 지역 도시 테마 -->
		<form action="#" class="p-5 bg-light">
			<div class="form-group">
				<label for="code">Product *</label> <input type="text" class="form-control" id="code">
			</div>
			<div class="form-group">
				<label for="name">Name *</label> <input type="text" class="form-control" id="name">
			</div>
			<div class="form-group">
				<label for="region">region *</label> <input type="text" class="form-control" id="region">
			</div>
			<div class="form-group">
				<label for="city">city *</label> <input type="text" class="form-control" id="city">
			</div>
			<div class="form-group">
				<label for="theme">theme *</label> <input type="text" class="form-control" id="theme">
			</div>
			<div class="form-group">
				<input type="submit" value="등록하기" class="btn py-3 px-4 btn-primary">
			</div>
		</form>
	</div>
</body>
</html>