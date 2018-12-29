<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <meta charset="utf-8">
    <title></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/board.css" />
    
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" /></noscript>
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
    //viewer 세팅
    var contents = jq('#detail-description').val();
  var editor = tui.Editor.factory({
      el: document.querySelector('#viewer-section'),
      viewer: true,
      height: '500px',
      initialValue: contents
  });

      jq("input[value=수정하기]").click(function(){
     
         jq("#requestForm").attr("action", "${pageContext.request.contextPath}/notice/updateForm");
         jq("#requestForm").submit();
      })


      jq("input[value=삭제하기]").click(function(){
         var yesOrNo = confirm("정말 삭제 하시겠습니까?");
         if(yesOrNo){
            jq("#requestForm").attr("action", "${pageContext.request.contextPath}/notice/delete");
            jq("#requestForm").submit();
         }
      });

  });
</script>

</head>

<body>
<table>
       <thead>
          <tr class="titel-color">
            <td colspan="5">글제목</td>
            <td>글쓴이</td> 
            <td>등록날짜</td>
            <td>조회수</td>
            <td>첨부파일</td>
          </tr>
        </thead>

    <tr>
      <td colspan="5">
      <span>${NoticeBoardDTO.noticeBoardTitle}</span>
      </td>
      
      <td>
      <span>${NoticeBoardDTO.noticeBoardWriterId}</span>
      </td>
      
      <td>
      <span>${NoticeBoardDTO.noticeBoardDate}</span>
      </td>

      <td>
      <span>${NoticeBoardDTO.noticeBoardViews}</span>
      </td>

      <td>
        <c:choose>
          <c:when test="${NoticeBoardDTO.noticeBoardAttachment!=null}">
            <a href='${pageContext.request.contextPath}/notice/downLoad?attachment=${NoticeBoardDTO.noticeBoardAttachment}'>
              ${NoticeBoardDTO.noticeBoardAttachment}
            </a>
          </c:when>
          <c:otherwise>
            파일없음
          </c:otherwise>
        </c:choose>
      </td> 
    </tr>

    <tr>
      <td class="tech-content" colspan="10">
        <div id = "viewer-section"></div>
        <input id ="detail-description" type="hidden" value="${NoticeBoardDTO.noticeBoardContents}">
      </td>
    </tr>

    <tr>
      <td colspan="10" align="center" valign="middle">
      <!-- 수정시 필요한 데이터들을 hidden으로 숨겨놓고 폼 데이터로 보내준다. -->
      <form name="requestForm" method=post  id="requestForm">
        <input type="hidden" name="noticeBoardPk" value="${NoticeBoardDTO.noticeBoardPk}"/>
        <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
        <input type=hidden name="classification" value="${requestScope.classification}">
        <input type=button value="수정하기" />
        <input type=button value="삭제하기" />
      </form>
      </td>
    </tr>
</table>

<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/notice/list">리스트로 돌아가기</a>&gt;</span></div>
<input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}"/>

</body>
</html>
