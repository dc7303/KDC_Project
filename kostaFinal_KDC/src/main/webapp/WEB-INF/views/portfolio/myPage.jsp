<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
	const jq = jQuery.noConflict();
	jq(function(){
	  
	  //유효성검사(제목 여부만 체크)
	  jq('#portfolio-form > input[type=button]').on('click',function(){
	    var title = jq('input[name=portFolioMainTitle]');
	    if (title.val().trim() === '') {
	      alert('제목은 필수항목 입니다.');
	      title.focus();
	    } else {
	      jq('#portfolio-form').submit();
	    }
	  });
  	

    //게시여부가 true일 경우 체크박스 체크
    if (jq('#original-visibility').val() === 'true') {
      jq('input[name=portFolioVisibility]').attr('checked', '');
    }

    /* 수정버튼 클릭시 수정폼 노출, 정보 숨김 */
    jq('#update-portfolio').on('click', function() {
      if (jq('#portfolio-update-form').css("display") === 'none') {
        jq('#portfolio-update-form').css("display", "block");
        jq('#portfolio-info').css("display", "none");
      }
    });

    //포트폴리오 상세 수정버튼 클릭 이벤트(a 태그 대체해도 좋을듯함)
    jq('.update-detail')
        .on(
            'click',
            function() {
              var pk = jq(this).attr('id');
              pk = pk.substr(7);
              location.href = '${pageContext.request.contextPath}/portfolio/selectDetail/'
                  + pk;
            });

  });
</script>
</head>
<body>
<sec:authentication var="member" property="principal" />
  <h3>${member.memberId}님의 portfolio/myPage입니다.</h3>
  <c:choose>
    <c:when test="${empty portfolio}">
        <!-- 대표이미지, 제목, 게시여부(체크박스) -->
      <form
        action="${pageContext.request.contextPath }/portfolio/insertPortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data" id="portfolio-form">
        <label>제목 : </label><input type="text" name="portFolioMainTitle"/></p></br> 
        <label>대표이미지 : </label><input type="file" name="MainImageFile" /></p></br> 
        <input type="checkBox" name="portFolioVisibility"/> <label>- 게시여부 </label></p></br>
        <input type="button" value="전송" />
      </form>
    </c:when>
    <c:otherwise>
      <!-- 포트폴리오가 있으면 포트폴리오 정보 노출 -->  
      <div id="portfolio-info">
        <h5>제목: ${portfolio.portFolioMainTitle} </h5></p>
        <h5>대표이미지 :</h5>
        <c:choose>      
          <c:when test="${not empty portfolio.portFolioMainImage}">
            <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
          </c:when>
          <c:otherwise>
            <h5>이미지가 없습니다.</h5>
          </c:otherwise>
        </c:choose>
        </br>
        <label>개시여부:</label>
        <input type="hidden" id="original-visibility" value="${portfolio.portFolioVisibility}"/>
        <input type="checkBox" name="portFolioVisibility" disabled/></p></br>
        </br>
        <input type="button" value="수정하기" id="update-portfolio"/>
      </div>
      
      <!-- 포트폴리오 수정일 경우 폼(수정버튼 클릭시 노출) -->
     <div id="portfolio-update-form" style="display:none;">
      <form
        action="${pageContext.request.contextPath }/portfolio/updatePortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data" id="portfolio-form">
        
        <label>제목 : </label>
        <input type="text" name="portFolioMainTitle" value="${portfolio.portFolioMainTitle}"/></p></br> 
        <c:if test="${not empty portfolio.portFolioMainImage}">
        <label>현재이미지 : </label>
        <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
        </c:if>         
        </br>         
        <label>대표이미지 : </label><input type="file" name="MainImageFile" /></p></br> 
        <input type="checkBox" name="portFolioVisibility" value="${portfolio.portFolioVisibility}"/> - 개시여부  </p></br>
        <input type="button" value="수정완료" />
      </form>
     </div>
     <!-- 포트폴리오가 존재하는경우 정보 노출 -->
     <c:choose>
       <c:when test="${empty portfolio.portFolioDetailList}">
         <h5>포트폴리오 상세가 1도 없습니다 추가해주세요.</h5>
       </c:when>
       <c:otherwise>
         </br>
         <hr>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail">
         <div>
           <h5>프로젝트명 : </h5>${detail.portfolioDetailProjectName}</p>
           <h5>해쉬태그 : </h5>
           <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
              ${hashTag.hashTagName}
           </c:forEach>
            </p>
            </br>
            <c:choose>
              <c:when test="${empty detail.portfolioDeltailProjectImage}">
                <h5>사진이 없습니다.</h5>
              </c:when>
              <c:otherwise>
                <h5>포트폴리오 이미지 : </h5>
                <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
              </c:otherwise>
            </c:choose>     
          </br>     
          <input type="button" value="수정하기" class="update-detail" id="detail-${detail.portFolioDetailPk}"/>
          <hr>
         </div>
         </c:forEach>
       </c:otherwise>
     </c:choose>
     <br>
     <a href="${pageContext.request.contextPath }/portfolio/detailForm">포트폴리오 상세 추가</a>
    </c:otherwise>
  </c:choose>
  
</body>
</html>
