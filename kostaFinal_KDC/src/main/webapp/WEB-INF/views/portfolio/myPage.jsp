<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/portfolio.css" />
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
<center>
<sec:authentication var="member" property="principal" />
  <h3 class="head-portfolio">${member.memberId}님의 Portfolio 제작 </h3>
  <c:choose>
    <c:when test="${empty portfolio}">
        <!-- 대표이미지, 제목, 게시여부(체크박스) -->
      <form
        action="${pageContext.request.contextPath }/portfolio/insertPortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data" id="portfolio-form">
        
        
        <label class="label-portfolio"> 1.포트폴리오 제목을 입력하세요.</label><p class="title-underline"></p>
        <input class="title-portfolio"type="text" name="portFolioMainTitle" /></p></br> <hr> 
        
        <label class="label-portfolio">2.대표이미지를 선택해주세요.</label>
        <input class="imgbtn-portfolio" type="file" name="MainImageFile" /></p></br> <hr> 
        
        <label class="label-portfolio">3.아래 체크박스를 선택하면 포트폴리오가 공개됩니다.</label></br>
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility"/></p></br>
        
        <hr>
        <input class="send-portfolio" type="button" value="전송" />
        
        
      </form>
    </c:when>
    <c:otherwise>
      <!-- 포트폴리오가 있으면 포트폴리오 정보 노출 -->  
      <div id="portfolio-info">
    
        <label class="label-portfolio">썸네일 제목</label>
         <h2 class="">${portfolio.portFolioMainTitle}</h2><p/><br/>

      <hr class="hr-border">
      
      
        <label class="label-portfolio">썸네일 대표이미지 </label><p/><br/>
        <c:choose>      
          <c:when test="${not empty portfolio.portFolioMainImage}">
            <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
          </c:when>
          <c:otherwise>
            <h5>이미지가 없습니다.</h5>
          </c:otherwise>
        </c:choose>
        </br>
        
         <hr class="hr-border">
        
        <label class="label-portfolio">포트폴리오 공개 여부</label><p/><br/>
        <input type="hidden" id="original-visibility" value="${portfolio.portFolioVisibility}"/>
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility" disabled/><p/><br/>
        </br>
        
        <hr class="hr-border">
        <input type="button" value="수정하기" class="update-portfolio" id="update-portfolio"/><p/><br/>
      </div>
      
      <!-- 포트폴리오 수정일 경우 폼(수정버튼 클릭시 노출) -->
     <div id="portfolio-update-form" style="display:none;">
      <form
        action="${pageContext.request.contextPath }/portfolio/updatePortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data" id="portfolio-form">
        
         <label class="label-portfolio">제목</label><p/><br/>
         <h2> <input type="text" name="portFolioMainTitle" value="${portfolio.portFolioMainTitle}"/></h2> </p></br> 
        <hr class="hr-border">
        
        
        <c:if test="${not empty portfolio.portFolioMainImage}">
         <label class="label-portfolio"> 현재이미지 </label><p/><br/>
        <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
        </c:if>         
        </br>         
        <hr class="hr-border">
        
         <label class="label-portfolio">대표이미지 수정 </label><p/><br/><input type="file" name="MainImageFile" /></p></br> 
         <hr class="hr-border">
         
          <label class="label-portfolio">개시여부</label><p/><br/>
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility" value="${portfolio.portFolioVisibility}"/> <p>
               <p>  *체크박스 선택시 포트폴리오가 공개됩니다</br>
                                        원하지 않으면 체크를 해제 해 주세요.  </p></br>
        <input class="update-portfolio" id="update-portfolio" type="button" value="수정완료" />
      </form>
     </div>
     <!-- 포트폴리오가 존재하는경우 정보 노출 -->
     <c:choose>
       <c:when test="${empty portfolio.portFolioDetailList}">
       
         <hr>
         <h6> *아래 버튼을 눌러 포트폴리오 제작을 마무리 하세요! </h6>
       </c:when>
       <c:otherwise>
         </br>
         <hr>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail">
         
         
           <!-- 여기부터 포트폴리오 상세내용  -->
         
           <h3 class="head-portfolio">Portfolio 상세내용 </h3>
         
         
         <div id="portfolio-detail">

          <label class="label-portfolio">프로젝트명</label>
         <h2 class="">${detail.portfolioDetailProjectName}</h2><p/><br/>
          <hr class="hr-border">
      
         
        
           <label class="label-portfolio">해쉬태그</label> </p>
            </br>
           <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
              ${hashTag.hashTagName}
           </c:forEach>
            </p>
            </br>
             <hr class="hr-border">
            
            
            <c:choose>
              <c:when test="${empty detail.portfolioDeltailProjectImage}">
                <h5>사진이 없습니다.</h5>
              </c:when>
              <c:otherwise>
              
              
              
                <label class="label-portfolio">포트폴리오 상세 이미지</label> </p>
            </br>
                <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
              </c:otherwise>
            </c:choose>     
          </br>     
           <hr class="hr-border">
  
          <input  type="button" value="수정하기" class="update-detail update-portfolio" id="detail-${detail.portFolioDetailPk}"/><br/>
          <hr>
         </div>
         </c:forEach>
       </c:otherwise>
     </c:choose>
     
     
     
     
     <br>
     <a class="add-portfolio" href="${pageContext.request.contextPath }/portfolio/detailForm">포트폴리오 상세내용 입력+추가</a>
    </c:otherwise>
  </c:choose>
 </center> 
</body>
</html>
