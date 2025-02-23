package com.rachana.EcomUserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@Entity
public class User extends BaseModel{

    private String email;
    private String password;
    @ManyToMany
    private HashSet<Role> roles =new HashSet<>();
}
