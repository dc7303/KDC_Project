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
     * ���̵� üũ
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
     * ��밡���� ��й�ȣ üũ
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

        if(memberPwd.length() < 8) message = "�н����� ���̰� 8�ڸ� �̸��Դϴ�.";
        else if (!m1.find()) message = "�н����忡 ���ڰ� ���Ե��� �ʾҽ��ϴ�.";
        else if (!m2.find()) message = "�н����忡 �ҹ��ڰ� ���Ե��� �ʾҽ��ϴ�.";
        else if (!m3.find()) message = "�н����忡 �빮�ڰ� ���Ե��� �ʾҽ��ϴ�.";
        else if (!m4.find()) message = "�н����忡 Ư�����ڰ� ���Ե��� �ʾҽ��ϴ�.";
        else message = "��밡���� ��й�ȣ�Դϴ�.";

        
        return message;
    }
    
    /**
     * ��й�ȣ�� ��й�ȣ Ȯ�� �Է°� �� �� ajax�� �޼��� ����
     * 
     * @return
     */
    @RequestMapping(value = "/pwdConfirm",  produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPwdConfirm(String memberPwd, String memberPwdConfirm) {
        
        String message = "��й�ȣ�� ��ġ���� �ʽ��ϴ�.";
        
        if(memberPwd.equals(memberPwdConfirm)) {
            message = "��밡���մϴ�.";
        }
        
        return message;
    }
    
    /**
     * ȸ������
     * 
     * @return
     */
    @RequestMapping("/memberInsert")
    public String memberInsert(MemberDTO memberDTO, String authCode) {
        
        memberService.memberInsert(memberDTO, authCode);
        
        return "/";
    }
}
