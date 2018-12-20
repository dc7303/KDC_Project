<%@ page language="java" contentType="text/html; charset=EUC-KR"
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
  
  jq("#sub").click(function(){

    var replyF = $("form[name=replyForm]").serialize() ;
    
    jq.ajax({
      url:'${pageContext.request.contextPath}/admin/sendMessage' ,			// 서버 요청 주소
      type:"post" ,			// 전송 방식. get or post
      dataType:"text" ,		// 서버가 보내오는 데이터타입(text,html,xml,json)
      data:replyF ,	// parameter
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success:function(result){		// 성공 했을 시 함수
      	alert("전송했습니다.");
      	self.close();
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

<form action="reply" id="replyForm" name="replyForm"><p/>
받는 사람 : <input type="text" name="receiverId" value="${requestScope.senderId }" readonly><p/>
쪽지 제목 : <input type="text" name="title" ><p/>
쪽지 내용 : <input type="text" name="context"><p/>
<input type="button" value="전송" id="btn" name="btn">
<input type="reset" value="다시쓰기">
</form>
</body>
</html>