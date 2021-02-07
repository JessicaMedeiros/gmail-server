package io.github.jetmedeiros.gmailserver.service;

import io.github.jetmedeiros.gmailserver.config.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuthentication {
    public static UserSS authenticated(){
        try{
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }

    }
}
