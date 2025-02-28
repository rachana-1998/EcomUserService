package com.rachana.EcomUserService.controller;

import com.rachana.EcomUserService.dto.RoleDTO;
import com.rachana.EcomUserService.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }
    @PostMapping
    public ResponseEntity<RoleDTO> createRole(@RequestBody RoleDTO request){
       roleService.createRole(request.getRoleName());
        return new ResponseEntity<>(request,HttpStatus.OK);
    }
}
