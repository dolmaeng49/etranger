
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
	
	// ID 체크
	$('#member_id').keyup(function(){
		// 4-12자리 영문자,숫자 조합
		var regex = /^[a-zA-Z0-9]{4,12}$/g;
		var regex_eng = /[a-zA-Z]/g;
		var regex_num = /[0-9]/g;
		
		// 메시지를 표시할 곳의 id 값을 지정하여 해당 태그 Element 가져오기
		var element = document.getElementById('checkIdResult'); // 태그 중 checkIdResult 에 해당하는 id 찾기
		if(regex.exec($(this).val()) && regex_eng.exec($(this).val()) && regex_num.exec($(this).val())) {
			// 입력받은 id 가져와서 정규표현식을 통해 유효성 검사 수행
			element.innerHTML = "사용 가능한 아이디입니다";
			checkIdResult = true;
		}else {
			element.innerHTML = "4-12자리 영문자,숫자 조합으로 입력해주세요";
			checkIdResult = false;
		}
	});
	// P/W 체크
	$('#member_passwd').keyup(function(){
		// 8-20자리 영문자대문자,소문자,숫자,특수문자(!@#$%^&*()_+)중 3가지 조합
		var lengthCaseRegex = /[A-Za-z0-9!@#$%^&*()_+]{8,20}/;
		var upperCaseRegex = /[A-Z]/;
		var lowerCaseRegex = /[a-z]/;
		var digitCaseRegex = /[0-9]/;
		var specialCharRegex = /[!@#$%^&*()_+]/;
		
		var element = document.getElementById('checkPasswdResult');
		var checkCount = 0;
		if(lengthCaseRegex.exec($(this).val())) {
			if(upperCaseRegex.exec($(this).val())){
				checkCount += 1;
			}if(lowerCaseRegex.exec($(this).val())) {
				checkCount += 1;
			}if(digitCaseRegex.exec($(this).val())) {
				checkCount += 1;
			}if(specialCharRegex.exec($(this).val())) {
				checkCount += 1;
			}
		}
		if(checkCount >= 3){
			element.innerHTML = "사용 가능 패스워드입니다";
			checkPasswdResult = true;
		}else {
			element.innerHTML = "8-20자리 영문자대문자,소문자,숫자,특수문자(!@#$%^&*()_+)중 3가지 이상 조합으로 입력해주세요";
			checkPasswdResult = false;
		}
	});
	// P/W 재입력 확인
	var checkPasswdRetype = false;
	$('#member_passwd_retype').keyup(function(){
		var element = document.getElementById('checkPasswdRetype');
		if($('#member_passwd').val()!=$(this).val()){
			element.innerHTML = "비밀번호가 일치하지 않습니다";
		}else{
			element.innerHTML = "";
			checkPasswdRetype = true;
		}
	});
	// ID 중복 체크 버튼
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
	// Email 인증 코드 발송
	$('#btn_email_check').click(function(){
		$.ajax('MemberSendEmailCode.me',{
			success:function(sdata){
				$('#email_check').html(sdata);
			}
		});
	});
	// Email 인증 코드 입력 확인
	var checkEmailCode = false;
	$('#btn_email_check').click(function(){
		var element = document.getElementById('member_email_code');
		if(element.value==$('#email_check').val()){
			element.innerHTML = "Email 인증 성공";
			element.attr('readonly',"readonly");
			checkEmailCode = true;
		}else{
			alert('인증코드를 확인해주세요');
		}
	});
	
	$('#member_join_submit').click(function(){
		var checkIdDup = false; // ID 중복 검사 여부를 확인할 변수
		if($('#member_id_DupCheck').val()==$('#member_id').val()){
			// ID 입력칸과 중복체크한 ID (input hidden)가 일치할 경우
			checkIdDup = true;
		}else{
			alert('ID 중복체크 필수!');
			return;
		}
		if(!(checkIdResult && checkPasswdResult && checkPasswdRetype)){
			// ID, 패스워드 유효성 검사, 패스워드 재입력 일치 중 하나라도 부족한 경우
			alert('ID 또는 패스워드를 확인해 주세요');
			return;
		}
		// 모든 조건 통과시 form submit
		document.member_join_form.submit();
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

	
	