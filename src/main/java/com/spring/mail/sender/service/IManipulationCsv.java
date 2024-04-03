package com.spring.mail.sender.service;

import com.spring.mail.sender.domain.EmailDTO;

import java.util.List;

public interface IManipulationCsv {

    public List<EmailDTO> listOfEmployees(String rutaCSV);

    public void writeResult(String ruta, List<String> message,int flag);
}
