<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />

  </head>

  <body>
  
  
      <!-- 게시판 타이틀 -->
  <c:choose>
    <c:when test="${requestScope.classification eq 'generalNotice'}">
    <h2 class="notice-title">공지사항</h2>
    <p class="underline-board"></p>
    </c:when>
    <c:when test="${requestScope.classification eq 'findJobNotice'}">
    <h2 class="notice-title">Tech 취업 게시판</h2>
    <p class="underline-board"></p>
    </c:when> 
    <c:when test="${requestScope.classification eq 'classNotice'}">
    <h2 class="notice-title">클래스 공지사항</h2>
    <p class="underline-board"></p>
    </c:when> 
  </c:choose>
    

    

    <div class="write-button">
      <a href="${pageContext.request.contextPath }/notice/writeForm?classification=${requestScope.classification}" class="button">글쓰기</a>
    </div> <br/><br/>

    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>글번호</th>
            <th style="width:600px;">글제목</th>
            <th>글쓴이</th>
            <th><a class="board-list-font" href="#">등록날짜</a></th>
            <th><a class="board-list-font" href="#">조회수</a></th>            
          </tr>
        </thead>
        <tbody>
 
    <c:choose>
    
    <c:when test="${empty requestScope.list}"> 
          <tr>
            <td colspan="7">게시글이 없습니다.</td>
          </tr>          
    </c:when>
    
    <c:otherwise>
    <c:forEach items="${requestScope.list}" var="NoticeBoardDTO" varStatus="state">          
          
          <tr>
            <td>${state.count}</td>
            <td>
              <sec:authorize access="isAuthenticated()">
                <sec:authentication var="member" property="principal" />
              <a href="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${NoticeBoardDTO.noticeBoardPk}">
                       ${NoticeBoardDTO.noticeBoardTitle}</a>
              </sec:authorize>
            </td>
            <td>${NoticeBoardDTO.noticeBoardWriterId}</td>
            <td>${NoticeBoardDTO.noticeBoardDate}</td>
            <td>${NoticeBoardDTO.noticeBoardViews}</td>
          </tr>
          
    </c:forEach>
    </c:otherwise>
    
    </c:choose>

        </tbody>
      </table>
    </div>

        <div class="field half">

    <form action="${pageContext.request.contextPath}/notice/listserch">
       <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
       <input type="hidden" name="classification" value="${requestScope.classification}"/>
        <select name="department" id="department">
          <option value="">- 분류 -</option>
          <option value="title">제목</option>
          <option value="content">내용</option>
        </select>

        <input class="tech-board-search" type="text" name="boardSearch"/>
        <input type="submit" value="검색"/>
    </form>

   </div>


  </body>
</html>
