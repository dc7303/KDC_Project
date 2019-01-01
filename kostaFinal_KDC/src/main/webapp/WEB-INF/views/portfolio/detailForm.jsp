<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/jquery/dist/jquery.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/tui-code-snippet/dist/tui-code-snippet.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/markdown-it/dist/markdown-it.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/to-mark/dist/to-mark.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/codemirror/lib/codemirror.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/highlightjs/highlight.pack.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/squire-rte/build/squire-raw.js"></script>
  <script src="${pageContext.request.contextPath }/resources/lib/tui-editor/tui-editor/dist/tui-editor-Editor.min.js"></script>
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/tui-editor/codemirror/lib/codemirror.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/tui-editor/highlightjs/styles/github.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/tui-editor/tui-editor/dist/tui-editor.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/tui-editor/tui-editor/dist/tui-editor-contents.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/portfolio.css" />
<title>Insert title here</title>
<!-- 해쉬태그 js -->
<script src="${pageContext.request.contextPath}/resources/js/portfolio-hashtag.js"></script>
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
    
  	
  	//작성자 이름 수정불가로 설정(시큐리티에서 가져올경우 삭제)
  	jq('input[name=portFolioDetailMemberId]').attr('readonly',true); 
  	
  	//에디터에 기존 설명내용 값 세팅
  	var original = jq('#original-description').val();
  	editor.setValue(original);
  	
  	//기존이미지 노출
  	jq('#original-img').css('display','block');
  	
  	//PK값 hidden에 숨겨서 보내줄 name설정
  	jq('#original-pk').attr('name','portFolioDetailPk')
  	
  	
  	jq('input[name=hashTagName]').val();
  }
  
  jq('h1').css('visibility','visible');
  
  
  
  
  /* 에디터 폼 submit control */
  jq('#editor-submit').on('click',function(){
    var content = editor.getValue();
  	//form 유효성검사(제목, 상세)
  	if(jq('input[name=portfolioDetailProjectName]').val().trim()===''){
  	  alert('제목은 필수입력사항 입니다.');
  	  jq('input[name=portfolioDetailProjectName]').focus();
  	  return;
  	}else if(content.trim()===''){
  	  alert('프로젝트 상세설명은 필수입력사항 입니다.')
  	  return;
  	}
    
    var input = jq('<input>').attr('type','hidden')
    	.attr('name','portfolioDetailDescription').val(content);
    jq('#editor-form').append(jq(input));
    
    jq('#editor-form').submit();
    
  });
  
  
  
  
});

</script>
</head>
<body>
<center>
<sec:authentication var="member" property="principal" />
  <h2 class="notice-title">상세 포트폴리오 작성</h2>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data" id="editor-form">
   
   <hr class="hr-border">
   
   <label class="label-portfolio">프로젝트명</label></p></br>
  <input type="text" name="portfolioDetailProjectName" value="${detail.portfolioDetailProjectName}"/></p></br>
  <div id="original-img" style="display:none;">현재 이미지 : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}"></div>
  <p>*프로젝트명을 입력하세요<br>
     ex) 커뮤니티 사이트 프로젝트</p>
  
  <hr class="hr-border">
  
  <label class="label-portfolio">해쉬태그</label></p></br>
  <span id="span">
  <!-- 이벤트 발생시 태그가 여기에 추가 -->
  </span>
  <input type="hidden" id="hashTagName" name="hashTagName"/>

  
  
  <c:choose>
    <c:when test="${empty detail.portfolioDetailHashTagList}">
      <input type="text" name="hashTagInput"/>
    </c:when>
    <c:otherwise>
      <input type="text" id = "hashTagInput" name="hashTagInput" value="<c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag" >${hashTag.hashTagName}, </c:forEach>"/>
    </c:otherwise>
  </c:choose>
  <span id="suggest">
  <!-- 제시어 단어 출력부분 --> 
  </span></p></br>
  <p>#OOO 내용입력후 스페이스 </p>
  
  <!-- 해쉬태그 끝-->
  <hr class="hr-border">
  
  <label class="label-portfolio">프로젝트 이미지</label>
  <input type="file" name="DeltailProjectImage" /></p></br>
  <p>*프로젝트 이미지 파일을 선택해주세요. </p>
  <hr class="hr-border">
  
  
  <label class="label-portfolio">프로젝트 설명</label>
  <div id="editSection"></div>
  <input type="hidden" id="original-pk"name="" value="${detail.portFolioDetailPk}">
  <input type="hidden" id="original-description" value="${detail.portfolioDetailDescription}"/>
  <br/><br/><br/>
  <input type="button" value="작성완료" id="editor-submit" />
  <br/><br/><br/>
  
  
</form>
<input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}"/>

</center>
</body>
</html>
