<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<HEAD>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

<SCRIPT language=javascript>
$(function(){
      $("input[value=수정하기]").click(function(){
         //document.requestForm.action="${pageContext.request.contextPath}/board/updateForm";
         
         $("#requestForm").attr("action", "${pageContext.request.contextPath}/reply/updateForm");
         $("#requestForm").submit();
      })
      
      
      $("input[value=삭제하기]").click(function(){
         var yesOrNo = confirm("정말 삭제 하시겠습니까?");
         if(yesOrNo){
            $("#requestForm").attr("action", "${pageContext.request.contextPath}/reply/delete");
            $("#requestForm").submit();
         }
      })
      
})
</script>


</HEAD>
<h2>${requestScope.classification}</h2>
<table align="center" border="1" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">

       <thead>
          <tr>
            <th colspan="2">글제목</th>
            <th>글쓴이</th>
            <th>등록날짜</th>
            <th>좋아요여부</th>
            <th>좋아요수</th>
            <th>댓글수</th>
            <th>조회수</th>
          </tr>
        </thead>

<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
    <tr>
      <td colspan="2">
      <span>${replyBoardDTO.replyBoardTitle}</span>
      </td>
      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyBoardDate}</span>
      </td>
      <td>
      <span>${replyBoardDTO.updown.isUp}</span>
      </td>
      <td>
      <span>${replyBoardDTO.likeNum}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyNum}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyBoardViews}</span>
      </td>
    </tr>
    <tr>
      <td colspan="8">
      <span>${replyBoardDTO.replyBoardContents}</span>
      </td>
    </tr>
    <tr>
      <td colspan="8">
      <span>${replyBoardDTO.hashTag.hashTagName}</span>
      </td>
    </tr>

</c:when>
</c:choose>
</c:forEach>

    <tr>
    <td>댓글번호</td>
    <td>멘션</td>
    <td>댓글작성자</td>
    <td>댓글내용</td>
    <td>댓글작성일</td>
    <td>좋아요여부</td>
    <td>좋아요수</td>
    <td><button onclick="window.open('address','window_name','width=430,height=500,location=no,status=no,scrollbars=yes');">신고</button></td>
    </tr>
        
<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">

<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo>0}">
    <tr>
      <td>
      <span>${state.count-1}</span>
      </td>
      <td>
      <span>${replyBoardDTO.mentionNickName}</span>
      </td>
      <td>
      <span>${replyBoardDTO.member.memberNickName}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyBoardContents}</span>
      </td>
      <td>
      <span>${replyBoardDTO.replyBoardDate}</span>
      </td>
      <td>
      <span>${replyBoardDTO.updown.isUp}</span>
      </td>
      <td>
      <span>${replyBoardDTO.likeNum}</span>
      </td>
      <td>
      <span><input type=button value="신고" ></span>
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
      <td><input type=text placeholder="멘션입력" name="replyBoardMention"></td>
      <td>본인닉네임표출</td>
      <td colspan="4"><input type=text placeholder="댓글내용입력" name="replyBoardContents"></td>
      <td><input type=submit value="등록" ></td>
      </tr>
    </form>
  </c:when>
  </c:choose>
  </c:forEach>

       <tr>
      <td colspan="8" height="20" colspan="4" align="center" valign="middle">
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
        <!-- <input type=hidden name="password" value="" id="password"> -->
        <input type=button value="수정하기" >
        <input type=button value="삭제하기" >
      </form>
      </td>
    </tr>
</table>

<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?classification=${requestScope.classification}">리스트로 돌아가기</a>&gt;</span></div>