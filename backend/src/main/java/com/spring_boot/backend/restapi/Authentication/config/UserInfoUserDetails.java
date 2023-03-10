package com.spring_boot.backend.restapi.Authentication.config;

import com.spring_boot.backend.restapi.entities.candidateSignup;
import com.spring_boot.backend.restapi.entities.recruiterSignup;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UserInfoUserDetails implements UserDetails  {
    private String name;
    private String password;
    private List<GrantedAuthority> authorities;


    public UserInfoUserDetails(candidateSignup userInfo,String role) {
        name=userInfo.getCId();
        password=userInfo.getPassword();
        authorities= Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public UserInfoUserDetails(recruiterSignup userInfo,String role) {
        name=userInfo.getEmp_id();
        password=userInfo.getPassword();
        authorities= Arrays.stream(role.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
