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
  <script type="text/javascript">
    const jq = jQuery.noConflict();
    </script>
<style type="text/css">
.new-img {
  /* Chrome, Safari, Opera */
  -webkit-animation: invert-new 0.5s infinite;
  animation: invert-new 0.5s infinite;
}

/* Chrome, Safari, Opera */
@
  -webkit-keyframes invert-new {
     50% {
      -webkit-filter: invert(100%);
      filter: invert(100%);
    }
  }

/* Standard syntax */
@
  keyframes invert-new { 
     50% {
      -webkit-filter: invert(100%);
      filter: invert(100%);
    }
  }
  
  .paging-hover:hover {
    color: orange;
  }
  
  .table-list-hover:hover {
    
  }
</style>
  <body>
  
  
      <!-- 게시판 타이틀 -->
  <c:choose>
    <c:when test="${requestScope.classification eq 'generalNotice'}">
    <h2 class="notice-title">공지사항</h2>
    <p class="underline-board"></p>
    <sec:authorize access="hasRole('ROLE_ADMIN')">
      <div class="write-button">
        <a href="${pageContext.request.contextPath }/notice/writeForm?classification=${requestScope.classification}" class="button">글쓰기</a>
      </div> <br/><br/>
    </sec:authorize>
    
    </c:when>
    <c:when test="${requestScope.classification eq 'findJobNotice'}">
    <h2 class="notice-title">채용 게시판</h2>
    <p class="underline-board"></p>
    <sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_COMPANY')">
      <div class="write-button">
        <a href="${pageContext.request.contextPath }/notice/writeForm?classification=${requestScope.classification}" class="button">글쓰기</a>
      </div> <br/><br/>
    </sec:authorize>
    
    </c:when> 
    <c:when test="${requestScope.classification eq 'classNotice'}">
    <h2 class="notice-title">클래스 공지사항</h2>
    <p class="underline-board"></p>
    <sec:authorize access="hasRole('ROLE_TEACHER')">
      <div class="write-button">
        <a href="${pageContext.request.contextPath }/notice/writeForm?classification=${requestScope.classification}" class="button">글쓰기</a>
      </div> <br/><br/>
    </sec:authorize>
    </c:when> 
  </c:choose>
    
    
    <div class="table-wrapper">
      <table>
        <thead>
          <tr>
            <th colspan="2" width="40%">글제목</th>
            <th width="20%">글쓴이</th>
            <th width="20%"><a class="board-list-font" href="#">등록날짜</a></th>
            <th width="20%"><a class="board-list-font" href="#">조회수</a></th>            
          </tr>
        </thead>
        <tbody>
 
    <c:choose>
    
    <c:when test="${empty requestScope.resultMap.noticeList}"> 
          <tr>
            <td colspan="7">게시글이 없습니다.</td>
          </tr>          
    </c:when>
    
    <c:otherwise>
    <c:forEach items="${requestScope.resultMap.noticeList}" var="noticeDTO" varStatus="state">          
          
          <tr class="table-list-hover">
            <td colspan="2">
            <input type="hidden" value="${noticeDTO.noticeBoardDate}"  name="newBoardCheck${state.count}">
            <script type="text/javascript">
              jq(function(){
                
               var writeDate = jq('input[name=newBoardCheck${state.count}]').val();            
               var adjustedWriteDate = new Date(writeDate);
               var currentDate = new Date();
               currentDate.setDate(currentDate.getDate()-1);
  
               if(adjustedWriteDate>currentDate){
                 jq('span[name=span${state.count}]').append("<img src='${pageContext.request.contextPath}/resources/testimg/replyBoard/newImg.jpg' class='new-img'/>");
               }      
              });
            </script>
            <span name="span${state.count}"></span>

                <c:if test="${noticeDTO.authName eq 'ROLE_ADMIN'}">
                <a href="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${noticeDTO.noticeBoardPk}" style="color: red; font-weight: bold">
                       ${noticeDTO.noticeBoardTitle}</a>
                </c:if>
                
                <c:if test="${noticeDTO.authName eq 'ROLE_MEMBER'}">
                <a href="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${noticeDTO.noticeBoardPk}" style="color: #7dc855">
                       ${noticeDTO.noticeBoardTitle}</a>
                </c:if>
                
                <c:if test="${noticeDTO.authName eq 'ROLE_TEACHER'}">
                <a href="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${noticeDTO.noticeBoardPk}" style="color: orange; font-weight: bold">
                       ${noticeDTO.noticeBoardTitle}</a>
                </c:if>
                
                <c:if test="${noticeDTO.authName eq 'ROLE_COMPANY'}">
                <a href="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${noticeDTO.noticeBoardPk}" style="color: blue">
                       ${noticeDTO.noticeBoardTitle}</a>
                </c:if>
            
            </td>
            
            <td>${noticeDTO.member.memberNickName}</td>
            <td>${noticeDTO.noticeBoardDate}</td>
            <td>${noticeDTO.noticeBoardViews}</td>
          </tr>
    </c:forEach>
    
    <tr>
      <td colspan="5">  
      <c:set var="pageDTO" value="${requestScope.resultMap.pageDTO }"></c:set>
      <c:if test="${pageDTO.firstMove }">
        <a href="${pageContext.request.contextPath }/notice/list?classification=${requestScope.classification }&pageNum=1" class="paging-hover">첫페이지로</a>
      </c:if>
      <c:if test="${pageDTO.backPage }">
        <a href="${pageContext.request.contextPath }/notice/list?classification=${requestScope.classification }&pageNum=${pageDTO.page -1}" class="paging-hover">◀</a>
      </c:if>
      <c:forEach begin="${pageDTD.startPage +1 }" end="${pageDTO.endPage }" var="i">
        <c:if test="${i == pageDTO.page }">
          <a href="${pageContext.request.contextPath }/notice/list?classification=${requestScope.classification }&pageNum=${i}" class="paging-hover" style="color:red">${i }</a>
        </c:if>
        <c:if test="${i != pageDTO.page }">
        <a href="${pageContext.request.contextPath }/notice/list?classification=${requestScope.classification }&pageNum=${i}" class="paging-hover">${i }</a>
        </c:if>
      </c:forEach>
      <c:if test="${pageDTO.nextPage}">
        <a href="${pageContext.request.contextPath }/notice/list?classification=${requestScope.classification }&pageNum=${pageDTO.page +1}" class="paging-hover">▶</a>
      </c:if>
      <c:if test="${pageDTO.lastMove }">
        <a href="${pageContext.request.contextPath }/notice/list?classification=${requestScope.classification }&pageNum=${pageDTO.lastMove}" class="paging-hover">마지막페이지</a>
      </c:if>
      </td>
    </tr>
    </c:otherwise>
    
    </c:choose>

        </tbody>
      </table>
    </div>

        <div class="field half">

    <form action="${pageContext.request.contextPath}/notice/listserch">
       <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
       <input type="hidden" name="classification" value="${requestScope.classification}"/>
       <input type="hidden" name="pageNum" value="1"/> 
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
