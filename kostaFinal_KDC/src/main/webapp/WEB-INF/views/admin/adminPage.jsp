<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>관리자 페이지</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>

</head>
<body>

<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
<caption>멤버 List</caption>
  
  <tr>
        <th bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">유저id</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">유저이름</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">닉네임</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">생년월일</span></b></font></p>
        </th>
        
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">휴대폰번호</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">이메일</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">가입일</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">유저 추방</span></b></font></p>
        </th>
    </tr>
    
    <c:choose>
    <c:when test="${empty requestScope.memberList}">
    <tr>
        <td colspan="8">
            <p align="center"><b><span style="font-size:9pt;">등록된 유저가 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    
    <c:otherwise>
  <c:forEach items="${requestScope.memberList}" var="memberList">
        <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
            <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                ${memberList.memberId}</span></p>
            </td>
            <td bgcolor="">
                <p><span style="font-size:9pt;">
          <%-- <a href="${pageContext.request.contextPath}/board/read/${memberList}"> --%>
                ${memberList.memberName}</span></p>
            </td>
            <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                ${memberList.memberNickName}</span></p>
            </td>
            <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                ${memberList.memberBirth}</span></p>
            </td>
             
             <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                ${memberList.memberPhone}</span></p>
            </td>
             <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                ${memberList.memberEmail}</span></p>
            </td>
            <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                ${memberList.memberDate}</span></p>
            </td>
            <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                <input type="button" value="삭제" id="deleteMember" onclick="location.href='${memberList.memberId}'"></span></p>
            </td>
        </tr>
    </c:forEach>
  </c:otherwise>
    </c:choose>
</table>
<hr>
<div align=center>
<form>
<input type="text" id="userId" value="아이디 검색" onfocus="this.value=''">
<input type="button" id="search" value="검색" onclick="location.href='selectMemberByUserId?userId='+$('#userId').val()">
</form>
</div>
<a href="${pageContext.request.contextPath }/admin/adminInsertTeacherForm">강사생성 페이지로</a><br>
<a href="${pageContext.request.contextPath }/admin/messageList">관리자 - 메시지 페이지로</a><br>
<a href="${pageContext.request.contextPath }/admin/adminReportList">관리자 - 신고 페이지로</a><br>
<a href="${pageContext.request.contextPath }/admin/classRoomInfo">관리자 - 클래스룸 생성</a><br>
<a href="${pageContext.request.contextPath }/admin/fullCalendar">관리자 - 풀 카렌다</a><br>  
</body>
</html>