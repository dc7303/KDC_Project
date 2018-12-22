<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<table align="center" border="0" cellpadding="5" cellspacing="2"
  width="100%" bordercolordark="white" bordercolorlight="black">
  <caption>메세지 답장</caption>

  <form name="writeForm" id="write" method="post" action="${pageContext.request.contextPath}/message/insert">

    <table align="center" cellpadding="5" cellspacing="2" width="600" border="1">

      <tr>
        <td width="1220" height="20" colspan="2" bgcolor="#00cc00">
          <p align="center">
            <font color="white" size="3"><b> 메세지 작성 </b></font>
          </p>
        </td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">받는사람</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span
            style="font-size: 9pt;"> <input type=text  
              name="senderId" value="${requestScope.senderId}"
              size="30" readonly="readonly"></span></b></td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">쪽지제목</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span
            style="font-size: 9pt;"> <input type=text
              name="messageTitle" size="30"></span></b></td>
      </tr>
      <tr>
        <td width="150" height="20">
          <p align="right">
            <b><span style="font-size: 9pt;">쪽지내용</span></b>
          </p>
        </td>
        <td width="450" height="20"><b><span style="font-size: 9pt;"> 
        <textarea name="messageContents" cols="50" rows="10"></textarea></span></b></td>
      </tr>
      <tr>
        <td width="450" height="20" colspan="2" align="center"><b><span style="font-size: 9pt;"> 
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <input type="hidden" name="receiverId" value="${sessionScope.userId}">
        <input type="submit" value="전송" id="replyMessage">
        <input type="button" value="취소" id="cancelWriteMessage" onclick="location.href='${pageContext.request.contextPath}/message/list'">
         </span></b></td>
      </tr>
    </table>
  
  </form>
</table>
  <hr>
  <div align=right>
    <span style="font-size: 9pt;">&lt;<a
      href="${pageContext.request.contextPath}/">마이페이지 홈</a>&gt;
    </span>
  </div>