package edu.kosta.kdc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

    /**
     * �α�����
     */
    @RequestMapping("/signInForm")
    public void memberSignInForm() {}
    
    /**
     * ȸ��������
     */
    @RequestMapping("/signUpForm")
    public void memberSignUpForm() {}
    
    /**
     * ȸ������
     * 
     * @return
     */
    @RequestMapping("/memberInsert")
    public String memberInsert() {
        
        return "";
    }
}
