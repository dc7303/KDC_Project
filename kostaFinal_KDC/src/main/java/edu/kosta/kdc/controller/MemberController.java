package edu.kosta.kdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

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
     * 회원가입
     * 
     * @return
     */
    @RequestMapping("/memberInsert")
    public String memberInsert() {
        
        return "";
    }
}
