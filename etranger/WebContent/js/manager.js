// 지역추가
$('#region_addbtn').click(function() {
	addRegion();
});

// 도시추가(지역코드 가져와서 같이 저장)
$('#city_addbtn').click(function() {
	addCity();
});

// 테마추가
$('#theme_addbtn2').click(function() {
	addTheme();
});

// 지역 목록 불러오기
function getRegion() {
	$('#addRegion').hide();
	// #selectRegion에 있는 내용 지우기
	$('#selectRegion').empty();

	// #selectRegion에 옵션 추가
	$('#selectRegion').append("<option>지역선택</option>");
	$('#selectRegion').append("<option value=" + "add" + ">지역추가</option>");

	// JSON으로 가져온 데이터 #SelectRegion에 옵션으로 추가
	$.getJSON('RegionSelect.ma', function(data) {

		$.each(data, function(index, value) {
			$('#selectRegion').append(
					"<option value=" + value.regionCode + ">지역코드 : "
							+ value.regionCode + ", 지역이름 : " + value.regionName
							+ "</option>");
		});
	});
}

// 도시 목록 불러오기
function getCity() {
	$('#addRegion').hide();
	$('#selectCity').empty();
	$('#selectCity').append("<option>도시선택</option>");
	$('#selectCity').append("<option value=" + "add" + ">도시추가</option>");

	var code = $('#selectRegion').val();
	$.getJSON('CitySelect.ma?code=' + code, function(data) {
		$.each(data, function(index, value) {

			$('#selectCity').append(
					"<option value=" + value.cityCode + ">도시코드 : "
							+ value.cityCode + ", 도시이름 : " + value.cityName
							+ "</option>");
		});
	});
}

function getTheme() {
	$('#addTheme').hide();
	$('#test').empty(); // #newTheme에 있는 내용 지우기
	$('#theme_addbox').val("");
	// <label id="newTheme">에 추가
	$.getJSON('ThemeCheckBox.ma', function(data) {
		$.each(data, function(index, value) {
			$('#test').append(
					'<label id="newTheme">'
							+ '<input type="checkbox" name="theme" value="'
							+ value.themeName + '">' + value.themeName
							+ '&nbsp; </label>');
		});
	});
}

// 지역 추가하기
function addRegion() {
	$.ajax('RegionInsert.ma', {
		data : {
			region_name : $('#region_addbox').val()
		},
		success : function(sdata) {
			if (sdata == 'false') {
				alert('지역추가 실패!');
				$('#city_addbox').val("");
			} else {
				getRegion();
				$('#region_addbox').val("");
			}
		}
	});
}

// 도시 추가하기
function addCity() {
	if ($("#selectRegion option").index($("#selectRegion option:selected")) > 1) {
		$.ajax('CityInsert.ma', {
			data : {
				region_code : $('#selectRegion').val(),
				city_name : $('#city_addbox').val()
			},
			success : function(sdata) {
				if (sdata == 'false') {
					alert('도시추가 실패!');
					$('#city_addbox').val("");
				} else {
					getCity();
					$('#city_addbox').val("");
				}
			}
		});
	} else {
		alert('지역을 선택해주세요!!!');
		$("#selectRegion").focus();
	}
}

// 테마 추가하기
function addTheme() {
	$.ajax('ThemeInsert.ma', {
		data : {
			theme_name : $('#theme_addbox').val()
		},
		success : function(sdata) {
			if (sdata == 'false') {
				alert('테마추가 실패!');
				$('#city_addbox').val("");
			} else {
				getTheme();
				$('#city_addbox').val("");
			}
		}
	});
}

// 지역 추가 표시, 선택한 지역의 도시출력
function showRegionAdd() {
	if ($('#selectRegion').val() == 'add') {
		$('#addRegion').show(100);
	} else if ($('#selectRegion').val() > 0) {
		getCity();
	} else {
		$('#addRegion').hide(100);
		0
	}
}

// 도시 추가 표시
function showCityAdd() {
	if ($('#selectCity').val() == 'add') {
		$('#addCity').show(100);
	} else {
		$('#addCity').hide(100);
	}
}

// 테마 추가 표시
function check() {
	if ($('#addTheme').css('display') == 'none') {
		$('#addTheme').show(100);
	} else {
		$('#addTheme').hide(100);
	}
}

// 상품 분류 등록 표시
function showCategoryInsert() {
	if ($('#categoryInsert').css('display') == 'none') {
		$('#productInsert').hide(300);
		$('#showDataChart').hide(300);
		$('#categoryInsert').show(300);
	}
}

function pageNum(page) {
	$.ajax('CategoryList.ma?page=' + page, {
		success : function(data) {
			$('#productList').html(data);
		}
	});
}

$('.pList').click(function() {
	if ($('#productInsert').css('display') == 'none') {
		$('#categoryInsert').hide(300);
		$('#showDataChart').hide(300);
		$('#productInsert').show(300);
		$.ajax('CategoryList.ma', {
			success : function(data) {
				$('#productList').html(data);
			}
		});
	}
});

$("#member").click(function() {

	$("#memberManagement").slideToggle("quick");

});

$('.dataChart').click(function() {
	$('#categoryInsert').hide(300);
	$('#productInsert').hide(300);
	$('#showDataChart').show(300);
	// $.ajax('CategoryList.ma', {
	// success: function (data) {
	// $('#productList').html(data);
	// }
	// });

});

// 지역추가, 도시추가 textbox 엔터키 이벤트
function enterKey(thisId) {
	if (event.keyCode == 13) {
		if ($(thisId).attr('id') == 'region_addbox') {
			addRegion();
		} else if ($(thisId).attr('id') == 'city_addbox') {
			addCity();
		} else if ($(thisId).attr('id') == 'theme_addbox') {
			addTheme();
		}
	}
}

function captureReturnKey(e) {
    if(e.keyCode==13 && e.srcElement.type != 'textarea')
    return false;
}
