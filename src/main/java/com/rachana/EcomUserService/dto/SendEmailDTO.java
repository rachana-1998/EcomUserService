package com.rachana.EcomUserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendEmailDTO {
    private String to;
    private String from;
    private String body;
    private String subject;
}
