package com.project.snackcode.model.member;

import org.springframework.security.core.context.SecurityContextHolder;

public class LoginContextHolder {

    private UserDetailsImpl userDetails;

    public LoginContextHolder(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }

    public static UserDetailsImpl getLoginUser(){
        return Holder.loginUser.userDetails;
    }


    private static class Holder{
        private static final LoginContextHolder loginUser = new LoginContextHolder((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

}
