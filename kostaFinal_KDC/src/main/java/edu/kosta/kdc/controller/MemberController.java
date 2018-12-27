package edu.kosta.kdc.controller;

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
     * ����������
     */
    @RequestMapping("/myPage")
    public void memberMyPage() {}
    
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
        //���ĺ�, ����, ����� 5�ڸ� �̻� 12�ڸ� ����
        String regex = "^[a-zA-Z0-9_]{5,12}$";
        
        //���Խ��� �������� �������Ѵ�.
        Pattern pattern = Pattern.compile(regex);
        //matcher�� ���� �Էµ� string���� ��ȿ���� ��
        Matcher matcher = pattern.matcher(memberId);

        //find()�� ��ȿ�� ��� true�� ��ȯ�Ѵ�. �׷��� ������ ! Ű���� �Է�.
        if(!matcher.find()) {
            message = "����� �� ���� ���̵��Դϴ�.";
        }
        else {
            message = "��밡���� ���̵��Դϴ�.";
        
            //���̵� ���翩�� DB���� üũ
            boolean checkResult = false;
            MemberDTO memberDTO = memberService.memberSelectByMemberId(memberId);
            if(memberDTO != null) {
                checkResult = true;
            }
            
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
        
        String message = "��밡���� ��й�ȣ�Դϴ�.";
        
        //����, ����, Ư����ȣ ���� 8�ڸ� �̻�
        String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberPwd);
        
        if(!matcher.find()) message = "��� �Ұ����� ��й�ȣ�Դϴ�.";
                
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
        
        //����, ����, �ѱ� ���ڸ� �̻� 8�ڸ� ����
        String regex = "^[a-zA-Z0-9��-�R]{1,8}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberNickName);
        
        if(!matcher.find()) {
            message = "���Ұ����� �г����Դϴ�.";
        }else {
            boolean checkResult = memberService.memberSelectByMemberNickName(memberNickName);
            if(checkResult) message = "�̹� ������� �г����Դϴ�.";
        }
        
        return message;
    }
    
    /**
     * ��ȭ��ȣ üũ
     * 
     * @param memberPhone
     * @return
     */
    @RequestMapping(value = "/phoneCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPhoneCheck(String memberPhone) {
        String message = "��ȭ��ȣüũ";
        
        //���ڸ� ���
        String regex = "^[0-9]*$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberPhone);
        
        if(!matcher.find()) {
            message = "���ڸ� �Է����ּ���.";
        }
        
        return message;
    }
    
    /**
     * �̸��� üũ 
     * 
     * @param memberEmail
     * @return
     */
    @RequestMapping(value = "/emailCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String emailCheck(String memberEmail) {
        
        String message = "�̸����Է�";
        //�̸��� ��ȿ�� üũ ���Խ�
        //@ �� ����, ����, dot, ����ٿ� �뽬 1���̻�
        //@ �� ����, ����, dot, �뽬 1�� �̻�
        //������ dot ���� ���� 2~6��
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberEmail);
        
        if(!matcher.find()) {
            message = "�̸��������� �ƴմϴ�.";
        }
        return message;
    }
    
    /**
     * ���� ��ȿ�� �˻�.
     * 
     * @param authName
     * @return
     */
    @RequestMapping(value = "/authCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String authCheck(String authName) {
        
        String message = "";
        /*
            ���� ����
            ������ : ROLE_ADMIN
            ���� : ROLE_TEACHER
            ������ : ROLE_STUDENT
            ��� : ROLE_COMPANY
            �Ϲ�ȸ�� : ROLE_MEMBER
        */
        
        /*
         *  reflect ����ؼ� �ʵ� �� �迭�� �̿��� for������ ���� �� ���� �� ��������,
         *  �̹� ����� ���ǵ� �ʵ尪�̱� ������ �޸𸮸� �߰� ������� �ʰ�
         *  if������ �������Ͽ� ����� ����� �� �ֵ��� ����.
         */
        if(Constants.ROLE_ADMIN.equals(authName) || 
                Constants.ROLE_TEACHER.equals(authName) || 
                Constants.ROLE_STUDENT.equals(authName) || 
                Constants.ROLE_COMPANY.equals(authName) || 
                Constants.ROLE_MEMBER.equals(authName)) {
            message = "��밡���� �ڵ��Դϴ�.";
        }else {
            message = "��� �Ұ����� �ڵ��Դϴ�.";
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

        return "/index";
    }
    
    /**
     * ȸ������ ����
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
     * ȸ�� Ż��. 
     * IsWithDrawal�� TRUE�� �ٲ��־� Ż���Ų��.
     * 
     * @param memberDTO
     * @return
     */
    @RequestMapping("/memberDelete")
    public String memberUpdateByIsWithDrawal(String memberId) {
        
        memberService.updateByIsWithDrawal(memberId);
        
        return "/index";
    }
    
    /**
     * ��й�ȣ ã��  �� ����
     * */
    @RequestMapping("/passwordSearch")
    public String passwordSearch() {
        return "/member/passwordSearchPopUpForm";
    }
    
    /**
     * �̸��� �����ֱ�
     * @throws Exception 
     * */
    @RequestMapping("/emailSend")
    @ResponseBody
    public String emailSend(String email) throws Exception {
        return "sucess";
    }
    
}
