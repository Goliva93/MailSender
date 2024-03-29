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
    private String number;
    private String name;
    private String toEmail;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmailDTO{");
        sb.append("number='").append(number).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", toEmail='").append(toEmail).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
