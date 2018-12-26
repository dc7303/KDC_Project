<<<<<<< HEAD
$(function() {

  /*
    keyup이벤트시 입력된 태그값을 담는 배열
    실제 submit시에도 배열에 있는 값을 변수에 += 하여 파라미터로 전송한다.
  */
  let arr = [];
  
  //window.onload = function() {
    
    $('input[name=hashTagInput]').val().trim()
    var existed = $('input[name=hashTagInput]').val().trim().split(',');
    
    if(existed!=''){
      
      $.each(existed, function(index, inputValue){
        
        inputValue.trim();
        $('#span').append('<button type="button" name="tag" value="' + inputValue + '">' + inputValue + '</button>');
        arr.push(inputValue);
        
        console.log(arr);
        
      });
      
    }
    
    $('input[name=hashTagInput]').val('');
    $('#hashTagName').val(arr);

  //}

  /**
   * 태그 입력시 이벤트 발생
   */
  $('input[name=hashTagInput]').on('keyup', function(key) {
    
    const regex = /^#([\w가-힣]*[\w가-힣]*[\w가-힣]){1,}/; //태그 유효성검사
    const regexSym = /[^\w^\#]/;          //# 제외한 특수문자

    let value = $('input[name=hashTagInput]').val();  //입력값
    
    /*여기부터*/
    
    if($('input[name=hashTagInput]').val() == ''){
      $("#suggest").hide();
      return;
    }
    
    $.ajax({
       type: "post" //요청방식
      ,url: $('input[name=contextPath]').val()+"/reply/hashtagSuggest" //서버주소
      ,dataType: "json" //서버가 보내준 데이터 타입(text, json, xml, html)
      ,data: "keyWord="+value //서버에게 보내는 parameter정보
      ,beforeSend: function(xhr) {
        xhr.setRequestHeader($('input[name=csrfName]').val(), $('input[name=csrfToken]').val());
      }
      ,success: function(result){ //요청결과 성공 했을때 끝
        
        var str="";
        $.each(result, function(index,item){
          str+="<a href='#'>" + item + "</a><br>";
        });
        
        $("#suggest").html(str);
        $("#suggest").show();
            
      } //요청결과 성공 했을때 끝
      ,error: function(err){ //요청결과 실패 했을때 끝
        console.log(err);
      } //요청결과 실패 했을때 끝
      
    });
    
    $("#suggest").on("click", "a", function(){
      $("input[name=hashTagInput]").val($(this).text());
      $("#suggest").hide();
    });

    let code = key.keyCode;   //이벤트 키코드
    let keyValue = key.key;   //이벤트 키값

      //엔터, 스페이스바 포함 모든 특수문자
    if(code === 13 || code === 32 || regexSym.test(keyValue)) {
      
      //맨앞에 #이 존재한다면
      if(regex.test(value)) {

        let inputValue = value.trim().replace(keyValue, '');    //공백, 특수문자 제거한 결과값

        $('input[name=hashTagInput]').val('');
        
        
        //중복값이 있다면 return
        for(let prop of arr) {
          if(prop === inputValue) {
            return;
          }
        }
        //배열에 추가
        arr.push(inputValue);
        
        $('#span').append('<button type="button" name="tag" value="' + inputValue + '">' + inputValue + '</button>'); /*class="btn btn-primary btn-xs" style="margin-left:3px"*/
        $('#hashTagName').val(arr);
        
      }else {
        $('input[name=hashTagInput]').val('');
      }
    
      //백스페이스
    }else if(key.keyCode === 8) {
      if(value === '') {
        let index = arr.length-1;
        $('input[name=hashTagInput]').val(arr[index]);
        $('button[name=tag]').eq(index).remove();
        //맨마지막 배열 요소 제거
        arr.pop();
      }
    }


  });

  /**
   *  태그 이미지 클릭시 삭제
   */
  $(document).on('click', 'button[name=tag]', function() {
    //클릭한 값 인덱스 조회
    let index = arr.indexOf($(this).val());
    
    //배열에서 값 삭제 
    arr.splice(index, 1);
    $('#hashTagName').val(arr);
    //버튼 삭제
    $(this).remove();
  });
=======
$(function() {

  /*
    keyup이벤트시 입력된 태그값을 담는 배열
    실제 submit시에도 배열에 있는 값을 변수에 += 하여 파라미터로 전송한다.
  */
  let arr = [];
  
  //window.onload = function() {
   
    $('input[name=hashTagInput]').val().trim()
    var existed = $('input[name=hashTagInput]').val().trim().split(',');
    
    if(existed!=''){
      
      $.each(existed, function(index, inputValue){
        
        inputValue.trim();
        $('#span').append('<button type="button" name="tag" value="' + inputValue + '">' + inputValue + '</button>');
        arr.push(inputValue);
        
        console.log(arr);
        
      });
      
    }
    
    $('input[name=hashTagInput]').val('');
    $('#hashTagName').val(arr);

  //}

  /**
   * 태그 입력시 이벤트 발생
   */
  $('input[name=hashTagInput]').on('keyup', function(key) {
    console.log('키업');
    const regex = /^#([\w가-힣]*[\w가-힣]*[\w가-힣]){1,}/; //태그 유효성검사
    const regexSym = /[^\w^\#]/;          //# 제외한 특수문자

    let value = $('input[name=hashTagInput]').val();  //입력값
    
    /*여기부터*/
    
    if($('input[name=hashTagInput]').val() == ''){
      $("#suggest").hide();
      return;
    }
    
    $.ajax({
       type: "post" //요청방식
      ,url: $('input[name=contextPath]').val()+"/reply/hashtagSuggest" //서버주소
      ,dataType: "json" //서버가 보내준 데이터 타입(text, json, xml, html)
      ,data: "keyWord="+value //서버에게 보내는 parameter정보
      ,beforeSend: function(xhr) {
        xhr.setRequestHeader($('input[name=csrfName]').val(), $('input[name=csrfToken]').val());
      }
      ,success: function(result){ //요청결과 성공 했을때 끝
        
        var str="";
        $.each(result, function(index,item){
          str+="<a href='#'>" + item + "</a><br>";
        });
        
        $("#suggest").html(str);
        $("#suggest").show();
            
      } //요청결과 성공 했을때 끝
      ,error: function(err){ //요청결과 실패 했을때 끝
        console.log(err);
      } //요청결과 실패 했을때 끝
      
    });
    
    $("#suggest").on("click", "a", function(){
      $("input[name=hashTagInput]").val($(this).text());
      $("#suggest").hide();
    });

    let code = key.keyCode;   //이벤트 키코드
    let keyValue = key.key;   //이벤트 키값

      //엔터, 스페이스바 포함 모든 특수문자
    if(code === 13 || code === 32 || regexSym.test(keyValue)) {
      
      //맨앞에 #이 존재한다면
      if(regex.test(value)) {

        let inputValue = value.trim().replace(keyValue, '');    //공백, 특수문자 제거한 결과값

        $('input[name=hashTagInput]').val('');
        
        
        //중복값이 있다면 return
        for(let prop of arr) {
          if(prop === inputValue) {
            return;
          }
        }
        //배열에 추가
        arr.push(inputValue);
        
        $('#span').append('<button type="button" name="tag" value="' + inputValue + '">' + inputValue + '</button>'); /*class="btn btn-primary btn-xs" style="margin-left:3px"*/
        $('#hashTagName').val(arr);
        
      }else {
        $('input[name=hashTagInput]').val('');
      }
    
      //백스페이스
    }else if(key.keyCode === 8) {
      if(value === '') {
        let index = arr.length-1;
        $('input[name=hashTagInput]').val(arr[index]);
        $('button[name=tag]').eq(index).remove();
        //맨마지막 배열 요소 제거
        arr.pop();
      }
    }


  });

  /**
   *  태그 이미지 클릭시 삭제
   */
  $(document).on('click', 'button[name=tag]', function() {
    //클릭한 값 인덱스 조회
    let index = arr.indexOf($(this).val());
    
    //배열에서 값 삭제 
    arr.splice(index, 1);
    $('#hashTagName').val(arr);
    //버튼 삭제
    $(this).remove();
  });
>>>>>>> MergBranch
});