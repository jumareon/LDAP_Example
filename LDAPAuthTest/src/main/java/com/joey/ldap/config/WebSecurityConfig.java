package com.joey.ldap.config;

import com.joey.ldap.controller.HomeController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("Test HttpSecurity configure");

        http
                .authorizeRequests()
                .antMatchers("/managers").hasRole("MANAGERS")
                .antMatchers("/employees").hasRole("EMPLOYEES")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("Test AuthenticationManagerBuilder configure");

        auth
                .ldapAuthentication()
                .userDnPatterns("uid={0},ou=people")
                .groupSearchBase("ou=groups")
                .contextSource(contextSource())
                .passwordCompare()
                .passwordEncoder(new LdapShaPasswordEncoder())
                .passwordAttribute("userPassword");
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        log.info("Test DefaultSpringSecurityContextSource");
        return  new DefaultSpringSecurityContextSource(
                Collections.singletonList("ldap://localhost:12345"), "dc=memorynotfound,dc=com");
    }

}
