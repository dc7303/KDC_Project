<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<h1>detailFrom �Դϴ�</h1>
<form action="${pageContext.request.contextPath }/portfolio/insertDetail?${_csrf.parameterName}=${_csrf.token}"
   method="post" enctype="multipart/form-data">
  �ۼ��� ���̵� : <input type="text" name="portFolioDetailMemberId"/></p></br>
  ������Ʈ�� : <input type="text" name="portfolioDetailProjectName"/></p></br>
  ������Ʈ �̹��� <input type="file" name="DeltailProjectImage" /></p></br> 
  ������Ʈ �󼼼��� <textarea name="portfolioDetailDescription"></textarea>
  <input type="submit" value="����" />
</form>

</body>
</html>