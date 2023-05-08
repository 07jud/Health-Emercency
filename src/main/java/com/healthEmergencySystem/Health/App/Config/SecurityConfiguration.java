package com.healthEmergencySystem.Health.App.Config;

import com.healthEmergencySystem.Health.App.Services.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    MyUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/login", "/register").permitAll()
        .antMatchers("/uploads/**","/incident/**", "/users/**", "Dashboard/**").authenticated()
        // .antMatchers("/admin/**", "/items/create").hasRole("ADMIN")
        .antMatchers("/api/**").hasAnyRole("ROLE_USER", "ADMIN")
        .and()
        .formLogin()
        .loginPage("/login")
        .defaultSuccessUrl("/Dashboard")
        .failureUrl("/login-error")
        .and()
        .exceptionHandling().accessDeniedPage("/accessDenied");

        http.csrf().disable();

        http.headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}