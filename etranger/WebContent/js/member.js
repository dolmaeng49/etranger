
(function($) {
// 아이디, 패스워드 유효성 검사 결과를 저장하는 전역 변수
	var checkIdResult = false, checkPasswdResult = false;

/* 	function emailSelect() {
// 		alert(document.join_fr.email_select.value);
		document.join_fr.email_2.value = document.join_fr.email_select.value;
		if(document.join_fr.email_select.selectedIndex == 0) {
			document.join_fr.email_2.focus();
		}
	} */
	
	
//	function changeDomain(domain) {
//		document.join_fr.email_2.value = domain.value;
//	}
	
	$('#member_id').keyup(function(){
		// 4-12자리 영문자,숫자 조합
		var regex = /^[a-zA-Z0-9]{4,12}$/g;
		var regex_eng = /[a-zA-Z]/g;
		var regex_num = /[0-9]/g;
		
		// 아이디 입력창 우측의 div 태그에 대한 id 값을 지정하여 해당 태그 Element 가져오기
		var element = document.getElementById('checkIdResult'); // 태그 중 checkIdResult 에 해당하는 id 찾기
		if(regex.exec($(this).val())) { // 입력받은 id 가져와서 정규표현식을 통해 유효성 검사 수행
			if(regex_eng.exec($(this).val()) && regex_num.exec($(this).val())){
				element.innerHTML = "사용 가능 아이디입니다";
				checkIdResult = true;
			}else {
				element.innerHTML = "4-12자리 영문자,숫자 조합으로 입력해주세요";
				checkIdResult = false;
			}
		}else {
			element.innerHTML = "4-12자리 영문자,숫자 조합으로 입력해주세요";
			checkIdResult = false;
		}
	});
	
	$('#member_passwd').keyup(function(){
		// 8-20자리 영문자,숫자,특수문자(!,@,#,$,%) 조합
		var regex_length = /^[a-zA-Z0-9!@#$%]{8,20}$/g;
		var regex_eng = /[a-zA-Z]/g;
		var regex_num = /[0-9]/g;
		var regex_spe = /[!@#$%]/g;
		
		var element = document.getElementById('checkPasswdResult');
		var checkCount = 0;
		if(regex_length.exec($(this).val())) {
			if(regex_eng.exec($(this).val())){
				checkCount += 1;
			}if(regex_num.exec($(this).val())) {
				checkCount += 1;
			}if(regex_spe.exec($(this).val())) {
				checkCount += 1;
			}
		}
		if(checkCount == 3){
			element.innerHTML = "사용 가능 패스워드입니다";
			checkPasswdResult = true;
		}else {
			element.innerHTML = "8-20자리 영문자,숫자,특수문자(!,@,#,$,%) 조합으로 입력해주세요";
			checkPasswdResult = false;
		}
	});
	
	$('#member_passwd_retype').keyup(function(){
		var element = document.getElementById('checkPasswdRetype');
		if($('#member_passwd').val()!=$(this).val()){
			element.innerHTML = "비밀번호가 일치하지 않습니다";
		}else{
			element.innerHTML = "";
		}
	});
	
	$('#btn_dup').click(function(){
		var fid=$('#member_id').val();
		
		/*$.getJSON('MemberIdDupCheck.me?id='+fid,function(data){
			$.each(data,function(index,item){
				$('#member_id_DupCheck').html(item.id);
			});
		});*/
		
		$.ajax('MemberIdDupCheck.me?id='+fid,{
			data:{id:fid},
			success:function(sdata){
				$('#member_id_DupCheck').html(sdata);
			}
		});
	});
	

	
	
	
	/*function checkSubmit() {
		//
		if(checkIdResult && checkPasswdResult) {
			return true;
		}else {
			alert('아이디 또는 패스워드 규칙 확인 필수');
			return false;
		}
	}*/
	
})(jQuery);

	
	