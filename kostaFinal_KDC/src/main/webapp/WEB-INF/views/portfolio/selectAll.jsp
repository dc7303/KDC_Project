<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
  const jq = jQuery.noConflict(); 
  jq(function(){
    jq('.portfolio').on('click',function(){
      var memberId = jq(this).find('.hidden-memberId').val();
      location.href='${pageContext.request.contextPath}/portfolio/selectAllDetail/'+memberId;
    })
  })
</script>
</head>
<body>
  select All

  <c:forEach items="${portfolioList}" var="portfolio">
    <div class="portfolio">
      <h3>작성자 : ${portfolio.portFolioMemberId}</h3>
      <h4>제목 : ${portfolio.portFolioMainTitle}</h4>
      <c:choose>
        <c:when test="${empty portfolio.portFolioMainImage}">
          <h5>등록된 대표이미지가 없습니다.</h5>
        </c:when>
        <c:otherwise>
          <img
            src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
        </c:otherwise>
      </c:choose>
      <input type="hidden" value="${portfolio.portFolioMemberId}" class="hidden-memberId">
    </div>
    <hr color="red">
  </c:forEach>
</body>
</html>