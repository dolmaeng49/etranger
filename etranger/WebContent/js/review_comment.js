//함수 호출목록

//댓글등록
$('#comment_add_btn').click(function () {	//댓글쓰기 버튼 클릭 시 addComment() 호출
    	    addComment();    						
    	});

//삭제
$('#comment_del_btn').click(function () {	//삭제 버튼 클릭 시 delComment() 호출
	    delComment();    						
	});



//----------------------------------

function addComment() {					

	
	  $.ajax({
		  url:'CommentWrite.cm',
		  type:'POST',
		  data:{"review_comment_member_id":	$("#sessionId").val(),
			  "nowPage": $("#nowPage").val(),
			  "review_comment_member_name":"tester",
			  "review_comment_review_num": $("#cmt_re_num").val() , // 외부에서 불러오기 위해 hidden 값으로 저장해놓고 불러와야됨
			  "review_comment_content": $("#review_comment_content").val()
		  },
		  success: function (sdata) {
		        if (sdata == 'false') {
		            alert('글 작성 실패!');		//나중에 alert 지울 것.
		        }
		        else {
		        	alert('댓글 등록완료!')
		        								//getCommentList() 불러오기
		        }
		    }
	  });
}




//---댓글 삭제--


//function delComment() {					
//	  
//	  $.ajax({
//		  url:'CommentDeletePro.cm',
//		  type:'POST',
//		  data:{"review_num": <%=article.getReview_num()%>,
//			  "review_comment_num":<%=commentList.get(i).getReview_comment_num()%>,
//			  "review_comment_member_name":"tester",
//			  "review_comment_member_id": <%=commentList.get(i).getReview_comment_member_id() %>,
//			  "page":<%=nowPage%>
//		  },
//		  success: function (sdata) {
//		        if (sdata == 'false') {
//		            alert('댓글 삭제 실패!');		//나중에 alert 지울 것.
//		        }
//		        else {
//		        	alert('댓글 삭제 성공!')
//		        								//getCommentList() 불러오기
//		        }
//		    }
//	  });
//}





