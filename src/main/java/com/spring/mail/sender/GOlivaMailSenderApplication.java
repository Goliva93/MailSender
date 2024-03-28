package com.spring.mail.sender;

import com.spring.mail.sender.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GOlivaMailSenderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GOlivaMailSenderApplication.class, args);
	}
	@Autowired
	private IEmailService emailService;

	@Override
	public void run(String... args) throws Exception {

		System.out.println("armando el mensaje");
		emailService.sendEmail("giomar.oliva.93@gmail.com","Primer Correo","asd");
		System.out.println("Correo enviado");
		
	}


}
