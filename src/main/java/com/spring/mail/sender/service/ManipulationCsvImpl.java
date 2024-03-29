package com.spring.mail.sender.service;

import com.spring.mail.sender.domain.EmailDTO;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
@Service
public class ManipulationCsvImpl implements IManipulationCsv {


    @Override
    public List<EmailDTO> listOfEmployees(String rutaCSV) {
        List<EmailDTO> emails = new ArrayList<>();

        try{
            File file = new File(rutaCSV);
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(streamReader);

            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                EmailDTO email = new EmailDTO();
                if(parts[0].trim().equals("num")){
                    continue;
                }
                email.setNumber(parts[0].trim());
                email.setName(parts[1].trim());
                email.setToEmail(parts[2].trim());
                emails.add(email);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("hay un error " + e.getMessage());
        }

        return emails;
    }
}
