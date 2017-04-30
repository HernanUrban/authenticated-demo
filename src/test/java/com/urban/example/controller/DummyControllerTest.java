package com.urban.example.controller;

import com.urban.example.dto.DemoResponseStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by hurban on 29/04/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DummyControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        DemoResponseStatus body = this.restTemplate.getForObject("/demo/free", DemoResponseStatus.class);
        assertThat(body.getStatus()).isEqualTo("It's a Free World. It Works!");
    }
}
