package com.rachana.EcomUserService.service;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.rachana.EcomUserService.dto.UserDTO;
import com.rachana.EcomUserService.exception.InvalidCredientialException;
import com.rachana.EcomUserService.exception.InvalidTokenException;
import com.rachana.EcomUserService.exception.UserNotFoundException;
import com.rachana.EcomUserService.model.Session;
import com.rachana.EcomUserService.model.SessionStatus;
import com.rachana.EcomUserService.model.User;
import com.rachana.EcomUserService.repo.SessionRepository;
import com.rachana.EcomUserService.repo.UserRepository;


import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;


import javax.crypto.SecretKey;
import java.time.LocalDate;
import java.util.*;

import static javax.crypto.Cipher.SECRET_KEY;


@Service
public class AuthService {
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ModelMapper modelMapper;

    public AuthService(UserRepository userRepository, SessionRepository sessionRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper, SecurityFilterChain securityFilterChain) {
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }

    public UserDTO signUp(String email, String password){
        User user= new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
         return null;
    }




    public ResponseEntity<UserDTO> login(String email, String password){

        //Get the User Detail from DB
        Optional<User> userOptional=userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            throw   new UserNotFoundException("user of given id does not exist");
        }
        User user=new User();
        user=userOptional.get();

        //verify the user password givwn at the time of login
        if( !bCryptPasswordEncoder.matches(password,user.getPassword())){
             throw new InvalidCredientialException("incurrect password");
        }
        // token generation
       // String token =  RandomStringUtils.randomAlphanumeric(30);
        MacAlgorithm alg = Jwts.SIG.HS256;//added HS256 algo for JWT

        SecretKey secretKey=alg.key().build();//generating secrete key

        //start adding the claims
        Map<String,Object> jsonForJwt=new HashMap<>();
        jsonForJwt.put("email",user.getEmail());
        jsonForJwt.put("role",user.getRoles());
        jsonForJwt.put("createdAt",new Date());
        jsonForJwt.put("expiryDate",new Date(LocalDate.now().plusDays(3).toEpochDay()));

        String token= Jwts.builder()
                .claims(jsonForJwt)// added the claims
                .signWith(secretKey,alg)//added the algo and key
                .compact();//building token
        //session creation
        Session session=new Session();
        session.setToken(token);
        session.setSessionStatus(SessionStatus.ACTIVE);
        session.setLoginTime(new Date());
        sessionRepository.save(session);
        //generating the response
        UserDTO userDTO= modelMapper.map(user,UserDTO.class);
        // setting up the header
        MultiValueMapAdapter<String,String> headers=new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,"Authtoken:"+token);

        return new ResponseEntity<>(userDTO,headers, HttpStatus.OK);


    }

    public SessionStatus validate(String token,Long userId){
        //TODO check expiry

        //verifying from DB if session exist
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token,userId);
        if (sessionOptional.isEmpty()||sessionOptional.get().getSessionStatus().equals(SessionStatus.ENDED)){
            throw  new InvalidTokenException("token is invalid");
        }
        return SessionStatus.ACTIVE;
    }




    public  ResponseEntity<List<Session>> getAllSessions(){
      List<Session> sessions=   sessionRepository.findAll();
      return  ResponseEntity.ok(sessions);
    }


    public ResponseEntity<Void> logout(String token,Long userId){
       Optional<Session> sessionOptional= sessionRepository.findByTokenAndUser_Id(token,userId);
       if (sessionOptional.isEmpty()){
           return null;
       }
       Session session=sessionOptional.get();
       session.setSessionStatus(SessionStatus.ENDED);
         sessionRepository.save(session);
         return ResponseEntity.ok().build();
    }
}
