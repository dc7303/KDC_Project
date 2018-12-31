package edu.kosta.kdc.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kosta.kdc.emailSend.EmailForm;
import edu.kosta.kdc.emailSend.EmailSender;

@Controller
@RequestMapping("/member")
public class EmailController {
	
	@Autowired
	private EmailSender emailSender;
	
	@RequestMapping(value="emailSend")
    @ResponseBody
	public String submit(EmailForm emailform, String email) throws Exception{
	        emailform.setReceiver(email);
            emailSender.sendEmail(emailform, email);
		return "success";
	}
	
}
