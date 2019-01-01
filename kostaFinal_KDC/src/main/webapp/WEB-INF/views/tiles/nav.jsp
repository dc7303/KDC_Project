<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html class="app-ui">

    <head>
      <!-- Meta -->
      <meta charset="UTF-8" />
      <meta http-equiv="X-UA-Compatible" content="IE=edge" />
      <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />

      <!-- Document title -->
      <title>KOSTA Developer Community</title>

      <!-- AppUI CSS stylesheets -->
      <link rel="stylesheet" id="css-font-awesome" href="${pageContext.request.contextPath }/resources/assets/css/font-awesome.css" />
      <link rel="stylesheet" id="css-ionicons" href="${pageContext.request.contextPath }/resources/assets/css/ionicons.css" />
      <link rel="stylesheet" id="css-bootstrap" href="${pageContext.request.contextPath }/resources/css/testcss/bootstrap.css" />
      <link rel="stylesheet" id="css-app" href="${pageContext.request.contextPath }/resources/css/testcss/app.css" />
      <link rel="stylesheet" id="css-app-custom" href="${pageContext.request.contextPath }/resources/css/testcss/app-custom.css" />
        <!-- End Stylesheets -->
        
        
       <script type="text/javascript">
         $(function(){
           $('.logout-Btn').on('click',function(){
             $('#logoutForm').submit();
           });
         });
       
      </script>
      
     <style>
     
      .img-responsive{
        margin: 20px 0 20px 5px;
       
      }

      </style>
       
    </head>

  <body class="app-ui layout-has-drawer layout-has-fixed-header">
    
    
      <div class="app-layout-canvas">
        <div class="app-layout-container">

          <!-- Drawer -->
          <aside class="app-layout-drawer">

            <!-- Drawer scroll area -->
            <div class="app-layout-drawer-scroll">
            <!-- Drawer logo -->
              <div id="logo" class="">
                <a href="${pageContext.request.contextPath }/"><img class="img-responsive" src="${pageContext.request.contextPath }/resources/testimg/logo/logo-kdc-02.png" title="AppUI" alt="AppUI" /></a>
              </div>
  
                    <!-- Drawer navigation -->
              <nav class="drawer-main">
  
                <ul class="nav nav-drawer">
    
                 <!--  <li class="nav-item nav-drawer-header">로그인영역</li> -->
      
                   <sec:authorize access="isAnonymous()">
                  <li class="nav-item active">
<a href="${pageContext.request.contextPath }/member/signInForm"><i class="ion-ios-speedometer-outline" id=""></i> 로그인</a>
                  </li>
         </sec:authorize>
   
         <sec:authorize access="isAuthenticated()">
               <li class="nav-item active">
               <form id="logoutForm" action="${pageContext.request.contextPath}/member/logout"  method="post">
               <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
               </form>
               <a href="#"  class="logout-Btn"><i class="ion-ios-speedometer-outline"></i> 로그아웃</a>
            </li>
         </sec:authorize>
    
         <sec:authorize access="isAnonymous()">
                  <li class="nav-item">
                    <a href="${pageContext.request.contextPath }/member/signUpForm"><i class="ion-ios-monitor-outline"></i> 회원가입</a>
                  </li>
         </sec:authorize>
      
          <sec:authorize access="isAuthenticated()">
                  <li class="nav-item nav-item-has-subnav" >
                    <a href="#"><i class="ion-ios-monitor-outline"></i> 마이페이지</a>
                    <!--  <a href="${pageContext.request.contextPath }/member/myPage"><i class="ion-ios-monitor-outline"></i> 마이페이지</a> -->
                  <ul class="nav nav-subnav">
          
      
                       <li><a href="${pageContext.request.contextPath }/member/myPageupdate">회원정보수정</a></li>         
                        <sec:authorize access="hasRole('ROLE_STUDENT')">   
                              <li><a href="${pageContext.request.contextPath }/portfolio/myPage">포트폴리오</a></li>

                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_TEACHER')" >
                          ${teacher}
                          
                      <li><a href="${pageContext.request.contextPath }/classRoom/classRoomInsertForm">클래스 생성</a></li>
                     
                      </sec:authorize>
                      
                      <li><a href="${pageContext.request.contextPath}/board/boardList">내가 쓴 글</a></li>
                      <li><a href="${pageContext.request.contextPath}/message/messageListNoPaging" name="messageListNoPaging">받은쪽지함</a></li>
                      <li><a href="${pageContext.request.contextPath}/classRoom/myClassRoom" name="myClassRoom">나의 클래스</a></li>   
                    </ul>
                  </li>
          </sec:authorize>
                  
      
      
                  <li class="nav-item nav-drawer-header">MENU</li>
      
                  <li class="nav-item ">
                    <a href="${pageContext.request.contextPath }/notice/list?classification=generalNotice"><i class="ion-ios-calculator-outline"></i> 공지사항 </a>
                  </li>
      
                  <li class="nav-item ">
                    <a href="${pageContext.request.contextPath }/notice/list?classification=findJobNotice"><i class="ion-ios-compose-outline"></i> 취업게시판</a>
                  </li>
      
                  <li class="nav-item ">
                    <a href="${pageContext.request.contextPath}/reply/tech?classification=tech&pageNo=1"> Tech Q&A </a>
                  </li>
      
                  <li class="nav-item ">
                    <a href="${pageContext.request.contextPath}/reply/lib?classification=lib&pageNo=1"> Tech 공유 게시판</a>
                  </li>
                  
                  <sec:authorize access="isAuthenticated()">
                  <li class="nav-item nav-item-has-subnav">
                    <a href="#"><i class="ion-social-javascript-outline"></i> 반별 게시판</a>
                    <ul class="nav nav-subnav">
       
                      <li>
                        <a href="${pageContext.request.contextPath }/notice/list?classification=classNotice">반별 공지사항</a>
                      </li>
        
                      <li>
                        <a href="${pageContext.request.contextPath}/calendar/calendarForm">스케줄</a>
                      </li>
        
                      <li>
                        <a href="${pageContext.request.contextPath}/chatting">채팅방</a>
                      </li>
      
                    </ul>
                  </li>
                  </sec:authorize>
      
                  <li class="nav-item ">
                    <a href="${pageContext.request.contextPath}/reply/study?classification=studypageNo=1">  스터디모집 </a>
                  </li>
      
                  <li class="nav-item ">
                    <a href="${pageContext.request.contextPath }/portfolio/selectAll"><i class="ion-ios-browsers-outline"></i> 포트폴리오</a>
                  </li>
                </ul>
              </nav>
              <!-- End drawer navigation -->
            </div>
            <!-- End drawer scroll area -->
          </aside>
                <!-- End drawer -->


        <!-- AppUI Core JS: jQuery, Bootstrap, slimScroll, scrollLock and App.js -->
        <script src="${pageContext.request.contextPath }/resources/js/testjs/core/jquery.min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/js/testjs/core/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/js/testjs/core/jquery.slimscroll.min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/js/testjs/core/jquery.scrollLock.min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/js/testjs/core/jquery.placeholder.min.js"></script>
        <script src="${pageContext.request.contextPath }/resources/js/testjs/app.js"></script>

    </body>
</html>