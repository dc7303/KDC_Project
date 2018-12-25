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
      url:'${pageContext.request.contextPath}/admin/sendMessage' ,			// ���� ��û �ּ�
      type:"post" ,			// ���� ���. get or post
      dataType:"text" ,		// ������ �������� ������Ÿ��(text,html,xml,json)
      data:replyF ,	// parameter
      beforeSend: function(xhr) {
        xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
      },
      success:function(result){		// ���� ���� �� �Լ�
      	alert("�����߽��ϴ�.");
      	self.close();
      } ,
      error: function(err){		// ���� ���� �� �Լ�
      	alert(err+" => ���� �߻�")
      }
    })
  })
})
</script>
</head>
<body>

<form action="reply" id="replyForm" name="replyForm"><p/>
�޴� ��� : <input type="text" name="receiverId" value="${requestScope.senderId }" readonly><p/>
���� ���� : <input type="text" name="title" ><p/>
���� ���� : <input type="text" name="context"><p/>
<input type="button" value="����" id="btn" name="btn">
<input type="reset" value="�ٽþ���">
</form>
</body>
</html>