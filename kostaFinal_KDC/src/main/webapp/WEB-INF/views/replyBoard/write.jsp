<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

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

<form name="writeForm" method="post" action="${pageContext.request.contextPath}/reply/insert?title=${requestScope.title}">
<input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
<table align="center" cellpadding="5" cellspacing="2" width="600" border="1" >

    <tr>
        <td width="1220" height="20" colspan="2" bgcolor="#00cc00">
            <p align="center"><font color="white" size="3"><b> 게시글 등록 </b></font></p>
        </td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">글 제목</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="replyBoardTitle" size="30"></span></b></td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">글 내용</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
		<input type=text name="replyBoardContents" size="50"></span></b></td>
    </tr>
    <tr>
        <td width="450" height="20" colspan="2" align="center"><b><span style="font-size:9pt;"><input type=submit value=글쓰기> 
        <input type=reset value=다시쓰기></span></b></td>
    </tr>
</table>

</form>

<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/reply/tech?title=${requestScope.title}">리스트로 돌아가기</a>&gt;</span></div>

</BODY>
</HTML>