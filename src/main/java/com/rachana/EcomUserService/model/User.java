package com.rachana.EcomUserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name="EcommUser")
public class User extends BaseModel{

    private String email;
    private String password;
    @ManyToMany
    private Set<Role> roles =new HashSet<>();
}
