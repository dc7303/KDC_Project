package edu.kosta.kdc.emailSend;

import java.util.UUID;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
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
		//���� �߼� ��� ����
		String uuid = UUID.randomUUID().toString().replaceAll("-", ""); // -�� ������ �־���. 
		        uuid = uuid.substring(0, 8); //uuid�� �տ������� 7�ڸ� �߶���. 
		        uuid = uuid+"!";
		
		MimeMessage msg = mailSender.createMimeMessage();
		System.out.println("�ӽú�й�ȣ : " + uuid);
		msg.setSubject("�ӽ� ��й�ȣ �Դϴ�."); //���� ����
		msg.setText("�ӽ� ��й�ȣ�� "+uuid+"�Դϴ�.");//���� ����
		msg.setRecipient(RecipientType.TO, new InternetAddress(emailForm.getReceiver()));
		mailSender.send(msg);
		
		return memberService.updatePwdByEmail(uuid, email);
	}
}