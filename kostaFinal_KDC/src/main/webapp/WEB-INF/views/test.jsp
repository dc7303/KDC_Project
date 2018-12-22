<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/resources/lib/jquery-3.3.1.min.js"></script>
<script type="text/javascript">

const jq = jQuery.noConflict();

jq(function(){
  jq(document).on('click','#messageList',function(){
    /* if (${member.memberId }.equels()) {
      
      redirect
    } */
    /* alert("${member.memberId}");
    if(('${member.memberId }').val() == ''){ */
      alert("로그인해주세요");
      self.close();
    /* } */
    
/*     jq.ajax({
      url:"${pageContext.request.contextPath}/message/checkId" , //서버요청주소
      type:"post" , //전송방식(get or post)
      dataType:"text", //서버가 보내주는 데이터타입(text,html,xml,json)
      data:"senderId="+senderId, //서버에게 보낼 parameter정보
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success:function(result){
        if(result=='0'){
          alert("삭제된 아이디 혹은 없는 아이디 입니다.");
          self.close();
        }else{
          location.href='${pageContext.request.contextPath}/message/replyMessage?senderId='+senderId;
        }
        
      } , //성공했을때
      error:function(err){
        alert(err+" => 오류 발생")
      }  //실패했을때
    }) */
  })
})

</script>
</head>
<body>
<h1>test</h1>

<a href="${pageContext.request.contextPath}/message/list?id=${member.memberId }"  id ="messageList" >전체메세지 출력</a> <br>
<a href="${pageContext.request.contextPath}/board/boardList?id=${member.memberId }">전체게시물 출력</a> <br>
<a href="${pageContext.request.contextPath}/classRoom/classList?id=heejung">heejung클래스 전체 출력</a> <br>
<a href="${pageContext.request.contextPath}/classRoom/classCreate">클래스 생성</a> <br>

</body>
</html>