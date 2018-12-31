<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/portfolio-tiles.css" />

<title>Insert title here</title>

<style>
.wf-container { margin: 0 auto; }
.wf-container:before,
.wf-container:after {
  content: '';
  display: table;
}
.wf-container:after { clear: both; }
.wf-box { margin: 10px; }
.wf-box img {
  display: block;
  width: 100%;
}
.wf-box .content {
  border: 1px solid #ccc;
  border-top-width: 0;
  padding: 5px 8px;
}
.wf-column { float: left; }
</style>


<script type="text/javascript">
const jq = jQuery.noConflict(); 

  jq(function(){
    
    //포트폴리오 상세보기 이벤트
    jq('.wf-box').on('click',function(){
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
    <div class="wf-container">
      <c:forEach items="${portfolioList}" var="portfolio">
          <div class="wf-box"><img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
            <div class="content">
              <h3>${portfolio.portFolioMainTitle}</h3>
              <p>${portfolio.portFolioMemberNickName }</p>
              <input type="hidden" value="${portfolio.portFolioMemberId}" class="hidden-memberId">
            </div>
          </div>
      </c:forEach>
      </div>
    </c:otherwise>
  </c:choose>
  
   
  <!-- pinterest-grid layout js-->
<script src="${pageContext.request.contextPath}/resources/lib/pinterest-grid/responsive_waterfall.js" ></script>
<script>

var waterfall = new Waterfall({
  containerSelector: '.wf-container',
  boxSelector: '.wf-box',
  minBoxWidth: 250
});

</script>
</body>

</html>
