<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<script src="${pageContext.request.contextPath }/resources/lib/bower_components/jquery/dist/jquery.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/tui-code-snippet/dist/tui-code-snippet.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/markdown-it/dist/markdown-it.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/to-mark/dist/to-mark.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/codemirror/lib/codemirror.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/highlightjs/highlight.pack.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/squire-rte/build/squire-raw.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/bower_components/tui-editor/dist/tui-editor-Editor.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/bower_components/codemirror/lib/codemirror.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/bower_components/highlightjs/styles/github.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/bower_components/tui-editor/dist/tui-editor.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/bower_components/tui-editor/dist/tui-editor-contents.css">
<title>Insert title here</title>
<script>
const jq = jQuery.noConflict();

jq(function(){
  /* update인 경우 form의 action값 수정 */
  if(location.href.match(/updateDetailForm/)){
    //제목수정
    jq('h1').text('상세 업데이트 폼');
    
    var url = '${pageContext.request.contextPath }/portfolio/updateDetail/'+
    ${detail.portFolioDetailPk}+'?${_csrf.parameterName}=${_csrf.token}';
    jq('#editor-form').attr('action',url); 
    
    //submit버튼명 수정
    jq('#editor-submit').attr('value','수정완료');
    
    //해당 게시물 삭제버튼 생성
    var deleteBtn = jq('<input>').attr('type','button')
  	.attr('value','삭제하기').on('click',function(){
  	  location.href = '${pageContext.request.contextPath}/portfolio/deleteDetail/'+${detail.portFolioDetailPk};
  	});
  	jq('#editor-form').append(jq(deleteBtn));
  }
  
  /* 에디터 객체 생성 */
  var editor = new tui.Editor({
    el: document.querySelector('#editSection'),
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    height: '300px',
  });
  
  /* 에디터 폼 submit control */
  jq('#editor-submit').on('click',function(){
    var content = editor.getValue();
    
    var input = jq('<input>').attr('type','hidden')
    	.attr('name','portfolioDetailDescription').val(content);
    jq('#editor-form').append(jq(input));
    
    jq('#editor-form').submit();
    
  }); 
  
});

</script>
</head>
<body>
<h1>상세 포트폴리오 작성 폼 입니다</h1>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data" id="editor-form">
  작성자 아이디 : <input type="text" name="portFolioDetailMemberId"/></p></br>
  프로젝트명 : <input type="text" name="portfolioDetailProjectName"/></p></br>
  프로젝트 이미지 <input type="file" name="DeltailProjectImage" /></p></br> 
  해쉬태그 : <input type="text" name="hashTagName"/></p></br>
  <div id="editSection"></div>
  <input type="button" value="작성완료" id="editor-submit" />
</form>

</body>
</html>