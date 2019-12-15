//함수 호출목록

//댓글등록
$('#comment_add_btn').click(function () {	//댓글쓰기 버튼 클릭 시 addComment() 호출
    	    addComment();    						
    	});

//리뷰삭제 confirm 창으로 제어
$('#review_del_button').click(function () {
	var delConfirm = confirm('정말 삭제하시겠습니까?');
	if (delConfirm){
	}else{
		return false;
	}
	
});

//----------중복클릭(광클) 클라이언트단 제어----------
var isClickDupl = false; // 중복클릭인가? = 아니.
function duplClickCheck() {
	setTimeout(function () {
    	isClickDupl = false;
    }, 3000);		//3초 뒤에 다시 false로 중복여부 초기화.
     
     if (isClickDupl) { 
        console.log("중복됨");
        return isClickDupl;
        	
     } else {
        console.log("첫클릭");
        isClickDupl=true;
        return false;
     }
}
//------------------------------------------

//-----------------새 댓글 작성
function addComment() {		
	if(duplClickCheck()) return;  //광클방지.
	
	var content = $("#review_comment_content").val();
	
	if(content.length==0){
		alert('댓글 내용을 입력해주세요!');
		return;
	}
	
	  $.ajax({
		  url:'CommentWrite.cm',
		  data:{
			  "review_comment_num" : $("#cmt_num").val(),
			  "review_comment_member_id":	$("#sessionId").val(),
			  "nowPage": $("#nowPage").val(),
			  "review_comment_member_name": $("#sessionName").val(),
			  "review_comment_review_num": $("#cmt_re_num").val() , 
			  "review_comment_content": content
		  },
		  success: function (result) {
		        if (result == 'false') {
		            alert('댓글 작성 실패!');		
		        }
		        else {
		        	$("#review_comment_content").val("");
		        	console.log('댓글 등록완료!');
		        	getCommentList();
//		        	$('.comment-form-wrap').get(0).scrollIntoView(true);		//스크롤 위치 이동 함수(선택된 요소를 화면 가운데 위치하게 하는 방법 찾기)
		        	
		        }
		    }
	  });
}

//-------------------------댓글 삭제
function deleteComment(cnum) {
	var delConfirm = confirm('정말 삭제하시겠습니까?');
	
	 if (delConfirm) {
	      var cmcode = $('#commentNum').attr('name'); // ?
	      $.ajax({
	    	  url:'CommentDeletePro.cm',
	    	  data: {
	    		  "review_comment_num": cnum,
	    		  "review_num":$("#cmt_re_num").val(),
	    		  "review_comment_member_name":$("#re_cmt_m_name").val(),
	    		  "review_comment_member_id": $("#re_cmt_m_id").val(),
	    		  "review_comment_review_num" : $("#review_comment_review_num").val(),
	    		  "page":$("#nowPage").val()
	    	  },
	    	  success: function (result) {
	    		  if(result == 'accessDenied'){
	    			  alert('작성자만 삭제할 수 있습니다!')
	    		  }else if (result == 'false') {
	    			  alert('삭제 실패!');
	    		  } else {
	    			  console.log('삭제 성공!');
	    			  getCommentList();
	    		  }
	    	  }
	      });
	   }else {
	   }
}
	
//---------------------댓글 리스트 불러오기
function getCommentList() {
	$.ajax({
		url:'CommentListAjax.cm',
		data:{
			"review_num" : $("#review_num").val(),	// article.get으로 불러오기 위한 값
			"sessionId"  : $("#sessionId").val(),
			"sessionName" : $('#sessionName').val()
		},
		success: function(result){
			if(result!=null){
				$('#forInsert').empty;
				$('#forInsert').html(result);
			}
		}
	});
}

// 댓글 수정폼 열기
function updateComment(cnum,content){
	
	var htmls="";
	htmls+='<textarea name="editContent" id="editContent" rows="5" style="width:100%;height:100;border:1;overflow:visible;text-overflow:ellipsis;">'+content+'</textarea>';
	htmls+='<a href="javascript:void(0)" onClick="updateCommentPro('+cnum+')"><i class="far fa-window-close"></i>저장<a>&emsp;';
	htmls+='<a href="javascript:void(0)" onClick="getCommentList()"><i class="fas fa-check-circle"></i>취소<a>';

	$('#cb'+cnum).replaceWith(htmls)
}

// 대댓글 작성폼 열기
function replyComment(cnum){
	
	var htmls2="";
	htmls2+='<textarea name="replyComment" id="replyComment" rows="5" style="width:100%;height:100;border:1;overflow:visible;text-overflow:ellipsis;"></textarea>';
	htmls2+='<a href="javascript:void(0)" onClick="replyCommentPro('+cnum+')"><i class="far fa-window-close"></i>저장<a>&emsp;';
	htmls2+='<a href="javascript:void(0)" onClick="getCommentList()"><i class="fas fa-check-circle"></i>취소<a>';
	
	$('#forReply'+cnum).append(htmls2)
}

//댓글 수정하기
function updateCommentPro(cnum){
	if(duplClickCheck()) return;  //광클방지(3초)

	var editContent = $('#editContent').val();
	if(editContent.length==0){
		alert('댓글 내용을 공백으로 수정할 수는 없습니다.');
		return;
	}
	$.ajax({
		url:'CommentModifyPro.cm',
		data: {
			"review_comment_num" : cnum,
			"review_comment_content" : editContent,
			"review_num" : $("#review_comment_review_num").val(),
			"page" : $("#nowPage").val()
		},
		success: function (result) {
			if (result == 'false') {
				alert('댓글 수정 실패!');
			} else {
				console.log('댓글 수정 성공!');
				getCommentList();
		}
	}
});
	
}

// 대댓글 작성하기

function replyCommentPro(cnum){
	if(duplClickCheck()) return;  //광클방지(3초)
	
	var replyComment = $('#replyComment').val();
	
	if(replyComment.length==0){
		alert('대댓글 내용은 공백으로 작성할 수 없습니다.');
		return;
	}
	
	$.ajax({
		url:'CommentReplyPro.cm',
		data: {
			"review_num" : $("#review_comment_review_num").val(),
			"review_comment_num" : cnum,
			"review_comment_member_id" : $("#review_comment_member_id").val(),
			"review_comment_member_name" : $("#review_comment_member_name").val(),
			"review_comment_review_num" : $("#review_comment_review_num").val(),
			"review_comment_content" : replyComment,
			"review_comment_ref" : cnum+1,
			"review_comment_lev" : $("#review_comment_lev").val(),
			"review_comment_seq" : $("#review_comment_seq").val()
		},
		success: function (result) {
			
			if (result == 'false') {
				alert('대댓글 작성 실패!');
			} else {
				console.log('대댓글 작성 성공!');
				getCommentList();
		}
	}
});
	
}
