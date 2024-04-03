package com.spring.mail.sender.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${user.email.user}")
    private String toFrom;
    @Override
    public int sendEmail(String toUser, String subject, String message) throws RuntimeException {

        try{

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(toFrom);
            mailMessage.setTo(toUser);
            mailMessage.setSubject(subject);
            mailMessage.setText(message);
            mailSender.send(mailMessage);

        } catch(RuntimeException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int sendEmailWithFile(String toUser, String subject, String message, File file) throws RuntimeException {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(toFrom);
            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.addAttachment(file.getName(), file);

            mailSender.send(mimeMessage);


        } catch (MessagingException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    @Override
    public int sendEmailWithFile2(String toUser, String subject, String message, File file1,File file2) throws RuntimeException {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true, StandardCharsets.UTF_8.name());
            mimeMessageHelper.setFrom(toFrom);
            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message);
            mimeMessageHelper.addAttachment(file1.getName(), file1);
            mimeMessageHelper.addAttachment(file2.getName(), file2);
            mailSender.send(mimeMessage);
            return 1;

        } catch (MessagingException e) {
            System.out.println("Error en: " + e.getMessage() + " " +  e.getCause());
            return 0;
        }

    }
}
