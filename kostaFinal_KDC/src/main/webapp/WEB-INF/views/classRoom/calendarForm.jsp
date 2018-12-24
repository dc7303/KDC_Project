<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/resources/lib/fullCalendar/fullcalendar.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/lib/fullCalendar/fullcalendar.print.min.css" rel="stylesheet" media="print"/>
<link href="${pageContext.request.contextPath}/resources/lib/jquery-ui/jquery-ui.css" rel="stylesheet"/>
<style ref="stylesheet">
  #calendar {
    padding-top: 50px;
    padding-left: 100px;
    width: 65%;
  }

  /* header 설정 없을 시 비정상적으로 공백이 늘어나는 현상나타남 */
  .fc-header-toolbar {
    height: 50px;
  }
</style>
<script src='${pageContext.request.contextPath}/resources/lib/fullCalendar/lib/jquery.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/lib/jquery-ui/jquery-ui.min.js'></script>
</head>
<body>
<h3>000기 스케줄</h3>
<div id="calendar"></div>

<div id="updateDialog" title="일정 수정">
  <form>
  <div class="form-group">
    <input type="hidden" id="updateNum" value=""/>
    <label for="title">Title</label>
    <input type="text" class="form-control" id="updateTitle" aria-describedby="titleHelp" placeholder="Title">
    <small id="titleHelp" class="form-text text-muted">수정하실 일정 이름을 입력하세요.</small>
  </div>
  <div class="form-group">
    <label for="Start">Start</label>
    <input type="text" class="form-control datepicker" id="updateStart" aria-describedby="startHelp" placeholder="Start">
    <small id="startHelp" class="form-text text-muted">수정하실 일정 시작일을 입력해주세요.</small>
  </div>
  <div class="form-group">
    <label for="end">End</label>
    <input type="text" class="form-control datepicker" id="updateEnd" aria-describedby="endHelp" placeholder="End">
    <small id="endHelp" class="form-text text-muted">수정하실 일정 이름을 입력하세요.</small>
  </div>
  <input type="button" id="updateBtn" value="수정"/>
  <input type="button" id="deleteBtn" value="삭제"/>
  <input type="button" value="취소"/>
</form>
</div>

<div id="insertDialog" title="일정 추가">
<form>
  <div class="form-group">
    <label for="title">Title</label>
    <input type="text" class="form-control" id="insertTitle" aria-describedby="titleHelp" placeholder="Title">
    <small id="insertTitleHelp" class="form-text text-muted">추가하실 일정 이름을 입력하세요.</small>
  </div>
  <div class="form-group">
    <label for="Start">Start</label>
    <input type="text" class="form-control datepicker" id="insertStart" aria-describedby="startHelp" placeholder="Start">
    <small id="insertStartHelp" class="form-text text-muted">추가하실 일정 시작일을 입력해주세요.</small>
  </div>
  <div class="form-group">
    <label for="end">End</label>
    <input type="text" class="form-control datepicker" id="insertEnd" aria-describedby="endHelp" placeholder="End">
    <small id="insertEndHelp" class="form-text text-muted">추가하실 일정 이름을 입력하세요.</small>
  </div>
  <input type="button" id="insertBtn" value="등록"/>
  <input type="button" value="취소"/>
</form>
</div>



<script src='${pageContext.request.contextPath}/resources/lib/fullCalendar/lib/moment.min.js'></script>
<script src="${pageContext.request.contextPath}/resources/lib/fullCalendar/fullcalendar.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(function() {
  var events = [];
  setEvents();

  function generateCalendar(events) {
    $('#calendar').fullCalendar('destroy');
    //캘린더 객체생성
    $('#calendar').fullCalendar({
      customButtons: {
        createButton: {
          text: 'Create',
          click: function() {
            $('#insertDialog').dialog('open');
            $('#insertTitle').val('');
            $('#insertStart').val('');
            $('#insertEnd').val('');
          }
        }
      },
      header: {
        left: 'createButton',
        center: 'title'
      },
      height: 'auto',
      themeSystem: 'jquery-ui',
      selectHelper: true,
      //셀렉트 이벤트 가능 설정.
      selectable: true,
      //수정 가능 이벤트 설정
      editable: true,
      //셀렉트 이벤트 발생시 메소드
      select: setSelectInsert,
      //이벤트 수정 이벤트 (이벤트 클릭스 이름 수정)
      eventClick: setEventClick,
      //드래그앤 드롭 Update 이벤트 처리
      eventDrop: setDrop,
      //일정 Resize
      eventResize: setResizeEvent,
      //캘린더 물리데이터 불러오기
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
            end: item.calendarEnd
          });
        });
        generateCalendar(events);
      },
      error: function(err) {
        console.log('err : ' + err);
      }
    });
  }

   
