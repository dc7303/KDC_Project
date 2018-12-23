<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="css/style.css">

<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">
<caption>공지사항</caption>
	<colgroup>
		<col width="15%"/>
		<col width="30%"/>
		<col width="16%"/>
		<col width="16%"/>
		<col width="7%"/>
		<col width="7%"/>
		<col width="7%"/>
       <col width="7%"/>
	</colgroup>
 
	<tr>
  <td bgcolor="#00cc00">
            <p align="center">
            <font color="white"><b><span style="font-size:9pt;">게시판 번호</span></b></font></p>
        </td>
        
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">게시판제목</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">작성자ID</span></b></font></p>
        </td>
        <td bgcolor="#00cc00">
            <p align="center"><font color="white"><b><span style="font-size:9pt;">작성일</span></b></font></p>
        </td>
        
       
         
  </tr>
   
           
       <c:choose>
           
        <c:when test="${empty requestScope.list}"> 
            <tr>
             <td colspan="7">게시글이 없습니다.</td>
           </tr>          
        </c:when>
    <c:otherwise>
	<c:forEach items="${requestScope.list}" var="NoticeBoardDTO" varStatus="state">
  
       <tr>
            <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
            <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                ${state.count}</span></p>
       
            <td bgcolor="">
                <p align="center"><span style="font-size:9pt;">
                 <a href="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${NoticeBoardDTO.noticeBoardPk}">
                ${NoticeBoardDTO.noticeBoardTitle}
                </a>
                </span></p>
            </td>
		    <td>
          <p lign="center"><span style="font-size:9pt;">
      ${NoticeBoardDTO.noticeBoardWriterId}
          </span></p>
            </td>
		 
		        <td bgcolor="">
		            <p align="center"><span style="font-size:9pt;">
		             ${NoticeBoardDTO.noticeBoardDate}</span></p>
		        </td>
		         
           
             
	  </tr>
        
    </c:forEach>
    </c:otherwise>
	</c:choose>
   <div class="field half">

    <form  method="post" action="${pageContext.request.contextPath}/notice/listserch">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <select name="department" id="department">
          <option value="1">목록</option>
          <option value="title">제목</option>
          <option value="contents">내용</option>
        </select>

        <input class="notice-board-search" type="text" name="noticeBoardSearch" />
        <input type="submit" value="검색">
        </form>
  
<div>
  
  

</table>
<hr>
<div align=right>
<span style="font-size:9pt;">&lt;<a href="write">글쓰기</a>&gt;</span></div>
