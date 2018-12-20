<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath }/resources/js/testjs/core/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
    	$("#other").click(function() {
    	  	$(".otherText").remove();
        	$("span").after("<br class='otherText'><input class='otherText'name='reportContents'type='text'>");
      	});
  	});
	$(function() {
    	$(".reportCheck").click(function() {
     		$(".otherText").remove();   
      });
  });
</script>

<script type="text/javascript">
	$(function() {
	  $("input[value=신고하기]").click(function() {
	    var reportContents = $("[name=reportContents]:checked").val();
	    var otherWords = $("#otherText").val();
	    $.ajax({
	      url : "${pageContext.request.contextPath}/reply/reportPop",
	      type : "post",   
	      dataType : "text", 
	      beforeSend: function(xhr) {
	         xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
	      },
	      data : "reportContents="+reportContents+"&replyBoardPkReport=${requestScope.replyBoardPkReport}&otherWords="+otherWords,
	      success : function(result){   //성공했을 때
	        alert("신고되었습니다.");
	        window.close();   
	      },
	      error : function(err){   //실패했을 때
	         alert(err+" => 오류 발생");
	      }
 
    	});
	 });
  });
    
</script>
</head>
<body>
<div>
<h1>신고하기</h1>
<div>
<h3>작성자 : 호또혼</h3>
<h4>사유 선택 : 여러사유에 해당되는 경우, 대표적인 사유 1개를 선택해주세요</h4> 
  <fieldset>
    <input type="radio" name="reportContents" class="reportCheck" value="부적절한 홍보 게시글">부적절한 홍보 게시글<br>
    <input type="radio" name="reportContents" class="reportCheck" value="욕설/사생활침해/명예훼손">욕설/사생활침해/명예훼손<br>
    <input type="radio" name="reportContents" id="other"><span>기타</span><br>
    
  </fieldset>
  <br>
    <input type="button" value="신고하기">
    <input type="button" value="취소">
</div>
</div>
</body>
</html>

