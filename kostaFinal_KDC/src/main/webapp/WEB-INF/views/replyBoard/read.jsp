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

<SCRIPT language=javascript>
$(function(){
  
      $("input[value=수정하기]").click(function(){
         
         $("#requestForm").attr("action", "${pageContext.request.contextPath}/reply/updateForm");
         $("#requestForm").submit();
      })
      
      
      $("input[value=삭제하기]").click(function(){
         var yesOrNo = confirm("정말 삭제 하시겠습니까?");
         if(yesOrNo){
            $("#requestForm").attr("action", "${pageContext.request.contextPath}/reply/delete");
            $("#requestForm").submit();
         }
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
      
      <!-- 여기부터 -->
      
      <td>
      <c:choose>
      <c:when test="${replyBoardDTO.updown.isUp==true}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/black_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_down.png"></div>
      </c:when>
      <c:when test="${replyBoardDTO.updown.isUp==false}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/black_thumbs_down.png"></div>
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
      <td class="tech-content" colspan="10">
      <span>${replyBoardDTO.replyBoardContents}</span>
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
    <td>댓글번호</td>
    <td>멘션</td>
    <td>댓글내용</td>
    <td>댓글작성일</td>
    <td colspan="2">좋아요</td>
    <td>댓글작성자</td>
    <td>수정</td>
    <td>삭제</td>
    <td>신고</td>
    </tr>
        
<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo>0}">
    <tr>
      <td>
      <span>${state.count-1}</span>
      </td>
      <td>
      <span id="mentionNickName">${replyBoardDTO.mentionNickName}</span>
      </td>
      <td>
      <span id="replyBoardContents">${replyBoardDTO.replyBoardContents}</span>
      </td>      
      <td>
      <span>${replyBoardDTO.replyBoardDate}</span>
      </td>

      <!-- 여기부터 -->
      
      <td>
      <c:choose>
      <c:when test="${replyBoardDTO.updown.isUp==true}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/black_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_down.png"></div>
      </c:when>
      <c:when test="${replyBoardDTO.updown.isUp==false}">
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/black_thumbs_down.png"></div>
      </c:when>      
      <c:otherwise>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_up.png"></div><br/>
      <div><img src="${pageContext.request.contextPath}/resources/assets/img/white_thumbs_down.png"></div>     
      </c:otherwise>
      </c:choose>
      </td>
      
      <!-- 여기까지 -->

      <td>
      <span>${replyBoardDTO.likeNum}</span>
      </td>
      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      
      <td><span>             
        <form id="replyUpdateForm" method="post" action="${pageContext.request.contextPath}/reply/replyUpdateForm">
          <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
          <input type="hidden" name="state" value="true"/>
          <input type="hidden" name="replyBoardPk" value="${replyBoardDTO.replyBoardReplyNo}"/>
          <input type="hidden" name="classification" value="${requestScope.classification}"/>
          <input type="hidden" name="replyBoardReplyPk" value="${replyBoardDTO.replyBoardPk}"/>      
          <input type="submit" value="수정">
        </form>
      </span>
      </td>
      
        <td><span>
          <form id="replyDeleteForm" method="post" action="${pageContext.request.contextPath}/reply/replyDelete">
            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
            <input type="hidden" name="state" value="true"/>
            <input type="hidden" name="replyBoardPk" value="${requestScope.replyBoardPk}"/>
            <input type="hidden" name="classification" value="${requestScope.classification}">
            <input type="hidden" name="replyBoardReplyPk" value="${replyBoardDTO.replyBoardPk}">            
            <input type="submit" value="삭제">           
          </form>
        </span>
        </td>
      
      <td>
        <span><input type="submit" value="신고"></span>
      </td>
    </tr>
    
</c:when>
</c:choose>
</c:forEach>

<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
   <form name="replyWriteForm" method="post" action="${pageContext.request.contextPath}/reply/replyInsert?classification=${requestScope.classification}&replyBoardPk=${requestScope.replyBoardPk}">
   <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
     
      <tr>
        <td>${replyBoardDTO.replyNum+1}</td>
        <td><input type="text" value="@" name="replyBoardMention" style="width:150px;"></td>
        <td colspan="6"><input type="text" placeholder="댓글내용입력" name="replyBoardContents"></td>
        <td>본인닉네임표출</td>
        <td><input type=submit value="등록" ></td>
      </tr>
      
    </form>
</c:when>
</c:choose>
</c:forEach>

    <tr>
      <td colspan="10" align="center" valign="middle">
      <!-- 수정시 필요한 데이터들을 hidden으로 숨겨놓고 폼 데이터로 보내준다. -->
      <form name="requestForm" method=post  id="requestForm">
      <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      
<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">
<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
      <input type=hidden name="replyBoardPk" value="${replyBoardDTO.replyBoardPk}">
</c:when>
</c:choose>
</c:forEach>

        <input type=hidden name="state" value="true">
        <input type=hidden name="classification" value="${requestScope.classification}">
        <input type=button value="수정하기" >
        <input type=button value="삭제하기" >
      </form>
      </td>
    </tr>
</table>


<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?classification=${requestScope.classification}">리스트로 돌아가기</a>&gt;</span></div>


</body>