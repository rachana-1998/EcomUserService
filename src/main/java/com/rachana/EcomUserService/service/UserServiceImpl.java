package com.rachana.EcomUserService.service;

import com.rachana.EcomUserService.dto.UserDTO;
import com.rachana.EcomUserService.model.Role;
import com.rachana.EcomUserService.model.User;
import com.rachana.EcomUserService.repo.RoleRepository;
import com.rachana.EcomUserService.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

//    public UserResponseDTO getALlUser(){
//        return null;
//    }
    public UserDTO getUserById(Long id){
       Optional<User> user= userRepository.findById(id);
       if (user.isEmpty()){
           return null;
       }
       User user1= user.get();
      // return UserResponseDTO.from(user);
        return  null;
    }
    public UserDTO setUserRoles(Long id, List<Long> roleIds){
       Optional<User> userOptional =userRepository.findById(id);
       List<Role> roles=roleRepository.findByIdIn(roleIds);
       if(userOptional.isEmpty()){
           return null;
       }
       User user=userOptional.get();

        user.setRoles(Set.copyOf(roles));
       User user1=userRepository.save(user);
      //return UserResponseDTO.form(user);
       return null;
    }

}
