package com.spring.mail.sender;

import com.spring.mail.sender.domain.EmailDTO;
import com.spring.mail.sender.service.IEmailService;
import com.spring.mail.sender.service.IManipulationCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


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

	@Value("${user.param.numberFiles}")
	private int numberFiles;



	@Override
	public void run(String... args) throws Exception {
		String rutaCsv = rutaGeneral + nombreCsv;
		List<String> result;

		List<EmailDTO> employees = manipulationCsv.listOfEmployees(rutaCsv);

		forcito(employees);

		if(numberFiles == 1){
			System.out.println("se va enviar correos con 1 archivo adjunto");
			result = envioEmailPDF(employees);

			if(result.isEmpty()){
				result.add("Se enviaron todos los correos satisfactoriamente");
				manipulationCsv.writeResult(rutaGeneral+"Resultado.txt",result,0);
			} else {
				manipulationCsv.writeResult(rutaGeneral+"Resultado.txt",result,1);
			}
		} else if (numberFiles == 2){
			System.out.println("se va enviar correos con 2 archivo adjunto");
		}
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

	public List<String> envioEmail(List<EmailDTO> x){
		List<String> nombres = new ArrayList<>();
		int cant = 0;
		/*
		x.forEach(y ->
				emailService.sendEmail(y.getToEmail(),subject+mes,emailMessage(y.getName(),mes)));
		*/
		x.forEach(ed -> {
			int value = emailService.sendEmail(ed.getToEmail(),
					subject + mes,
					emailMessage(ed.getName(), mes));
			if (value == 0){
				nombres.add(ed.getName());
			}
		});
		return nombres;
	}

	public List<String> envioEmailPDF(List<EmailDTO> x){

		List<String> nombres = new ArrayList<>();
		try {

			x.forEach(ed ->{
				String fileName = ed.getNameFile1() + ".pdf";
				Path path = Paths.get(rutaGeneral + fileName);
				File file = path.toFile();
				int value = emailService.sendEmailWithFile(
						ed.getToEmail(),
						subject + mes,
						emailMessage(ed.getName(), mes),
						file);
				if (value == 0){
					nombres.add(ed.getName());
				}
			});

			return nombres;
		} catch (Exception e) {
			e.printStackTrace();
			//throw new RuntimeException("Error al enviar el Email con el archivo. " + e.getMessage());
			return nombres;
		}

	}
}