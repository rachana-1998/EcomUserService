package com.rachana.EcomUserService.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogoutRequestDTO {
    private Long userId;
    private  String token;
}
