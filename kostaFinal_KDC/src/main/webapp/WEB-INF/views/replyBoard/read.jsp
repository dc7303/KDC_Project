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

<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
   <colgroup>
      <col width="15%"/>
      <col width="30%"/>
      <col width="16%"/>
      <col width="16%"/>
      <col width="7%"/>
      <col width="7%"/>
      <col width="7%"/>
   </colgroup>
       <thead>
          <tr>
            <th>글번호</th>
            <th>글제목</th>
            <th>글쓴이</th>
            <th>등록날짜</th>
            <th>좋아요수</th>
            <th>댓글수</th>
            <th>조회수</th>
          </tr>
        </thead>
        

<c:forEach items="${requestScope.replyBoardDTO}" var="replyBoardDTO" varStatus="state">          
<c:choose>
<c:when test="${replyBoardDTO.replyBoardReplyNo==0}">
   <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                 ${replyBoardDTO.replyBoardPk}</span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
             ${replyBoardDTO.replyBoardTitle}
               </span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
             ${replyBoardDTO.member.memberNickName}
               </span></p>
              </td>
              
                <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                   ${replyBoardDTO.replyBoardDate}</span></p>
                </td>

               <td bgcolor=""><!-- 좋아요: 누를수있게해야함(이미 좋아요했으면 표출되고 못눌리고) -->
                   <p align="center"><span style="font-size:9pt;">
                  ${replyBoardDTO.likeNum}</span></p>
               </td>
               <td bgcolor=""><!-- 댓글수 -->
                   <p align="center"><span style="font-size:9pt;">
                  ${replyBoardDTO.replyNum}</span></p>
               </td>
              <td bgcolor=""><!-- 조회수 -->
                 <p align="center"><span style="font-size:9pt;">
                 ${replyBoardDTO.replyBoardViews}</span></p>
              </td>
          </tr>
    <tr>
        <td colspan="7">
            <p align="center"><b><span style="font-size:9pt;">${replyBoardDTO.replyBoardContents}</span></b></p>
        </td>
    </tr>
    <tr>
        <td colspan="7">
            <p align="center"><b><span style="font-size:9pt;">${replyBoardDTO.hashTag.hashTagName}</span></b></p>
        </td>
    </tr>   
    </c:when>
    <c:otherwise>
    <tr>
      <td>${replyBoardDTO.replyBoardPk}</td>
      <td>${replyBoardDTO.mentionNickName}</td>
      <td>${replyBoardDTO.member.memberNickName}</td>
      <td>${replyBoardDTO.replyBoardDate}</td>
      <td>${replyBoardDTO.updown.isUp}</td>
      <td>${replyBoardDTO.likeNum}</td>
      <td>신고할수있는부분</td>
    </tr>
    
    
     <tr>
      <td>댓글번호</td>
      <td>댓글멘션</td>
      <td colspan="4">댓글작성자</td>
      <td>등록</td>
    </tr>
    </c:otherwise>
    </c:choose>
    </c:forEach>


    <tr>
        <td height="20" colspan="4" align="center" valign="middle">
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








