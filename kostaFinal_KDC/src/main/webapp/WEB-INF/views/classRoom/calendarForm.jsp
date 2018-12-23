<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/lib/fullCalendar/fullcalendar.min.css" rel="stylesheet">

<script src='${pageContext.request.contextPath}/resources/lib/fullCalendar/lib/jquery.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/lib/fullCalendar/lib/moment.min.js'></script>
<script src="${pageContext.request.contextPath}/resources/lib/fullCalendar/fullcalendar.min.js" type="text/javascript"></script>

<script type="text/javascript">

$(function() {
  //캘린더 객체생성
  var cal = $('#calendar').fullCalendar({
    header: {
      left: 'none',
      center: 'title'
    },
    selectHelper: true,
    //셀렉트 이벤트 가능 설정.
    selectable: true,
    //셀렉트 이벤트 발생시 메소드
    select: function(start, end, jsEvent, view) {
      var title = prompt('등록하실 이벤트를 입력하세요.', 'New event');
      
      if (title !== null) {
        var event = {
          title: title.trim() !== '' ? title : 'New event',
          start: start.format(),
          end: end.format(),
        };
         
        
        $.ajax({
          url: '/kdc/calendar/calendarInsert',
          type: 'post',
          dataType: 'text',
          data: event,
          beforeSend: function(xhr) {
            xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
          success: function(result) {
			
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
    //이벤트 수정 이벤트 (이벤트 클릭스 이름 수정)
    eventClick: function(event, jsEvent, view) {
      console.log(jsEvent);
      var newTitle = prompt('수정할 이벤트 제목을 입력하세요.', event.title);
	  
      if (newTitle !== null) {
        event.title = newTitle.trim() !== '' ? newTitle : event.title;
        
        $.ajax({
          url: '/kdc/calendar/calendarUpdateDate',
          type: 'post',
          dataType: 'text',
          data: {
            num: event.num,
            title: event.title,
            start: event.start.format(),
            end: event.end.format(),
          },
          beforeSend: function(xhr) {
            xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
          success: function(result) {
            //캘린더에 수정 뷰 적용
            cal.fullCalendar('updateEvent', event);
          },
          error: function(err) {
            console.log('실패했습니다. error : ' + err);
          }
        });
      }
    },
    //드래그앤 드롭 Update 이벤트 처리
    eventDrop: function(event, delta, revertFunc) {
      //취소시 되돌리기
      if (!confirm("정말로 수정하시겠습니까?")) {
        revertFunc();
      }else {
        //calendar 없데이트로.
        $.ajax({
          url: '/kdc/calendar/calendarUpdateDate',
          type: 'post',
          dataType: 'text',
          data: {
            num: event.num,
            title: event.title,
            start: event.start.format(),
            end: event.end.format(),
          },
          beforeSend: function(xhr) {
            xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
          },
          success: function(result) {},
          error: function(err) {
            console.log('실패했습니다. error : ' + err);
          }
        });
      }
    },
    //일정 Resize
    eventResize: function( event, delta, revertFunc, jsEvent, ui, view ) { 
      $.ajax({
        url: '/kdc/calendar/calendarUpdateDate',
        type: 'post',
        dataType: 'text',
        data: {
          num: event.num,
          title: event.title,
          start: event.start.format(),
          end: event.end.format(),
        },
        beforeSend: function(xhr) {
          xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
        },
        success: function(result) {},
        error: function(err) {
          console.log('실패했습니다. error : ' + err);
        }
      });
    },
    events: function(start, end, timezone, callback) {
      $.ajax({
        url: '/kdc/calendar/calendarSelectByClassCode',
        type : 'post',
        dataType: 'json',
        beforeSend: function(xhr) {
          xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
        },
        success: function(data) {
            var events = [];
            $(data).each(function(index, item) {
                events.push({
                  	num: item.calendarPk,
                    title: item.calendarTitle,
                    start: item.calendarStart,
                    end: item.calendarEnd,
                });
            });
            callback(events);
        },
        error: function(err) {
          console.log('err : ' + err);
        }
    });

	}
  });
});

</script>

</head>
<body>

<div id="calendar"></div>

</body>
</html>