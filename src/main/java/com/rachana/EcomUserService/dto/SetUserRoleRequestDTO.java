package com.rachana.EcomUserService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRoleRequestDTO {
    private List<Long> roleId;
}
