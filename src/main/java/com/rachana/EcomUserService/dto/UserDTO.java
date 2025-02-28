package com.rachana.EcomUserService.dto;

import com.rachana.EcomUserService.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

@Getter
@Setter
public class UserDTO {
    private String email;

    private HashSet<Role> roles =new HashSet<>();
}
