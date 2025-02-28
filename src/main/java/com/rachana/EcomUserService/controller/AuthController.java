package com.rachana.EcomUserService.controller;


import com.rachana.EcomUserService.dto.LoginRequestDTO;
import com.rachana.EcomUserService.dto.SignUpRequestDTO;
import com.rachana.EcomUserService.dto.UserDTO;
import com.rachana.EcomUserService.model.Session;
import com.rachana.EcomUserService.model.SessionStatus;
import com.rachana.EcomUserService.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO>  login(@RequestBody LoginRequestDTO request){
        return authService.login(request.getEmail(),request.getPassword());
    }

     @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequestDTO requestDTO){
        UserDTO userDTO=authService.signUp(requestDTO.getEmail(),requestDTO.getPassword());
        return new ResponseEntity<>(userDTO,HttpStatus.OK);
     }

     @GetMapping("/session")
    public ResponseEntity<List<Session>> getAllSession(){
        return authService.getAllSessions();
     }

     @GetMapping("/validate/{id}/token")
    public ResponseEntity<SessionStatus> validateToken(@PathVariable("id") Long userId, String token){
       return new  ResponseEntity<>(authService.validate(token,userId),HttpStatus.OK);

     }

     @GetMapping("/logout/{id}")
    public  ResponseEntity<Void> logout(@PathVariable("id") Long userId, @RequestHeader("token") String token){
      return  authService.logout(token,userId);
     }



}
