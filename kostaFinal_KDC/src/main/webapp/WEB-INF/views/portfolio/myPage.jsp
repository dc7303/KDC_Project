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
	  
	  //��ȿ���˻�(���� ���θ� üũ)
	  jq('#portfolio-form > input[type=button]').on('click',function(){
	    var title = jq('input[name=portFolioMainTitle]');
	    if (title.val().trim() === '') {
	      alert('������ �ʼ��׸� �Դϴ�.');
	      title.focus();
	    } else {
	      jq('#portfolio-form').submit();
	    }
	  });
  	

    //�Խÿ��ΰ� true�� ��� üũ�ڽ� üũ
    if (jq('#original-visibility').val() === 'true') {
      jq('input[name=portFolioVisibility]').attr('checked', '');
    }

    /* ������ư Ŭ���� ������ ����, ���� ���� */
    jq('#update-portfolio').on('click', function() {
      if (jq('#portfolio-update-form').css('display') === 'none') {
        jq('#portfolio-update-form').css('display', 'block');
        jq('#portfolio-info').css('display', 'none');
        jq(this).css('display','none');
      }
    });

    //��Ʈ������ �� ������ư Ŭ�� �̺�Ʈ(a �±� ��ü�ص� ��������)
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
  
  /* selectAllDetail.jsp�� �ߺ��ڵ� */
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
  <h2 class="notice-title">��Ʈ������ ���� </h2>
  <c:choose>
    <c:when test="${empty portfolio}">
        <!-- ��ǥ�̹���, ����, �Խÿ���(üũ�ڽ�) -->
      <form
        action="${pageContext.request.contextPath }/portfolio/insertPortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data" id="portfolio-form">
        
        
        <label class="label-portfolio"> 1.��Ʈ������ ������ �Է��ϼ���.</label><p class="title-underline"></p>
        <input class="title-portfolio"type="text" name="portFolioMainTitle" /></p></br> <hr> 
        
        <label class="label-portfolio">2.��ǥ�̹����� �������ּ���.</label>
        <input class="imgbtn-portfolio" type="file" name="MainImageFile" /></p></br> <hr> 
        
        <label class="label-portfolio">3.�Ʒ� üũ�ڽ��� �����ϸ� ��Ʈ�������� �����˴ϴ�.</label></br>
        <div class="check-visible">
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility"/></p></br>
        </div>
        <hr>
        <input class="send-portfolio" type="button" value="����" />
        
        
      </form>
    </c:when>
    <c:otherwise>
      <div id="portfolio-info">
        <!-- �̹��� -->
        <div class="portfolio-img">
          <c:choose>      
          <c:when test="${not empty portfolio.portFolioMainImage}">
            <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
          </c:when>
          <c:otherwise>
            <h5>�̹����� �����ϴ�.</h5>
          </c:otherwise>
        </c:choose>
        </div>
        <!-- ���� �� ��ư�� -->
        <div class="text-box">
          <div>
            <h2>��Ʈ������ ����</h2>
            <span class="label-text">��Ʈ������ ����</span>
            <h3>${portfolio.portFolioMainTitle}</h3>
          </div>
            <div class="check-visible">
              <span class="label-text">��Ʈ������ ���� ����</span>
              <input type="hidden" id="original-visibility" value="${portfolio.portFolioVisibility}"/>
              <input class="check-portfolio" type="checkBox" name="portFolioVisibility" disabled/>
            </div>
        </div>
      </div>
      <div class="update-btn">
        <input type="button" value="�����ϱ�" class=" common-button" id="update-portfolio"/>
      </div>
      
      <!-- ��Ʈ������ ������ ��� ��(������ư Ŭ���� ����) -->
     <div id="portfolio-update-form" style="display:none;">
      <form
        action="${pageContext.request.contextPath }/portfolio/updatePortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data" id="portfolio-form">
        
         <label class="label-portfolio">����</label><p/><br/>
         <h2> <input type="text" name="portFolioMainTitle" value="${portfolio.portFolioMainTitle}"/></h2> 
        
        <c:if test="${not empty portfolio.portFolioMainImage}">
         <label class="label-portfolio"> �����̹��� </label><p/><br/>
        <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
        </c:if>         
        </br>         
        <hr class="hr-border">
        
         <label class="label-portfolio">��ǥ�̹��� ���� </label><p/><br/><input type="file" name="MainImageFile" /></p></br> 
         <hr class="hr-border">
         
          <label class="label-portfolio">���ÿ���</label><p/><br/>
          <div class="check-visible">
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility" value="${portfolio.portFolioVisibility}"/> <p>
               </div>
               <p>  *üũ�ڽ� ���ý� ��Ʈ�������� �����˴ϴ�</br>
                                        ������ ������ üũ�� ���� �� �ּ���.  </p></br>
        <input class="common-button" type="button" value="�����Ϸ�" />
      </form>
     </div>
     <!-- ��Ʈ�������� �����ϴ°�� ���� ���� -->
     <c:choose>
       <c:when test="${empty portfolio.portFolioDetailList}">
       
         <hr>
         <h6> *�Ʒ� ��ư�� ���� ��Ʈ������ ������ ������ �ϼ���! </h6>
       </c:when>
       <c:otherwise>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail" varStatus="status">
         <div class="portfolio-detail">
           <div class="image-box detail-box">
             <c:choose>
               <c:when test="${empty detail.portfolioDeltailProjectImage}">
               <div class="no-img">
                 <h5>������ �����ϴ�.</h5>
               </div>
               </c:when>
               <c:otherwise>
               <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
               </c:otherwise>
             </c:choose>
           </div>
           <div class="text-box detail-box">
             <div>
               <span class="label-detail">������Ʈ��</span>
               <span class="detail-title">${detail.portfolioDetailProjectName}</span>
             </div>
             <div>
               <span class="label-detail">�ؽ��±�</span>
               <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
                 <span>${hashTag.hashTagName}</span>
               </c:forEach>
             </div>
             <div class="update-btn">
               <input type="button" value="�����ϱ�" class="update-detail common-button" id="detail-${detail.portFolioDetailPk}"/>
             </div>
           </div>
         
         </div> 
         
         </c:forEach>
       </c:otherwise>
     </c:choose>
     
     <br>
     <div class="update-btn">
      <a class="add-portfolio button" href="${pageContext.request.contextPath }/portfolio/detailForm">�󼼳��� �߰�</a>
     </div>
    </c:otherwise>
  </c:choose>
 </center> 
</body>
</html>
