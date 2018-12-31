<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>나의 클래스룸</title>
<script type="text/javascript">
	const jq = jQuery.noConflict();
	
	jq(document).on('click', '#insertMyClassRoom', function(){
	  var myClassRoomCode = jq('#myClassRoomCode').val();
	  jq.ajax({
	    url:'${pageContext.request.contextPath}/classRoom/insertMyClassRoom' ,			// 서버 요청 주소
	    type:"post" ,			// 전송 방식. get or post
	    dataType:"text" ,		// 서버가 보내오는 데이터타입(text,html,xml,json)
	    data:"myClassRoomCode=" + myClassRoomCode ,	// parameter
	    beforeSend: function(xhr) {
	      xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	    },
	    success:function(result){		// 성공 했을 시 함수
	      	alert(result);
	      	window.location.reload(true);
	    } ,
	    error: function(err){		// 실패 했을 시 함수
	    	alert(err+" => 오류 발생")
	    }
	  })
	})
	
	jq(document).on('change', '.selectRadio', function(){
	  if(this.checked){
	    var classRoomCode = jq(this).val();
	  }
	  jq.ajax({
	    url:'${pageContext.request.contextPath}/classRoom/defaultClassSet' ,			// 서버 요청 주소
	    type:"post" ,			// 전송 방식. get or post
	    dataType:"text" ,		// 서버가 보내오는 데이터타입(text,html,xml,json)
	    data:"classRoomCode=" + classRoomCode ,	// parameter
	    beforeSend: function(xhr) {
	      xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	    },
	    success:function(result){		// 성공 했을 시 함수
	      	alert("기본 클래스로 지정되었습니다.")
	      	window.location.reload(true);
	    } ,
	    error: function(err){		// 실패 했을 시 함수
	    	alert(err+" => 오류 발생")
	    }
	  })
	})	
	
</script>

</head>
<body>
<br>
<h3>나의 클래스룸</h3>
<br>
<form>
클래스 코드 입력 : <input type="text" name="myClassRoomCode" id="myClassRoomCode" value="">
<input type="button" id="insertMyClassRoom" value="전송">
</form>
<br/>
<h4>기본 클래스룸</h4>
<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">


   <tr>
        <th>
            <p>
            <font><b><span>클래스명</span></b></font></p> 
        </th>
        <th>
            <p><font><b><span>클래스 시작일</span></b></font></p>
        </th>
        <th>
            <p><font><b><span>클래스 종료일</span></b></font></p>
        </th>
    </tr>

    <c:choose>
    <c:when test="${empty requestScope.classRoomIsCurrentList}">
    <tr>
        <td colspan="4">
            <p align="center"><b><span>기본으로 정해진 클래스가 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    <c:otherwise>
   <c:forEach items="${requestScope.classRoomIsCurrentList}" var="classRoomIsCurrentList">
          <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
              <td bgcolor="">
                  <p align="center">${classRoomIsCurrentList.classRoomInfoName}</span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
                 ${classRoomIsCurrentList.classRoomInfoStartDate}
               </span></p>
              </td>
              <td bgcolor="">
                  <p align="center"><span style="font-size:9pt;">
                  ${classRoomIsCurrentList.classRoomInfoEndDate}</span></p>
              </td>
          </tr>
    </c:forEach>
  </c:otherwise>
    </c:choose>
</table>

<br/>

<h4>내가 수강한 클래스</h4>
<table align="center" border="0" cellpadding="5" cellspacing="2" width="100%" bordercolordark="white" bordercolorlight="black">


   <tr>
        <td>
            <p>
            <font><b><span>선택</span></b></font></p> 
        </td>
        <td>
            <p><font><b><span>클래스명</span></b></font></p>
        </td>
        <td>
            <p><font><b><span>클래스 시작일</span></b></font></p>
        </td>
        <td>
            <p><font><b><span>클래스 종료일</span></b></font></p>
        </td>
    </tr>

    <c:choose>
    <c:when test="${empty requestScope.myClassRoomList}">
  <tr>
        <td colspan="4">
            <p align="center"><b><span>등록된 클래스가 없습니다.</span></b></p>
        </td>
    </tr>
    </c:when>
    <c:otherwise>
   <c:forEach items="${requestScope.myClassRoomList}" var="myClassRoomList">
          <tr onmouseover="this.style.background='#eaeaea'" onmouseout="this.style.background='white'">
              <td bgcolor="">
                  <input type="hidden" value="${myClassRoomList.classRoomCode}">
                  <p align="center"><span style="font-size:9pt;"><input type="radio" name="classCode" class="selectRadio" value="${myClassRoomList.classRoomCode }">
                  </span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
                 ${myClassRoomList.classRoomInfoName}
               </span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
                 ${myClassRoomList.classRoomInfoStartDate}
               </span></p>
              </td>
              <td bgcolor="">
               <p><span style="font-size:9pt;">
                 ${myClassRoomList.classRoomInfoEndDate}
               </span></p>
              </td>
          </tr>
    </c:forEach>
  </c:otherwise>
    </c:choose>
</table>

</body>
</html>