<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>������ ������</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>

</head>
<body>

<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
<caption>��� List</caption>
  
  <tr>
        <th bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">����id</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">�����̸�</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">�г���</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">�������</span></b></font></p>
        </th>
        
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">�޴�����ȣ</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">�̸���</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">������</span></b></font></p>
        </th>
        <th bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">���� �߹�</span></b></font></p>
        </th>
    </tr>
    
    <c:choose>
    <c:when test="${empty requestScope.memberList}">
    <tr>
        <td colspan="8">
            <p align="center"><b><span style="font-size:9pt;">��ϵ� ������ �����ϴ�.</span></b></p>
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
                <input type="button" value="����" id="deleteMember" onclick="location.href='${memberList.memberId}'"></span></p>
            </td>
        </tr>
    </c:forEach>
  </c:otherwise>
    </c:choose>
</table>
<hr>
<div align=center>
<form>
<input type="text" id="userId" value="���̵� �˻�" onfocus="this.value=''">
<input type="button" id="search" value="�˻�" onclick="location.href='selectMemberByUserId?userId='+$('#userId').val()">
</form>
</div>
<a href="${pageContext.request.contextPath }/admin/adminInsertTeacherForm">������� ��������</a><br>
<a href="${pageContext.request.contextPath }/admin/messageList">������ - �޽��� ��������</a><br>
<a href="${pageContext.request.contextPath }/admin/adminReportList">������ - �Ű� ��������</a><br>
<a href="${pageContext.request.contextPath }/admin/classRoomInfo">������ - Ŭ������ ����</a><br>
<a href="${pageContext.request.contextPath }/admin/fullCalendar">������ - Ǯ ī����</a><br>  
</body>
</html>