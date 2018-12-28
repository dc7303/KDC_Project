package edu.kosta.kdc.emailSend;

import java.util.UUID;

import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailSender {
	@Autowired
	private JavaMailSender mailSender;
	
	public void sendEmail(EmailForm emailForm) throws Exception{
		//메일 발송 기능 제공
		String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // -를 제거해 주었다. 
		        uuid = uuid.substring(0, 7); //uuid를 앞에서부터 7자리 잘라줌. 

		MimeMessage msg = mailSender.createMimeMessage();
		System.out.println("msg = "+msg);
		System.out.println("receiver : "+emailForm.getReceiver());
		msg.setSubject("임시 비밀번호 입니다."); //메일 제목
		msg.setText("임시 비밀번호는 "+uuid+"입니다.");//메일 내용
		msg.setRecipient(RecipientType.TO, new InternetAddress(emailForm.getReceiver()));
		mailSender.send(msg);
	}
	
     public void SendEmail(EmailDTO email) throws Exception {
          
         MimeMessage msg = mailSender.createMimeMessage();
         try {
             msg.setSubject(email.getSubject());
             msg.setText(email.getContent());
             msg.setRecipients(MimeMessage.RecipientType.TO , InternetAddress.parse(email.getReceiver()));
            
         }catch(MessagingException e) {
             System.out.println("MessagingException");
             e.printStackTrace();
         }
         try {
             mailSender.send(msg);
         }catch(MailException e) {
             System.out.println("MailException발생");
             e.printStackTrace();
         }
     }


}