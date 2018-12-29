const jq = jQuery.noConflict();

jq(function() {
  const emailCheck = 'input[name=email]';       // Email Check

  //email Check
    jq(emailCheck).on('keyup', function() {
      jq.ajax({
        url : '${pageContext.request.contextPath }/member/memberByEmailCheck',
        type : 'post',
        beforeSend : function(xhr) {
          xhr.setRequestHeader($(csrfName).val(), $(csrfToken).val());
        },
        dataType : 'text',
        data : {
          emailCheck : $(emailCheck).val()
        },
        success : function(result) {
          $('.ajax').text(result);
          if ($(memberId).val() === '') {
            $('.ajax').text('이메일을 입력하세요');
          }
        },
        error : function(err) {
          console.log(err);
        }
      });
    });
  
});