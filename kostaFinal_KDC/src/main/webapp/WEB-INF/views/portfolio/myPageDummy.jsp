<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
  <h1>myPageDummy</h1>
  <c:choose>
    <c:when test="${empty portfolio}">
        <!-- ���̵�, ��ǥ�̹���, ����, ���ÿ���(üũ�ڽ�) -->
      <form
        action="${pageContext.request.contextPath }/portfolio/insertPortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data">
                  ���̵� : <input type="text" name="portFolioMemberId"/></p></br>
                  ���� : <input type="text" name="portFolioMainTitle"/></p></br> 
                  ��ǥ�̹��� : <input type="file" name="MainImageFile" /></p></br> 
        <input type="checkBox" name="portFolioVisibility"/> - ���ÿ���  </p></br>
        <input type="submit" value="����" />
        
      </form>
    </c:when>
    <c:otherwise>
      <div>
            ���̵�: </p>
            ����: </p>
            ��ǥ�̹���: <img src="${pageContext.request.contextPath}/">
            ���ÿ���: </p>
     </div> 
  </c:otherwise>
  </c:choose>
  
</body>
</html>
