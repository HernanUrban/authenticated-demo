package com.urban.example.controller;

import com.urban.example.dto.DemoResponseStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hurban
 */
@RestController
@RequestMapping(value = "/demo", produces = "application/json")
@Api(basePath = "/demo", description = "Dummy authenticated operations", produces = "application/json")
public class DummyController {

    @RequestMapping(value = "/authenticated", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Shows a demo message for authenticated users",
            notes = "This endpoint is just for demo purposes",
            response = DemoResponseStatus.class)
    @ApiImplicitParams({@ApiImplicitParam(name = "Authorization", value = "Authorization : Bearer <token>",
            required = true, dataType = "string", paramType = "header")})
    public DemoResponseStatus demoAuthenticatedEndpoint() {
        DemoResponseStatus responseStatus = new DemoResponseStatus();
        responseStatus.setStatus("Is Authenticated. It Works!");
        return responseStatus;
    }

    @RequestMapping(value = "/free", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Shows a demo message for all users",
            notes = "This endpoint is just for demo purposes",
            response = DemoResponseStatus.class)
    public DemoResponseStatus demoFreeEndpoint() {
        DemoResponseStatus responseStatus = new DemoResponseStatus();
        responseStatus.setStatus("It's a Free World. It Works!");
        return responseStatus;
    }

}
