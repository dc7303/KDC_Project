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
        <!-- 아이디, 대표이미지, 제목, 개시여부(체크박스) -->
      <form
        action="${pageContext.request.contextPath }/portfolio/insertPortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data">
                  아이디 : <input type="text" name="portFolioMemberId"/></p></br>
                  제목 : <input type="text" name="portFolioMainTitle"/></p></br> 
                  대표이미지 : <input type="file" name="MainImageFile" /></p></br> 
        <input type="checkBox" name="portFolioVisibility"/> - 개시여부  </p></br>
        <input type="submit" value="전송" />
        
      </form>
    </c:when>
    <c:otherwise>
      <div>
            아이디: </p>
            제목: </p>
            대표이미지: <img src="${pageContext.request.contextPath}/">
            개시여부: </p>
     </div> 
  </c:otherwise>
  </c:choose>
  
</body>
</html>
