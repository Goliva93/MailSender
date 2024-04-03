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

                if(parts[0].trim().equals("nameFile1")){
                    continue;
                }

                email.setNameFile1(parts[0].trim());
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

    @Override
    public void writeResult(String ruta, List<String> message, int flag) {

        try {
            File f = new File(ruta);
            FileWriter fw = new FileWriter(f);
            if(flag == 1){
                fw.write("No se enviaron correos a los siguientes colaboradores \n");
            }
            for(String m : message) {
                fw.write(m + " \n");
            }

            fw.close();

        } catch(Exception e) {
            System.out.println(" ocurrio un error mi chamo, mira tu codigo :v. - escribirArchivo -" + e.getMessage().toString());
        }

    }

}
