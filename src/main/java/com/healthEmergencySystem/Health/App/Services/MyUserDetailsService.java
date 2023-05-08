package com.healthEmergencySystem.Health.App.Services;

import java.util.Optional;

import com.healthEmergencySystem.Health.App.Domain.MyUserDetails;
import com.healthEmergencySystem.Health.App.Domain.User;
import com.healthEmergencySystem.Health.App.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        
        Optional<User> user = userRepository.findByEmail(userName);

        user.orElseThrow(() -> new UsernameNotFoundException("User: "+userName+" doesnt exist"));

        return user.map(MyUserDetails::new).get();
    }

    
}