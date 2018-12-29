package edu.kosta.kdc.emailSend;

import java.util.UUID;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kosta.kdc.model.service.MemberService;

@Service
public class EmailSender {
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private MemberService memberService;
	
	@Transactional
	public int sendEmail(EmailForm emailForm, String email) throws Exception{
		//메일 발송 기능 제공
		String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다. 
		        uuid = uuid.substring(0, 8); //uuid를 앞에서부터 7자리 잘라줌. 
		        uuid = uuid+"!";
		
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
		System.out.println("임시비밀번호 : " + uuid);
		msg.setSubject("임시 비밀번호 입니다."); //메일 제목
		helper.setText("임시 비밀번호는 <b>"+uuid+"</b>입니다.<br><br>임시 비밀번호 받으신후 꼭 비밀번호를 수정해주시기 바랍니다.", true);//메일 내용
		msg.setRecipient(RecipientType.TO, new InternetAddress(emailForm.getReceiver()));
		mailSender.send(msg);
		
		return memberService.updatePwdByEmail(uuid, email);
	}
}