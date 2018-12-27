<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
<center>
  <%
  	//1.application에 저장되어있는 userCount의 정보를 가져온다.
  	Object cnt = application.getAttribute("userCount");
  	
  	//2. 만약 가져온 userCount정보가 null이라면(최초의 손님) userCount의 값을 1로 저장한후
  	//   다시 userCount의 정보를 가져온다.
  	if( cnt == null ){
  		application.setAttribute("userCount", 1);
  		cnt = application.getAttribute("userCount");
  			
  	}else{
  	//3.  가지고 온 userCount의 값을 +1을 증가하여 다시 변경된 값으로 저장한후 출력한다.
  	
  		int cn = (Integer)cnt;
  		
  		if(session.isNew()){
  		    cn++;
  		}
      
  		application.setAttribute("userCount",cn);
  	}	
  %>
  <sec:authorize access="isAuthenticated()">
	<sec:authentication var="member" property="principal" />
    
    <h2>
      ${member.memberId } 어서오십쇼.
    </h2>
    <a href="${pageContext.request.contextPath }/member/myPage">마이페이지</a><br/>
    <form action="${pageContext.request.contextPath}/member/logout" method="post">
      <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
      <input type="submit" value="로그아웃"/>
    </form>
  </sec:authorize>
  <h1>Index입니다</h1>
<a href="${pageContext.request.contextPath }/portfolio/myPage">포트폴리오 마이페이지</a>
<a href="${pageContext.request.contextPath}/message/list?id=${sessionScope.userId}" name="messageList">전체메세지 출력</a> <br>
<a href="${pageContext.request.contextPath}/board/boardList?id=${sessionScope.userId}">전체게시물 출력</a> <br> <br>

<a href="${pageContext.request.contextPath}/classRoom/classList?id=heejung">heejung클래스 전체 출력</a> <br>
<a href="${pageContext.request.contextPath}/classRoom/classCreate">클래스 생성</a> <br/><br/>
<a href="${pageContext.request.contextPath }/classRoom/classRoomInsertForm">강사 - 클래스룸 생성</a><br>
<a href="${pageContext.request.contextPath }/calendar/calendarForm">강사 - 풀 카렌다</a><br>  
<br/>
  <a href="${pageContext.request.contextPath }/admin/selectMember">관리자 페이지로</a>
  </center>
  
  
  

<div>
  
  <div style="width:30%; float:left; margin-right:100px">
    <h4 class="notice-header">공지사항 게시판</h4>
    <table border="1" width="80%">
      <colgroup>
        <col width="50%">
        <col width="20%">
        <col width="20%">
        <col width="*">
      </colgroup>
      <c:choose>
        <c:when test="${empty requestScope.noticeListFive }">
          <th colspan="4">게시물이 존재하지 않습니다.</th>
        </c:when>
        <c:otherwise>
          <c:forEach items="${requestScope.noticeListFive }" var="noticeListFive">
            <tr>
              <th><a href="#">${noticeListFive.noticeBoardTitle }</a></th>
              <th>${noticeListFive.noticeBoardWriterId }</th>
              <th>${noticeListFive.noticeBoardDate }</th>
              <th>${noticeListFive.noticeBoardViews }</th>
            </tr>
          </c:forEach>  
        </c:otherwise>
      </c:choose>
    </table>
  </div>
  
  <div style="width:30%; float:left">
    <h4 class="Tech-header">Tech 게시판</h4>
    <table border="1" width="80%">
      <colgroup>
        <col width="50%">
        <col width="20%">
        <col width="20%">
        <col width="*">
      </colgroup>
      <c:choose>
        <c:when test="${empty requestScope.techListFive }">
          <th colspan="4">게시글이 존재하지 않습니다.</th>
        </c:when>
        <c:otherwise>
          <c:forEach items="${requestScope.techListFive }" var="techListFive">
            <tr>
              <th><a href="#">${techListFive.replyBoardTitle }</a></th>
              <th>${techListFive.replyBoardWriterId }</th>
              <th>${techListFive.replyBoardDate }</th>
              <th>${techListFive.replyBoardViews }</th>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
  </div>
  <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  <div style="width:50%;">
    <h4 class="QnA-header" style="margin-left:auto; margin-right:auto;">Q&A 게시판</h4>
    <table border="1" width="80%">
      <colgroup>
        <col width="50%">
        <col width="20%">
        <col width="20%">
        <col width="*">
      </colgroup>
      <c:choose>
        <c:when test="${empty requestScope.libListFive }">
          <th colspan="4">게시글이 존재하지 않습니다.</th>
        </c:when>
        <c:otherwise>
          <c:forEach items="${requestScope.libListFive }" var="libListFive">
            <tr>
              <th><a href="#">${libListFive.replyBoardTitle }</a></th>
              <th>${libListFive.replyBoardWriterId }</th>
              <th>${libListFive.replyBoardDate }</th>
              <th>${libListFive.replyBoardViews }</th>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
  </div>
</div>
</body>
</html>