package com.spring.mail.sender;

import com.spring.mail.sender.domain.EmailDTO;
import com.spring.mail.sender.service.IEmailService;
import com.spring.mail.sender.service.IManipulationCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Iterator;
import java.util.stream.Collectors;

@SpringBootApplication
public class GOlivaMailSenderApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GOlivaMailSenderApplication.class, args);
	}
	@Autowired
	private IEmailService emailService;

	@Autowired
	private IManipulationCsv manipulationCsv;
	@Value("${user.rute.general}")
	private String rutaGeneral;

	@Value("${user.rute.namecsv}")
	private String nombreCsv;

	@Value("${user.param.mes}")
	private String mes;

	@Value("${user.param.reason}")
	private String subject;

	@Override
	public void run(String... args) throws Exception {
		String rutaCsv = rutaGeneral + nombreCsv;

		List<EmailDTO> employees = manipulationCsv.listOfEmployees(rutaCsv);

		forcito(employees);

		// Para envio individual
		//envioEmail(employees);

		envioEmailPDF(employees);

		System.out.println("Programa terminado");

	}

	public String emailMessage(String name,String mes){
		return String.format("""
        Estimado(a) %s
                                     
        Buenas tardes.
        Se envía la boleta de pago de %s 2024 para los fines pertinentes.
       
        Cualquier observación, duda o reclamo puede hacerla conocer como respuesta a este correo con copia a: kgallardo@mepso.com.pe, seguido a ello el personal de RR. HH. se pondrá en contacto con usted para resolver sus consultas.
        De no presentar ninguna consulta luego de revisar sus boletas, responder este correo como recepcionado.
        
        Quedo atenta a sus comentarios.
        
        Angelica Yañez
        Analista de Recursos Humanos""",name,mes);
	}

	public void forcito (List<EmailDTO> x) {
		System.out.println("Los empleados a quienes se enviara el correo son: ");
		x.forEach(System.out::println);
	}
	public void envioEmail(List<EmailDTO> x){
		x.forEach(y ->
				emailService.sendEmail(y.getToEmail(),subject+mes,emailMessage(y.getName(),mes)));
		}

	public void envioEmailPDF(List<EmailDTO> x){
		try {

			for (EmailDTO y : x) {
				String fileName = y.getNumber() + ".pdf";
				Path path = Paths.get(rutaGeneral + fileName);
				File file = path.toFile();

				emailService.sendEmailWithFile(
					y.getToEmail(),
					subject + mes,
					emailMessage(y.getName(), mes),
					file);
				}
			}catch (Exception e){
				throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
			}

	}
}