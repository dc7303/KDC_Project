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

<script type="text/javascript">

const jq = jQuery.noConflict();

$(function() {
  //이벤트 데이터베이스에서 불러오기
  const eventArr = (() => {
    const arr = [];    	//이벤트 담을 배열
    const dateLength = $('input[name=title]').length;

    //hidden값 가져오기.
    //추후 좀 더 좋은 방향으로 개선되어야함.
    for (let i = 0; i < dateLength; i++) {
      let title = $('input[name=title]')
        .eq(i)
        .val();
      let start = $('input[name=startDate]')
        .eq(i)
        .val();
      let end = $('input[name=endDate]')
        .eq(i)
        .val();

      //배열에 추가
      arr.push({
        title: title,
        start: start,
        end: end
      });
    }

    return arr;
  })();

  //캘린더 객체생성
  var cal = $('#calendar').fullCalendar({
    header: {
      left: 'none',
      center: 'title'
    },
    //셀렉트 이벤트 가능 설정.
    selectable: true,
    //셀렉트 이벤트 발생시 메소드
    select: function(start, end, jsEvent, view) {
      var title = prompt('등록하실 이벤트를 입력하세요.', 'New event');
      
      if (title !== null) {
        var event = {
          title: title.trim() !== '' ? title : 'New event',
          start: start,
          end: end
        };
        
        jq.ajax({
          url: '${contextPath.request.contextPath}/calendar/calendarInsert',
          type: 'post',
          dataType: 'text',
          data: event,
          success: function(result) {
            console.log(result);
          },
          error: function(err) {
            console.log(err);
          }
        });
        
        cal.fullCalendar('renderEvent', event, true);
      }
      cal.fullCalendar('unselect');
    },
    //수정 가능 이벤트 설정
    editable: true,
    //이벤트 수정 이벤트 
    eventClick: function(event, jsEvent, view) {
      var newTitle = prompt('수정할 이벤트 제목을 입력하세요.', event.title);

      if (newTitle !== null) {
        event.title = newTitle.trim() !== '' ? newTitle : event.title;
        
        cal.fullCalendar('updateEvent', event);
      }
    },
    events: eventArr	//불러온데이터 초기화
  });
});

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