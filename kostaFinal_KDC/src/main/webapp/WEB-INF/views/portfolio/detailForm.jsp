<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  /* 에디터 객체 생성 */
  var editor = new tui.Editor({
    el: document.querySelector('#editSection'),
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    height: '300px',
  });
  
  /* update인 경우 form의 action값 수정 */
  if(location.href.match(/updateDetailForm/)){
    //제목수정
    jq('h1').text('상세 업데이트 폼');
    
    var url = '${pageContext.request.contextPath }/portfolio/updateDetail?${_csrf.parameterName}=${_csrf.token}';
    jq('#editor-form').attr('action',url); 
    
    //submit버튼명 수정
    jq('#editor-submit').attr('value','수정완료');
    
     //해당 게시물 삭제버튼 생성
    var deleteBtn = jq('<input>').attr('type','button').attr('value','삭제하기').on('click',function(){
  	  location.href = '${pageContext.request.contextPath}/portfolio/deleteDetail/'+'${detail.portFolioDetailPk}';
  	});
  	jq('#editor-form').append(jq(deleteBtn));
  	
  	//작성자 이름 수정불가로 설정(시큐리티에서 가져올경우 삭제)
  	jq('input[name=portFolioDetailMemberId]').attr('readonly',true); 
  	
  	//에디터에 기존 설명내용 값 세팅
  	var original = jq('#original-description').val();
  	editor.setValue(original);
  	
  	//기존이미지 노출
  	jq('#original-img').css('display','block');
  	
  	//PK값 hidden에 숨겨서 보내줄 name설정
  	jq('#original-pk').attr('name','portFolioDetailPk')
  	
  	//HASH태그값 가져오기
  	var hashTags = '${detail.portfolioDetailHashTagList}'
  	console.log('hashTags = '+hashTags);
  	 for(let hashTag of hashTags){
  	  console.log(hashTag.hashTagName);
  	} 
  	
  	jq('input[name=hashTagName]').val();
  }
  
  jq('h1').css('visibility','visible');
  
  
  
  
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
<h1 style="visibility:hidden;">상세 포트폴리오 작성 폼 입니다</h1>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data" id="editor-form">
  작성자 아이디 : <input type="text" name="portFolioDetailMemberId" value="${detail.portFolioDetailMemberId}"></p></br>
  
  프로젝트명 : <input type="text" name="portfolioDetailProjectName" value="${detail.portfolioDetailProjectName}"/></p></br>
 <div id="original-img" style="display:none;">현재 이미지 : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}"></div>
 </br> 
 
 <c:choose>
    <c:when test="${empty detail.portfolioDetailHashTagList}">
      해쉬태그 : <input type="text" name="hashTagName"/></p></br>
    </c:when>
    <c:otherwise>
    해쉬태그 : <input type="text" name="hashTagName" value="<c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag" >${hashTag.hashTagName}, </c:forEach>"/>
       </p></br>
    </c:otherwise>
  </c:choose>
  
  프로젝트 이미지 <input type="file" name="DeltailProjectImage" /></p></br>
  
  
  <div id="editSection"></div>
  <input type="hidden" id="original-pk"name="" value="${detail.portFolioDetailPk}">
  <input type="hidden" id="original-description" value="${detail.portfolioDetailDescription}"/>
  <input type="button" value="작성완료" id="editor-submit" />
</form>

</body>
</html>