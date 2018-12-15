package edu.kosta.kdc.controller;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kosta.kdc.model.dto.MemberDTO;
import edu.kosta.kdc.model.service.MemberService;

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
     * 회원가입 아이디 체크
     * 
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/idCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberIdCheck(String memberId) {
        
        String message = "";        //AJAX 메세지
        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,12}$";       //유효성 정규식 표현
        
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
        
        String message = "";
        
        Pattern pattern1 = Pattern.compile("[0-9]"); // Number 0 through 9
        Pattern pattern2 = Pattern.compile("[a-z]"); // Characters a through z
        Pattern pattern3 = Pattern.compile("[A-Z]"); // Characters A through Z
        Pattern pattern4 = Pattern.compile("[^A-Za-z0-9]"); // Any character except ( A through Z and a through z and 0 through 9)

        Matcher matcher1 = pattern1.matcher(memberPwd);
        Matcher matcher2 = pattern2.matcher(memberPwd);
        Matcher matcher3 = pattern3.matcher(memberPwd);
        Matcher matcher4 = pattern4.matcher(memberPwd);

        if(memberPwd.length() < 8 || memberPwd.length() > 14) message = "패스워드 길이는 8~14 자리입니다.";
        else if (!matcher1.find()) message = "패스워드에 숫자가 포함되지 않았습니다.";
        else if (!matcher2.find()) message = "패스워드에 소문자가 포함되지 않았습니다.";
        else if (matcher3.find()) message = "패스워드는 대문자 소문자를 구분합니다.";
        else if (!matcher4.find()) message = "패스워드에 특수문자가 포함되지 않았습니다.";
        else message = "사용가능한 비밀번호입니다.";

        
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
        
        String regex = "^[a-zA-Z0-9가-힣]*$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberNickName);
        
        if(!matcher.find()) {
            message = "사용불가능한 닉네임입니다.";
        }else if(memberNickName.length() < 2 || memberNickName.length() > 8) {
            message = "사용불가능한 닉네임입니다.";
        }else {
            boolean checkResult = memberService.memberSelectByMemberNickName(memberNickName);
            if(checkResult) message = "이미 사용중인 닉네임입니다.";
        }
        
        return message;
    }
    
    @RequestMapping("/phoneCheck")
    @ResponseBody
    public String memberPhoneCheck(String memberPhone) {
        
        String message = "";
        
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

        return "/";
    }
}
