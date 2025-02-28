package com.rachana.EcomUserService.service;

import com.rachana.EcomUserService.model.Role;
import com.rachana.EcomUserService.repo.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void createRole(String name){
        Role role=new Role();
        role.setRoleName(name);
               roleRepository.save(role);

    }
}
