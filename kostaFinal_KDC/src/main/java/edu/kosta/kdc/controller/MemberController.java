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
     * ȸ������ ���̵� üũ
     * 
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/idCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberIdCheck(String memberId) {
        
        String message = "";        //AJAX �޼���
        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,12}$";       //��ȿ�� ���Խ� ǥ��
        
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(memberId);

        if(!matcher.find()) {
            message = "����� �� ���� ���̵��Դϴ�.";
        }
        else {
            message = "��밡���� ���̵��Դϴ�.";
        
            //���̵� ���翩�� DB���� üũ
            boolean checkResult = memberService.memberSelectById(memberId);
            //true�� �̹����� false�� ��밡��
            if(checkResult) message = "�̹� �����ϴ� ���̵��Դϴ�.";
            else message = "��밡���� ���̵��Դϴ�.";
        }
        
        return message;
    }
    
    /**
     * ȸ������ ��밡���� ��й�ȣ üũ
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

        if(memberPwd.length() < 8 || memberPwd.length() > 14) message = "�н����� ���̴� 8~14 �ڸ��Դϴ�.";
        else if (!matcher1.find()) message = "�н����忡 ���ڰ� ���Ե��� �ʾҽ��ϴ�.";
        else if (!matcher2.find()) message = "�н����忡 �ҹ��ڰ� ���Ե��� �ʾҽ��ϴ�.";
        else if (matcher3.find()) message = "�н������ �빮�� �ҹ��ڸ� �����մϴ�.";
        else if (!matcher4.find()) message = "�н����忡 Ư�����ڰ� ���Ե��� �ʾҽ��ϴ�.";
        else message = "��밡���� ��й�ȣ�Դϴ�.";

        
        return message;
    }
    
    /**
     * ȸ������ ��й�ȣ�� ��й�ȣ Ȯ�� �Է°� �� �� ajax�� �޼��� ����
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
     * ȸ������ �г��� üũ
     * 
     * @param memberNickName
     * @return
     */
    @RequestMapping(value = "/nickNameCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberNickNameCheck(String memberNickName) {
        
        String message = "��밡���� �г����Դϴ�.";
        
        String regex = "^[a-zA-Z0-9��-�R]*$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberNickName);
        
        if(!matcher.find()) {
            message = "���Ұ����� �г����Դϴ�.";
        }else if(memberNickName.length() < 2 || memberNickName.length() > 8) {
            message = "���Ұ����� �г����Դϴ�.";
        }else {
            boolean checkResult = memberService.memberSelectByNickName(memberNickName);
            if(checkResult) message = "�̹� ������� �г����Դϴ�.";
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
     * ȸ������
     * 
     * @return
     */
    @RequestMapping("/memberInsert")
    public String memberInsert(MemberDTO memberDTO, String authCode) {
        
        String memberPwd = memberDTO.getMemberPwd();

        return "/";
    }
}
