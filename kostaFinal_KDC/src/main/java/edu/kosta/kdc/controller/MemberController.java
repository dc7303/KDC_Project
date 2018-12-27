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
     * È¸¿ø°¡ÀÔ ¾ÆÀÌµğ Ã¼Å©
     * 
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/idCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberIdCheck(String memberId) {
        
        String message = "";        //AJAX ¸Ş¼¼Áö
        //¾ËÆÄºª, ¼ıÀÚ, ¾ğ´õ¹Ù 5ÀÚ¸® ÀÌ»ó 12ÀÚ¸® ÀÌÇÏ
        String regex = "^[a-zA-Z0-9_]{5,12}$";
        
        //Á¤±Ô½ÄÀ» ÆĞÅÏÀ¸·Î ÄÄÆÄÀÏÇÑ´Ù.
        Pattern pattern = Pattern.compile(regex);
        //matcher¸¦ ÅëÇØ ÀÔ·ÂµÈ string°ªÀÌ À¯È¿ÇÑÁö ºñ±³
        Matcher matcher = pattern.matcher(memberId);

        //find()´Â À¯È¿ÇÒ °æ¿ì true¸¦ ¹İÈ¯ÇÑ´Ù. ±×·¸°Ô ¶§¹®¿¡ ! Å°¿öµå ÀÔ·Â.
        if(!matcher.find()) {
            message = "»ç¿ëÇÒ ¼ö ¾ø´Â ¾ÆÀÌµğÀÔ´Ï´Ù.";
        }
        else {
            message = "»ç¿ë°¡´ÉÇÑ ¾ÆÀÌµğÀÔ´Ï´Ù.";
        
            //¾ÆÀÌµğ Á¸Àç¿©ºÎ DB¿¡¼­ Ã¼Å©
            boolean checkResult = false;
            MemberDTO memberDTO = memberService.memberSelectByMemberId(memberId);
            if(memberDTO != null) {
                checkResult = true;
            }
            
            //true¸é ÀÌ¹ÌÁ¸Àç false¸é »ç¿ë°¡´É
            if(checkResult) message = "ÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµğÀÔ´Ï´Ù.";
            else message = "»ç¿ë°¡´ÉÇÑ ¾ÆÀÌµğÀÔ´Ï´Ù.";
        }
        
        return message;
    }
    
    /**
     * È¸¿ø°¡ÀÔ Çã¿ë°¡´ÉÇÑ ºñ¹Ğ¹øÈ£ Ã¼Å©
     * 
     * @param memberPwd
     * @return
     */
    @RequestMapping(value = "/pwdCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPwdCheck(String memberPwd) {
        
        String message = "»ç¿ë°¡´ÉÇÑ ºñ¹Ğ¹øÈ£ÀÔ´Ï´Ù.";
        
        //¼ıÀÚ, ¿µ¹®, Æ¯¼ö±âÈ£ Æ÷ÇÔ 8ÀÚ¸® ÀÌ»ó
        String regex = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,25}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberPwd);
        
        if(!matcher.find()) message = "»ç¿ë ºÒ°¡´ÉÇÑ ºñ¹Ğ¹øÈ£ÀÔ´Ï´Ù.";
                
        return message;
    }
    
    /**
     * È¸¿ø°¡ÀÔ ºñ¹Ğ¹øÈ£¿Í ºñ¹Ğ¹øÈ£ È®ÀÎ ÀÔ·Â°ª ºñ±³ ÈÄ ajax·Î ¸Ş¼¼Áö ¸®ÅÏ
     * 
     * @return
     */
    @RequestMapping(value = "/pwdConfirm",  produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberPwdConfirm(String memberPwd, String memberPwdConfirm) {
        
        String message = "ºñ¹Ğ¹øÈ£°¡ ÀÏÄ¡ÇÏÁö ¾Ê½À´Ï´Ù.";
        
        if(memberPwd.equals(memberPwdConfirm)) {
            message = "»ç¿ë°¡´ÉÇÕ´Ï´Ù.";
        }
        
        return message;
    }
    
    /**
     * È¸¿ø°¡ÀÔ ´Ğ³×ÀÓ Ã¼Å©
     * 
     * @param memberNickName
     * @return
     */
    @RequestMapping(value = "/nickNameCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberNickNameCheck(String memberNickName) {
        
        String message = "»ç¿ë°¡´ÉÇÑ ´Ğ³×ÀÓÀÔ´Ï´Ù.";
        
        //¿µ¾î, ¼ıÀÚ, ÇÑ±Û ÇÑÀÚ¸® ÀÌ»ó 8ÀÚ¸® ÀÌÇÏ
        String regex = "^[a-zA-Z0-9°¡-ÆR]{1,8}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberNickName);
        
        if(!matcher.find()) {
            message = "»ç¿ëºÒ°¡´ÉÇÑ ´Ğ³×ÀÓÀÔ´Ï´Ù.";
        }else {
            boolean checkResult = memberService.memberSelectByMemberNickName(memberNickName);
            if(checkResult) message = "ÀÌ¹Ì »ç¿ëÁßÀÎ ´Ğ³×ÀÓÀÔ´Ï´Ù.";
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
        
        //¼ıÀÚ¸¸ Çã¿ë
        String regex = "^[0-9]*$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberPhone);
        
        if(!matcher.find()) {
            message = "¼ıÀÚ¸¸ ÀÔ·ÂÇØÁÖ¼¼¿ä.";
        }
        
        return message;
    }
    
    /**
     * ÀÌ¸ŞÀÏ Ã¼Å© 
     * 
     * @param memberEmail
     * @return
     */
    @RequestMapping(value = "/emailCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String emailCheck(String memberEmail) {
        
        String message = "ÀÌ¸ŞÀÏÀÔ·Â";
        //ÀÌ¸ŞÀÏ À¯È¿¼º Ã¼Å© Á¤±Ô½Ä
        //@ Àü ¿µ¹®, ¼ıÀÚ, dot, ¾ğ´õ¹Ù¿Í ´ë½¬ 1°³ÀÌ»ó
        //@ ÈÄ ¿µ¹®, ¼ıÀÚ, dot, ´ë½¬ 1°³ ÀÌ»ó
        //¸¶Áö¸· dot ÀÌÈÄ ¿µ¹® 2~6°³
        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberEmail);
        
        if(!matcher.find()) {
            message = "ÀÌ¸ŞÀÏÇü½ÄÀÌ ¾Æ´Õ´Ï´Ù.";
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
            ¼ö°­»ı : ROLE_STUDENT
            ±â¾÷ : ROLE_COMPANY
            ÀÏ¹İÈ¸¿ø : ROLE_MEMBER
        */
        
        /*
         *  reflect »ç¿ëÇØ¼­ ÇÊµå °ª ¹è¿­À» ÀÌ¿ëÇØ for¹®À¸·Î ºñ±³ÇÒ ¼ö ÀÖÀ» µí º¸ÀÌÁö¸¸,
         *  ÀÌ¹Ì »ó¼ö·Î Á¤ÀÇµÈ ÇÊµå°ªÀÌ±â ¶§¹®¿¡ ¸Ş¸ğ¸®¸¦ Ãß°¡ »ç¿ëÇÏÁö ¾Ê°í
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
     * È¸¿ø Å»Åğ. 
     * IsWithDrawalÀ» TRUE·Î ¹Ù²ãÁÖ¾î Å»Åğ½ÃÅ²´Ù.
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
     * ºñ¹Ğ¹øÈ£ Ã£±â  Æû ¶ç¿ì±â
     * */
    @RequestMapping("/passwordSearch")
    public String passwordSearch() {
        return "/member/passwordSearchPopUpForm";
    }
    
    /**
     * ÀÌ¸ŞÀÏ º¸³»ÁÖ±â
     * @throws Exception 
     * */
    @RequestMapping("/emailSend")
    @ResponseBody
    public String emailSend(String email) throws Exception {
        return "sucess";
    }
    
}
