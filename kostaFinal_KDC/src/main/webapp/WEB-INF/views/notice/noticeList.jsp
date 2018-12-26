<<<<<<< HEAD
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
    <h2>${requestScope.classification}</h2>
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
                  <p align="center">
                  <span style="font-size:9pt;">
                  ${state.count}</span></p>
              </td>
              <td bgcolor="">
                <p align="center">
                <span style="font-size:9pt;">
                  <a href="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${NoticeBoardDTO.noticeBoardPk}&classification=${requestScope.classification}">
                    ${NoticeBoardDTO.noticeBoardTitle}
                  </a>
                </span></p>
              </td>
  		      <td>
                <p align="center">
                  <span style="font-size:9pt;">
        	           ${NoticeBoardDTO.noticeBoardWriterId}
                  </span></p>
              </td>
  		      <td bgcolor="">
                <p align="center">
                  <span style="font-size:9pt;">
                   ${NoticeBoardDTO.noticeBoardDate}
                  </span></p>
  		        </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    
    
    <div class="field half">
      <form  method="post" action="${pageContext.request.contextPath}/notice/listserch">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
       <input type="hidden" name="classification" value="${requestScope.classification}"/>
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
  
  <hr/>
  
  <div align=right>
 
    <span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath }/notice/writeForm?classification=${requestScope.classification}">글쓰기</a>&gt;</span>
  </div>
</body>
</html>
=======
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
    
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css"/></noscript>
  </head>

  <body>


    <h2>${requestScope.classification}</h2>
    <br/><br/>

    <div class="write-button">
      <a href="${pageContext.request.contextPath }/notice/writeForm?classification=classNotice" class="button">글쓰기</a>
    </div> <br/><br/>

    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th>글번호</th>
            <th>글제목</th>
            <th>글쓴이</th>
            <th><a href="#">등록날짜</a></th>
            <th><a href="#">조회수</a></th>            
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
>>>>>>> MergBranch
