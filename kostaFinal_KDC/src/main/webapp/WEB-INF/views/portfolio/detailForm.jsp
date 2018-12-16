<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>detailFrom 입니다</h1>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data">
  작성자 아이디 : <input type="text" name="portFolioDetailMemberId"/></p></br>
  프로젝트명 : <input type="text" name="portfolioDetailProjectName"/></p></br>
  프로젝트 이미지 <input type="file" name="DeltailProjectImage" /></p></br> 
  프로젝트 상세설명 <textarea name="portfolioDetailDescription"></textarea>
  <input type="submit" value="전송" />
</form>

</body>
</html>