/////////////////////////////////////////////////////////
   /**
    * 아래 Jquery-ui Dialog, Datepicker 설정
    */
///////////////////////////////////////////////////////////
  
  //Jquery-ui updateDialog 설정
  $('#updateDialog').dialog({
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
        end: $('#insertEnd').val()
      },
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success: function(result) {
        console.log('성공');
        setEvents();

        $('#insertDialog').dialog('close');
      },
      error: function(err) {
        console.log(err);
      }
    });
  });
  
  //수정버튼 클릭 이벤트
  $('#updateBtn').on('click', function() {
    $.ajax({
      url: '/kdc/calendar/calendarUpdateDate',
      type: 'post',
      dataType: 'text',
      data: {
        num: $('#updateNum').val(),
        title: $('#updateTitle').val(),
        start: $('#updateTitle').val(),
        end: $('#updateEnd').val()
      },
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success: function(result) {
        //캘린더에 수정 뷰 적용
        setEvents();

        $('#updateDialog').dialog('close');
      },
      error: function(err) {
        console.log('실패했습니다. error : ' + err);
      }
    });
  });

  //삭제이벤트
  $('#deleteBtn').on('click', function() {
    var confirmResult = confirm('정말 삭제하시겠습니까?');
    if (confirmResult) {
      $.ajax({
        url: '/kdc/calendar/calendarDelete',
        type: 'post',
        dataType: 'text',
        data: {
          num: $('#updateNum').val()
        },
        beforeSend: function(xhr) {
          xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
        },
        success: function(result) {
          //캘린더에 수정 뷰 적용
          setEvents();

          $('#updateDialog').dialog('close');
        },
        error: function(err) {
          console.log('실패했습니다. error : ' + err);
        }
      });
    }
  });

  $('input[value=취소]').on('click', function() {
    if($(this).parent().parent().attr('id') === 'insertDialog') {
      $('#insertDialog').dialog('close');
    }else {
      $('#updateDialog').dialog('close'); 
    }
  });
  
  /////////////////////////////////////////////////////////
  /**
   * 아래부터 캘린더 캘린더 function
   */
  ////////////////////////////////////////////////////////
  
  
  /**
   * 캘린더 클릭 시 이벤트 추가 이벤트 set
   *
   * @param {*} start
   * @param {*} end
   * @param {*} jsEvent
   * @param {*} view
   */
  function setSelectInsert(start, end, jsEvent, view) {
	$('#insertDialog').dialog('open');
	if(start) {
	  $('#insertStart').val(start.format());
	}
	if(end) {
	  $('#insertEnd').val(end.format());
	}
  }

  /**
   * 이벤트 클릭시 이벤트 발생
   *
   * @param {*} event
   * @param {*} jsEvent
   * @param {*} view
   */
  function setEventClick(event, jsEvent, view) {
    $('#updateDialog').dialog('open');
    $('#updateNum').val(event.num);
    $('#updateTitle').val(event.title);
    $('#updateStart').val(event.start.format());
    $('#updateEnd').val(event.end.format());
  }

  /**
   * Drag * Drop 이벤트 수정 set
   *
   * @param {*} event
   * @param {*} delta
   * @param {*} revertFunc
   */
  function setDrop(event, delta, revertFunc) {
    //취소시 되돌리기
    if (!confirm('정말로 수정하시겠습니까?')) {
      revertFunc();
    } else {
      //calendar 없데이트로.
      $.ajax({
        url: '/kdc/calendar/calendarUpdateDate',
        type: 'post',
        dataType: 'text',
        data: {
          num: event.num,
          title: event.title,
          start: event.start.format(),
          end: event.end.format()
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
  }

  /**
   * 일정 사이즈 조절 업데이트
   *
   * @param {*} event
   * @param {*} delta
   * @param {*} revertFunc
   * @param {*} jsEvent
   * @param {*} ui
   * @param {*} view
   */
  function setResizeEvent(event, delta, revertFunc, jsEvent, ui, view) {
    $.ajax({
      url: '/kdc/calendar/calendarUpdateDate',
      type: 'post',
      dataType: 'text',
      data: {
        num: event.num,
        title: event.title,
        start: event.start.format(),
        end: event.end.format()
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
});



</script>
</body>
</html>