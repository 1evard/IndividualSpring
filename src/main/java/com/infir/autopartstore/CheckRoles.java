package com.infir.autopartstore;


import org.springframework.security.core.Authentication;

public class CheckRoles {
    public boolean userCheck(Authentication auth){
        return auth.getAuthorities().toString().contains("USER");
    }
    public boolean adminCheck(Authentication auth){
        return auth.getAuthorities().toString().contains("ADMIN");
    }
    public boolean employeeCheck(Authentication auth){
        return auth.getAuthorities().toString().contains("EMPLOYEE");
    }

}
