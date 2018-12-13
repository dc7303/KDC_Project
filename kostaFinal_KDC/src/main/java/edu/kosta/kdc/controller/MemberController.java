package edu.kosta.kdc.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
     * 아이디 체크
     * 
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/idCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberIdCheck(String memberId) {
        
        String message = "";
        
        
        return message;
    }
    
    /**
     * 허용가능한 비밀번호 체크
     * 
     * @param memberPwd
     * @return
     */
    @RequestMapping(value = "/pwdCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPwdCheck(String memberPwd) {
        
        System.out.println(memberPwd);
        String message = "";
        
        Pattern p1 = Pattern.compile("[0-9]"); // Number 0 through 9
        Pattern p2 = Pattern.compile("[a-z]"); // Characters a through z
        Pattern p3 = Pattern.compile("[A-Z]"); // Characters A through Z
        Pattern p4 = Pattern.compile("[^A-Za-z0-9]"); // Any character except ( A through Z and a through z and 0 through 9)

        Matcher m1 = p1.matcher(memberPwd);
        Matcher m2 = p2.matcher(memberPwd);
        Matcher m3 = p3.matcher(memberPwd);
        Matcher m4 = p4.matcher(memberPwd);

        if(memberPwd.length() < 8) message = "패스워드 길이가 8자리 미만입니다.";
        else if (!m1.find()) message = "패스워드에 숫자가 포함되지 않았습니다.";
        else if (!m2.find()) message = "패스워드에 소문자가 포함되지 않았습니다.";
        else if (!m3.find()) message = "패스워드에 대문자가 포함되지 않았습니다.";
        else if (!m4.find()) message = "패스워드에 특수문자가 포함되지 않았습니다.";
        else message = "사용가능한 비밀번호입니다.";

        
        return message;
    }
    
    /**
     * 비밀번호와 비밀번호 확인 입력값 비교 후 ajax로 메세지 리턴
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
