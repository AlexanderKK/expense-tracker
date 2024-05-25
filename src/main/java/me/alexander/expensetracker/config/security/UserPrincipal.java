package me.alexander.expensetracker.config.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class UserPrincipal implements UserDetails {

    private Long userId;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UserPrincipal setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;

        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public UserPrincipal setUserId(Long userId) {
        this.userId = userId;

        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserPrincipal setEmail(String email) {
        this.email = email;

        return this;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public UserPrincipal setPassword(String password) {
        this.password = password;

        return this;
    }

    @Override
    public String getUsername() {
        return email;
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
