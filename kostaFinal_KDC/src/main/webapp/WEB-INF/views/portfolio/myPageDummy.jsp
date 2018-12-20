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
	  console.log(jq('#original-visibility').val());
	  if(jq('#original-visibility').val()==='true'){
	    jq('input[name=portFolioVisibility]').attr('checked','');
	  }
	  
	  /* 수정버튼 클릭시 수정폼 노출, 정보 숨김 */
	  jq('#update-portfolio').on('click',function(){
	    if(jq('#portfolio-update-form').css("display")==='none'){
	      jq('#portfolio-update-form').css("display","block");
	      jq('#portfolio-info').css("display","none");
	    }
	  });
	  
	  //포트폴리오 상세 수정버튼 클릭 이벤트(a 태그 대체해도 좋을듯함)
	  jq('.update-detail').on('click',function(){
	    var pk = jq(this).attr('id');
	    pk = pk.substr(7);
	    location.href='${pageContext.request.contextPath}/portfolio/selectDetail/'+pk;
	  })
	  
	});
</script>
</head>
<body>
  <h1>myPageDummy</h1>
  <c:choose>
    <c:when test="${empty portfolio}">
        <!-- 아이디, 대표이미지, 제목, 게시여부(체크박스) -->
      <form
        action="${pageContext.request.contextPath }/portfolio/insertPortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data">
                  아이디 : <input type="text" name="portFolioMemberId"/></p></br>
                  제목 : <input type="text" name="portFolioMainTitle"/></p></br> 
                  대표이미지 : <input type="file" name="MainImageFile" /></p></br> 
        <input type="checkBox" name="portFolioVisibility"/> - 게시여부  </p></br>
        <input type="submit" value="전송" />
      </form>
    </c:when>
    <c:otherwise>
      <div id="portfolio-info">
            아이디: ${portfolio.portFolioMemberId}</p>
            제목: ${portfolio.portFolioMainTitle} </p>
            대표이미지 :
      <c:choose>      
         <c:when test="${not empty portfolio.portFolioMainImage}">
           <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
         </c:when>
         <c:otherwise>
                    이미지가 없습니다.
         </c:otherwise>
      </c:choose>
      </br>
            개시여부:<input type="hidden" id="original-visibility" value="${portfolio.portFolioVisibility}"/>
         <input type="checkBox" name="portFolioVisibility" disabled/></p></br>
            </br>
            <input type="button" value="수정하기" id="update-portfolio"/>
     </div>
     <div id="portfolio-update-form" style="display:none;">
      <form
        action="${pageContext.request.contextPath }/portfolio/updatePortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data">
                  아이디 : <input type="text" name="portFolioMemberId" value="${portfolio.portFolioMemberId}" readonly="true"/></p></br>
                  제목 : <input type="text" name="portFolioMainTitle" value="${portfolio.portFolioMainTitle}"/></p></br> 
         <c:if test="${not empty portfolio.portFolioMainImage}">
                  현재이미지 : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
         </c:if>         
         </br>         
                  대표이미지 : <input type="file" name="MainImageFile" /></p></br> 
        <input type="checkBox" name="portFolioVisibility" value="${portfolio.portFolioVisibility}"/> - 개시여부  </p></br>
        <input type="submit" value="수정완료" />
      </form>
     </div>
     <c:choose>
       <c:when test="${empty portfolio.portFolioDetailList}">
                포트폴리오 상세가 1도 없습니다 추가해주세요.
       </c:when>
       <c:otherwise>
         </br>
         </br>
         <hr>
         </br>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail">
         <div>
                    프로젝트명 : ${detail.portfolioDetailProjectName}</p>
                    해쉬태그 :
            <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
              ${hashTag.hashTagName}
            </c:forEach>
            </p>
            </br>
                    포트폴리오 이미지 : <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
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
