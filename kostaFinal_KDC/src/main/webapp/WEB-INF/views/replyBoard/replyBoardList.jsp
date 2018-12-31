<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

  <head>
    <meta charset="utf-8">
    <title></title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
    <script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
    
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css"/></noscript>
    
    <script type="text/javascript">
    const jq = jQuery.noConflict();
    </script>
  <style type="text/css">
  #new-img{
  /* Chrome, Safari, Opera */
  -webkit-animation: invert-new 0.5s infinite; 
  animation: invert-new 0.5s infinite;
}

/* Chrome, Safari, Opera */
@-webkit-keyframes invert-new {
  50% {
    -webkit-filter: invert(100%); 
    filter: invert(100%);
  }
}

/* Standard syntax */
@keyframes invert-new {
  50% {
    -webkit-filter: invert(100%); 
    filter: invert(100%);
  }
}
  </style>
  </head>

  <body>


    <h2>${requestScope.classification}</h2>
    <br/><br/>
    <sec:authorize access="isAuthenticated()">
        <div class="write-button">
          <a href="write?classification=${requestScope.classification}" class="button">글쓰기</a>
        </div> <br/><br/>
    </sec:authorize>
    
    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th colspan="2">글제목</th>
            <th>글쓴이</th>
            <th><a href="${pageContext.request.contextPath}/reply/dateOrderby?sort=reply_board_write_date&classification=${requestScope.classification}">등록날짜</a></th>
            <th><a href="${pageContext.request.contextPath}/reply/likeOrderby?sort=likeNum&classification=${requestScope.classification}">좋아요</a></th>
            <th><a href="${pageContext.request.contextPath}/reply/replyOrderby?sort=replyNum&classification=${requestScope.classification}">댓글수</a></th>
            <th><a href="${pageContext.request.contextPath}/reply/viewOrderby?sort=REPLY_BOARD_VIEWS&classification=${requestScope.classification}">조회수</a></th>            
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
            <td colspan="2">
            <input type="hidden" value="${replyBoardDTO.replyBoardDate}"  name="newBoardCheck${state.count}">
            
            <script type="text/javascript">
              jq(function(){
                
               var writeDate = jq('input[name=newBoardCheck${state.count}]').val();            
               var adjustedWriteDate = new Date(writeDate);
               var currentDate = new Date();
               currentDate.setDate(currentDate.getDate()-1);
  
               if(adjustedWriteDate>currentDate){
                 jq('span[name=span${state.count}]').append("<img src='${pageContext.request.contextPath}/resources/testimg/replyBoard/newImg.jpg' id='new-img'/>");
               }      
              });
            </script>
            
              <span name="span${state.count}"></span>

                <c:if test="${replyBoardDTO.authName eq 'ROLE_ADMIN'}">
                <a href="${pageContext.request.contextPath}/reply/read?replyBoardPk=${replyBoardDTO.replyBoardPk}&classification=${requestScope.classification}" style="color: red; font-weight: bold">
                       ${replyBoardDTO.replyBoardTitle}</a>
                </c:if>
                
                <c:if test="${replyBoardDTO.authName eq 'ROLE_MEMBER'}">
                <a href="${pageContext.request.contextPath}/reply/read?replyBoardPk=${replyBoardDTO.replyBoardPk}&classification=${requestScope.classification}" style="color: #7dc855">
                       ${replyBoardDTO.replyBoardTitle}</a>
                </c:if>
                
                <c:if test="${replyBoardDTO.authName eq 'ROLE_TEACHER'}">
                <a href="${pageContext.request.contextPath}/reply/read?replyBoardPk=${replyBoardDTO.replyBoardPk}&classification=${requestScope.classification}" style="color: orange; font-weight: bold">
                       ${replyBoardDTO.replyBoardTitle}</a>
                </c:if>
                
                <c:if test="${replyBoardDTO.authName eq 'ROLE_COMPANY'}">
                <a href="${pageContext.request.contextPath}/reply/read?replyBoardPk=${replyBoardDTO.replyBoardPk}&classification=${requestScope.classification}" style="color: blue">
                       ${replyBoardDTO.replyBoardTitle}</a>
                </c:if>
            </td>
            
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

        <form action="${pageContext.request.contextPath}/reply/replyBoardListSearch">
           <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
           <input type="hidden" name="classification" value="${requestScope.classification}"/>
            <select name="department" id="department">
              <option value="">- 분류 -</option>
              <option value="A.REPLY_BOARD_TITLE">제목</option>
              <option value="A.REPLY_BOARD_CONTENTS">내용</option>
              <option value="B.MEMBER_NICKNAME">작성자</option>
              <option value="C.HASHTAG">해시태그</option>
            </select>
    
            <input class="tech-board-search" type="text" name="boardSearch"/>
            <input type="submit" value="검색"/>
        </form>

   </div>

  </body>
</html>