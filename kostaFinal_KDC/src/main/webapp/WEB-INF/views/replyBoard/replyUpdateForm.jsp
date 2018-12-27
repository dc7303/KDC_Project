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

<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main2.js"></script>
<script type="text/javascript">
const jq = jQuery.noConflict();
jq(function() {
  //viewer 세팅
  var contents = jq('#detail-description').val();
  var editor = tui.Editor.factory({
    el: document.querySelector('#viewer-section'),
    viewer: true,
    height: '500px',
    initialValue: contents
    });
});
</script>
</head>

<body>
<table>

       <thead>
          <tr class="titel-color">
            <td colspan="6">글제목</td>
            <td>글쓴이</td>
            <td>등록날짜</td>
            <td>좋아요</td>
            <td>조회수</td>
          </tr>
        </thead>

<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
    <tr>
      <td colspan="6">
      <span>${replyBoardDTO.replyBoardTitle}</span>
      </td>
      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyBoardDate}</span>
      </td>
      <td>
      <span>${replyBoardDTO.likeNum}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyBoardViews}</span>
      </td>
    </tr>
    <tr>
      <td class="tech-content" colspan="10" style=" text-align: left;">
        <div id = "viewer-section"></div>
        <input id ="detail-description" type="hidden" value="${replyBoardDTO.replyBoardContents}">
      </td>
    </tr>
    <tr>
   
      <td colspan="10">
      <span>${replyBoardDTO.hashTag.hashTagName}</span>
      </td>
    </tr>

</c:when>
</c:choose>
</c:forEach>

    <tr class="titel-color">
    <td>멘션</td>
    <td colspan="7">댓글내용</td>
    <td>댓글작성자</td>
    <td>수정</td>
    </tr>

<!-- 댓글수정하기 멘션수정 시작 -->
<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardPk==replyBoardReplyPk}">
<tr>
<form id="replyUpdate" method="post" action="${pageContext.request.contextPath}/reply/replyUpdate">
  <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
  <sec:authorize access="isAuthenticated()">
    <sec:authentication var="member" property="principal" />
    <input type="hidden" name="memberId" value="${member.memberId}">
  </sec:authorize>
  <!-- 여기여기여기 -->
      <td><div id="mentionButton"></div>
          <input type="hidden" id="mentionNickName" name="mentionNickName"/>
          <c:choose>
          <c:when test="${empty replyBoardDTO.mentionNickName}">
          <input type="text" placeholder="@닉네임, 형태로입력" name="mentionInput" autocomplete="off">
          </c:when>
          <c:otherwise>
          <input type="text" value="@${replyBoardDTO.mentionNickName}" placeholder="@닉네임, 형태로입력" name="mentionInput" autocomplete="off">
          </c:otherwise>
          </c:choose>

          <div id="suggest"></div>
      </td>
  <!-- 여기여기여기 -->      
      
      <td colspan="7">
      <span><input type="text" name="replyBoardContents" value="${replyBoardDTO.replyBoardContents}"></span>
      </td>      


      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      
      <td> <!-- 여기에다가 수정된 댓글내용 저장되도록 하면됨 -->
        <input type="hidden" name="replyBoardPk" value="${requestScope.replyBoardPk}"/>
        <input type="hidden" name="classification" value="${requestScope.classification}"/>
        <input type="hidden" name="replyBoardReplyNo" value="${replyBoardDTO.replyBoardPk}"/>
        <input type="submit" value="저장">
      </td>
</form>
</tr>
</c:when>
</c:choose>
</c:forEach>
<!-- 댓글수정하기 멘션수정 끝 -->

</table>

<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?classification=${requestScope.classification}">리스트로 돌아가기</a>&gt;</span></div>

<input type="hidden" name="csrfName" value="${_csrf.headerName}"/>
<input type="hidden" name="csrfToken" value="${_csrf.token}"/>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}"/>

</body>