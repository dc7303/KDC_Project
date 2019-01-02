<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
<title>Insert title here</title>
<script type="text/javascript">
const jq = jQuery.noConflict();

//마이페이지 접속시, 로그인 유무 체크
jq(function(){
  jq(document).on('click','#myPageValid',function(){

    if(jq('input[name=memberIdCheck]').val() === ''){
      alert("로그인 해주세요.");
      return false;
    }
  })
})
</script>
<style>

/* div.index-img{
  width: 100%;
  height: 400px;
  background-color: pink;
  margin-bottom:

} */

/* body { 

  background:url('${pageContext.request.contextPath }/resources/testimg/photos/photo11.jpg') rgba(0,0,0,0.5); 


  background-size: 100%;
 
} */


i{
  margin: 0 5px 0 0;
}

h4{
  float: left;
  color: white;
  background-color: #ffb03a;
  padding: 5px;
}


</style>

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
      </sec:authorize>
<div>


<%-- <a href="${pageContext.request.contextPath }/calendar/calendarForm">강사 - 풀 카렌다</a><br>  
<br/>
  <a href="${pageContext.request.contextPath }/admin/selectMember">관리자 페이지로</a> --%>
  </center>
  
  
<!-- 
  <div class="index-img">
  
   <img href="index-01.jpg">
   <img href="index-02.jpg">
   <img href="index-03.jpg">
   <img href="index-04.jpg">

  </div> -->
  
  <div style="width:45%; float:left; margin-right:100px">
    <h4 class="notice-header"><i class="ion-ios-calculator-outline"></i>공지사항</h4>
    <table border="1" width="80%">
      <colgroup>
        <col width="50%">
        <col width="20%">
        <col width="20%">
        <col width="*">
      </colgroup>
      <c:choose>
        <c:when test="${empty requestScope.generalNoticeListFive }">
          <th colspan="4">게시물이 존재하지 않습니다.</th>
        </c:when>
        <c:otherwise>
          <c:forEach items="${requestScope.generalNoticeListFive }" var="generalNoticeListFive">
            <tr>
              <th><a href="${pageContext.request.contextPath}/notice/read?noticeBoardPk=${generalNoticeListFive.noticeBoardPk}">${generalNoticeListFive.noticeBoardTitle }</a></th>
              <th>${generalNoticeListFive.noticeBoardWriterId }</th>
              <th>${generalNoticeListFive.noticeBoardDate }</th>
              <th>${generalNoticeListFive.noticeBoardViews }</th>
            </tr>
          </c:forEach>  
        </c:otherwise>
      </c:choose>
    </table>
  </div>
  
  <div style="width:45%; float:right">
    <h4 class="Tech-header"><i class="ion-ios-list-outline"></i>Tech 공유게시판</h4>
    <table border="1" width="100%">
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
              <th><a href="${pageContext.request.contextPath}/reply/read?replyBoardPk=${libListFive.replyBoardPk}&&classification=lib">${libListFive.replyBoardTitle }</a></th>
              <th>${libListFive.replyBoardWriterId }</th>
              <th>${libListFive.replyBoardDate }</th>
              <th>${libListFive.replyBoardViews }</th>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
  </div>
  <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
  
  <div style="width:100%;">
    <h4 class="QnA-header"><i class="ion-ios-list-outline"></i>Tech Q&A</h4>
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
              <th><a href="${pageContext.request.contextPath}/reply/read?replyBoardPk=${techListFive.replyBoardPk}&&classification=tech">${techListFive.replyBoardTitle }</a></th>
              <th>${techListFive.replyBoardWriterId }</th>
              <th>${techListFive.replyBoardDate }</th>
              <th>${techListFive.replyBoardViews }</th>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
    </table>
  </div>
</div>
</center>
</body>
</html>