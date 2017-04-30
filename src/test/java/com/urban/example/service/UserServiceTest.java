package com.urban.example.service;

import com.urban.example.domain.User;
import com.urban.example.dto.UserDTO;
import com.urban.example.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * Created by hurban on 29/04/17.
 */
public class UserServiceTest {

    private UserService userService;

    private UserRepository userRepository;

    @Before
    public void setup(){
        userService = new UserService();
        userRepository = mock(UserRepository.class);
        ReflectionTestUtils.setField(userService, "repository", userRepository);
        ReflectionTestUtils.setField(userService, "modelMapper", new ModelMapper());

    }

    @Test
    public void getUserTest(){
        String username = "testUser";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setId(1l);
        expectedUser.setFullname("Test User");
        expectedUser.setPassword("fakePwd");
        expectedUser.setCreationDate(new Date());
        Optional<User> maybeUser = Optional.of(expectedUser);
        given(this.userRepository.findByUsername(username)).willReturn(maybeUser);
        UserDTO user = userService.getUser(username);
        assertThat(user.getUsername()).isEqualTo(username);
    }

    @Test
    public void createUserTest(){
        assertTrue(true);
    }
}
