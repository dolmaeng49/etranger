
(function($) {
	
// ============= 회원가입 & 회원정보 수정 & PW 변경

	// ID 체크
	var checkIdResult = false;
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
	var checkPasswdResult = false;
	$('#member_passwd').keyup(function(){
		// 8-20자리 영문자대문자,소문자,숫자,특수문자(!@#$%^&*()_+)중 3가지 조합
		var lengthCaseRegex = /[A-Za-z0-9!@#$%^&*()_+]{8,20}/g;
		var upperCaseRegex = /[A-Z]/g;
		var lowerCaseRegex = /[a-z]/g;
		var digitCaseRegex = /[0-9]/g;
		var specialCharRegex = /[!@#$%^&*()_+]/g;
		
		var element = document.getElementById('checkPasswdResult');
		var checkCount = 0;
		if(lengthCaseRegex.exec($(this).val())) {
			// 대문자, 소문자, 숫자, 특수문자가 포함되었을 경우 마다 checkCount += 1
			checkCount += upperCaseRegex.exec($(this).val()) ? 1 : 0;
			checkCount += lowerCaseRegex.exec($(this).val()) ? 1 : 0;
			checkCount += digitCaseRegex.exec($(this).val()) ? 1 : 0;
			checkCount += specialCharRegex.exec($(this).val()) ? 1 : 0;
		}
		if(checkCount >= 3){
			// 3가지 이상의 조합을 사용한 경우 메세지
			element.innerHTML = "사용 가능 패스워드입니다";
			checkPasswdResult = true;
		}else {
			element.innerHTML = "대,소문자,숫자,특수문자(!@#$%^&*()_+)중 셋 이상 조합 8-20자리";
			checkPasswdResult = false;
		}
	});
	
	// P/W 재입력 일치여부 확인
	var checkPasswdRetype = false;
	$('#member_passwd_retype').keyup(function(){
		var element = document.getElementById('checkPasswdRetype');
		if($('#member_passwd').val()!=$(this).val()){
			element.innerHTML = "비밀번호가 일치하지 않습니다";
			checkPasswdRetype = false;
		}else{
			element.innerHTML = "";
			checkPasswdRetype = true;
		}
	});
	
	// ID 중복 체크 버튼
	$('#btn_dup').click(function(){
		if(!checkIdResult){
			alert('아이디를 확인해주세요!');
			return;
		}
		var fid=$('#member_id').val();
//		alert(fid);
		/*$.getJSON('MemberIdDupCheck.me?id='+fid,function(data){
			$.each(data,function(index,item){
				$('#member_id_DupCheck').html(item.id);
			});
		});*/
		$.ajax('MemberIdDupCheck.me',{
			data:{id:fid},
			success:function(sdata){
				if(fid==sdata){
					$('#member_id_DupCheck').val(sdata);
					alert(fid + '는 사용가능한 아이디입니다!');
				}else{
					alert('이미 존재하는 아이디입니다!');
				}
			}
		});
	});
	
	// Email 인증 코드 발송
	$('#btn_email_code').click(function(){
		var fEmail=$('#member_email').val();
		if(fEmail.length==0){
			alert('Email을 입력해주세요!');
			return;
		}
		$.ajax('MemberSendEmailCode.me',{
			data:{email:fEmail},
			success:function(sdata){
				// 인증코드 일치 확인을 위해 input hidden 에 인증코드 저장 
				$('#email_check').val(sdata);
				alert('인증 코드 발송 완료!');
			}
		});
	});
	
	// Email 인증 코드 입력 확인
	var checkEmailCode = false;
	$('#btn_email_check').click(function(){
		var element = document.getElementById('member_email_code');
//		alert('입력받은코드:' + element.value + '발송한코드:' + $('#email_check').val());
		if(checkEmailCode){
			alert('이미 인증완료 되었습니다!\n 다른 이메일 주소를 이용하려면 페이지를 새로고침해주세요');
		}
		if(element.value==""){alert('인증코드를 입력해주세요!'); return;}
		if(element.value==($('#email_check').val())){
			alert('인증완료 되었습니다!');
			$('#member_email_code').val("인증 성공");
			$('#member_email_code').attr('readonly',"readonly");
			$('#member_email').attr('readonly',"readonly");
			checkEmailCode = true;
		}else{
			alert('인증코드를 확인해주세요');
		}
	});
	
	// 회원가입,회원정보수정  submit() 이벤트 발생시 제어
	$('#joinForm, #modifyForm').submit(function(){
		// 성별체크 입력 확인
		if($('#gender_man').is(":checked")==false && $('#gender_woman').is(":checked")==false){
			alert('성별을 체크 해주세요');
			$('#gender_man').focus();
			return false;
		}
		// 아이디 유효성 검사 확인
		if(!checkIdResult){
			alert('아이디를 확인해주세요');
			$('#member_id').focus();
			return false;
		}
		// ID 중복체크 확인
		if(!($('#member_id_DupCheck').val()==($('#member_id').val()))){
			alert('아이디 중복체크는 필수입니다!');
			$('#member_id').focus();
			return false;
		}
		// 패스워드 유효성 검사 확인
		if(!checkPasswdResult){
			alert('패스워드를 확인해주세요');
			$('#member_passwd').focus();
			return false;
		}
		// 패스워드 재입력 일치여부 확인
		if(!checkPasswdRetype){
			alert('패스워드 재입력을 확인해주세요');
			$('#member_passwd2').focus();
			return false;
		}
		// Email 인증 확인
		if(!checkEmailCode){
			alert('E-Mail 인증을 확인해주세요');
			$('#member_email_code').focus();
			return false;
		}
	});
	
	// 회원정보수정에서 기존 메일 사용
	$('#select_old_email').click(function(){
		checkEmailCode = true; // email 인증 확인하는 전역변수 true로 초기화
		$('#old_email_check').css('display',"none");
	});
	
	// 회원정보수정에서 새로운 메일 인증
	$('#select_new_email').click(function(){
		checkEmailCode = false; // email 인증 확인하는 전역변수 false로 초기화
		$('#member_email').removeAttr("readonly");
		$('#old_email_check').css('display',"none");
		$('#new_email_check').css('display',"inline");
	});

	
// ============= ID, PW 찾기	
	
	// ID 찾기 submit() 이벤트 발생시 제어
	$('#findIdForm').submit(function(){
		// 성별체크 입력 확인
		if($('#gender_man').is(":checked")==false && $('#gender_woman').is(":checked")==false){
			alert('성별을 체크 해주세요');
			$('#gender_man').focus();
			return false;
		}
	});
	// PW찾기 - ID, Email 정보 확인, 인증 코드 발송
	$('#btn_email_code_findPasswd').click(function(){
		var fId=$('#member_id_findPasswd').val();
		var fEmail=$('#member_email').val();
		$.ajax('MemberResetPasswdForm.me',{
			data:{member_id:fId,member_email:fEmail},
			dataType:"xml",
			success:function(sdata){
				var result = $(sdata).find('findId').find('result').text();
				var checkCode = $(sdata).find('findId').find('checkCode').text();
				if(result == '0'){
					alert('존재하지 않는 아이디입니다');
				}else if(result == '-1'){
					alert('Email 주소가 일치하지 않습니다');
				}else if(result == '1'){
					// 인증코드 일치 확인을 위해 input hidden 에 인증코드 저장 
					$('#email_check').val(checkCode);
					alert('인증 코드 발송 완료!');
					// 인증 후 아이디, Email 변경 방지 
					$('#member_id_findPasswd').attr('readonly',"readonly");
					$('#member_email').attr('readonly',"readonly");
				}
				
			}
		});
	});
	// PW찾기 - submit() 이벤트 발생시 제어
	$('#findPasswdForm').submit(function(){
		// 패스워드 유효성 검사 확인
		if(!checkPasswdResult){
			alert('패스워드를 확인해주세요');
			$('#member_passwd').focus();
			return false;
		}
		// 패스워드 재입력 일치여부 확인
		if(!checkPasswdRetype){
			alert('패스워드 재입력을 확인해주세요');
			$('#member_passwd2').focus();
			return false;
		}
		// 이메일 인증 확인
		if(!checkEmailCode){
			alert('E-Mail 인증을 확인해주세요');
			$('#member_email_code').focus();
			return false;
		}
	});
		
	
})(jQuery);

/* --------은지씨 스크립트
 * 
//아이디 유효성 
function checkId(member_id) {
	var regex = /^[A-Za-z0-9_]{4,16}$/g;
	var element = document.getElementById('checkIdResult');
	if (regex.exec(member_id.value)) {
		element.innerHTML = "사용 가능한 아이디입니다.";
	} else {
		element.innerHTML = "사용 불가능한 아이디입니다.";
	}
}

function checkPasswd(member_passwd) {
	var lengthCaseRegex = /[A-Za-z0-9!@#$%^&*()_+]{8,20}/;
	var upperCaseRegex = /[A-z]/;
	var lowerCaseRegex = /[a-z]/;
	var digitCaseRegex = /[0-9]/;
	var specialCharRegex = /[!@#$%^&*()_+]/
	var element = document.getElementById('checkPasswdResult');
	if (lengthCaseRegex.exec(member_passwd.value)
			&& upperCaseRegex.exec(member_passwd.value)
			&& lowerCaseRegex.exec(member_passwd.value)
			&& digitCaseRegex.exec(member_passwd.value)
			&& specialCharRegex.exec(member_passwd.value)) {
		element.innerHTML = "사용 가능한 패스워드 입니다.";
	} else {
		element.innerHTML = "사용 불가능한 패스워드 입니다.";
	}
}
 */

function checkEmail(member_email) {

	var element = document.getElementById('checkEmailResult');

	var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

	if (exptext.exec(member_email.value)) {
		//이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식이 아닐경우
		element.innerHTML = "올바른 이메일 형식입니다."
	} else {
		element.innerHTML = "올바른 이메일 형식이 아닙니다."
	}
}
	
	