package com.project.snackcode.model.member;

import org.springframework.security.core.context.SecurityContextHolder;

public class LoginContextHolder {


    public static UserDetailsImpl getLoginUser(){
        return ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }


}
