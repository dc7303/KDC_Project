<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
  <span>${requestScope.title}</span>
	<colgroup>
		<col width="15%"/>
		<col width="30%"/>
		<col width="16%"/>
		<col width="16%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
	</colgroup>
	<tr>
        <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">글번호</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">글제목</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">글쓴이</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">등록날짜</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">좋아요수</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">댓글수</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">조회수</span></b></font></p>
        </td>
    </tr>
    <c:choose>
    <c:when test="${empty requestScope.list}">
	<tr>
        <td colspan="5">
            <p align="center"><b><span style="font-size:9pt;">게시글이 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    <c:otherwise>
	<c:forEach items="${requestScope.list}" var="replyBoardDTO">
		    <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${replyBoardDTO.replyBoardPk}</span></p>
		        </td>
		        <td bgcolor="">
					<p><span style="font-size:9pt;">
					<a href="${pageContext.request.contextPath}/reply/read?replyBoardTitle=${replyBoardDTO.replyBoardTitle}">
					  ${replyBoardDTO.replyBoardTitle}
					</a>
					</span></p>
		        </td>
		        
                <td bgcolor="">
                    <p align="center"><span style="font-size:9pt;">
                    ${replyBoardDTO.replyBoardWriterId}</span></p>
                </td>
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		            ${replyBoardDTO.replyBoardDate}</span></p>
		        </td>
               <td bgcolor=""><!-- 좋아요 -->
                   <p align="center"><span style="font-size:9pt;">
                   ${replyBoardDTO.likeNum}</span></p>
               </td>
               <td bgcolor=""><!-- 댓글수 -->
                   <p align="center"><span style="font-size:9pt;">
                   ${replyBoardDTO.replyNum}</span></p>
               </td>
	           <td bgcolor="">
	              <p align="center"><span style="font-size:9pt;">
	              ${replyBoardDTO.replyBoardViews}</span></p>
	           </td>
		    </tr>
    </c:forEach>
	</c:otherwise>
    </c:choose>
</table>
<hr>
<div align=right>
<span style="font-size:9pt;">&lt;<a href="write?title=${requestScope.title}">글쓰기</a>&gt;</span></div>

