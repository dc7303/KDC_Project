<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/fullCalendar/fullcalendar.min.css" rel="stylesheet">

<script src='${pageContext.request.contextPath}/resources/fullCalendar/lib/jquery.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/fullCalendar/lib/moment.min.js'></script>
<script src="${pageContext.request.contextPath}/resources/fullCalendar/fullcalendar.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

const jq = jQuery.noConflict();

$(function(){
  
/*   //이벤트 데이터
  	const eventArr = (function() {
  	  const arr = [];
  	  const dateLength = $('input[name=title]').length;
  	  
  	  for(let i = 0; i < dateLength; i++) {
  	    let title = $('input[name=title]').eq(i).val();
  	    let start = $('input[name=startDate]').eq(i).val();
  	    let end = $('input[name=endDate]').eq(i).val();
  	    
  
  	    arr.push({
  	      title: title,
  	      start: start,
  	      end: end,
  	    });
  	  }
  	  
  	  return arr;
  	  
  	})(); */
  	
  const eventArr = (() => {
	  const arr = [];
	  const dateLength = $('input[name=title]').length;
	  
	  for(let i = 0; i < dateLength; i++) {
	    let title = $("input[name=title]").eq(i).val();
	    let start = $("input[name=startDate]").eq(i).val();
	    let end = $("input[name=endDate]").eq(i).val();
	    

	    arr.push({
	      title: title,
	      start: start,
	      end: end,
	    });
	  }
	  
	  return arr;
	  
	})();
  	
  	console.log(eventArr);
    const cal = $('#calendar').fullCalendar({
    	events: eventArr
    });
    
})


</script>

</head>
<body>
    <c:forEach items="${requestScope.classList}" var="classList" varStatus="state">
      <input type="hidden" name="title" value="${classList.classRoomInfoName }"/>
      <input type="hidden" name="startDate" value="${classList.classRoomInfoStartDate }"/>
      <input type="hidden" name="endDate" value="${classList.classRoomInfoEndDate }"/>
    </c:forEach>

<div id="calendar"></div>

</body>
</html>