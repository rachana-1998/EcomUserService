package com.rachana.EcomUserService.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Role extends  BaseModel {
    private  String roleName;
}
