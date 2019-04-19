package com.memorynotfound.ldap.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping("/")
    public String index() {

        log.info("Getting UsernamePasswordAuthenticationToken from SecurityContextHolder");
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();

        log.info("Getting principal from UsernamePasswordAuthenticationToken");
        LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();

        log.info("authentication: " + authentication);
        log.info("principal: " + principal);

        log.info("user name: " + principal.getUsername());

        return "Spring Security + Spring LDAP Authentication Configuration Example";
    }

    @GetMapping("/managers")
    public String managers(){
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();

        LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();

        return "Hello managers. " + principal.getUsername();
    }

    @GetMapping("/employees")
    public String employees(){
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();

        LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();

        return "Hello employees. " + principal.getUsername();
    }

    @GetMapping("/joey")
    public String joey(){
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken)
                        SecurityContextHolder.getContext().getAuthentication();

        LdapUserDetailsImpl principal = (LdapUserDetailsImpl) authentication.getPrincipal();

        return "Hello. " + principal.getUsername();
    }
}