package com.urban.example.service;

import com.urban.example.domain.User;
import com.urban.example.dto.UserDTO;
import com.urban.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by hurban on 16/04/17.
 */
@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    PasswordEncoder defaultPasswordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @PreAuthorize("#username == authentication.getPrincipal() or hasAuthority('ROLE_ADMIN')")
    public UserDTO getUser(String username) {
        User user = repository.findByUsername(username)
                              .orElseThrow(() -> new
                                      EntityNotFoundException("User not found for username " + username));
        return modelMapper.map(user, UserDTO.class);
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        userDTO.setPassword(defaultPasswordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        user.setCreationDate(new Date());
        return modelMapper.map(repository.save(user), UserDTO.class);
    }

}
