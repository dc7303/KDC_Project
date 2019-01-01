<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
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

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- Custom CSS -->
<link href="${pageContext.request.contextPath }/resources/lib/test/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="${pageContext.request.contextPath }/resources/lib/test/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- jQuery -->
<script src="${pageContext.request.contextPath }/resources/lib/test/vendor/jquery/jquery.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${pageContext.request.contextPath }/resources/lib/test/dist/js/sb-admin-2.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath }/resources/lib/test/vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath }/resources/lib/test/vendor/bootstrap/js/bootstrap.js"></script>

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

      <!-- 로그아웃 상태 -->
      <!-- /.dropdown -->
      <sec:authorize access="isAnonymous()">
        <li class="dropdown" id="logoutState">
          <a class="dropdown-toggle" data-toggle="dropdown" href="${pageContext.request.contextPath }/member/signInForm">
            <i class="fa fa-sign-in fa-fw"></i> Login </a></li>
        <!-- /.dropdown -->
      </sec:authorize>

      <sec:authorize access="isAuthenticated()">
        <sec:authentication var="member" property="principal" />

        <!-- 접속한 계정 ID 확인 -->
        <input type="hidden" value="${member.memberId }" name="memberIdCheck">

        <!-- 로그인 상태 -->
        <!-- /.dropdown -->
        <li class="dropdown" id="loginState">
          <a class="dropdown-toggle" id="messageDropToggle" data-toggle="dropdown" href="#"> 
            <i class="fa fa-envelope fa-fw"></i>
            <span class="badge badge-danger" id="header-unReadMessage"></span>
          </a>
          <ul class="dropdown-menu dropdown-messages">


            <!-- 메세지함 -->
            <div class="cart-info">
              <small> &nbsp; &nbsp; <span style="font-weight: bold; color: red;" id="header-unReadMessageDetail"></span>의 새로운 메세지가 있습니다.
              </small> <br>
              <li class="divider"></li>

              <!-- 안읽은 메세지 리스트 -->
              <table class="table table-hover" id="unReadMessageList" align="center">

              </table>
              <div style="display: flex; justify-content: center;">

                <div class="cart-btn">

                  <a style="" class="text-center" href="${pageContext.request.contextPath}/message/messageList" class="btn btn-success"> 
                    <strong> 더 많은 메세지 </strong>
                    <i class="fa fa-angle-right"></i>
                  </a>
                </div>
              </div>
            </div>
          </ul> <!-- /.dropdown-alerts --></li>

        <!-- /.dropdown -->
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#"> 
            <i class="fa fa-user fa-fw"></i> 
            <i class="fa fa-caret-down"></i>
          </a>
          <ul class="dropdown-menu dropdown-user">
            <li style="margin-bottom: 10px;">&nbsp;
              ${member.memberId } 님</li>
            <li class="divider"></li>
            <li style="margin-bottom: 10px;">&nbsp; 마이페이지</li>
            <li>
              <a href="${pageContext.request.contextPath }/member/myPageupdate">
                <i class="fa fa-user fa-fw"></i> 회원정보수정 
              </a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath }/portfolio/myPage">
                <i class="fa fa-tasks fa-fw"></i> 포트폴리오 
              </a>
            </li>
            <li>
              <a href="${pageContext.request.contextPath}/board/boardList">
                <i class="fa fa-edit fa-fw"></i> 내가 쓴 글 
              </a>
            </li>
            <li class="divider"></li>
            <li>
              <a id="header-logout" href="#"> 
                <i class="fa fa-sign-out fa-fw"></i> 로그아웃
              </a>
            </li>
          </ul> <!-- 로그아웃 form -->
          <form id="logoutFormTag"
            action="${pageContext.request.contextPath}/member/logout"
            method="post">
            <input type="hidden" name="${_csrf.parameterName }" value="${_csrf.token }" />
          </form> <!-- /.dropdown-user --></li>
      </sec:authorize>
      <!-- /.dropdown -->
    </ul>
  </div>
  <!-- /#wrapper -->


  <script type="text/javascript">
      (function(jq) {
        // 로그아웃시 , a태그를 submit에 연결하는 메소드
        jq('#header-logout').on('click', function() {
          jq('#logoutFormTag').submit();
        });
      })(jQuery);
    </script>


  <script type="text/javascript">
      //로그인시, 읽지 않은 메세지 표시
      window.onload = function() {

        var id = $('input[name=memberIdCheck]').val();

        $
            .ajax({
              url : '${pageContext.request.contextPath}/message/count',
              type : 'post',
              dataType : 'text',
              data : {
                id : id
              },
              beforeSend : function(xhr) {
                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
              },
              success : function(result) {
                if (id != '') {
                  document.getElementById('header-unReadMessage').innerHTML = result;
                  document.getElementById('header-unReadMessageDetail').innerHTML = result
                      + "개";
                }
              },
              error : function(err) {
                console.log(err);
              }
            });

        //header에 안읽은 메세지 읽어옴
        $
            .ajax({
              url : '${pageContext.request.contextPath}/message/unReadMessageList',
              type : 'post',
              dataType : 'json',
              data : {
                id : id
              },
              beforeSend : function(xhr) {
                xhr.setRequestHeader('${_csrf.headerName}', '${_csrf.token}');
              },
              success : function(data) {
                if (id != '') {
                  var str = '';
                  var i = 0;
                  $
                      .each(
                          data,
                          function(index, item) {
                            str += '<li>';
                            str += ' <a href="${pageContext.request.contextPath}/message/'+item.messageNum+'">';
                            str += '     <div>';
                            str += '         <strong>' + item.senderId + '</strong>';
                            str += '         <span class="pull-right text-muted">';
                            str += '             <em>' + item.messageDate + '</em>';
                            str += '         </span>';
                            str += '     </div>';
                            str += '     <div>' + item.messageTitle + '</div>';
                            str += ' </a>';
                            str += '</li>';
                            str += '<li class="divider"></li>';
                          });
                  $("#unReadMessageList").append(str);
                }
              },
              error : function(err) {
                console.log(err);
              }
            });
      }
    </script>
</body>
</html>