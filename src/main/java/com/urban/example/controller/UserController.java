package com.urban.example.controller;

import com.urban.example.dto.UserDTO;
import com.urban.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hurban
 */
@RestController
@RequestMapping(value = "/user", produces = "application/json")
@Api(basePath = "/user", value = "Users", description = "User resources", produces = "application/json")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization : Bearer <token>", required = true, dataType = "string", paramType = "header")})
    public UserDTO getUser(@PathVariable(name = "username") String username) {
        return userService.getUser(username);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@ApiParam(name = "User", required = true, value = "UserDTO")
                          @RequestBody UserDTO user) {
        return userService.create(user);
    }
}
