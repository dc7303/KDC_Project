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
    
    //포트폴리오 상세보기 이벤트
    jq('.portfolio').on('click',function(){
      var memberId = jq(this).find('.hidden-memberId').val();
      location.href='${pageContext.request.contextPath}/portfolio/selectAllDetail/'+memberId;
    });
  });
</script>
</head>
<body>
  select All
  <!-- 검색창 -->
  <form action="${pageContext.request.contextPath}/portfolio/portfolioListSearch">
    <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
    <select name="keyfield" id="department">
      <option value="">- 분류 -</option>
      <option value="portfolioTitle">포트폴리오 제목</option>
      <option value="projectName">프로젝트명</option>
      <option value="description">내용</option>
      <option value="nickname">작성자</option>
      <option value="hashTag">해시태그</option>
    </select>
    <input class="tech-board-search" type="text" name="keyword"/>
    <input type="submit" value="검색"/>
  </form>
    
  <c:choose>
    <c:when test="${empty portfolioList}">
      <h3>현재 게시중인 포트폴리오가 없습니다.</h3>
    </c:when>
    <c:otherwise>
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
    </c:otherwise>
  </c:choose>
  
</body>
</html>
