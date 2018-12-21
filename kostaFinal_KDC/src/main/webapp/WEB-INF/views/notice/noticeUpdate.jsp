<%@ page info="게시판 수정하기" contentType="text/html;charset=UTF-8" %>
<HTML>
<HEAD>
<link rel="stylesheet" href="css/style.css">

</HEAD>

<BODY>

<form name=requestForm method=post  id="requestForm" action="${pageContext.request.contextPath}/notice/update?noticeBoardPk=${requestScope.noticeBoardPk}&${_csrf.parameterName}=${_csrf.token}"  enctype="multipart/form-data">

  
	<table align="center" cellpadding="5" cellspacing="1" width="600" border="1">
    <tr>
        <td width="1220" height="20" colspan="2" bgcolor="#00cc00">
            <p align="center"><font color="white" size="3"><b> 게시물 수정하기</b></font></p>
        </td>
    </tr>
    <tr>
        <td width="150" height="20">
            <p align="right"><b><span style="font-size:9pt;">제목</span></b></p>
        </td>
        <td width="450" height="20"><b><span style="font-size:9pt;">
		<input type=text name="noticeBoardTitle" size="30"  value="${NoticeBoardDTO.noticeBoardTitle}"></span></b></td>
    </tr>

    <tr>
        <td width="150" height="20" >
            <p align="right"><b><span style="font-size:9pt;">내 용</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
		<textarea name="noticeBoardContents" cols="50" rows="10">${NoticeBoardDTO.noticeBoardContents}</textarea></span></b></td>
    </tr>
  
  <tr>
        <td width="150" height="20" >
            <p align="right"><b><span style="font-size:9pt;">첨부파일</span></b></p>
        </td>
        <td width="450" height="20" ><b><span style="font-size:9pt;">
          <input type="file" name="file" maxlength="60" size="40"> 
    <textarea name="noticeBoardAttachment" cols="50" rows="10">${NoticeBoardDTO.noticeBoardAttachment}</textarea></span></b></td>
    </tr>
 
    <tr>
        <td width="450" height="20" colspan="2" align="center"><b><span style="font-size:9pt;">
		<input type="submit" value="수정하기"> </span></b></td>
    </tr>
</table>
</form>
<hr>
<div align=right><span style="font-size:9pt;">&lt;<a href="${pageContext.request.contextPath}/notice/list">리스트로 돌아가기</a>&gt;</span></div>
</BODY>


</HTML>