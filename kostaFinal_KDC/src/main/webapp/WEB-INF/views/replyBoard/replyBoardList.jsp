<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

  <head>
    <meta charset="utf-8">
    <title></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
    
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" /></noscript>
  </head>

  <body>


    <h2>${requestScope.title}</h2>
    <br/><br/>

    <div class="write-button">
      <a href="write?title=${requestScope.title}" class="button">글쓰기</a>
    </div> <br/><br/>

    <div class="table-wrapper">
      <table>
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
        <tbody>
 
    <c:choose>
    
    <c:when test="${empty requestScope.list}"> 
          <tr>
            <td colspan="7">게시글이 없습니다.</td>
          </tr>          
    </c:when>
    
    <c:otherwise>
    <c:forEach items="${requestScope.list}" var="replyBoardDTO" varStatus="state">          
          
          <tr>
            <td>${state.count}</td>
            <td><a href="${pageContext.request.contextPath}/reply/read?replyBoardPk=${replyBoardDTO.replyBoardPk}&classification=${requestScope.title}">
                ${replyBoardDTO.replyBoardTitle}</a></td>
            <td>${replyBoardDTO.member.memberNickName}</td>
            <td>${replyBoardDTO.replyBoardDate}</td>
            <td>${replyBoardDTO.likeNum}</td>
            <td>${replyBoardDTO.replyNum}</td>
            <td>${replyBoardDTO.replyBoardViews}</td>
          </tr>
          
    </c:forEach>
    </c:otherwise>
    
    </c:choose>

        </tbody>
      </table>
    </div>

    <div class="field half">

        <select name="department" id="department">
          <option value="">- 분류 -</option>
          <option value="1">제목</option>
          <option value="2">내용</option>
          <option value="3">작성자</option>
          <option value="4">헤시태그</option>
        </select>

        <input class="tech-board-search" type="text" name="tech-board-search" />
        <a href="#" class="button">검색</a>

     </div>


  </body>
</html>