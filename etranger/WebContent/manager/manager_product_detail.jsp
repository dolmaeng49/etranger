<%@page import="manager.vo.ProductBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="manager.vo.CategoryBean"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="manager.dao.ManagerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	// 패키지 카테고리 상세내용 불러오기 & 제목 내용 수정 기능
	// 패키지상품 insert & delete 

// 	ArrayList<CategoryBean> regionList = (ArrayList<CategoryBean>) request.getAttribute("regionList");
// 	ArrayList<CategoryBean> themeList = (ArrayList<CategoryBean>) request.getAttribute("themeList");
// 	ArrayList<CategoryBean> productList = (ArrayList<CategoryBean>) request.getAttribute("productList");
	CategoryBean pdetail = (CategoryBean) request.getAttribute("pdetail");
	ArrayList<ProductBean> pdList = (ArrayList<ProductBean>) request.getAttribute("pdList");
	String code = "";
	String image = "";
	String[] imgs = new String[4];
	String name = "";
	String content = "";
	String theme = "";
	int region = 0;
	int city = 0;
	
	if (pdetail != null) {
		code = pdetail.getPackage_category_code();
		image = pdetail.getPackage_category_image();
		String[] DBimgs = image.split("\\*");
		for(int j = 0; j < 4; j++) {
			// 4장의 사진을 탑슬라이더에 뿌려줌
			// 업로드된 사진이 4장이 안 될 경우 대비 
			if(DBimgs.length < j + 1) {
				imgs[j] = imgs[j - 1]; 
			} else {
				imgs[j] = DBimgs[j];
			}
		}
		name = pdetail.getPackage_category_name();
		content = pdetail.getPackage_category_content();
		theme = pdetail.getPackage_category_theme();
		region = pdetail.getPackage_category_region();
		city = pdetail.getPackage_category_city();
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<!-- 스타일 인클루드 -->
<jsp:include page="../include/style.jsp" />
<style type="text/css">
table.pdList {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 20px 10px;
	width: 90%;
}

table.Add {
	width: 115%;
}

table.pdList th {
	width: 155px;
	padding: 10px;
	font-weight: bold;
	vertical-align: center;
	color: #fff;
	background: #0f4c81;
	font-size: 0.7em;
}

table.pdList td {
	width: 155px;
	padding: 10px;
	vertical-align: center;
	border-bottom: 1px solid #ccc;
	background: #eee;
	font-size: 0.7em;
}

table.pdList td input {
	font-size: smaller;
}

.sticky {
	position: sticky;
	top: 100px;
}
</style>
<link rel="stylesheet" href="./css/style_management.css" type="text/css">
</head>
<body>

	<!-- 탑메뉴 인클루드 -->
	<jsp:include page="../include/top_menu.jsp" />

	<section class="home-slider owl-carousel">
		<div class="slider-item"
			style="background-image: url('images/bg_3.jpg');"
			data-stellar-background-ratio="0.5">
			<div class="overlay"></div>
			<div class="container">
				<div class="row slider-text align-items-center">
					<div class="col-md-7 col-sm-12 ftco-animate">
						<p class="breadcrumbs">
							<span class="mr-2"><a href="../main/index.jsp"></a></span> <span><a
								href="blog.html"></a></span> <span></span>
						</p>
						<h1 class="mb-3">카테고리 상세내용&amp;패키지상품 추가</h1>

						<!-- package_category_code       | varchar(100)  | NO   | PRI | NULL    |                | -->
						<!-- package_category_name       | varchar(50)   | NO   | UNI | NULL    |                | -->
						<!-- package_category_region     | int(11)       | NO   | MUL | NULL    |                | -->
						<!-- package_category_city       | int(11)       | NO   | MUL | NULL    |                | -->
						<!-- package_category_theme      | varchar(100)  | YES  |     | NULL    |                | -->
						<!-- package_category_image      | varchar(100)  | NO   |     | NULL    |                | -->
						<!-- package_category_content    | varchar(2000) | NO   |     | NULL    |                | -->

						<br>
					</div>
				</div>
			</div>
		</div>
	</section>

	<!-- END slider -->
	<section class="ftco-section">
		<div class="col-md-2 sticky" style="float: left;">
			<div class="panel panel-info sticky" id="leftside">
				<div class="panel-heading">

					<h3 class="panel-title">Manager Page</h3>
				</div>
				<ul class="list-group">
					<li class="list-group-item active li_hover"
						onclick="location.href='./index.jsp'"><span
						class="icon icon-home"></span> 홈</li>
					<li class="list-group-item li_hover dataChart"
						onclick="location.href='ManagerMain.ma'"><span
						class="icon icon-line-chart"></span> 관리자 메인</li>
					<li class="list-group-item li_hover"
						onclick="location.href='ManagerMain.ma'"><span
						class="icon icon-pencil"></span> 카테고리 등록&amp;수정 돌아가기</li>
					
					<li class="list-group-item li_hover dropdown" id="dropdownMenuButton" 
						data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<span class="icon icon-users"></span>회원관리
						</li>
						<div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> 
						<a class="dropdown-item" href="ReservManagement.ma">예약목록</a> 
						</div>
				</ul>

			</div>
		</div>
		<div class="container">
			<div class="row">
				<!--  카테고리 디테일 & 수정 폼 시작 -->
				<div class="col-md-8 ftco-animate">
					<form action="CategoryUpdate.ma" class="p-5 bg-light" method="post">
						<div class="managerdetailform">
							<h3 class="mb-5">카테고리 상세내용</h3>
						</div>

						<div class="writeform-group">
							<label for="image">이미지</label><a href="#" class="block-20"
								style="background-image: url('ManagerImgUpload/<%=imgs[0]%>');">
							</a>
						</div>

						<div class="writeform-group">
							<br> <label for="code">상품코드</label><input type="text"
								class="form-control" name="category_code" id="product_code_id"
								value="<%=code%>" readonly />
						</div>

						<br>
						<div class="writeform-group">
							<label for="code">지역코드</label><input type="text"
								class="form-control" name="category_region"
								id="product_region_code_id" value="<%=region%>" readonly />
						</div>

						<br>
						<div class="writeform-group">
							<label for="code">도시코드</label><input type="text"
								class="form-control" name="category_city"
								id="product_city_code_id" value="<%=city%>" readonly />
						</div>

						<br>
						<div class="writeform-group">
							<label for="name">상품테마</label> <input type="text"
								class="form-control" name="category_theme" id="product_theme_id"
								value="<%=theme%>" readonly />
						</div>

						<br>
						<div class="writeform-group">
							<label for="name">상품명</label> <input type="text"
								class="form-control" name="category_name" id="product_name_id"
								value="<%=name%>" />
						</div>

						<div class="writeform-group">
							<br> <label for="name">상세내용</label> <br>
							<textarea id="summernoteManager" name="category_content"
								rows="20" cols="80" required="required">
								<%=content%>
							</textarea>
						</div>
						<div class="custom-file mt-3" style="width: 100%">
								<label for="name">img</label>
								<input multiple="multiple" type="hidden" name="category_image" required="required" id="category_image" value="<%=image%>"
								style="width: 100%;"/>
						</div>

						<input type="submit" class="btn btn-outline-dark btn-lg mt-3 mb-3"
							value="수정하기"> <input type="Button"
							class="btn btn-outline-dark btn-lg mt-3 mb-3" value="삭제하기"
							class="btn btn-info" data-toggle="tooltip"
							data-placement="bottom" title="예약이 없을 때만 삭제가능"
							onclick="location.href='CategoryDelete.ma?package_category_code=<%=code%>'">
					</form>
				</div>
				<!--  카테고리 수정 폼 끝 -->
				<input type="hidden" id="deletenum" name="<%=code%>">

				<div class="col-md-4 ftco-animate">
					<div class="writeform-group sticky">
						<div class="writeform-group">
							<h3>
								<label for="name" class="mt-5">패키지상품 리스트</label>
							</h3>

							<div style="overflow-y: scroll; height: 500px;">
								<table class="pdList" id="pdlist">
									<%
										if (pdList != null && pdList.size() > 0) {
									%>
									<tr>
										<!-- 									<th>상품코드</th> -->
										<th>출발날짜/<br>도착날짜
										</th>
										<th style="width: 20%;">가격</th>
										<th style="width: 15%;">인원</th>
										<th style="width: 10%;">제어</th>
									</tr>

									<%
										for (int i = 0; i < pdList.size(); i++) {
									%>
									<tr>
										<%-- 									<td><%=pdList.get(i).getProductNum()%></td> --%>
										<td style="font-size: 0.5em;"><%=pdList.get(i).getProductDepartDate()%>
											/ <br><%=pdList.get(i).getProductArrivDate()%></td>
										<td><%=pdList.get(i).getProductPrice()%></td>
										<td><%=pdList.get(i).getProductTotal()%></td>
										<td><input type="button"
											class="btn py-1 px-2 btn-primary deletecode" value="delete"
											onclick="deleteCode('<%=pdList.get(i).getProductNum()%>')"></td>
									</tr>
									<%
										}
										} else {
									%><tr>
										<td>패키지상품을 등록한 날짜가 없습니다.</td>
									</tr>
									<%
										}
									%>

									<label id="newProduct"></label>
								</table>
							</div>

						</div>


						<div class="form-group mt-3">
							<input type="button" value="날짜별 패키지상품 추가하기"
								class="btn py-2 px-2 btn-primary" onclick="product('<%=code%>')">
						</div>
						<div class="form-group" id="addProduct" style="display: none;">
							<!-- 						<form action="ProductInsertPro.ma" method="post"> -->
							<table class="pdList Add">

								<tr id="tr_top">

									<th>출발날짜</th>
									<th>도착날짜</th>
									<th>가격</th>
									<th>총인원수</th>
									<th style="width: 10%;">등록</th>
								</tr>
								<tr id="event_period">
									<td><input type="hidden" value="<%=code%>"
										id="package_category_code"> <input type="text"
										class="form-control form-control-shortshort actual_range"
										id="product_depardate" placeholder="depardate"
										name="product_depardate" required="required"
										readonly="readonly"></td>
									<td><input type="text"
										class="form-control form-control-shortshort actual_range"
										id="product_arrivdate" placeholder="arrivdate"
										name="product_arrivdate" required="required"
										readonly="readonly"></td>
									<td><input type="text" class="form-control"
										name="product_price" id="product_price" placeholder="price" /></td>
									<td><input type="text" class="form-control"
										name="product_total" id="product_total" placeholder="total" /></td>
									<td><input type="button" value="등록" id="product_addbtn"
										class="btn py-2 px-1 btn-primary" onclick="addProduct()"></td>
								</tr>
							</table>
						</div>
					</div>
				</div>



			</div>
		</div>
	</section>

	<!-- footer 인클루드 -->
	<jsp:include page="../include/footer.jsp" />

	<!-- loader 인클루드 -->
	<jsp:include page="../include/loader.jsp" />
	<script src="js/manager.js"></script>
</body>
<script type="text/javascript">
	// 상품 추가폼 출력
	function product() {
		if ($('#addProduct').css('display') == 'none') {
			$('#addProduct').show(100);
		} else {
			$('#addProduct').hide(100);
		}
	}
	
	// 상품 추가
	function addProduct() {
		var dpcode = $('#deletenum').attr('name');
		$.ajax('ProductInsertPro.ma', {
			data : {
				package_category_code : $('#package_category_code').val(),
				product_depardate : $('#product_depardate').val(),
				product_arrivdate : $('#product_arrivdate').val(),
				product_price : $('#product_price').val(),
				product_total : $('#product_total').val()
			},
			success : function(sdata) {
				if (sdata == 'false') {
					alert('상품추가 실패!');

				} else {
					getProductList(dpcode);
				}
			}
		});
	}
<%-- 	onclick="deleteCode('<%=pdList.get(i).getProductNum()%>')" --%>
	//상품 삭제
	function deleteCode(pnum) {
		var dpcode = $('#deletenum').attr('name');
		$.ajax('DeleteProduct.ma', {
			data: {
				package_product_num: pnum
			},
			success: function (sdata) {
				if (sdata == 'false') {
					alert('상품삭제 실패!');
				} else {
					getProductList(dpcode);
				}
			}
		});
	}




function getProduct() {
    $('#addProduct').hide();
    $('#pdList').empty(); // #pdList에 있는 내용 지우기
//     $('#theme_addbox').val("");

    // <label id="newProduct">에 추가
    $.getJSON('ProductList.ma', function (data) {
        $.each(data, function (index, value) {
            $('#pdList').append('<tr><td>"' + value.package_category_code + '"</td><td>' 
            						+ value.product_depardate + '</td><td>'
            						+ value.product_arrivdate + '</td><td>'
            						+ value.product_price + '</td><td>'
            						+ value.product_total + '</td><td><tr>'
            						);
			// 테이블 뿌려주기 
        });
    });
}



// <th style="width: 20%;">가격</th>
// <th style="width: 15%;">인원</th>
// <th style="width: 10%;">제어</th>
	// 상품 리스트 불러오기 
	function getProductList(dpcode) {
		$('#pdlist').empty();
		$('#pdlist').append("<tr>" +
// 			"<th>상품코드</th>" +
			"<th>출발날짜/도착날짜</th>" +
// 			"<th>도착날짜</th>" +
			"<th style=\"width: 20%;\">가격</th>" +
			"<th style=\"width: 15%;\">총인원수</th>" +
			"<th style=\"width: 10%;\">제어</th>" +
			"</tr>");
		$.getJSON('ProductList.ma?dpcode=' + dpcode, function (data) {
			$.each(data, function (index, item) {
				$('#pdlist').append(
					"<tr>"+
// 					<td>" + item.product_num + "</td>" +
					"<td>" + item.productDepartDate + "/<br>"+ item.productArrivDate+"</td>" +
// 					"<td>" + item.productArrivDate + "</td>" +
					"<td>" + item.productPrice + "</td>" +
					"<td>" + item.productTotal + "</td>" +																	
					"<td>" + '<input type="button" class="btn py-1 px-2 btn-primary deletecode" id="deletecode" value="delete" onclick="deleteCode('+"'" +item.product_num +"'"+')">'
					+'</td></tr>'
				);
			});
		});
	}

</script>

</html>