package com.rachana.EcomUserService.security;

import com.rachana.EcomUserService.model.User;
import com.rachana.EcomUserService.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;


@Service
public class CustomSpringUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomSpringUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user=userRepository.findByEmail(username);
        if (user.isEmpty())
        {
            throw new UsernameNotFoundException("user detail not found for give user name");
        }
         User savedUser=user.get();
        return new CustomSpringUserDetail(savedUser);
    }
}
