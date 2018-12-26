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
  
	//이벤트 데이터
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
    var $calendar = $('#calendar').fullCalendar({
      	header: {
      	  left: 'none',
      	  center: 'title',
      	},
      	selectable: true,
      	
      	select: function(start, end, jsEvent, view) {
      	  
      	   // Ask for a title. If empty it will default to "New event"
      	   var title = prompt("Enter a title for this event", "New event");

      	   // If did not pressed Cancel button
      	   if (title != null) {
            	   // Create event
            	   var event = {
            	   title: title.trim() != "" ? title : "New event",
            	   start: start,
            	   end: end
        	   };
        	  
        	  
        	   // Push event into fullCalendar's array of events
        	   // and displays it. The last argument is the
        	   // "stick" value. If set to true the event
        	   // will "stick" even after you move to other
        	   // year, month, day or week.
        	  
        	   $calendar.fullCalendar("renderEvent", event, true);
      	   };
      	   // Whatever happens, unselect selection
      	   $calendar.fullCalendar("unselect");
      	  
      	   },
      	   select: function(start, end, jsEvent, view) {
         	  
        	   // Ask for a title. If empty it will default to "New event"
        	   var title = prompt("Enter a title for this event", "New event");

        	   // If did not pressed Cancel button
        	   if (title != null) {
              	   // Create event
              	   var event = {
              	   title: title.trim() != "" ? title : "New event",
              	   start: start,
              	   end: end
          	   };
          	  
          	  
          	   // Push event into fullCalendar's array of events
          	   // and displays it. The last argument is the
          	   // "stick" value. If set to true the event
          	   // will "stick" even after you move to other
          	   // year, month, day or week.
          	  
          	   $calendar.fullCalendar("renderEvent", event, true);
        	   };
        	   // Whatever happens, unselect selection
        	   $calendar.fullCalendar("unselect");
  		},
  		editable : true,
    		events: eventArr,
  		eventClick: function(event, jsEvent, view){
  		   // Ask for a title. If empty it will default to "New event"
  		   var newTitle = prompt("Enter a new title for this event", event.title);
  		  
  		  
  		   // If did not pressed Cancel button
  		   if (newTitle != null) {
  		   // Update event
  		   event.title = newTitle.trim() != "" ? newTitle : event.title;
  		  
  		   // Call the "updateEvent" method
  		   $calendar.fullCalendar("updateEvent", event);
  		  
  		   }
  		},
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