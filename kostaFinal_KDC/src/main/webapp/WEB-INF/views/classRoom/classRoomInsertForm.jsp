<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
<script type="text/javascript">
	const jq = jQuery.noConflict();

	jq(function(){
	  jq("#classRoomCode").on('keyup',function(){
	    jq.ajax({
	      url:'${pageContext.request.contextPath}/admin/codeCheck' ,			// ���� ��û �ּ�
	      type:"post" ,			// ���� ���. get or post
	      dataType:"text" ,		// ������ �������� ������Ÿ��(text,html,xml,json)
	      data:"classRoomCode="+this.value,	// parameter
	      beforeSend: function(xhr) {
	        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	      },
	      success:function(result){		// ���� ���� �� �Լ�
	      		jq("#aJaxResult").text(result);
	      } ,
	      error: function(err){		// ���� ���� �� �Լ�
	      	alert(err+" => ���� �߻�")
	      }
	    })
	  })
	  
	  jq("#classRoomInfoTeacherId").on('keyup',function(){
	    jq.ajax({
	      url:'${pageContext.request.contextPath}/admin/teacherCheck' ,			// ���� ��û �ּ�
	      type:"post" ,			// ���� ���. get or post
	      dataType:"text" ,		// ������ �������� ������Ÿ��(text,html,xml,json)
	      data:"teacherId="+this.value,	// parameter
	      beforeSend: function(xhr) {
	        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	      },
	      success:function(result){		// ���� ���� �� �Լ�
	      		jq("#aJaxResult2").text(result);
	      } ,
	      error: function(err){		// ���� ���� �� �Լ�
	      	alert(err+" => ���� �߻�")
	      }
	    })
	  })
	})
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
   <th>Ŭ���� �ڵ�</th>
   <td><input type="text" name="classRoomCode" id="classRoomCode" value=""><span id="aJaxResult" ></span></td>
 </tr>
  <tr>
   <th>Ŭ���� �̸�</th>
   <td><input type="text" name="classRoomInfoName" ></td>
 </tr>
  <tr>
   <th>Ŭ���� ������</th>
   <td><input type="text" name="classRoomInfoStartDate" ></td>
 </tr>
  <tr>
   <th>Ŭ���� ������</th>
   <td><input type="text" name="classRoomInfoEndDate" ></td>
 </tr>
 <tr>
   <th>Ŭ���� ���� ���̵�</th>
   <td><input type="text" name="classRoomInfoTeacherId" id="classRoomInfoTeacherId" value=""><span id="aJaxResult2" ></span></td>
 </tr>
 <tr>
   <th>Ŭ���� ä�� ���� ��</th>
   <td><input type="text" name="classRoomInfoChatFile" ></td>
 </tr>
 <tr>
   <th colspan="2">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
     <input type="submit" value="����ϱ�">
     <input type="reset" value="����ϱ�">
   </th>
 </tr>
</table>
</form>
</body>
</html>