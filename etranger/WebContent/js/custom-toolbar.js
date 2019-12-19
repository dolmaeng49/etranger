//히든버튼 생성
 	  var hiddenButton = function (context) {
 		  var ui = $.summernote.ui;
 		  var button = ui.button({
 		    contents: '<i class="fa fa-cat"/>',
 		    tooltip: 'guess what',
 		    click: function () {
 		    	alert("etranger==\"TheBestTeamEver\"");
 		    }
 		  });
 		  return button.render();   // return button as jquery object
 		}
 	  
//========================
//버튼추가시, .summernote 안에 들어갈 속성
//buttons: {
//        hidden : hiddenButton
//      		} 	
//--------------
/* ,
 ['mybutton', ['hidden']] */
//=========================

$(function() {

	$('#summernote').summernote(
			{
				height : 450,
				placeholder : '당신의 소중한 추억을 함께 나누세요!',
				lang : 'ko-KR',
				focus : true,
				fontNames : [ '굴림', '바탕', '돋움', '궁서', 'Arial', 'Verdana',
						'Courier New', 'Times New Roman' ],
				toolbar : [ [ 'style', [ 'style' ] ],
						[ 'font', [ 'bold', 'underline', 'clear' ] ],
						[ 'fontname', [ 'fontname' ] ],
						[ 'color', [ 'color' ] ],
						[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
						[ 'table', [ 'table' ] ],
						[ 'insert', [ 'link', 'picture', 'video' ] ],
						[ 'view', [ 'codeview', 'help' ] ], 
						['mybutton', ['hidden'] ] ],
						
				buttons : {
					        hidden : hiddenButton
					    },		
				callbacks : {
					onImageUpload : function(files, editor, welEditable) {
						for (var i = files.length - 1; i >= 0; i--) {
							sendFile(files[i], this);
						}
					}
				}
			});
});

function sendFile(file, el) {
	var data = new FormData();
	data.append('file', file);
	$.ajax({
		data : data,
		type : "POST",
		url : 'ImageCallback.rv',
		cache : false,
		contentType : false,
		enctype : 'multipart/form-data',
		processData : false,
		success : function(data) {
			$(el).summernote('editor.insertImage', data.url);
			$('#review_image').val(data.fileName);
		}
	});
};

//관리자페이지 섬머노트
$(function() {
	$('#summernoteManager').summernote(
			{
				height : 450,
				width : 650,
				placeholder : '내용입력',
				lang : 'ko-KR',
				focus : true,
				fontNames : [ '굴림', '바탕', '돋움', '궁서', 'Arial', 'Verdana', 'Courier New', 'Times New Roman' ],
				toolbar : [ [ 'style', [ 'style' ] ],
						[ 'font', [ 'bold', 'underline', 'clear' ] ],
						[ 'fontname', [ 'fontname' ] ],
						[ 'color', [ 'color' ] ],
						[ 'para', [ 'ul', 'ol', 'paragraph' ] ],
						[ 'table', [ 'table' ] ],
						[ 'insert', [ 'link', 'picture', 'video' ] ],
						[ 'view', [ 'codeview', 'help' ] ] ],
				callbacks : {
					onImageUpload : function(files, editor, welEditable) {
						var category_image = "";
						for (var i = files.length - 1; i >= 0; i--) {
							if(i < files.legth - 1) {
								category_image += '*';
							}
							sendFileManager(files[i], this);
							category_image += 1;
						}
						$('#category_image').val(category_image);
					}
				}
			});
});

//관리자 페이지 이미지 전송
var sendFileManager = function (file, el) {
	var data = new FormData();
	var fileName = null;
  	data.append('file', file);
  	$.ajax({
    	data: data,
    	type: "POST",
    	url: 'ManagerImageCallback.ma',
    	cache: false,
    	contentType: false,
    	enctype: 'multipart/form-data',
    	processData: false,
    	success: function(data) {
	      	$(el).summernote('editor.insertImage', data.url);
    	}
  	});
  	return fileName;
};
