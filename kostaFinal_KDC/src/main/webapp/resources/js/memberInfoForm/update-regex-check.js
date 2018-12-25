const jq = jQuery.noConflict();

jq(function() {
  
  var contextPath = $('input[name=contextPath]').val(); //contextPath 설정

  const memberPwd = 'input[name=memberPwd]';            // 비밀번호 Selector
  const memberPwdConfirm = 'input[name=memberPwdConfirm]';// 비밀번호확인 Selector
  const memberPhone = 'input[name=memberPhone]';       // 전화번호 Selector
  const memberEmail = 'input[name=memberEmail]';       // Email Selector
  
  const csrfName = 'input[name=csrfName]';             // csrf.headerName
  const csrfToken = 'input[name=csrfToken]';           // csrf.csrfToken

  
  // 패스워드 유효성체크
  jq(memberPwd).on('keyup', function() {
    jq.ajax({
      url : contextPath + '/member/pwdCheck',
      type : 'post',
      beforeSend : function(xhr) {
        xhr.setRequestHeader($(csrfName).val(), $(csrfToken).val());
      },
      dataType : 'text',
      data : {
        memberPwd : $(memberPwd).val()
      },
      success : function(result) {
        $('.ajax').eq(0).text(result);
        if ($(memberPwd).val() === '') {
          $('.ajax').eq(0).text('비밀번호입력');
          if ($(memberPwdConfirm).val() === '') {
            $('.ajax').eq(1).text('비밀번호확인');
          }
        } else if ($(memberPwd).val() !== $(memberPwdConfirm).val()) {
          $('.ajax').eq(1).text('비밀번호가 일치하지 않습니다.');
        }
      },
      error : function(err) {
        console.log(err);
      }
    });
  });

  // 패스워드 확인 매칭
  jq(memberPwdConfirm).on('keyup', function() {
    jq.ajax({
      url : contextPath + '/member/pwdConfirm',
      type : 'post',
      beforeSend : function(xhr) {
        xhr.setRequestHeader($(csrfName).val(), $(csrfToken).val());
      },
      dataType : 'text',
      data : {
        memberPwd : $(memberPwd).val(),
        memberPwdConfirm : $(memberPwdConfirm).val()
      },
      success : function(result) {
        $('.ajax').eq(1).text(result);
        if ($(memberPwdConfirm).val() === '' && $(memberPwd).val() === '') {
          $('.ajax').eq(1).text('비밀번호 확인');
        }
      },
      error : function(err) {
        console.log(err)
      }
    });
  });

  // 전화번호 체크
  jq(memberPhone).on('keyup', function() {
    jq.ajax({
      url : contextPath + '/member/phoneCheck',
      type : 'post',
      beforeSend : function(xhr) {
        xhr.setRequestHeader($(csrfName).val(), $(csrfToken).val());
      },
      dataType : 'text',
      data : {
        memberPhone : $(memberPhone).val(),
      },
      success : function(result) {
        $('.ajax').eq(4).text(result);
        if ($(memberPhone).val() === '') {
          $('.ajax').eq(4).text('전화번호체크');
        }
      },
      error : function(err) {
        console.log(result);
      }
    });
  });

  // 이메일 체크
  jq(memberEmail).on('keyup', function() {
    jq.ajax({
      url : contextPath + '/member/emailCheck',
      type : 'post',
      beforeSend : function(xhr) {
        xhr.setRequestHeader($(csrfName).val(), $(csrfToken).val());
      },
      dataType : 'text',
      data : {
        memberEmail : $(memberEmail).val(),
      },
      success : function(result) {
        $('.ajax').eq(5).text(result);
        if ($(memberEmail).val() === '') {
          $('.ajax').eq(5).text('이메일입력');
        }
      },
      error : function(err) {
        console.log(err);
      }
    });
  });
  

  // submit
  jq('.setbutton').on('click',function() {

    var pwdRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$/; // 비밀번호 유효성 정규식
    var nickNameRegex = /^[a-zA-Z0-9가-힣]{1,8}$/;                      // 닉네임 유효성 정규식
    var emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/; // 이메일 유효성 정규식
    var phoneRegex = /^[0-9]*$/;                                      // 핸드폰 번호 유효성 정규식


    // 비밀번호 유효성 검사
    if (!pwdRegex.test(jq(memberPwd).val())) {
      alert("비밀번호를 확인해주세요.");
      jq(memberPwd).val('');
      jq(memberPwd).focus();
      return;
      
      // 비밀번호 확인 검사
    } else if ($(memberPwd).val() !== $(memberPwdConfirm).val()) {
      alert("비밀번호가 일치하지 않습니다.");
      jq(memberPwdConfirm).val('');
      jq(memberPwdConfirm).focus();
      return;

      // 이름검사
    } else if ($('input[name=memberName]').val() === '') {
      alert("이름을 입력해주세요.");
      jq('input[name=memberName]').focus();
      return;

      // 생일검사
    } else if (jq('input[name=memberBirth]').val() === '') {
      alert('생일을 입력해주세요.');
      jq('input[name=memberBirth]').focus();
      return;

      // 전화번호 검사
    } else if (!phoneRegex.test(jq(memberPhone).val()) || jq(memberPhone).val() === '') {
      alert('전화번호를 확인해주세요.');
      jq(memberPhone).focus();
      return;

      // 이메일검사
    } else if (!emailRegex.test(jq(memberEmail).val())) {
      alert('이메일을 확인해주세요.');
      jq(memberEmail).focus();
      return;

    }

    jq('#updateForm').submit();

  });
});