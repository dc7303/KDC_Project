<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	const jq = jQuery.noConflict();

	jq(function(){
	  jq("#classRoomCode").on('keyup',function(){
	    jq.ajax({
	      url:'${pageContext.request.contextPath}/admin/codeCheck' ,			// 서버 요청 주소
	      type:"post" ,			// 전송 방식. get or post
	      dataType:"text" ,		// 서버가 보내오는 데이터타입(text,html,xml,json)
	      data:"classRoomCode="+this.value,	// parameter
	      beforeSend: function(xhr) {
	        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	      },
	      success:function(result){		// 성공 했을 시 함수
	      		jq("#aJaxResult").text(result);
	      } ,
	      error: function(err){		// 실패 했을 시 함수
	      	alert(err+" => 오류 발생")
	      }
	    })
	  })
	  
	  jq("#classRoomInfoTeacherId").on('keyup',function(){
	    jq.ajax({
	      url:'${pageContext.request.contextPath}/admin/teacherCheck' ,			// 서버 요청 주소
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
	})
</script>
</head>
<body>
<form action="${pageContext.request.contextPath }/admin/insertClassRoom" method="post" >
<table border="1">
 <tr>
   <th>클래스 코드</th>
   <td><input type="text" name="classRoomCode" id="classRoomCode" value=""><span id="aJaxResult" ></span></td>
 </tr>
  <tr>
   <th>클래스 이름</th>
   <td><input type="text" name="classRoomInfoName" ></td>
 </tr>
  <tr>
   <th>클래스 개강일</th>
   <td><input type="text" name="classRoomInfoStartDate" ></td>
 </tr>
  <tr>
   <th>클래스 종강일</th>
   <td><input type="text" name="classRoomInfoEndDate" ></td>
 </tr>
 <tr>
   <th>클래스 강사 아이디</th>
   <td><input type="text" name="classRoomInfoTeacherId" id="classRoomInfoTeacherId" value=""><span id="aJaxResult2" ></span></td>
 </tr>
 <tr>
   <th>클래스 채팅 파일 명</th>
   <td><input type="text" name="classRoomInfoChatFile" ></td>
 </tr>
 <tr>
   <th colspan="2">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     <input type="submit" value="등록하기">
     <input type="reset" value="취소하기">
   </th>
 </tr>
</table>
</form>
</body>
</html>