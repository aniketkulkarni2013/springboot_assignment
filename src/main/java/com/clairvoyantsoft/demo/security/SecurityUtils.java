package com.clairvoyantsoft.demo.security;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class SecurityUtils {


    public static Optional<String> getCurrentLoggedInUSer(){

        SecurityContext securityContext = SecurityContextHolder.getContext();

        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {

                    if(authentication.getPrincipal() instanceof UserDetails){
                        return ((UserDetails) authentication.getPrincipal()).getUsername();
                    } else if(authentication.getPrincipal() instanceof  String){
                        return (String) authentication.getPrincipal();

                    }
                    return null;
                });
    }
}
