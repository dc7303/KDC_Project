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
  //이벤트 데이터베이스에서 불러오기
  const eventArr = (() => {
    const arr = [];    	//이벤트 담을 배열
    const dateLength = $('input[name=title]').length;

    //hidden값 가져오기.
    //추후 좀 더 좋은 방향으로 개선되어야함.
    for (let i = 0; i < dateLength; i++) {
      let num = $('input[name=calendarNum]')
		.eq(i)
		.val();
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
        num: num,
        title: title,
        start: start,
        end: end
      });
    }
	console.log(arr);
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
<<<<<<< HEAD
=======
        }
      },
      header: {
        left: 'createButton',
        center: 'title'
      },
      height: 'auto',
      //jquery-ui theme 사용
      themeSystem: 'jquery-ui',
      //날짜 선택시 강조표시 설정
      selectHelper: true,
      //셀렉트 이벤트 가능 설정.
      selectable: true,
      //수정 가능 이벤트 설정
      editable: true,
      //셀렉트 이벤트 발생시 메소드
      select: setSelectInsert,
      //이벤트 클릭시 사용하는 메소드
      eventClick: setEventClick,
      //드래그앤 드롭 Update 이벤트 처리
      eventDrop: setDrop,
      //일정 Resize 수정
      eventResize: setResizeEvent,
      //캘린더 물리데이터 rendering 작업
      events: events
    });
  }

  /**
   * 이벤트 셋팅
   *
   * @param {*} start
   * @param {*} end
   * @param {*} timezone
   * @param {*} callback
   */
  function setEvents() {
    events = [];

    $.ajax({
      url: '/kdc/calendar/calendarSelectByClassCode',
      type: 'post',
      dataType: 'json',
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success: function(data) {
        $.each(data, function(index, item) {
          events.push({
            num: item.calendarPk,
            title: item.calendarTitle,
            start: item.calendarStart,
            end: item.calendarEnd,
            color: item.calendarColor,
          });
>>>>>>> MergBranch
        });
        
        cal.fullCalendar('renderEvent', event, true);
      }
      cal.fullCalendar('unselect');
    },
<<<<<<< HEAD
    //수정 가능 이벤트 설정
    editable: true,
    //이벤트 수정 이벤트 (이벤트 클릭스 이름 수정)
    eventClick: function(event, jsEvent, view) {
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
=======
    position: {
      my: 'center',
      at: 'center',
      of: '#calendar'
    }
  });

  //insertDialog 설정
  $('#insertDialog').dialog({
    autoOpen: false,
    modal: true,
    show: {
      effect: 'blind',
      duration: 300
    },
    hide: {
      effect: 'clip',
      duration: 300
    },
    position: {
      my: 'center',
      at: 'center',
      of: '#calendar'
    }
  });

  //datepicker 셋팅 및 설정
  $('.datepicker').datepicker({
    dateFormat: 'yy-mm-dd'
  });

  //등록버튼 클릭 이벤트
  $('#insertBtn').on('click', function() {
    $.ajax({
      url: '/kdc/calendar/calendarInsert',
      type: 'post',
      dataType: 'text',
      data: {
        title: $('#insertTitle').val(),
        start: $('#insertStart').val(),
        end: $('#insertEnd').val(),
        color: $('#insertColor').val(),
      },
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success: function(result) {

        setEvents();

        $('#insertDialog').dialog('close');
      },
      error: function(err) {
        console.log(err);
>>>>>>> MergBranch
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
    events: eventArr	//불러온데이터 초기화
  });
});

</script>

</head>
<body>
    <c:forEach items="${requestScope.calendarList}" var="calendarList" varStatus="state">
      <input type="hidden" name="calendarNum" value="${calendarList.calendarPk }"/>
      <input type="hidden" name="title" value="${calendarList.calendarTitle }"/>
      <input type="hidden" name="startDate" value="${calendarList.calendarStart }"/>
      <input type="hidden" name="endDate" value="${calendarList.calendarEnd }"/>
    </c:forEach>

<div id="calendar"></div>

</body>
</html>