package com.spring.mail.sender.service;
import java.io.File;

public interface IEmailService {

    int sendEmail(String toUser, String subject,String message);

    int sendEmailWithFile(String toUser, String subject, String message, File file);

    int sendEmailWithFile2(String toUser, String subject, String message, File file1,File file2);

}
