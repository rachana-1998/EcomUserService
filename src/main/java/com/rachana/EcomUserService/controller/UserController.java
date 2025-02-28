package com.rachana.EcomUserService.controller;

import com.rachana.EcomUserService.dto.SetUserRoleRequestDTO;
import com.rachana.EcomUserService.dto.UserDTO;
import com.rachana.EcomUserService.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id){
        UserDTO user=userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PostMapping("/{id}/roles")
    public ResponseEntity<UserDTO> setUserRoles(@PathVariable("id") Long id, @RequestBody SetUserRoleRequestDTO roles){
        UserDTO user=userService.setUserRoles(id,roles.getRoleId());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
}
