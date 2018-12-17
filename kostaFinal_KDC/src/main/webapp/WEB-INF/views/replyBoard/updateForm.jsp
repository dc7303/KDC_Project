<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<HEAD>

</HEAD>
<h1>${requestScope.classification} ${requestScope.replyBoardPk} 수정하기</h1>

<form name="update" method=post action="${pageContext.request.contextPath}/reply/replyBoardUpdate?classification=${requestScope.classification}&replyBoardPk=${requestScope.replyBoardPk}">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
 
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
          <span><input type=text name="replyBoardTitle" value="${replyBoardDTO.replyBoardTitle}"></span>
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
          <span><input type=text name="replyBoardContents" value="${replyBoardDTO.replyBoardContents}"></span>
          </td>
        </tr>
        <tr>
          <td colspan="8">
          <span><input type=text name="hashTagName" value="${replyBoardDTO.hashTag.hashTagName}"></span>
          </td>
        </tr>
    
      </c:when>
      </c:choose>
    </c:forEach>
  
      <tr>
        <td colspan="8" height="20" colspan="4" align="center" valign="middle">
        <!-- 수정시 필요한 데이터들을 hidden으로 숨겨놓고 폼 데이터로 보내준다. -->
          <input type=submit value="수정하기" >
          <input type=reset value="다시쓰기" >
        
        </td>
      </tr>
  
  </table>
</form>
<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?classification=${requestScope.classification}">리스트로 돌아가기</a>&gt;</span></div>
