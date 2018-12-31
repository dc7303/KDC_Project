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
      if (jq('#portfolio-update-form').css("display") === 'none') {
        jq('#portfolio-update-form').css("display", "block");
        jq('#portfolio-info').css("display", "none");
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
</head>
<body>
<center>
<sec:authentication var="member" property="principal" />
  <h3 class="head-portfolio">${member.memberId}���� Portfolio ���� </h3>
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
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility"/></p></br>
        
        <hr>
        <input class="send-portfolio" type="button" value="����" />
        
        
      </form>
    </c:when>
    <c:otherwise>
      <!-- ��Ʈ�������� ������ ��Ʈ������ ���� ���� -->  
      <div id="portfolio-info">
    
        <label class="label-portfolio">����� ����</label>
         <h2 class="">${portfolio.portFolioMainTitle}</h2><p/><br/>

      <hr class="hr-border">
      
      
        <label class="label-portfolio">����� ��ǥ�̹��� </label><p/><br/>
        <c:choose>      
          <c:when test="${not empty portfolio.portFolioMainImage}">
            <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
          </c:when>
          <c:otherwise>
            <h5>�̹����� �����ϴ�.</h5>
          </c:otherwise>
        </c:choose>
        </br>
        
         <hr class="hr-border">
        
        <label class="label-portfolio">��Ʈ������ ���� ����</label><p/><br/>
        <input type="hidden" id="original-visibility" value="${portfolio.portFolioVisibility}"/>
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility" disabled/><p/><br/>
        </br>
        
        <hr class="hr-border">
        <input type="button" value="�����ϱ�" class="update-portfolio" id="update-portfolio"/><p/><br/>
      </div>
      
      <!-- ��Ʈ������ ������ ��� ��(������ư Ŭ���� ����) -->
     <div id="portfolio-update-form" style="display:none;">
      <form
        action="${pageContext.request.contextPath }/portfolio/updatePortfolio?${_csrf.parameterName}=${_csrf.token}"
        method="post" enctype="multipart/form-data" id="portfolio-form">
        
         <label class="label-portfolio">����</label><p/><br/>
         <h2> <input type="text" name="portFolioMainTitle" value="${portfolio.portFolioMainTitle}"/></h2> </p></br> 
        <hr class="hr-border">
        
        
        <c:if test="${not empty portfolio.portFolioMainImage}">
         <label class="label-portfolio"> �����̹��� </label><p/><br/>
        <img src="${pageContext.request.contextPath}/resources/testimg/photos/${portfolio.portFolioMainImage}">
        </c:if>         
        </br>         
        <hr class="hr-border">
        
         <label class="label-portfolio">��ǥ�̹��� ���� </label><p/><br/><input type="file" name="MainImageFile" /></p></br> 
         <hr class="hr-border">
         
          <label class="label-portfolio">���ÿ���</label><p/><br/>
        <input class="check-portfolio" type="checkBox" name="portFolioVisibility" value="${portfolio.portFolioVisibility}"/> <p>
               <p>  *üũ�ڽ� ���ý� ��Ʈ�������� �����˴ϴ�</br>
                                        ������ ������ üũ�� ���� �� �ּ���.  </p></br>
        <input class="update-portfolio" id="update-portfolio" type="button" value="�����Ϸ�" />
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
         <hr>
         </br>
         <c:forEach items="${portfolio.portFolioDetailList}" var="detail">
         
         
           <!-- ������� ��Ʈ������ �󼼳���  -->
         
           <h3 class="head-portfolio">Portfolio �󼼳��� </h3>
         
         
         <div id="portfolio-detail">

          <label class="label-portfolio">������Ʈ��</label>
         <h2 class="">${detail.portfolioDetailProjectName}</h2><p/><br/>
          <hr class="hr-border">
      
         
        
           <label class="label-portfolio">�ؽ��±�</label> </p>
            </br>
           <c:forEach items="${detail.portfolioDetailHashTagList}" var="hashTag">
              ${hashTag.hashTagName}
           </c:forEach>
            </p>
            </br>
             <hr class="hr-border">
            
            
            <c:choose>
              <c:when test="${empty detail.portfolioDeltailProjectImage}">
                <h5>������ �����ϴ�.</h5>
              </c:when>
              <c:otherwise>
              
              
              
                <label class="label-portfolio">��Ʈ������ �� �̹���</label> </p>
            </br>
                <img src="${pageContext.request.contextPath}/resources/testimg/photos/${detail.portfolioDeltailProjectImage}">
              </c:otherwise>
            </c:choose>     
          </br>     
           <hr class="hr-border">
  
          <input  type="button" value="�����ϱ�" class="update-detail update-portfolio" id="detail-${detail.portFolioDetailPk}"/><br/>
          <hr>
         </div>
         </c:forEach>
       </c:otherwise>
     </c:choose>
     
     
     
     
     <br>
     <a class="add-portfolio" href="${pageContext.request.contextPath }/portfolio/detailForm">��Ʈ������ �󼼳��� �Է�+�߰�</a>
    </c:otherwise>
  </c:choose>
 </center> 
</body>
</html>
