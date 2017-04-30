package com.urban.example;

import com.urban.example.security.DefaultPasswordEncoder;
import com.urban.example.security.DefaultUserDetailsService;
import com.urban.example.security.JWTAuthenticationFilter;
import com.urban.example.security.JWTLoginFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@PropertySource("application.properties")
public class AuthenticatedDemoApp extends WebSecurityConfigurerAdapter {

    @Value("${password.secret:thisisasecret}")
    private String secret;

    @Autowired
    DefaultUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
            .disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/login", "/user/register")
            .permitAll()
            .antMatchers(HttpMethod.GET, "/demo/free", "/oai/api-docs",
                    "/swagger-resources/**", "/swagger-ui.html")
            .permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
               .passwordEncoder(defaultPasswordEncoder());
    }

    @Bean
    public DefaultPasswordEncoder defaultPasswordEncoder() {
        return new DefaultPasswordEncoder(secret);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthenticatedDemoApp.class, args);
    }
}
