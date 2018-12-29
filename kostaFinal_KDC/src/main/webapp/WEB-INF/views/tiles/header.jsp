<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title>Insert title here</title>

<!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath }/resources/lib/test/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath }/resources/lib/test/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath }/resources/lib/test/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath }/resources/lib/test/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <!-- jQuery -->
    <script src="${pageContext.request.contextPath }/resources/lib/test/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${pageContext.request.contextPath }/resources/lib/test/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/lib/test/vendor/bootstrap/js/bootstrap.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${pageContext.request.contextPath }/resources/lib/test/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${pageContext.request.contextPath }/resources/lib/test/dist/js/sb-admin-2.js"></script>
        

</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"></a>
            </div>
            <!-- /.navbar-header -->
            
            <ul class="nav navbar-top-links navbar-right">
            
              <!-- 계정정보 check -->
                <sec:authentication var="member" property="principal" />
                
                <!-- 로그아웃 상태 -->
                <!-- /.dropdown -->
                <sec:authorize access="isAnonymous()">
                <li class="dropdown" id="logoutState">
                    <a class="dropdown-toggle" data-toggle="dropdown " href="${pageContext.request.contextPath }/member/signInForm">
                        <i class="fa fa-sign-in fa-fw"></i> Login
                    </a>
                </li>
                <!-- /.dropdown -->
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                <!-- 로그인 상태 -->
                <!-- /.dropdown -->
                <li class="dropdown" id="loginState">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <!-- 메세지함 -->
                        <li><a href="#"><i class="fa fa-envelope fa-fw"></i> Message
                                    <span class="pull-right text-muted small">Current Message</span>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
            
            <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li style="margin-bottom: 10px;">
                            &nbsp; ${member.memberId } 님
                        </li>
                        <li>
                          <a href="#"><i class="fa fa-user fa-fw"></i> 마이페이지 </a> 
                        </li>
                        <li>
                          <a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                          <a id="header-logout" href="#">
                          <i class="fa fa-sign-out fa-fw"></i> 로그아웃 </a>
                        </li>
                    </ul>
                    
                    <!-- 로그아웃 form -->
                    <form id="logoutFormTag" action="${pageContext.request.contextPath}/member/logout" method="post">
                          <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }"/>
                    </form>
                    
                    
                    <!-- /.dropdown-user -->
                </li>
                </sec:authorize>
                <!-- /.dropdown -->
                
            </ul>

    </div>
    <!-- /#wrapper -->
    
    <script type="text/javascript">
      (function(jq) {
        // a태그 submit 연결 메소드
        jq('#header-logout').on('click', function() {
          jq('#logoutFormTag').submit();
        });
        
      })(jQuery);
    </script>
    
</body>
</html>