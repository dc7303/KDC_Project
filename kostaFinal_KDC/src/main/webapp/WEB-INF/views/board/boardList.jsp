<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
<caption>내가 작성한 게시글 리스트</caption>
  
   <tr>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">분류</span></b></font></p> 
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">글제목</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">작성날짜</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">삭제</span></b></font></p>
        </td>
    </tr>

    <c:choose>
    <c:when test="${empty requestScope.list}">
  <tr>
        <td colspan="3">
            <p align="center"><b><span style="font-size:9pt;">게시글이 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    <c:otherwise>
   <c:forEach items="${requestScope.list}" var="writtenBoardList">
          <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  ${writtenBoardList.replyBoardClassification}</span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
               <a href="#"> <!-- path variable RESTful -->
                 ${writtenBoardList.replyBoardTitle}
               </a>
               </span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  ${writtenBoardList.replyBoardDate}</span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  <input type="button" value="삭제" id="deleteBoard" onclick="location.href='${pageContext.request.contextPath}/board/deleteBoard?replyBoardPk=${writtenBoardList.replyBoardPk}'"></span></p>
              </td>
          </tr>
    </c:forEach>
  </c:otherwise>
    </c:choose>
</table>
<hr>
<div align=right>
<span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/">마이페이지 홈</a>&gt;</span></div>


</body>
</html>