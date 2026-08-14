package com.miniProject2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String subject,String body,String to) {
		try {
			MimeMessage mime=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mime);
			helper.setSubject(subject);
			helper.setText(body,true);
			helper.setTo(to);
			mailSender.send(mime);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
