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
     * È¸¿ø°¡ÀÔ ¾ÆÀÌµð Ã¼Å©
     * 
     * @param memberId
     * @return
     */
    @RequestMapping(value = "/idCheck", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String memberIdCheck(String memberId) {
        
        String message = "";        //AJAX ¸Þ¼¼Áö
        String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,12}$";       //À¯È¿¼º Á¤±Ô½Ä Ç¥Çö
        
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(memberId);

        if(!matcher.find()) {
            message = "»ç¿ëÇÒ ¼ö ¾ø´Â ¾ÆÀÌµðÀÔ´Ï´Ù.";
        }
        else {
            message = "»ç¿ë°¡´ÉÇÑ ¾ÆÀÌµðÀÔ´Ï´Ù.";
        
            //¾ÆÀÌµð Á¸Àç¿©ºÎ DB¿¡¼­ Ã¼Å©
            boolean checkResult = memberService.memberSelectById(memberId);
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
        
        String message = "";
        
        Pattern pattern1 = Pattern.compile("[0-9]"); // Number 0 through 9
        Pattern pattern2 = Pattern.compile("[a-z]"); // Characters a through z
        Pattern pattern3 = Pattern.compile("[A-Z]"); // Characters A through Z
        Pattern pattern4 = Pattern.compile("[^A-Za-z0-9]"); // Any character except ( A through Z and a through z and 0 through 9)

        Matcher matcher1 = pattern1.matcher(memberPwd);
        Matcher matcher2 = pattern2.matcher(memberPwd);
        Matcher matcher3 = pattern3.matcher(memberPwd);
        Matcher matcher4 = pattern4.matcher(memberPwd);

        if(memberPwd.length() < 8 || memberPwd.length() > 14) message = "ÆÐ½º¿öµå ±æÀÌ´Â 8~14 ÀÚ¸®ÀÔ´Ï´Ù.";
        else if (!matcher1.find()) message = "ÆÐ½º¿öµå¿¡ ¼ýÀÚ°¡ Æ÷ÇÔµÇÁö ¾Ê¾Ò½À´Ï´Ù.";
        else if (!matcher2.find()) message = "ÆÐ½º¿öµå¿¡ ¼Ò¹®ÀÚ°¡ Æ÷ÇÔµÇÁö ¾Ê¾Ò½À´Ï´Ù.";
        else if (matcher3.find()) message = "ÆÐ½º¿öµå´Â ´ë¹®ÀÚ ¼Ò¹®ÀÚ¸¦ ±¸ºÐÇÕ´Ï´Ù.";
        else if (!matcher4.find()) message = "ÆÐ½º¿öµå¿¡ Æ¯¼ö¹®ÀÚ°¡ Æ÷ÇÔµÇÁö ¾Ê¾Ò½À´Ï´Ù.";
        else message = "»ç¿ë°¡´ÉÇÑ ºñ¹Ð¹øÈ£ÀÔ´Ï´Ù.";

        
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
        
        String regex = "^[a-zA-Z0-9°¡-ÆR]*$";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(memberNickName);
        
        if(!matcher.find()) {
            message = "»ç¿ëºÒ°¡´ÉÇÑ ´Ð³×ÀÓÀÔ´Ï´Ù.";
        }else if(memberNickName.length() < 2 || memberNickName.length() > 8) {
            message = "»ç¿ëºÒ°¡´ÉÇÑ ´Ð³×ÀÓÀÔ´Ï´Ù.";
        }else {
            boolean checkResult = memberService.memberSelectByNickName(memberNickName);
            if(checkResult) message = "ÀÌ¹Ì »ç¿ëÁßÀÎ ´Ð³×ÀÓÀÔ´Ï´Ù.";
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
     * È¸¿ø°¡ÀÔ
     * 
     * @return
     */
    @RequestMapping("/memberInsert")
    public String memberInsert(MemberDTO memberDTO, String authCode) {
        
        String memberPwd = memberDTO.getMemberPwd();

        return "/";
    }
}
