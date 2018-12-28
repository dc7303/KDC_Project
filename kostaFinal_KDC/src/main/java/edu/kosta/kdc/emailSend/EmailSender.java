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
		//���� �߼� ��� ����
		String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // -�� ������ �־���. 
		        uuid = uuid.substring(0, 7); //uuid�� �տ������� 7�ڸ� �߶���. 

		MimeMessage msg = mailSender.createMimeMessage();
		System.out.println("msg = "+msg);
		System.out.println("receiver : "+emailForm.getReceiver());
		msg.setSubject("�ӽ� ��й�ȣ �Դϴ�."); //���� ����
		msg.setText("�ӽ� ��й�ȣ�� "+uuid+"�Դϴ�.");//���� ����
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
             System.out.println("MailException�߻�");
             e.printStackTrace();
         }
     }


}