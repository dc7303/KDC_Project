<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/board.css" />
    
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" /></noscript>


  </head>

<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/main.js"></script>

<body>


<form name="update" method=post action="${pageContext.request.contextPath}/reply/replyBoardUpdate?classification=${requestScope.classification}&replyBoardPk=${requestScope.replyBoardPk}">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>

<table>

       <thead>
          <tr class="titel-color">
            <td colspan="4">글제목</td>
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
      <td colspan="4">
      <span><input type=text name="replyBoardTitle" value="${replyBoardDTO.replyBoardTitle}"></span>
      </td>
      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyBoardDate}</span>
      </td>

      <!-- 여기부터 -->
      
      <td>
      <c:choose>
      <c:when test="${replyBoardDTO.updown.isUp==true}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/black_thumbs_down.png"></div>
      </c:when>
      <c:when test="${replyBoardDTO.updown.isUp==false}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/black_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_down.png"></div>
      </c:when>      
      <c:otherwise>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_down.png"></div>     
      </c:otherwise>
      </c:choose>
      </td>
      
      <!-- 여기까지 -->

      <td>
      <span>${replyBoardDTO.replyBoardViews}</span>
      </td>
    </tr>
    <tr>
      <td class="tech-content" colspan="8">
      <input type=text name="replyBoardContents" value="${replyBoardDTO.replyBoardContents}" style="height:100%;">
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
        <input type="text" name="hashTagInput" autocomplete="off" value="${replyBoardDTO.hashTag.hashTagName}" class="textbox"/>
        <span id="suggest" style="float:left;">
        <!-- 제시어 단어 출력부분 --> 
        </span>
        </div>
      </td>
    </tr>
    <!-- 여기까지 -->
   

</c:when>
</c:choose>
</c:forEach>

    <tr>
      <td colspan="8" height="20" colspan="4" align="center" valign="middle">
      <!-- 수정시 필요한 데이터들을 hidden으로 숨겨놓고 폼 데이터로 보내준다. -->
        <!-- <input type=hidden name="password" value="" id="password"> -->
        <input type=submit value="수정하기"/>
        <input type=reset value="다시쓰기"/>
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