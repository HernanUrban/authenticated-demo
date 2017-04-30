package com.urban.example.security;

import com.urban.example.domain.User;
import com.urban.example.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by hurban on 16/04/17.
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        DefaultUserDetails userDetails = new DefaultUserDetails();
        User user = repository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("Unable to fin user " + s));
        BeanUtils.copyProperties(user, userDetails);
        return userDetails;
    }
}
