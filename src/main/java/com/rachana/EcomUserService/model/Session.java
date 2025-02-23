package com.rachana.EcomUserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Session extends BaseModel{
    private String token;
    private Date expiringAt;
    private Date loginTime;
    @ManyToOne
    private List<User> users;
    @Enumerated(EnumType.ORDINAL)
    private SessionStatus sessionStatus;
}
