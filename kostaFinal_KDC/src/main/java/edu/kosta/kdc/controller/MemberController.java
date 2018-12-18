package edu.kosta.kdc.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.service.MemberService;
import edu.kosta.kdc.util.Constants;

@Controller
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    
    /**
     * 로그인폼
     */
    @RequestMapping("/signInForm")
    public void memberSignInForm() {} 
    
    /**
     * 회원가입폼
     */
    @RequestMapping("/signUpForm")
    public void memberSignUpForm() {}
    
    /**
     * 마이페이지
     */
    @RequestMapping("/myPage")
    public void memberMyPage() {}
    
    /**
     * 회원가입 아이디 체크
     * 
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/idCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberIdCheck(String memberId) {
        
        String message = "";        //AJAX 메세지
        //String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,12}$";       //유효성 정규식 표현
        String regex = "^[a-zA-Z0-9_]{5,12}$";
        
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(memberId);

        if(!matcher.find()) {
            message = "사용할 수 없는 아이디입니다.";
        }
        else {
            message = "사용가능한 아이디입니다.";
        
            //아이디 존재여부 DB에서 체크
            boolean checkResult = memberService.memberSelectByMemberId(memberId);
            //true면 이미존재 false면 사용가능
            if(checkResult) message = "이미 존재하는 아이디입니다.";
            else message = "사용가능한 아이디입니다.";
        }
        
        return message;
    }
    
    /**
     * 회원가입 허용가능한 비밀번호 체크
     * 
     * @param memberPwd
     * @return
     */
    @RequestMapping(value = "/pwdCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPwdCheck(String memberPwd) {
        
        String message = "사용가능한 비밀번호입니다.";
        
        //숫자, 영문, 특수기호 포함 8자리 이상
        String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberPwd);
        
        if(!matcher.find()) message = "사용 불가능한 비밀번호입니다.";
                
        return message;
    }
    
    /**
     * 회원가입 비밀번호와 비밀번호 확인 입력값 비교 후 ajax로 메세지 리턴
     * 
     * @return
     */
    @RequestMapping(value = "/pwdConfirm",  produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPwdConfirm(String memberPwd, String memberPwdConfirm) {
        
        String message = "비밀번호가 일치하지 않습니다.";
        
        if(memberPwd.equals(memberPwdConfirm)) {
            message = "사용가능합니다.";
        }
        
        return message;
    }
    
    /**
     * 회원가입 닉네임 체크
     * 
     * @param memberNickName
     * @return
     */
    @RequestMapping(value = "/nickNameCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberNickNameCheck(String memberNickName) {
        
        String message = "사용가능한 닉네임입니다.";
        
        String regex = "^[a-zA-Z0-9가-힣]{1,8}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberNickName);
        
        if(!matcher.find()) {
            message = "사용불가능한 닉네임입니다.";
        }else {
            boolean checkResult = memberService.memberSelectByMemberNickName(memberNickName);
            if(checkResult) message = "이미 사용중인 닉네임입니다.";
        }
        
        return message;
    }
    
    /**
     * 전화번호 체크
     * 
     * @param memberPhone
     * @return
     */
    @RequestMapping(value = "/phoneCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPhoneCheck(String memberPhone) {
        String message = "전화번호체크";
        
        //숫자만 허용
        String regex = "^[0-9]*$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberPhone);
        
        if(!matcher.find()) {
            message = "숫자만 입력해주세요.";
        }
        
        return message;
    }
    
    /**
     * 이메일 체크 
     * 
     * @param memberEmail
     * @return
     */
    @RequestMapping(value = "/emailCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String emailCheck(String memberEmail) {
        
        String message = "이메일입력";
        
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberEmail);
        
        if(!matcher.find()) {
            message = "이메일형식이 아닙니다.";
        }
        return message;
    }
    
    /**
     * 권한 유효성 검사.
     * 
     * @param authName
     * @return
     */
    @RequestMapping(value = "/authCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String authCheck(String authName) {
        
        String message = "";
        /*
            권한 종류
            관리자 : ROLE_ADMIN
            강사 : ROLE_TEACHER
            수강생 : ROLE_STUDENT
            기업 : ROLE_COMPANY
            일반회원 : ROLE_MEMBER
        */
        
        /*
         *  reflect 사용해서 필드 값 배열을 이용해 for문으로 비교할 수 있을 듯 보이지만,
         *  이미 상수로 정의된 필드값이기 때문에 메모리를 추가 사용하지 않고
         *  if문으로 논리연산하여 결과값 출력할 수 있도록 구현.
         */
        if(Constants.ROLE_ADMIN.equals(authName) || 
                Constants.ROLE_TEACHER.equals(authName) || 
                Constants.ROLE_STUDENT.equals(authName) || 
                Constants.ROLE_COMPANY.equals(authName) || 
                Constants.ROLE_MEMBER.equals(authName)) {
            message = "사용가능한 코드입니다.";
        }else {
            message = "사용 불가능한 코드입니다.";
        }
        
        return message;
    }
    
    /**
     * 회원가입
     * 
     * @return
     */
    @RequestMapping("/memberInsert")
    public String memberInsert(MemberDTO memberDTO, String authCode) {
        
        memberService.memberInsert(memberDTO, authCode);

        return "/index";
    }
    
    /**
     * 회원정보 수정
     * 
     * @param memberDTO
     * @return
     */
    @RequestMapping("/memberUpdate")
    public String memberUpdate(MemberDTO memberDTO) {
        
        memberService.updateByMemberInfo(memberDTO);
        
        return "/index";
    }
    
    /**
     * 회원 탈퇴. 
     * IsWithDrawal을 TRUE로 바꿔주어 탈퇴시킨다.
     * 
     * @param memberDTO
     * @return
     */
    @RequestMapping("/memberDelete")
    public String memberUpdateByIsWithDrawal(String memberId) {
        
        memberService.updateByIsWithDrawal(memberId);
        
        return "/index";
    }
}
