<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/portfolio-tiles.css" />

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
    
    
    
  <div class="tiles">
    
  <c:choose>
    <c:when test="${empty portfolioList}">
      <h3>현재 게시중인 포트폴리오가 없습니다.</h3>
    </c:when>
    <c:otherwise>
      <c:forEach items="${portfolioList}" var="portfolio">
        <div class="portfolio">
         
         <%--  <h4>${portfolio.portFolioMainTitle}</h4> --%>
          <c:choose>
            <c:when test="${empty portfolio.portFolioMainImage}">
              <h5>등록된 대표이미지가 없습니다.</h5>
            </c:when>
            <c:otherwise>
              <img class="selelctall-img"
                src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
            </c:otherwise>
            
          </c:choose>
           <h4 class="selectall-title">${portfolio.portFolioMainTitle}</h4>
          <input type="hidden" value="${portfolio.portFolioMemberId}" class="hidden-memberId">
        </div>
      
      </c:forEach>
    </c:otherwise>
  </c:choose>
   </div>
  
</body>

<script type="text/javascript">
var $tiles = $('#tiles'),
$handler = $('li', $tiles),
$main = $('#main'),
$window = $(window),
$document = $(document),
options = {
  autoResize: true, // This will auto-update the layout when the browser window is resized.
  container: $tiles, // Optional, used for some extra CSS styling
  offset: 10, // Optional, the distance between grid items
  outerOffset: 20, // Optional the distance from grid to parent
  itemWidth: 210 // Optional, the width of a grid item
};

/**
* Reinitializes the wookmark handler after all images have loaded
*/
function applyLayout() {
$tiles.imagesLoaded(function() {
// Destroy the old handler
if ($handler.wookmarkInstance) {
  $handler.wookmarkInstance.clear();
}

// Create a new layout handler.
$handler = $('li', $tiles);
  
$handler.wookmark(options);
});
}

/**
* When scrolled all the way to the bottom, add more tiles
*/
function onScroll() {
// Check if we're within 100 pixels of the bottom edge of the broser window.
var winHeight = window.innerHeight ? window.innerHeight : $window.height(), // iphone fix
  closeToBottom = ($window.scrollTop() + winHeight > $document.height() - 100);

if (closeToBottom) {
// Get the first then items from the grid, clone them, and add them to the bottom of the grid
var $items = $('li', $tiles),
    $firstTen = $items.slice(0, 10);
$tiles.append($firstTen.clone());

applyLayout();
}
};

// Call the layout function for the first time
applyLayout();

// Capture scroll event.
$window.bind('scroll.wookmark', onScroll);


</script>



</html>
