<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
      <link rel="stylesheet" id="css-font-awesome" href="${pageContext.request.contextPath }/resources/css/testcss/font-awesome.css" />
      <link rel="stylesheet" id="css-ionicons" href="${pageContext.request.contextPath }/resources/css/testcss/ionicons.css" />
      <link rel="stylesheet" id="css-bootstrap" href="${pageContext.request.contextPath }/resources/css/testcss/bootstrap.css" />
      <link rel="stylesheet" id="css-app" href="${pageContext.request.contextPath }/resources/css/testcss/app.css" />
      <link rel="stylesheet" id="css-app-custom" href="${pageContext.request.contextPath }/resources/css/testcss/app-custom.css" />
        <!-- End Stylesheets -->
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
                <a href="/"><img class="img-responsive" src="${pageContext.request.contextPath }/resources/testimg/logo/logo-kdc.png" title="AppUI" alt="AppUI" /></a>
              </div>
  
                    <!-- Drawer navigation -->
              <nav class="drawer-main">
  
                <ul class="nav nav-drawer">
    
                  <li class="nav-item nav-drawer-header">로그인영역</li>
      
                  <li class="nav-item active">
                    <a href="${pageContext.request.contextPath }/member/signInForm"><i class="ion-ios-speedometer-outline" id=""></i> 로그인</a>
                  </li>
      
                  <!-- <li class="nav-item active">
                    <a href="#"><i class="ion-ios-speedometer-outline"></i> 로그아웃</a>
                  </li> -->
      
      
      
                  <li class="nav-item">
                    <a href="${pageContext.request.contextPath }/member/signUpForm"><i class="ion-ios-monitor-outline"></i> 회원가입</a>
                  </li>
      
            <!-- <li class="nav-item">
                   <a href="#"><i class="ion-ios-monitor-outline"></i> 마이페이지</a>
                 </li> -->
      
                  <li class="nav-item nav-drawer-header">메뉴영역</li>
      
                  <li class="nav-item ">
                    <a href="#"><i class="ion-ios-calculator-outline"></i> 공지사항 </a>
                  </li>
      
                  <li class="nav-item ">
                    <a href="#"><i class="ion-ios-compose-outline"></i> 취업게시판</a>
                  </li>
      
                  <li class="nav-item ">
                    <a href="#"><i class="ion-ios-list-outline"></i> Tech Q&A</a>
                  </li>
      
                  <li class="nav-item ">
                    <a href="#"><i class="ion-ios-list-outline"></i> Tech 공유 게시판</a>
                  </li>
      
                  <li class="nav-item nav-item-has-subnav">
                    <a href="#"><i class="ion-social-javascript-outline"></i> 반별 게시판</a>
                    <ul class="nav nav-subnav">
      
                      <li>
                        <a href="#">반별 공지사항</a>
                      </li>
        
                      <li>
                        <a href="#">스케줄</a>
                      </li>
        
                      <li>
                        <a href="#">채팅방</a>
                      </li>
      
                    </ul>
                  </li>
      
                  <li class="nav-item ">
                    <a href="javascript:void(0)"><i class="ion-ios-list-outline"></i> 스터디모집</a>
                  </li>
      
                  <li class="nav-item ">
                    <a href="${pageContext.request.contextPath }/portfolio/"><i class="ion-ios-browsers-outline"></i> 포트폴리오</a>
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