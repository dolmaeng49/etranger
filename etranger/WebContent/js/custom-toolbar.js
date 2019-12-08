  //히든버튼 생성
// 	  var hiddenButton = function (context) {
// 		  var ui = $.summernote.ui;
// 		  var button = ui.button({
// 		    contents: '<i class="fa fa-cat"/>',
// 		    tooltip: 'guess what',
// 		    click: function () {
// 		      // invoke insertText method with 'hello' on editor module.
// 		      context.invoke('editor.insertText', 'etranger==TheBestTeamEver');
// 		    }
// 		  });
// 		  return button.render();   // return button as jquery object
// 		}
//========================
//버튼추가시, .summernote 안에 들어갈 속성
//buttons: {
//        hidden : hiddenButton
//      		} 	
//--------------
/* ,
		  ['mybutton', ['hidden']] */
//=========================


//function sendFile(file, editor) {
//            // 파일 전송을 위한 폼생성
//	 		data = new FormData();
//	 	    data.append("uploadFile", file);
//	 	    $.ajax({ // ajax를 통해 파일 업로드 처리
//	 	        data : data,
//	 	        type : "POST",
//	 	        url : "/summernote_imageUpload.jsp",
//	 	        cache : false,
//	 	        contentType : false,
//	 	        processData : false,
//	 	        success : function(url) { // 처리가 성공할 경우
//                    // 에디터에 이미지 출력
//	 	        	$(editor).summernote('editor.insertImage', url);
//	 	        }
//	 	    });
//	 	}


$(function() {
	
	  $('#summernote').summernote({
	    height: 450,
	    placeholder: '당신의 소중한 추억을 함께 나누세요!',
	    lang: 'ko-KR',
	    focus: true,
	    fontNames: ['굴림',  '바탕', '돋움', '궁서', 'Arial','Verdana','Courier New','Times New Roman'],
	  	toolbar: [
		  ['style', ['style']],
		  ['font', ['bold', 'underline', 'clear']],
		  ['fontname', ['fontname']],
		  ['color', ['color']],
		  ['para', ['ul', 'ol', 'paragraph']],
		  ['table', ['table']],
		  ['insert', ['link', 'picture', 'video']],
		  ['view', ['codeview', 'help']]
		]
//	  ,
//		 callbacks: { 
//			 onImageUpload: function(image) {
//		            uploadImage(image[0]);
//		        }
//		 }
//				onImageUpload: function(files, editor, welEditable) {
//////					 sendFile(files[0], editor, welEditable);	//ver1
//////					sendFile(files[0], this);  //ver2
//		            for (var i = files.length - 1; i >= 0; i--) {	//ver3
//		            	sendFile(files[i], this);
//		            }
//		        }
//		 }
	  });
	});


//



















//============================================

//저장은 안되고 return값은 옴.

//function uploadImage(image) {
//	var data = new FormData();
//	data.append("image", image);
//	$.ajax({
//		url: 'ImageCallback.rv',  
//		cache: false,
//		contentType: false,
//		processData: false,
//		enctype: 'multipart/form-data',
//		data: data,
//		type: "get",
//		success: function(url) {
//			
//			alert(url);
//			var image = $('<img>').attr('src', 'http://' + url.path);
//			alert(image);
//			$('#summernote').summernote("insertNode", image[0]);
//			console.log(url);
//		},
//		error: function(data) {
//			console.log(data);
//			alert(data);
//		}
//	});
//}





//========================================

//function sendFile(file, el) {
//	var data = new FormData();
//  	data.append('file', file);
//  	$.ajax({
//    	data: data,
//    	type: "POST",
//    	url: 'ImageCallback.rv',
//    	cache: false,
//    	contentType: false,
//    	enctype: 'multipart/form-data',
//    	processData: false,
//    	success: function(data) {
//    		alert(data);
//    		console.log(data);
////    		editor.insertImage(welEditable,data.url);  //editor가 defined
////      		$(el).summernote('editor.insertImage', url);	//url이 defined
//      		$('#test').append('<img src="'+url+'" width="300" height="auto"/>') //업로드 확인용
//      		
//    	}
//  	});
//};

//1119 to do 
//================	
//	 $(document).ready(function() {
//	        $('#summernote').summernote({
//	            height: 200,
//	            onImageUpload: function(files, editor, welEditable) {
//	                sendFile(files[0], editor, welEditable);
//	            }
//	        });
//	        function sendFile(file, editor, welEditable) {
//	            data = new FormData();
//	            data.append("file", file);
//	            $.ajax({
//	                data: data,
//	                type: "POST",
//	                url: "Your URL POST (php)",
//	                cache: false,
//	                contentType: false,
//	                processData: false,
//	                success: function(url) {
//	                    editor.insertImage(welEditable, url);
//	                }
//	            });
//	        }
//	    });
//===============
	
//function sendFile(file, editor, welEditable) {
//    data = new FormData();
//    data.append("uploadFile", file);
//    $.ajax({
//        data : data,
//        type : "POST",
//        url : "ReviewWritePro.rv",
//        cache : false,
//        contentType : false,
//        processData : false,
//        success : function(data) {
//        	alert(data);
//        	consol.log(data);
////            editor.insertImage(welEditable, data.url);
//        	$(editor).summernote('insertImage', data.url);
//        	alert("완료!2");
//            consol.log(data);
//            consol.log(url);
//        }
//    });
//}


//=================================================

//function sendFile(file, editor) {
//    // 파일 전송을 위한 폼생성
//		data = new FormData();
//	    data.append("uploadFile", file);
//	    $.ajax({ // ajax를 통해 파일 업로드 처리
//	        data : data,
//	        type : "POST",
//	        url : "ReviewWritePro.rv",
//	        cache : false,
//	        contentType : false,
//	        processData : false,
//	        success : function(data) { // 처리가 성공할 경우
//            // 에디터에 이미지 출력
//	        	$(editor).summernote('editor.insertImage',url);
//	        }
//	    });
//	}





//function sendFile(file, el) {
//	var form_data = new FormData();
//  	form_data.append('file', file);
//  	$.ajax({
//    	data: form_data,
//    	type: "POST",
//    	url: './profileImage.mpf',
//    	cache: false,
//    	contentType: false,
//    	enctype: 'multipart/form-data',
//    	processData: false,
//    	success: function(img_name) {
//      		$(el).summernote('editor.insertImage', img_name);
//    	}
//  	});
//}

//==========================================

//시도해볼것

//callbacks: {
//    onImageUpload: function(files){
//	that = $(this);
//	sendFile(files[0], that);
//  }
//  }
//    function sendFile(file, that){
//        var data = new FormData();
//        data.append('file', file);
//        $.ajax({
//            data: data,
//            type: 'post',
//            url: '/ajax/file',
//            cache: false,
//            contentType: false,
//            processData: false,
//            success: function(url){
//                $(that).summernote('insertImage', location.origin+'/'+url, '')
//            }
//        });
//    }


	

//function uploadImage(image) {
//    var data = new FormData();
//    data.append("image", image);
//    $.ajax({
//        type: "post",
//        cache: false,
//        contentType:false,
//        processData: false,
//        dataType :'jsonp',
//        url: '/cop/bbs/insertSummberNoteFile.do',
//        data: data,
//        success: function(data) {
////이미지 경로를 작성하면 됩니다 ^  ^
//            var image = $('<img>').attr('src', '/cmm/fms/getImage.do?atchFileId='+data[0].atchFileId+'&fileSn=0');
//            $('#nttCn').summernote("insertNode", image[0]);
//        },
//        error: function(data) {
//            alert('error : ' +data);
//        }
//    });
//}

	
	