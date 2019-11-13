(function ($) {

    // 지역추가
    $('#region_addbtn').click(function () {
        $.ajax('ManagerProInsert.ma', {
            data: {
                region_name: $('#region_addbox').val()
            }
        });
        $('#addRegion').hide();
        // #selectRegion에 있는 내용 지우기
        $('#selectRegion').empty();

        // #selectRegion에 옵션 추가
        $('#selectRegion').append("<option>지역선택</option>");
        $('#selectRegion').append("<option value=" + "add" + ">지역추가</option>");

        // JSON으로 가져온 데이터 #SelectRegion에 옵션으로 추가
        $.getJSON('RegionSelect.ma', function (data) {
            $.each(data, function (index, value) {
                $('#selectRegion').append("<option value=" + value.regionCode + ">지역코드 : " + value.regionCode + ", 지역이름 : " + value.regionName + "</option>");
            });
        });
    });
    
    // 도시추가(지역코드 가져와서 같이 저장)
    $('#city_addbtn').click(function(){
    	$.ajax('CityInsert.ma',{
    		data:{
    			region_code: $('#selectRegion').val(), city_name: $('#city_addbox').val()
    		}
    	});
    	 $('#addCity').hide();
    	// #selectRegion에 있는 내용 지우기
        $('#selectCity').empty();

        // #selectRegion에 옵션 추가
        $('#selectCity').append("<option>도시선택</option>");
        $('#selectCity').append("<option value=" + "add" + ">도시추가</option>");

        // JSON으로 가져온 데이터 #SelectRegion에 옵션으로 추가
        var code = $('#selectRegion').val();
        $.getJSON('CitySelect.ma?code='+ code, function (data){
        	$.each(data, function(index, value){
        		$('#selectCity').append("<option value=" + value.cityCode + ">도시코드 : " + value.cityCode + ", 도시이름 : " + value.cityName + "</option>");
        	});
        });
    });
    

})(jQuery);

// 상품 분류 등록 표시
function dis() {
    if ($('#dis').css('display') == 'none') {
        $('#dis').show();
    } else {
        $('#dis').hide();
    }
}

// 지역 추가 표시, 선택한 지역의 도시출력
function showRegionAdd() {
    if ($('#selectRegion').val() == 'add') {
        $('#addRegion').show();
    }
    else if ($('#selectRegion').val() > 0) {
        $('#addRegion').hide();
        $('#selectCity').empty();
        $('#selectCity').append("<option>도시선택</option>");
        $('#selectCity').append("<option value=" + "add" + ">도시추가</option>");
        
        var code = $('#selectRegion').val();
                $.getJSON('CitySelect.ma?code='+ code, function (data){
                	$.each(data, function(index, value){
                		
                		$('#selectCity').append("<option value=" + value.cityCode + ">도시코드 : " + value.cityCode + ", 도시이름 : " + value.cityName + "</option>");
                	});
                });
    }
    else{
    	$('#addRegion').hide();
    }
}

// 도시 추가 표시
function showCityAdd() {
    if ($('#selectCity').val() == 'add') {
        $('#addCity').show();
    } else {
        $('#addCity').hide();
    }
}

// 테마 추가 표시
function check() {
    if ($('#addTheme').css('display') == 'none') {
        $('#addTheme').show();
    } else {
        $('#addTheme').hide();
    }
}