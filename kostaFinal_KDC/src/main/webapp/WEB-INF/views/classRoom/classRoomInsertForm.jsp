<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/lib/jquery-ui-admin/jquery-ui.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
 

 <script type="text/javascript" src="${pageContext.request.contextPath }/resources/lib/jquery-ui-admin/jquery-ui.min.js"></script>
<script type="text/javascript">
	const jq = jQuery.noConflict();

	jq(function(){
	  jq("#classRoomInfoTeacherId").on('keyup',function(){
	    jq.ajax({
	      url:'${pageContext.request.contextPath}/classRoom/teacherCheck' ,			// 서버 요청 주소
	      type:"post" ,			// 전송 방식. get or post
	      dataType:"text" ,		// 서버가 보내오는 데이터타입(text,html,xml,json)
	      data:"teacherId="+this.value,	// parameter
	      beforeSend: function(xhr) {
	        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	      },
	      success:function(result){		// 성공 했을 시 함수
	      		jq("#aJaxResult2").text(result);
	      } ,
	      error: function(err){		// 실패 했을 시 함수
	      	alert(err+" => 오류 발생")
	      }
	    })
	  })
	  
      //datepicker option
      jq( function() {
        jq( ".datepicker" ).datepicker({
          dateFormat: 'yy-mm-dd',
        });
      });
	  
	  jq(document).on('click','#submit',function(){
	    if(jq('#classRoomInfoName').val() === ''){
	      alert("클래스 이름을 입력해주세요.");
	      jq('#classRoomInfoName').focus();
	      return false;
	    }
	  })
	});
</script>

<style>

body{
   text-align: center;
}

</style>

</head>
<body>
<form action="${pageContext.request.contextPath }/classRoom/insertClassRoom" method="post" >
<table border="1">
  <tr>
   <th>클래스 이름</th>
   <td><input type="text" autocomplete="off" name="classRoomInfoName" id="classRoomInfoName"></td>
 </tr>
  <tr>
   <th>클래스 개강일</th>
   <td><input type="text" class="datepicker" autocomplete="off" name="classRoomInfoStartDate" ></td>
 </tr>
  <tr>
   <th>클래스 종강일</th>
   <td><input type="text" class="datepicker" autocomplete="off" name="classRoomInfoEndDate" ></td>
 </tr>
 <tr>
   <th>클래스 강사 아이디</th>
   <td><input type="text" name="classRoomInfoTeacherId" id="classRoomInfoTeacherId" autocomplete="off" value="${requestScope.memberId }"><span id="aJaxResult2" ></span></td>
 </tr>
 <tr>
   <th colspan="2">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     <input type="submit" value="등록하기" id="submit">
     <input type="reset" value="취소하기">
   </th>
 </tr>
</table>
</form>
</body>
</html>