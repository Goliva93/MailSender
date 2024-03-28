package com.spring.mail.sender.controller;

import com.spring.mail.sender.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class MailController {

    @Autowired
    private IEmailService emailService;

    @GetMapping("/sendMessage")
    public ResponseEntity<?> reciveRequestEmail(){

        System.out.println("armando el mensaje");
        emailService.sendEmail("giomar.oliva.93@gmail.com","Primer Correo","asd");
        System.out.println("Correo enviado");

        return ResponseEntity.ok().build();

    }

}
