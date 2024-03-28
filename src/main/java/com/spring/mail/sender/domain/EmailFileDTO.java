package com.spring.mail.sender.domain;

import jakarta.mail.Multipart;
import org.springframework.web.multipart.MultipartFile;

public class EmailFileDTO {
    private String numberColaborador;
    private String toUser;
    private MultipartFile file;
}
