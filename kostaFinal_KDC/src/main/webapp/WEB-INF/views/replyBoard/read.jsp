<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<HEAD>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

<SCRIPT language=javascript>
$(function(){
      $("input[value=수정하기]").click(function(){
         //document.requestForm.action="${pageContext.request.contextPath}/board/updateForm";
         
         $("#requestForm").attr("action", "${pageContext.request.contextPath}/board/updateForm");
         $("#requestForm").submit();
      })
      
      
      $("input[value=삭제하기]").click(function(){
         var pwd = prompt("비밀번호를 입력하세요.");
         if(pwd){
              $("#password").val(pwd);
            $("#requestForm").attr("action", "${pageContext.request.contextPath}/board/delete");
            $("#requestForm").submit();
         }
      })
      
})
</script>


</HEAD>

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
    
    <tr>
    <td>${replyBoardDTO.replyNum+1}</td>
    <td><input type=text placeholder="멘션입력"></td>
    <td>본인닉네임표출</td>
    <td colspan="4"><input type=text placeholder="댓글내용입력"></td>
    <td><input type=button value="등록" ></td>
    </tr>

</c:when>
</c:choose>
</c:forEach>

    <tr>
      <td colspan="8" height="20" colspan="4" align="center" valign="middle">
      <!-- 수정시 필요한 데이터들을 hidden으로 숨겨놓고 폼 데이터로 보내준다. -->
      <form name="requestForm" method=post  id="requestForm">
        <input type=hidden name="modelNum" value="${elec.modelNum}">
        <input type=hidden name="password" value="" id="password">
        <input type=button value="수정하기" >
        <input type=button value="삭제하기" >
      </form>
      </td>
    </tr>
</table>

<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/board/list">리스트로 돌아가기</a>&gt;</span></div>