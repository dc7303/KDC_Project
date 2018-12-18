<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/board.css" />
    
    <noscript><link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" /></noscript>

<SCRIPT language=javascript>
function checkValid() {
    var f = window.document.writeForm;
		
	if ( f.modelNum.value == "") {
	    alert( "제목을 입력해 주세요." );
	    f.modelNum.focus();
		return false;
    }
	if ( f.modelName.value == "" ) {
		alert( "글 내용을 입력해 주세요." );
		f.modelName.focus();
		return false;
	}
    return true;
}
</script>

</head>
<body>


<form name="writeForm" method="post" action="${pageContext.request.contextPath}/reply/insert?classification=${requestScope.classification}">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>

<table>
       <thead>
          <tr class="titel-color">
            <td colspan="6">글제목</td>
            <td>글쓴이</td>
            <td>등록날짜</td>
          </tr>
        </thead>

    <tr>
      <td colspan="6">
      <span><input type=text name="replyBoardTitle" placeholder="게시글 제목 작성"></span>
      </td>
      <td>
      <span>글쓴이닉네임표출</span>
      </td>
      <td>
      <span>현재시간표출</span>
      </td>
    </tr>
    
    <tr>
      <td class="tech-content" colspan="8">
      <input type=text name="replyBoardContents" placeholder="게시글 내용 작성" style="height:100%;">
      </td>
    </tr>
    <tr>
   
      <td colspan="8">
      <span><input type=text name="hashTagName" placeholder="해시태그 작성" size="50"></span>
      </td>
    </tr>

    <tr>
      <td colspan="8" height="20" colspan="4" align="center" valign="middle">

      <input type=submit value="글쓰기">
      <input type=reset value="다시쓰기">

      </td>
    </tr>
</table>
</form>


<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?classification=${requestScope.classification}">리스트로 돌아가기</a>&gt;</span></div>

</BODY>
</HTML>