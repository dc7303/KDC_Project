<%@ page language="java" contentType="text/html; charset=EUC-KR"
  pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
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
      if (jq('#portfolio-update-form').css('display') === 'none') {
        jq('#portfolio-update-form').css('display', 'block');
        jq('#portfolio-info').css('display', 'none');
        jq(this).css('display','none');
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
<style type="text/css">
 
  #portfolio-info > div{
    flex: 1;
  }
  .portfolio-img > img{
    width:100%;
  }
  .check-visible > span{
    display: inline-block;
    padding: 5px;
  } 
  
  .text-box h2{
    margin: 0px;
    font-weight: bold;
    font-size: 50px;
    padding: 20px;
  }
  .label-text{
    margin-top: 50px;
    text-align: left;
    opacity: 0.4;
    width: 100%;
    display: inline-block;
    margin-left: 50px;
    font-size: 20px;
  }
  .text-box h3{
    font-size: 35px;
    width: fit-content;
    padding: 10px;
    border-bottom: gainsboro solid 4px;
  }
  .update-btn{
    margin: 20px;
  }
  .label-detail{
    display: block;
    font-size: 20px;
    opacity: 0.4;
  }
 
  /* .add-portfolio{
    background-color: #ffb03a;
  } */
  
  /* selectAllDetail.jsp와 중복코드 */
  .portfolio-detail{
    display: flex;
  }
  .image-box{
    flex: 1;
  }
  .image-box img{
    width:100%;
  }
  .text-box{
    flex: 1;
  }
  .detail-box{
    border: gray solid 2px;
    margin: 10px;
  }
  .text-box > div{
    margin: 10px;
  }
  .detail-title{
    font-size:40px;
  }
      
</style>
</head>
<body>
<center>
<sec:authentication var="member" property="principal" />
  <h2 class="notice-title">포트폴리오 제작 </h2>
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
        <div class="check-visible">
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility"/></p></br>
        </div>
        <hr>
        <input class="send-portfolio" type="button" value="전송" />
        
        
      </form>
    </c:when>
    <c:otherwise>
      <div id="portfolio-info">
        <!-- 이미지 -->
        <div class="portfolio-img">
          <c:choose>      
          <c:when test="${not empty portfolio.portFolioMainImage}">
            <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
          </c:when>
          <c:otherwise>
            <h5>이미지가 없습니다.</h5>
          </c:otherwise>
        </c:choose>
        </div>
        <!-- 제목 및 버튼들 -->
        <div class="text-box">
          <div>
            <h2>포트폴리오 정보</h2>
            <span class="label-text">포트폴리오 제목</span>
            <h3>${portfolio.portFolioMainTitle}</h3>
          </div>
            <div class="check-visible">
              <span class="label-text">포트폴리오 공개 여부</span>
              <input type="hidden" id="original-visibility" value="${portfolio.portFolioVisibility}"/>
              <input class="check-portfolio" type="checkBox" name="portFolioVisibility" disabled/>
            </div>
        </div>
      </div>
      <div class="update-btn">
        <input type="button" value="수정하기" class=" common-button" id="update-portfolio"/>
      </div>
      
      <!-- 포트폴리오 수정일 경우 폼(수정버튼 클릭시 노출) -->
     <div id="portfolio-update-form" style="display:none;">
      <form
        action="${pageContext.request.contextPath }/portfolio/updatePortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data" id="portfolio-form">
        
         <label class="label-portfolio">제목</label><p/><br/>
         <h2> <input type="text" name="portFolioMainTitle" value="${portfolio.portFolioMainTitle}"/></h2> 
        
        <c:if test="${not empty portfolio.portFolioMainImage}">
         <label class="label-portfolio"> 현재이미지 </label><p/><br/>
        <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
        </c:if>         
        </br>         
        <hr class="hr-border">
        
         <label class="label-portfolio">대표이미지 수정 </label><p/><br/><input type="file" name="MainImageFile" /></p></br> 
         <hr class="hr-border">
         
          <label class="label-portfolio">개시여부</label><p/><br/>
          <div class="check-visible">
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility" value="${portfolio.portFolioVisibility}"/> <p>
               </div>
               <p>  *체크박스 선택시 포트폴리오가 공개됩니다</br>
                                        원하지 않으면 체크를 해제 해 주세요.  </p></br>
        <input class="common-button" type="button" value="수정완료" />
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
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail" varStatus="status">
         <div class="portfolio-detail">
           <div class="image-box detail-box">
             <c:choose>
               <c:when test="${empty detail.portfolioDeltailProjectImage}">
               <div class="no-img">
                 <h5>사진이 없습니다.</h5>
               </div>
               </c:when>
               <c:otherwise>
               <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
               </c:otherwise>
             </c:choose>
           </div>
           <div class="text-box detail-box">
             <div>
               <span class="label-detail">프로젝트명</span>
               <span class="detail-title">${detail.portfolioDetailProjectName}</span>
             </div>
             <div>
               <span class="label-detail">해쉬태그</span>
               <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
                 <span>${hashTag.hashTagName}</span>
               </c:forEach>
             </div>
             <div class="update-btn">
               <input type="button" value="수정하기" class="update-detail common-button" id="detail-${detail.portFolioDetailPk}"/>
             </div>
           </div>
         
         </div> 
         
         </c:forEach>
       </c:otherwise>
     </c:choose>
     
     <br>
     <div class="update-btn">
      <a class="add-portfolio button" href="${pageContext.request.contextPath }/portfolio/detailForm">상세내용 추가</a>
     </div>
    </c:otherwise>
  </c:choose>
 </center> 
</body>
</html>
