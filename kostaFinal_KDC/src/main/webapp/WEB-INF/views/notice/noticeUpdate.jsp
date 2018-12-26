<<<<<<< HEAD
<%@ page info="게시판 수정하기" contentType="text/html;charset=UTF-8" %>
<HTML>
<HEAD>
<link rel="stylesheet" href="css/style.css">

</HEAD>

<BODY>

<form name=requestForm method=post  id="requestForm" action="${pageContext.request.contextPath}/notice/update?noticeBoardPk=${requestScope.noticeBoardPk}&${_csrf.parameterName}=${_csrf.token}"  enctype="multipart/form-data">

  
	<table align="center" cellpadding="5" cellspacing="1" width="600" border="1">
    <tr>
        <td width="1220" height="20" colspan="2" bgcolor="#00cc00">
            <p align="center"><font color="white" size="3"><b> 게시물 수정하기</b></font></p>
        </td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">제목</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="noticeBoardTitle" size="30"  value="${NoticeBoardDTO.noticeBoardTitle}"></span></b></td>
    </tr>

    <tr>
        <td width="150" height="20" >
            <p align="right"><b><span style="font-size:9pt;">내 용</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
		<textarea name="noticeBoardContents" cols="50" rows="10">${NoticeBoardDTO.noticeBoardContents}</textarea></span></b></td>
    </tr>
  
  <tr>
        <td width="150" height="20" >
            <p align="right"><b><span style="font-size:9pt;">첨부파일</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
          <input type="file" name="file" maxlength="60" size="40"> 
    <textarea name="noticeBoardAttachment" cols="50" rows="10">${NoticeBoardDTO.noticeBoardAttachment}</textarea></span></b></td>
    </tr>
 
    <tr>
        <td width="450" height="20" colspan="2" align="center"><b><span style="font-size:9pt;">
		<input type="submit" value="수정하기"> </span></b></td>
    </tr>
</table>
</form>
<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/notice/list">리스트로 돌아가기</a>&gt;</span></div>
</BODY>


</HTML>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

  <head>
    <meta charset="utf-8">
    <title></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/board.css" />
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" /></noscript>

  </head>

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

<script>
const jq = jQuery.noConflict();

jq(function(){

  /* 에디터 객체 생성 */
  var editor = new tui.Editor({
    el: document.querySelector('#editSection'),
    initialEditType: 'markdown',
    previewStyle: 'vertical',
    height: '400px',
  });
  
  if(location.href.match(/updateForm/)){
    //에디터에 기존 설명내용 값 세팅
    var original = jq('#original-description').val();
    editor.setValue(original);
    
  }
  
  /* 에디터 폼 submit control */
  jq('#editor-submit').on('click',function(){
    var content = editor.getValue();
    console.log(content)
    var input = jq('<input>').attr('type','hidden').attr('name','noticeBoardContents').val(content);
    jq('#editor-form').append(jq(input));
    
    jq('#editor-form').submit();
    
  }); 
});
</script>

<body>


<form name="update" method=post id="editor-form" action="${pageContext.request.contextPath}/notice/update?noticeBoardPk=${requestScope.noticeBoardPk}&${_csrf.parameterName}=${_csrf.token}"  enctype="multipart/form-data">
<sec:authorize access="isAuthenticated()">
  <sec:authentication var="member" property="principal" />
  <input type="hidden" name="memberId" value="${member.memberId}">
</sec:authorize>
<table>

       <thead>
          <tr class="titel-color">
            <td colspan="4">글제목</td>
            <td>글쓴이</td>
            <td>등록날짜</td>
            <td>조회수</td>
            <td>첨부파일</td>
          </tr>
        </thead>

    <tr>
      <td colspan="4">
      <span><input type=text name="noticeBoardTitle" value="${NoticeBoardDTO.noticeBoardTitle}"></span>
      </td>
      <td>
      <span>${NoticeBoardDTO.noticeBoardWriterId}</span>
      </td>
      <td>
      <span>${NoticeBoardDTO.noticeBoardDate}</span>
      </td>

      <td>
      <span>${replyBoardDTO.replyBoardViews}</span>
      </td>
      <td><b><span style="font-size:9pt;">
        <input type="file" name="file" maxlength="60" size="40"> 
        <small>${NoticeBoardDTO.noticeBoardAttachment}</small></span></b>
      </td>
    </tr>
    <tr>
      <td class="tech-content" colspan="8">
        <div id="editSection"></div>
        <input type="hidden" id="original-description" value="${NoticeBoardDTO.noticeBoardContents}"/>
      </td>
    </tr>
    

    <tr>
      <td colspan="8" height="20" colspan="4" align="center" valign="middle">
      <!-- 수정시 필요한 데이터들을 hidden으로 숨겨놓고 폼 데이터로 보내준다. -->
        <!-- <input type=hidden name="password" value="" id="password"> -->
        <input type=submit value="수정하기" id="editor-submit">
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

</body>
>>>>>>> MergBranch
