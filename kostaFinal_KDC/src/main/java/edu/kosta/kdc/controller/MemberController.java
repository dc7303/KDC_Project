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
     * ·Î±×ÀÎÆû
     */
    @RequestMapping("/signInForm")
    public void memberSignInForm() {} 
    
    /**
     * È¸¿ø°¡ÀÔÆû
     */
    @RequestMapping("/signUpForm")
    public void memberSignUpForm() {}
    
    /**
     * ¸¶ÀÌÆäÀÌÁö
     */
    @RequestMapping("/myPage")
    public void memberMyPage() {}
    
    /**
     * È¸¿ø°¡ÀÔ ¾ÆÀÌµð Ã¼Å©
     * 
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/idCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberIdCheck(String memberId) {
        
        String message = "";        //AJAX ¸Þ¼¼Áö
        //String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,12}$";       //À¯È¿¼º Á¤±Ô½Ä Ç¥Çö
        String regex = "^[a-zA-Z0-9_]{5,12}$";
        
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(memberId);

        if(!matcher.find()) {
            message = "»ç¿ëÇÒ ¼ö ¾ø´Â ¾ÆÀÌµðÀÔ´Ï´Ù.";
        }
        else {
            message = "»ç¿ë°¡´ÉÇÑ ¾ÆÀÌµðÀÔ´Ï´Ù.";
        
            //¾ÆÀÌµð Á¸Àç¿©ºÎ DB¿¡¼­ Ã¼Å©
            boolean checkResult = memberService.memberSelectByMemberId(memberId);
            //true¸é ÀÌ¹ÌÁ¸Àç false¸é »ç¿ë°¡´É
            if(checkResult) message = "ÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµðÀÔ´Ï´Ù.";
            else message = "»ç¿ë°¡´ÉÇÑ ¾ÆÀÌµðÀÔ´Ï´Ù.";
        }
        
        return message;
    }
    
    /**
     * È¸¿ø°¡ÀÔ Çã¿ë°¡´ÉÇÑ ºñ¹Ð¹øÈ£ Ã¼Å©
     * 
     * @param memberPwd
     * @return
     */
    @RequestMapping(value = "/pwdCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPwdCheck(String memberPwd) {
        
        String message = "»ç¿ë°¡´ÉÇÑ ºñ¹Ð¹øÈ£ÀÔ´Ï´Ù.";
        
        //¼ýÀÚ, ¿µ¹®, Æ¯¼ö±âÈ£ Æ÷ÇÔ 8ÀÚ¸® ÀÌ»ó
        String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberPwd);
        
        if(!matcher.find()) message = "»ç¿ë ºÒ°¡´ÉÇÑ ºñ¹Ð¹øÈ£ÀÔ´Ï´Ù.";
                
        return message;
    }
    
    /**
     * È¸¿ø°¡ÀÔ ºñ¹Ð¹øÈ£¿Í ºñ¹Ð¹øÈ£ È®ÀÎ ÀÔ·Â°ª ºñ±³ ÈÄ ajax·Î ¸Þ¼¼Áö ¸®ÅÏ
     * 
     * @return
     */
    @RequestMapping(value = "/pwdConfirm",  produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPwdConfirm(String memberPwd, String memberPwdConfirm) {
        
        String message = "ºñ¹Ð¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.";
        
        if(memberPwd.equals(memberPwdConfirm)) {
            message = "»ç¿ë°¡´ÉÇÕ´Ï´Ù.";
        }
        
        return message;
    }
    
    /**
     * È¸¿ø°¡ÀÔ ´Ð³×ÀÓ Ã¼Å©
     * 
     * @param memberNickName
     * @return
     */
    @RequestMapping(value = "/nickNameCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberNickNameCheck(String memberNickName) {
        
        String message = "»ç¿ë°¡´ÉÇÑ ´Ð³×ÀÓÀÔ´Ï´Ù.";
        
        String regex = "^[a-zA-Z0-9°¡-ÆR]{1,8}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberNickName);
        
        if(!matcher.find()) {
            message = "»ç¿ëºÒ°¡´ÉÇÑ ´Ð³×ÀÓÀÔ´Ï´Ù.";
        }else {
            boolean checkResult = memberService.memberSelectByMemberNickName(memberNickName);
            if(checkResult) message = "ÀÌ¹Ì »ç¿ëÁßÀÎ ´Ð³×ÀÓÀÔ´Ï´Ù.";
        }
        
        return message;
    }
    
    /**
     * ÀüÈ­¹øÈ£ Ã¼Å©
     * 
     * @param memberPhone
     * @return
     */
    @RequestMapping(value = "/phoneCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPhoneCheck(String memberPhone) {
        String message = "ÀüÈ­¹øÈ£Ã¼Å©";
        
        //¼ýÀÚ¸¸ Çã¿ë
        String regex = "^[0-9]*$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberPhone);
        
        if(!matcher.find()) {
            message = "¼ýÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä.";
        }
        
        return message;
    }
    
    /**
     * ÀÌ¸ÞÀÏ Ã¼Å© 
     * 
     * @param memberEmail
     * @return
     */
    @RequestMapping(value = "/emailCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String emailCheck(String memberEmail) {
        
        String message = "ÀÌ¸ÞÀÏÀÔ·Â";
        
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberEmail);
        
        if(!matcher.find()) {
            message = "ÀÌ¸ÞÀÏÇü½ÄÀÌ ¾Æ´Õ´Ï´Ù.";
        }
        return message;
    }
    
    /**
     * ±ÇÇÑ À¯È¿¼º °Ë»ç.
     * 
     * @param authName
     * @return
     */
    @RequestMapping(value = "/authCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String authCheck(String authName) {
        
        String message = "";
        /*
            ±ÇÇÑ Á¾·ù
            °ü¸®ÀÚ : ROLE_ADMIN
            °­»ç : ROLE_TEACHER
            ¼ö°­»ý : ROLE_STUDENT
            ±â¾÷ : ROLE_COMPANY
            ÀÏ¹ÝÈ¸¿ø : ROLE_MEMBER
        */
        
        /*
         *  reflect »ç¿ëÇØ¼­ ÇÊµå °ª ¹è¿­À» ÀÌ¿ëÇØ for¹®À¸·Î ºñ±³ÇÒ ¼ö ÀÖÀ» µí º¸ÀÌÁö¸¸,
         *  ÀÌ¹Ì »ó¼ö·Î Á¤ÀÇµÈ ÇÊµå°ªÀÌ±â ¶§¹®¿¡ ¸Þ¸ð¸®¸¦ Ãß°¡ »ç¿ëÇÏÁö ¾Ê°í
         *  if¹®À¸·Î ³í¸®¿¬»êÇÏ¿© °á°ú°ª Ãâ·ÂÇÒ ¼ö ÀÖµµ·Ï ±¸Çö.
         */
        if(Constants.ROLE_ADMIN.equals(authName) || 
                Constants.ROLE_TEACHER.equals(authName) || 
                Constants.ROLE_STUDENT.equals(authName) || 
                Constants.ROLE_COMPANY.equals(authName) || 
                Constants.ROLE_MEMBER.equals(authName)) {
            message = "»ç¿ë°¡´ÉÇÑ ÄÚµåÀÔ´Ï´Ù.";
        }else {
            message = "»ç¿ë ºÒ°¡´ÉÇÑ ÄÚµåÀÔ´Ï´Ù.";
        }
        
        return message;
    }
    
    /**
     * È¸¿ø°¡ÀÔ
     * 
     * @return
     */
    @RequestMapping("/memberInsert")
    public String memberInsert(MemberDTO memberDTO, String authCode) {
        
        memberService.memberInsert(memberDTO, authCode);

        return "/index";
    }
    
    /**
     * È¸¿øÁ¤º¸ ¼öÁ¤
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
     * È¸¿ø Å»Åð. 
     * IsWithDrawalÀ» TRUE·Î ¹Ù²ãÁÖ¾î Å»Åð½ÃÅ²´Ù.
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
