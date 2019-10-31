<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<!-- 스타일 include -->
<%-- <jsp:include page="../include/style.jsp" /> --%>
<title>etranger - The best choice for your travel</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="https://fonts.googleapis.com/css?family=Muli:300,400,600,700" rel="stylesheet">
<link rel="stylesheet" href="../css/open-iconic-bootstrap.min.css">
<link rel="stylesheet" href="../css/animate.css">
<link rel="stylesheet" href="../css/owl.carousel.min.css">
<link rel="stylesheet" href="../css/owl.theme.default.min.css">
<link rel="stylesheet" href="../css/magnific-popup.css">
<link rel="stylesheet" href="../css/aos.css">
<link rel="stylesheet" href="../css/ionicons.min.css">
<link rel="stylesheet" href="../css/bootstrap-datepicker.css">
<link rel="stylesheet" href="../css/jquery.timepicker.css">
<link rel="stylesheet" href="../css/flaticon.css">
<link rel="stylesheet" href="../css/icomoon.css">
<link rel="stylesheet" href="../css/style.css">


</head>
<body>
	<h1>관리자 페이지 테스트</h1>
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