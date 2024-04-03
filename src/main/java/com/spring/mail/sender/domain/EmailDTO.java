package com.spring.mail.sender.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmailDTO {
    private String nameFile1;
    private String nameFile2;
    private String name;
    private String toEmail;

    @Override
    public String toString() {
        return "EmailDTO{" +
                "nameFile1='" + nameFile1 + '\'' +
                ", nameFile2='" + nameFile2 + '\'' +
                ", name='" + name + '\'' +
                ", toEmail='" + toEmail + '\'' +
                '}';
    }
}
