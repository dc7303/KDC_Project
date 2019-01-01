<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
    <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"> -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/board.css" /> 


<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>
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
  <link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/tui-editor-css/editor.css">

 
<script language=javascript>
const jq = jQuery.noConflict();

jq(function(){
  /* 에디터 객체 생성 */
  
  var editor = new tui.Editor({
    el: document.querySelector('#editSection'),
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    height: '400px',
  });
  
  /* 에디터 폼 submit control */
  jq('#editor-submit').on('click',function(){
    var content = editor.getValue();
    
    if(jq('input[name=replyBoardTitle]').val().trim()===''){
      alert('제목을 입력해주세요.');
      jq('input[name=replyBoardTitle]').focus();
      return;
    }
    else if(content.trim()===''){
      alert('내용을 입력해주세요');
      return;
    }
    
    var input = jq('<input>').attr('type','hidden').attr('name','replyBoardContents').val(content);
    jq('#editor-form').append($(input));
    
    jq('#editor-form').submit();
    
  }); 
});

</script>

</head>
<body>


<form name="writeForm" method="post"  id="editor-form" action="${pageContext.request.contextPath}/reply/insert?classification=${requestScope.classification}">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
<sec:authorize access="isAuthenticated()">
  <sec:authentication var="member" property="principal" />
  <input type="hidden" name="memberId" value="${member.memberId}">
</sec:authorize>
<table>
       <thead>
          <tr class="titel-color">
            <td colspan="7">글제목</td>
            <td>글쓴이</td>
          </tr>
        </thead>
    <tr>
      <td colspan="7">
      <span><input type=text name="replyBoardTitle" placeholder="게시글 제목 작성"></span>
      </td>
      <td>
      <span>
        <sec:authorize access="isAuthenticated()">
          <sec:authentication var="member" property="principal" />
          ${member.memberNickName}
        </sec:authorize>
      </span>
      </td>
      
    </tr>
    
    <tr>
      <td class="tech-content" colspan="8">
        <div id="editSection"></div>
      </td>
    </tr>
    
    <!-- 여기부터 -->
    <tr>
      <td colspan="8">
        <div class="form-group">
        <span id="span">
        <!-- 이벤트 발생시 태그가 여기에 추가 -->
        </span>
        <input type="hidden" id="hashTagName" name="hashTagName"/>
        <input type="text" name="hashTagInput" autocomplete="off" placeholder="#해시태그, 형태로 입력" class="textbox"/>
        <span id="suggest" style="float:left;">
        <!-- 제시어 단어 출력부분 --> 
        </span>
        </div>
      </td>
    </tr>
    <!-- 여기까지 -->

    <tr>
      <td colspan="8" height="20" colspan="4" align="center" valign="middle">

      <input type=button value="글쓰기" id="editor-submit">
      <input type=reset value="다시쓰기">

      </td>
    </tr>
</table>
</form>


<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?classification=${requestScope.classification}">리스트로 돌아가기</a>&gt;</span></div>
<input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}"/>
</BODY>
</HTML>